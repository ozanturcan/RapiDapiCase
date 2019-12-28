package co.penguinLab.data.remote

import co.penguinLab.data.feed.response.CardItemResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RapiDapiRestInterface {

    @GET("cards/search/{query}")
    fun fetchSearchResult(@Path("query") query: String): Observable<List<CardItemResponse>>


    @GET("cards/classes/{className}")
    fun fetchFilteredCardResult(@Path("className") className: String): Observable<List<CardItemResponse>>


    @GET("cards/{cardId}")
    fun fetchSelectedCardResult(@Path("cardId") cardId: String): Observable<List<CardItemResponse>>


}