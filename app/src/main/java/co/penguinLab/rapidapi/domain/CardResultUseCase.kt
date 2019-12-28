package co.penguinLab.rapidapi.domain

import co.penguinLab.data.feed.RapiDapiRepository
import co.penguinLab.model.CardResultItem
import co.penguinLab.model.Resource
import io.reactivex.Observable
import javax.inject.Inject

class CardResultUseCase @Inject constructor(
    private val repository: RapiDapiRepository,
    private val mapper: CardResultShowMapper
) {

    fun fetchSearchResults(query: String): Observable<Resource<List<CardResultItem>>> {
        return fetchSearchResultsFromRemote(query)

    }

    fun fetchFilterResults(filter: String): Observable<Resource<List<CardResultItem>>> {
        return fetchFilterResultsFromRemote(filter)

    }

    private fun fetchSearchResultsFromRemote(query: String): Observable<Resource<List<CardResultItem>>> {
        return repository
            .fetchSearchResult(query)
            .map { resource ->
                Resource(
                    status = resource.status,
                    data = resource.data?.let { mapper.mapFromResponse(it) },
                    error = resource.error
                )
            }
    }

    private fun fetchFilterResultsFromRemote(query: String): Observable<Resource<List<CardResultItem>>> {
        return repository
            .fetchFilterResult(query)
            .map { resource ->
                Resource(
                    status = resource.status,
                    data = resource.data?.let { mapper.mapFromResponse(it) },
                    error = resource.error
                )
            }
    }

}

