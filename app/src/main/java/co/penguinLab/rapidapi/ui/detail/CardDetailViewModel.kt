package co.penguinLab.rapidapi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.penguinLab.common.RxAwareViewModel
import co.penguinLab.common.plusAssign
import co.penguinLab.model.CardDetailItem
import co.penguinLab.model.Resource
import co.penguinLab.rapidapi.domain.CardDetailUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CardDetailViewModel @Inject constructor(private val cardDetailUseCase: CardDetailUseCase) :
    RxAwareViewModel() {

    private val searchResultsLiveData = MutableLiveData<CardDetailFeedViewState>()

    fun getCardDetailLiveData(): LiveData<CardDetailFeedViewState> =
        searchResultsLiveData

    fun fetchSelectedCardResult(cardId: String) {
        cardDetailUseCase
            .fetchSelectedCardResult(cardId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onCardDetailResultReady)
            .also {
                disposable += it
            }
    }

    private fun onCardDetailResultReady(resource: Resource<List<CardDetailItem>>) {
        searchResultsLiveData.value =
            CardDetailFeedViewState(
                status = resource.status,
                error = resource.error,
                data = resource.data
            )
    }
}