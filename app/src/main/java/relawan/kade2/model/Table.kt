package relawan.kade2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Table(
    val draw: Int?,
    val goalsagainst: Int?,
    val goalsdifference: Int?,
    val goalsfor: Int?,
    val loss: Int?,
    val name: String?,
    val played: Int?,
    val teamid: String?,
    val total: Int?,
    val win: Int?
) : Parcelable