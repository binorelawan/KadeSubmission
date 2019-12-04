package relawan.kade2.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import relawan.kade2.model.Match


class MyDatabaseOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "FavoriteMatch.db", null, 1) {

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
        // Here create tables
        db.createTable(Match.TABLE_FAVORITE, true,
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
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here upgrade tables
        db.dropTable(Match.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)