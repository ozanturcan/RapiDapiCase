package co.penguinLab.rapidapi.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.penguinLab.common.RxAwareViewModel
import co.penguinLab.common.plusAssign
import co.penguinLab.model.CardResultItem
import co.penguinLab.model.Resource
import co.penguinLab.rapidapi.domain.CardResultUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val cardResultUseCase: CardResultUseCase) :
    RxAwareViewModel() {

    private val searchResultsLiveData = MutableLiveData<SearchResultFeedViewState>()

    fun getSearchResultsLiveData(): LiveData<SearchResultFeedViewState> =
        searchResultsLiveData

    fun fetchSearchResults(page: String) {
        cardResultUseCase
            .fetchSearchResults(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onSearchResultReady)
            .also {
                disposable += it
            }
    }

    private fun onSearchResultReady(resource: Resource<List<CardResultItem>>) {
        searchResultsLiveData.value =
            SearchResultFeedViewState(
                status = resource.status,
                error = resource.error,
                data = resource.data
            )
    }
}