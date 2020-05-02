package relawan.kade2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_match")
data class FavoriteMatch(

    @PrimaryKey
    @ColumnInfo(name = "id_event")
    var idEvent: String = "",

    @ColumnInfo(name = "date_event")
    var dateEvent: String? = null,

    @ColumnInfo(name = "str_time")
    var strTime: String? = null,

    @ColumnInfo(name = "id_away_team")
    var idAwayTeam: String? = null,

    @ColumnInfo(name = "id_home_team")
    var idHomeTeam: String? = null,

    @ColumnInfo(name = "str_away_team")
    var strAwayTeam: String? = null,

    @ColumnInfo(name = "str_home_team")
    var strHomeTeam: String? = null,

    @ColumnInfo(name = "int_away_score")
    var intAwayScore: String? = null,

    @ColumnInfo(name = "int_home_score")
    var intHomeScore: String? = null,

    @ColumnInfo(name = "str_away_goal_details")
    var strAwayGoalDetails: String? = null,

    @ColumnInfo(name = "str_home_goal_details")
    var strHomeGoalDetails: String? = null,

    @ColumnInfo(name = "str_away_red_cards")
    var strAwayRedCards: String? = null,

    @ColumnInfo(name = "str_home_red_cards")
    var strHomeRedCards: String? = null,

    @ColumnInfo(name = "str_away_yellow_cards")
    var strAwayYellowCards: String? = null,

    @ColumnInfo(name = "str_home_yellow_cards")
    var strHomeYellowCards: String? = null
)