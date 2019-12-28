package co.penguinLab.rapidapi.ui.detail

import androidx.core.text.HtmlCompat
import co.penguinLab.model.CardDetailItem
import co.penguinLab.model.Status

class CardDetailFeedViewState(
    val status: Status,
    val error: Throwable? = null,
    val data: List<CardDetailItem>? = null
) {

    private val item = data?.first()
    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error?.message

    fun shouldShowErrorMessage() = error != null


    fun getPlayerClass() = "<b>Player Class :</b> ${item?.playerClass}".fromHtml()
    fun getRarity() = "<b>Rarity : </b>${item?.rarity}".fromHtml()
    fun getType() = "<b>Type :</b> ${item?.type}".fromHtml()
    fun getCost() = "<b>Cost :</b> ${item?.cost}".fromHtml()
    fun getAttack() = "<b>Attack : </b>${item?.attack}".fromHtml()
    fun getHealth() = "<b>Health :</b> ${item?.health}".fromHtml()
    fun getDescription() = item?.text?.fromHtml()
    fun getFlavor() = "<b>Flavor</b> : ${item?.flavor}".fromHtml()
    fun getImageUrl() = item?.imageUrl

    fun getCardName() = item?.name


    private fun String?.fromHtml() =
        HtmlCompat.fromHtml(
            this?.replace("(\\r|\\n|\\r\\n)", "</br>")
                ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY
        )

}