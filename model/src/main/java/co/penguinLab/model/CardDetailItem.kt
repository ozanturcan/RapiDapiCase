package co.penguinLab.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CardDetailItem(
    val imageUrl: String?,
    val name: String?,
    val overview: String?,
    val artist: String?,
    val attack: Int?,
    val cardId: String?,
    val cardSet: String?,
    val collectible: Boolean?,
    val cost: Int?,
    val flavor: String?,
    val health: Int?,
    val imgGold: String?,
    val locale: String?,
    val playerClass: String?,
    val rarity: String?,
    val text: String?,
    val type: String?
) : Parcelable