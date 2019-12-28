package co.penguinLab.rapidapi.ui.search

import androidx.core.text.HtmlCompat
import co.penguinLab.model.CardResultItem

class SearchResultsFeedItemViewState(private val resultItem: CardResultItem) {

    fun getImageUrl() = resultItem.imageUrl
    fun getCardId() = resultItem.cardId

    fun getCardName() = resultItem.name
    fun getCardOverview() =
        HtmlCompat.fromHtml(
            resultItem.overview?.replace("(\\r|\\n|\\r\\n)", "</br>")
                ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY
        )
}