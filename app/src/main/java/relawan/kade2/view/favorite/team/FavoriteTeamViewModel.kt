package relawan.kade2.view.favorite.team

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import relawan.kade2.database.FavoriteTeam
import relawan.kade2.database.MyDatabase
import relawan.kade2.repository.FavoriteTeamRepository


class FavoriteTeamViewModel(application: Application) : AndroidViewModel(application){

    private val repository: FavoriteTeamRepository
    val favoriteTeam: LiveData<List<FavoriteTeam>>

    init {

        val favoriteTeamDao = MyDatabase.getInstance(application).favoriteTeamDao
        repository = FavoriteTeamRepository(favoriteTeamDao)
        favoriteTeam = repository.favoriteTeams

    }
}