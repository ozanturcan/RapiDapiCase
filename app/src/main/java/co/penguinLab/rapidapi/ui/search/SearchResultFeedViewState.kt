package co.penguinLab.rapidapi.ui.search

import co.penguinLab.model.CardResultItem
import co.penguinLab.model.Status

class SearchResultFeedViewState(
    val status: Status,
    val error: Throwable? = null,
    val data: List<CardResultItem>? = null
) {
    fun getSearchResults() = data ?: mutableListOf()

    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error?.message

    fun shouldShowErrorMessage() = error != null
}