package relawan.kade2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    val id: Long?,
    val idEvent: String?,
    val dateEvent: String?,
    val strTime: String?,
    val idHomeTeam: String?,
    val idAwayTeam: String?,
    val strHomeTeam: String?,
    val strAwayTeam: String?,
    val intHomeScore: String?,
    val intAwayScore: String?,
    val strHomeGoalDetails: String?,
    val strAwayGoalDetails: String?,
    val strHomeYellowCards: String?,
    val strAwayYellowCards: String?,
    val strHomeRedCards: String?,
    val strAwayRedCards: String?

) : Parcelable {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "_ID"
        const val ID_EVENT: String = "ID_EVENT"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val STR_TIME: String = "TIME_EVENT"
        const val ID_HOME_TEAM = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM = "ID_AWAY_TEAM"
        const val STR_HOME_TEAM: String = "STR_HOME_TEAM"
        const val STR_AWAY_TEAM: String = "STR_AWAY_TEAM"
        const val INT_HOME_SCORE: String = "INT_HOME_SCORE"
        const val INT_AWAY_SCORE: String = "INT_AWAY_SCORE"
        const val STR_HOME_GOAL_DETAILS: String = "STR_HOME_GOAL_DETAILS"
        const val STR_AWAY_GOAL_DETAILS: String = "STR_AWAY_GOAL_DETAILS"
        const val STR_HOME_YELLOW_CARDS: String = "STR_HOME_YELLOW_CARDS"
        const val STR_AWAY_YELLOW_CARDS: String = "STR_AWAY_YELLOW_CARDS"
        const val STR_HOME_RED_CARDS: String = "STR_HOME_RED_CARDS"
        const val STR_AWAY_RED_CARDS: String = "STR_AWAY_RED_CARDS"
    }
}