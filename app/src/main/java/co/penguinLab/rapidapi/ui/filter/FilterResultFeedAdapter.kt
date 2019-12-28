package co.penguinLab.rapidapi.ui.filter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.penguinLab.common.ItemClickListener
import co.penguinLab.common.inflate
import co.penguinLab.model.CardResultItem
import co.penguinLab.rapidapi.R
import co.penguinLab.rapidapi.databinding.ItemFilterCardViewBinding
import javax.inject.Inject

class FilterResultFeedAdapter @Inject constructor() :
    RecyclerView.Adapter<FilterResultFeedAdapter.CardFeedItemViewHolder>() {


    private var cardResults: MutableList<CardResultItem> = mutableListOf()
    lateinit var onClickListener: ItemClickListener<CardResultItem>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardFeedItemViewHolder {
        val itemBinding = parent.inflate<ItemFilterCardViewBinding>(
            R.layout.item_filter_card_view,
            false
        )
        return CardFeedItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = cardResults.size

    override fun onBindViewHolder(holder: CardFeedItemViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setSearchedResultList(list: List<CardResultItem>) {
        cardResults.clear()
        cardResults.addAll(list)
        notifyDataSetChanged()
    }

    inner class CardFeedItemViewHolder(private val binding: ItemFilterCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            with(binding) {
                viewState =
                    FilterResultFeedItemViewState(cardResults[position])
                root.setOnClickListener { view ->
                    onClickListener.onItemClick(
                        view.id,
                        cardResults,
                        cardResults[position],
                        position
                    )
                }
                executePendingBindings()
            }
        }

    }
}