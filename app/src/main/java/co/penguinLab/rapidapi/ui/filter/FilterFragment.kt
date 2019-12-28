package co.penguinLab.rapidapi.ui.filter

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.penguinLab.common.ItemClickListener
import co.penguinLab.common.observeNonNull
import co.penguinLab.model.CardResultItem
import co.penguinLab.rapidapi.R
import co.penguinLab.rapidapi.databinding.FragmentFilterBinding
import co.penguinLab.rapidapi.ui.base.BaseFragment
import javax.inject.Inject


class FilterFragment : BaseFragment<FragmentFilterBinding, FilterViewModel>() {

    @Inject
    internal lateinit var filterResultFeedAdapter: FilterResultFeedAdapter

    override val viewModelClass: Class<FilterViewModel> =
        FilterViewModel::class.java
    override val layoutRes: Int = R.layout.fragment_filter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.getFilterResultLiveData().observeNonNull(this) {
            renderFilterResult(it)
        }
        initSpinner()
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        with(binding) {
            recyclerView.apply {
                adapter = filterResultFeedAdapter.apply {
                    onClickListener = listener
                }
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun initSpinner() {
        with(binding) {
            val cardList = arrayListOf(
                "Please select Class",
                "Druid",
                "Hunter",
                "Mage",
                "Paladin",
                "Priest",
                "Rogue",
                "Shaman",
                "Warlock",
                "Warrior"
            )
            val spinnerAdapter = object : ArrayAdapter<String>(
                context!!,
                R.layout.support_simple_spinner_dropdown_item,
                cardList
            ) {
                override fun isEnabled(position: Int): Boolean {
                    return position != 0
                }

                override fun getDropDownView(
                    position: Int, convertView: View?,
                    parent: ViewGroup
                ): View {
                    val view = super.getDropDownView(position, convertView, parent)
                    val tv = view as TextView
                    if (position == 0) {
                        // Set the hint text color gray
                        tv.setTextColor(Color.GRAY)
                    } else {
                        tv.setTextColor(Color.BLACK)
                    }
                    return view
                }
            }
            spCardClass.adapter = spinnerAdapter

            spCardClass.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>,
                    view: View?,
                    position: Int,
                    l: Long
                ) {
                    if (position > 0) {
                        makeToast("Selected class is ${cardList[position]}")
                        fetchFilterResult(cardList[position])
                    }
                }

                override fun onNothingSelected(adapterView: AdapterView<*>) {

                }
            }
        }
    }

    private fun renderFilterResult(feedViewState: FilterResultFeedViewState) {
        with(binding) {
            viewState = feedViewState
            executePendingBindings()
        }
        filterResultFeedAdapter.setSearchedResultList(feedViewState.getFilterResults())
    }

    private fun fetchFilterResult(page: String) {
        viewModel.fetchFilterResults(page)
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
                R.id.action_filterFragment_to_cardDetailFragment,
                bundleOf("postObject" to modelList[position])
            )
            makeToast("onItemClick")
        }

    }
}