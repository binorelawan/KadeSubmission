package relawan.kade2.repository

import androidx.lifecycle.LiveData
import relawan.kade2.database.FavoriteTeam
import relawan.kade2.database.FavoriteTeamDao

class FavoriteTeamRepository(private val favoriteTeamDao: FavoriteTeamDao) {

    // to show database to Favorite Fragment
    val favoriteTeams: LiveData<List<FavoriteTeam>> = favoriteTeamDao.getFavorite()

    suspend fun insert(favoriteTeam: FavoriteTeam) {
        favoriteTeamDao.insert(favoriteTeam)
    }

    suspend fun delete(favoriteTeam: FavoriteTeam) {
        favoriteTeamDao.delete(favoriteTeam)
    }

    // to check if data already in database
    fun isFavoriteTeam(idTeam: String) = favoriteTeamDao.isFavorite(idTeam)
}