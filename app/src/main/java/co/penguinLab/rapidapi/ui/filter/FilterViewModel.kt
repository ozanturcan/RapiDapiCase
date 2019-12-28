package co.penguinLab.rapidapi.ui.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.penguinLab.common.RxAwareViewModel
import co.penguinLab.common.plusAssign
import co.penguinLab.model.CardResultItem
import co.penguinLab.model.Resource
import co.penguinLab.rapidapi.domain.CardResultUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class FilterViewModel @Inject constructor(private val cardResultUseCase: CardResultUseCase) :
    RxAwareViewModel() {

    private val filterResultsLiveData = MutableLiveData<FilterResultFeedViewState>()

    fun getFilterResultLiveData(): LiveData<FilterResultFeedViewState> = filterResultsLiveData

    fun fetchFilterResults(page: String) {
        cardResultUseCase
            .fetchFilterResults(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onMoviesResultReady)
            .also {
                disposable += it
            }
    }

    private fun onMoviesResultReady(resource: Resource<List<CardResultItem>>) {
        filterResultsLiveData.value =
            FilterResultFeedViewState(
                status = resource.status,
                error = resource.error,
                data = resource.data
            )
    }
}