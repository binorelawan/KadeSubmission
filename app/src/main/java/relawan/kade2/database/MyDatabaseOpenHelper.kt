package relawan.kade2.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

// TODO database class
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
        db.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.ID_EVENT to TEXT + UNIQUE,
            Favorite.DATE_EVENT to TEXT,
            Favorite.TIME_EVENT to TEXT,
            Favorite.HOME_TEAM to TEXT,
            Favorite.AWAY_TEAM to TEXT,
            Favorite.HOME_SCORE to TEXT,
            Favorite.AWAY_SCORE to TEXT,
            Favorite.HOME_GOAL_DETAILS to TEXT,
            Favorite.AWAY_GOAL_DETAILS to TEXT,
            Favorite.HOME_YELLOW_CARDS to TEXT,
            Favorite.AWAY_YELLOW_CARDS to TEXT,
            Favorite.HOME_RED_CARDS to TEXT,
            Favorite.AWAY_RED_CARDS to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here upgrade tables
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)