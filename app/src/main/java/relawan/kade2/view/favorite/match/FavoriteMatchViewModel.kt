package relawan.kade2.view.favorite.match

import android.app.Application
import androidx.lifecycle.*
import relawan.kade2.database.FavoriteMatch
import relawan.kade2.database.MyDatabase
import relawan.kade2.repository.FavoriteMatchRepository


class FavoriteMatchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FavoriteMatchRepository
    val favoriteMatch: LiveData<List<FavoriteMatch>>


    init {

        val favoriteMatchDao = MyDatabase.getInstance(application).favoriteMatchDao
        repository = FavoriteMatchRepository(favoriteMatchDao)
        favoriteMatch = repository.favoriteMatches

    }



}
