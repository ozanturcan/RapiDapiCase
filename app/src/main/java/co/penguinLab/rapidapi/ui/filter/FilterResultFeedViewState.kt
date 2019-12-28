package co.penguinLab.rapidapi.ui.filter

import co.penguinLab.model.CardResultItem
import co.penguinLab.model.Status

class FilterResultFeedViewState(
    val status: Status,
    val error: Throwable? = null,
    val data: List<CardResultItem>? = null
) {
    fun getFilterResults() = data ?: mutableListOf()

    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error?.message

    fun shouldShowErrorMessage() = error != null
}