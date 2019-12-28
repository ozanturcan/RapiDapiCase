package co.penguinLab.data.feed

import co.penguinLab.data.remote.RapiDapiRestInterface
import javax.inject.Inject

class RapidapiRemoteDataSource @Inject constructor(private val restInterface: RapiDapiRestInterface) {

    fun fetchSearchResult(query: String) = restInterface.fetchSearchResult(query)
    fun fetchFilterResult(filter: String) = restInterface.fetchFilteredCardResult(filter)
    fun fetchSelectedCardResult(cardId: String) = restInterface.fetchSelectedCardResult(cardId)

}