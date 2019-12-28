package co.penguinLab.rapidapi.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.penguinLab.common.ItemClickListener
import co.penguinLab.common.inflate
import co.penguinLab.model.CardResultItem
import co.penguinLab.rapidapi.R
import co.penguinLab.rapidapi.databinding.ItemSearchCardViewBinding
import javax.inject.Inject

class SearchResultFeedAdapter @Inject constructor() :
    RecyclerView.Adapter<SearchResultFeedAdapter.CardFeedItemViewHolder>() {

    private var cardResults: MutableList<CardResultItem> = mutableListOf()
    lateinit var onClickListener: ItemClickListener<CardResultItem>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardFeedItemViewHolder {
        val itemBinding = parent.inflate<ItemSearchCardViewBinding>(
            R.layout.item_search_card_view,
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

    inner class CardFeedItemViewHolder(private val binding: ItemSearchCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            with(binding) {
                viewState =
                    SearchResultsFeedItemViewState(cardResults[position])
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