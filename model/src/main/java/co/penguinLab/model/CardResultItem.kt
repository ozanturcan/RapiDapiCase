package co.penguinLab.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CardResultItem(
    val cardId: String,
    val imageUrl: String?,
    val name: String?,
    val overview: String?
) : Parcelable