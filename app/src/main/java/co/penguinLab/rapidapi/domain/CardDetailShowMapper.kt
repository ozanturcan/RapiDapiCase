package co.penguinLab.rapidapi.domain

import co.penguinLab.common.Mapper
import co.penguinLab.data.feed.response.CardDetailResultsResponse
import co.penguinLab.model.CardDetailItem
import javax.inject.Inject

class CardDetailShowMapper @Inject constructor() {

    fun mapFromResponse(type: CardDetailResultsResponse): List<CardDetailItem> {
        return object :
            Mapper<CardDetailResultsResponse, List<CardDetailItem>> {
            override fun mapFrom(type: CardDetailResultsResponse): List<CardDetailItem> {
                return type.results.map { itemResponse ->
                    CardDetailItem(
                        imageUrl = itemResponse.img,
                        name = itemResponse.name,
                        overview = itemResponse.text,
                        cardId = itemResponse.cardId,
                        artist = itemResponse.artist,
                        attack = itemResponse.attack,
                        cardSet = itemResponse.cardSet,
                        cost = itemResponse.cost,
                        flavor = itemResponse.flavor,
                        health = itemResponse.health,
                        imgGold = itemResponse.imgGold,
                        locale = itemResponse.locale,
                        playerClass = itemResponse.playerClass,
                        rarity = itemResponse.rarity,
                        text = itemResponse.text,
                        type = itemResponse.type,
                        collectible = itemResponse.collectible
                    )
                }
            }
        }.mapFrom(type)
    }

}