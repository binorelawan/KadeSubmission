package relawan.kade2.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import relawan.kade2.model.Match
import relawan.kade2.model.Teams


class MyDatabaseOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "Favorite.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(context.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here create tables favorite match
        db.createTable(Match.TABLE_FAVORITE_MATCH, true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.ID_EVENT to TEXT + UNIQUE,
            Match.DATE_EVENT to TEXT,
            Match.STR_TIME to TEXT,
            Match.ID_HOME_TEAM to TEXT,
            Match.ID_AWAY_TEAM to TEXT,
            Match.STR_HOME_TEAM to TEXT,
            Match.STR_AWAY_TEAM to TEXT,
            Match.INT_HOME_SCORE to TEXT,
            Match.INT_AWAY_SCORE to TEXT,
            Match.STR_HOME_GOAL_DETAILS to TEXT,
            Match.STR_AWAY_GOAL_DETAILS to TEXT,
            Match.STR_HOME_YELLOW_CARDS to TEXT,
            Match.STR_AWAY_YELLOW_CARDS to TEXT,
            Match.STR_HOME_RED_CARDS to TEXT,
            Match.STR_AWAY_RED_CARDS to TEXT)

        // Here create tables favorite team
        db.createTable(Teams.TABLE_FAVORITE_TEAM, true,
            Teams.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Teams.ID_LEAGUE to TEXT + UNIQUE,
            Teams.ID_TEAM to TEXT,
            Teams.INT_FORMED_YEAR to TEXT,
            Teams.STR_COUNTRY to TEXT,
            Teams.STR_DESCRIPTION_EN to TEXT,
            Teams.STR_STADIUM to TEXT,
            Teams.STR_TEAM to TEXT,
            Teams.STR_TEAM_BADGE to TEXT,
            Teams.STR_TEAM_BANNER to TEXT)


    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here upgrade tables
        db.dropTable(Match.TABLE_FAVORITE_MATCH, true)
        db.dropTable(Teams.TABLE_FAVORITE_TEAM, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)