package relawan.kade2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailMatch(
    var idEvent: String?,


    var dateEvent: String?,


    var strTime: String?,


    var idAwayTeam: String?,


    var idHomeTeam: String?,


    var strAwayTeam: String?,


    var strHomeTeam: String?,


    var intAwayScore: String?,


    var intHomeScore: String?,


    var strAwayGoalDetails: String?,


    var strHomeGoalDetails: String?,


    var strAwayRedCards: String?,


    var strHomeRedCards: String?,


    var strAwayYellowCards: String?,


    var strHomeYellowCards: String?
) : Parcelable