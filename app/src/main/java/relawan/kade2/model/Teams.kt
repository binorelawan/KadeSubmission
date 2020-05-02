package relawan.kade2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(
    val id: Long?,
    val idLeague: String?,
    val idTeam: String?,
    val intFormedYear: String?,
    val strCountry: String?,
    val strDescriptionEN: String?,
    val strStadium: String?,
    val strTeam: String?,
    val strTeamBadge: String?,
    val strTeamBanner: String?

) : Parcelable {

    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "_ID"
        const val ID_LEAGUE: String = "ID_LEAGUE"
        const val ID_TEAM: String = "ID_TEAM"
        const val INT_FORMED_YEAR: String = "INT_FORMED_YEAR"
        const val STR_COUNTRY: String = "STR_COUNTRY"
        const val STR_DESCRIPTION_EN: String = "STR_DESCRIPTION_EN"
        const val STR_STADIUM: String = "STR_STADIUM"
        const val STR_TEAM: String = "STR_TEAM"
        const val STR_TEAM_BADGE: String = "STR_TEAM_BADGE"
        const val STR_TEAM_BANNER: String = "STR_TEAM_BANNER"


    }
}