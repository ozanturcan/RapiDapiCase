package co.penguinLab.data.feed.response


import com.google.gson.annotations.SerializedName

data class CardItemResponse(
    @SerializedName("artist")
    val artist: String?,
    @SerializedName("attack")
    val attack: Int?,
    @SerializedName("cardId")
    val cardId: String?,
    @SerializedName("cardSet")
    val cardSet: String?,
    @SerializedName("collectible")
    val collectible: Boolean?,
    @SerializedName("cost")
    val cost: Int?,
    @SerializedName("dbfId")
    val dbfId: String?,
    @SerializedName("flavor")
    val flavor: String?,
    @SerializedName("health")
    val health: Int?,
    @SerializedName("img")
    val img: String?,
    @SerializedName("imgGold")
    val imgGold: String?,
    @SerializedName("locale")
    val locale: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("playerClass")
    val playerClass: String?,
    @SerializedName("rarity")
    val rarity: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("type")
    val type: String?
)