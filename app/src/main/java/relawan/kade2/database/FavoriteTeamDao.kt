package relawan.kade2.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteTeamDao {

    // Insert database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteTeam: FavoriteTeam)

    // Delete database
    @Delete
    suspend fun delete(favoriteTeam: FavoriteTeam)

    // show database at favoriteTeamFragment
    @Query("SELECT * FROM favorite_team")
    fun getFavorite(): LiveData<List<FavoriteTeam>>

    // represent if data added to favorite
    @Query("SELECT * FROM favorite_team WHERE id_team = :idTeam")
    fun isFavorite(idTeam: String): LiveData<FavoriteTeam>
}