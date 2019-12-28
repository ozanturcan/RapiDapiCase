package co.penguinLab.rapidapi.domain

import co.penguinLab.common.Mapper
import co.penguinLab.data.feed.response.CardResultsResponse
import co.penguinLab.model.CardResultItem
import javax.inject.Inject

class CardResultShowMapper @Inject constructor() {


    fun mapFromResponse(type: CardResultsResponse): List<CardResultItem> {
        return object :
            Mapper<CardResultsResponse, List<CardResultItem>> {
            override fun mapFrom(type: CardResultsResponse): List<CardResultItem> {
                return type.results.map { itemResponse ->
                    CardResultItem(
                        cardId = itemResponse.cardId!!,
                        imageUrl = itemResponse.img,
                        name = itemResponse.name,
                        overview = itemResponse.text
                    )
                }
            }
        }.mapFrom(type)
    }

}