package relawan.kade2.repository

import androidx.lifecycle.LiveData
import relawan.kade2.database.FavoriteMatch
import relawan.kade2.database.FavoriteMatchDao


class FavoriteMatchRepository(private val favoriteMatchDao: FavoriteMatchDao) {

    // to show database to Favorite Fragment
    val favoriteMatches: LiveData<List<FavoriteMatch>> = favoriteMatchDao.getFavorite()

    suspend fun insert(favoriteMatch: FavoriteMatch) {
        favoriteMatchDao.insert(favoriteMatch)
    }

    suspend fun delete(favoriteMatch: FavoriteMatch) {
        favoriteMatchDao.delete(favoriteMatch)
    }

    // to check if data already in database
    fun isFavoriteMatch(idEvent: String) = favoriteMatchDao.isFavorite(idEvent)

}