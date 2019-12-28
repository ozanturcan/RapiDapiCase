package co.penguinLab.rapidapi.domain

import co.penguinLab.data.feed.RapiDapiRepository
import co.penguinLab.model.CardDetailItem
import co.penguinLab.model.Resource
import io.reactivex.Observable
import javax.inject.Inject

class CardDetailUseCase @Inject constructor(
    private val repository: RapiDapiRepository,
    private val mapper: CardDetailShowMapper
) {

    fun fetchSelectedCardResult(cardId: String): Observable<Resource<List<CardDetailItem>>> {
        return fetchSelectedCardFromRemote(cardId)

    }

    private fun fetchSelectedCardFromRemote(cardId: String): Observable<Resource<List<CardDetailItem>>> {
        return repository
            .fetchSelectedCardResult(cardId)
            .map { resource ->
                Resource(
                    status = resource.status,
                    data = resource.data?.let { mapper.mapFromResponse(it) },
                    error = resource.error
                )
            }
    }

}

