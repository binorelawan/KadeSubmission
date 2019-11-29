package relawan.kade2.database

// TODO favorite model class

data class Favorite (
    val id: Long?,
    val idEvent: String?,
    val dateEvent: String?,
    val timeEvent: String?,
    val homeTeam: String?,
    val awayTeam: String?,
    val homeScore: String?,
    val awayScore: String?,
    val homeGoalDetails: String?,
    val awayGoalDetails: String?,
    val homeYellowCards: String?,
    val awayYellowCards: String?,
    val homeRedCards: String?,
    val awayRedCards: String?
) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "_ID"
        const val ID_EVENT: String = "ID_EVENT"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val TIME_EVENT: String = "TIME_EVENT"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_GOAL_DETAILS: String = "HOME_GOAL_DETAILS"
        const val AWAY_GOAL_DETAILS: String = "AWAY_GOAL_DETAILS"
        const val HOME_YELLOW_CARDS: String = "HOME_YELLOW_CARDS"
        const val AWAY_YELLOW_CARDS: String = "AWAY_YELLOW_CARDS"
        const val HOME_RED_CARDS: String = "HOME_RED_CARDS"
        const val AWAY_RED_CARDS: String = "AWAY_RED_CARDS"
    }
}