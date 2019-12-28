package co.penguinLab.rapidapi.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.penguinLab.common.ItemClickListener
import co.penguinLab.common.RxSearchObservable
import co.penguinLab.common.observeNonNull
import co.penguinLab.common.plusAssign
import co.penguinLab.model.CardResultItem
import co.penguinLab.rapidapi.R
import co.penguinLab.rapidapi.databinding.FragmentSearchBinding
import co.penguinLab.rapidapi.ui.base.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    @Inject
    internal lateinit var searchResultFeedAdapter: SearchResultFeedAdapter

    override val viewModelClass: Class<SearchViewModel> =
        SearchViewModel::class.java
    override val layoutRes: Int = R.layout.fragment_search


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.getSearchResultsLiveData().observeNonNull(this) {
            renderSearchResult(it)
        }

        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRxViewObserver().also { disposables += it }
    }

    private fun initRecyclerView() {
        with(binding) {
            recyclerView.apply {
                adapter = searchResultFeedAdapter.apply {
                    onClickListener = listener
                }
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun renderSearchResult(searchResultFeedViewState: SearchResultFeedViewState) {
        with(binding) {
            viewState = searchResultFeedViewState
            executePendingBindings()
        }
        searchResultFeedAdapter.setSearchedResultList(searchResultFeedViewState.getSearchResults())
    }

    private fun fetchSearchResult(page: String) {
        viewModel.fetchSearchResults(page)
    }

    private fun initRxViewObserver(): Disposable {
        return RxSearchObservable.fromView(search_view)
            .debounce(300, TimeUnit.MILLISECONDS)
            .filter { text ->
                text.isNotEmpty() && text.length >= 3
            }
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                fetchSearchResult(it)
            }
    }


    private val listener = object :
        ItemClickListener<CardResultItem> {
        override fun onItemClick(
            viewId: Int,
            modelList: List<CardResultItem>,
            clickedItem: CardResultItem,
            position: Int
        ) {
            findNavController().navigate(
                R.id.action_searchFragment_to_nav_cardDetail,
                bundleOf("postObject" to modelList[position])
            )
            makeToast("onItemClick")
        }

    }
}