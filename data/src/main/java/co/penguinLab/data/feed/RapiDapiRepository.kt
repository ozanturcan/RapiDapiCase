package co.penguinLab.data.feed

import co.penguinLab.common.applyLoading
import co.penguinLab.data.feed.response.CardDetailResultsResponse
import co.penguinLab.data.feed.response.CardResultsResponse
import co.penguinLab.model.Resource
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class RapiDapiRepository @Inject constructor(
    private val rapidapiRemoteDataSource: RapidapiRemoteDataSource
) {

    fun fetchSearchResult(query: String): Observable<Resource<CardResultsResponse>> =
        rapidapiRemoteDataSource
            .fetchSearchResult(query)
            .map { Resource.success(CardResultsResponse(it)) }
            .onErrorReturn { Resource.error(it) }
            .subscribeOn(Schedulers.io())
            .compose(applyLoading())

    fun fetchFilterResult(query: String): Observable<Resource<CardResultsResponse>> =
        rapidapiRemoteDataSource
            .fetchFilterResult(query)
            .map { Resource.success(CardResultsResponse(it)) }
            .onErrorReturn { Resource.error(it) }
            .subscribeOn(Schedulers.io())
            .compose(applyLoading())

    fun fetchSelectedCardResult(cardId: String): Observable<Resource<CardDetailResultsResponse>> =
        rapidapiRemoteDataSource
            .fetchSelectedCardResult(cardId)
            .map { Resource.success(CardDetailResultsResponse(it)) }
            .onErrorReturn { Resource.error(it) }
            .subscribeOn(Schedulers.io())
            .compose(applyLoading())

    private fun <T> Observable<T>.handleResourceType(): Observable<Resource<T>> {

        return this.map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .compose(applyLoading())
    }
}