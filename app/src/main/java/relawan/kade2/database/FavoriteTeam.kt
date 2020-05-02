package relawan.kade2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_team")
data class FavoriteTeam (

    @PrimaryKey
    @ColumnInfo(name = "id_team")
    val idTeam: String = "",

    @ColumnInfo(name = "id_league")
    val idLeague: String?,

    @ColumnInfo(name = "int_formed_year")
    val intFormedYear: String?,

    @ColumnInfo(name = "str_country")
    val strCountry: String?,

    @ColumnInfo(name = "str_description_en")
    val strDescriptionEN: String?,

    @ColumnInfo(name = "str_stadium")
    val strStadium: String?,

    @ColumnInfo(name = "str_team")
    val strTeam: String?,

    @ColumnInfo(name = "str_team_badge")
    val strTeamBadge: String?,

    @ColumnInfo(name = "str_team_banner")
    val strTeamBanner: String?
){
}