package co.penguinLab.rapidapi.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.penguinLab.common.observeNonNull
import co.penguinLab.model.CardResultItem
import co.penguinLab.rapidapi.R
import co.penguinLab.rapidapi.databinding.FragmentCardDetailBinding
import co.penguinLab.rapidapi.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_card_detail.*


class CardDetailFragment : BaseFragment<FragmentCardDetailBinding, CardDetailViewModel>() {

    override val viewModelClass: Class<CardDetailViewModel> =
        CardDetailViewModel::class.java
    override val layoutRes: Int = R.layout.fragment_card_detail


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        getPostObject()
        viewModel.getCardDetailLiveData().observeNonNull(this) {
            renderCardDetailResult(it)
            toolbar.title = it.getCardName()

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }


    private fun getPostObject() {
        arguments?.getParcelable<CardResultItem>("postObject")?.let {
            fetchSelectedCardResult(it.name!!)
        }
    }

    private fun renderCardDetailResult(feedViewState: CardDetailFeedViewState) {
        with(binding) {
            viewState = feedViewState
            executePendingBindings()
        }
    }

    private fun fetchSelectedCardResult(page: String) {
        viewModel.fetchSelectedCardResult(page)
    }


}