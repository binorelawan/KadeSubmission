package relawan.kade2.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteMatchDao {

    // Insert database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteMatch: FavoriteMatch)

    // Delete database
    @Delete
    suspend fun delete(favoriteMatch: FavoriteMatch)

    // show database at favoriteMatchFragment
    @Query("SELECT * FROM favorite_match")
    fun getFavorite(): LiveData<List<FavoriteMatch>>

    // represent if data added to favorite
    @Query("SELECT * FROM favorite_match WHERE id_event = :idEvent")
    fun isFavorite(idEvent: String): LiveData<FavoriteMatch>
}