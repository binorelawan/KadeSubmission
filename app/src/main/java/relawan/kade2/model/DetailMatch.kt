package relawan.kade2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailMatch(
    val dateEvent: String?,
    val idAwayTeam: String?,
    val idEvent: String?,
    val idHomeTeam: String?,
    val intAwayScore: String?,
    val intHomeScore: String?,
    val strAwayGoalDetails: String?,
    val strAwayRedCards: String?,
    val strAwayTeam: String?,
    val strAwayYellowCards: String?,
    val strHomeGoalDetails: String?,
    val strHomeRedCards: String?,
    val strHomeTeam: String?,
    val strHomeYellowCards: String?,
    val strTime: String?
) : Parcelable