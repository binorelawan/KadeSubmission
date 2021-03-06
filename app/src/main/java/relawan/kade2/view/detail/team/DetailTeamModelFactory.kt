package relawan.kade2.view.detail.team

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import relawan.kade2.database.FavoriteTeamDao
import relawan.kade2.model.SearchTeam
import relawan.kade2.model.Teams
import relawan.kade2.repository.Repository

class DetailTeamModelFactory(
    private val database: FavoriteTeamDao,
    private val teams: Teams?,
    private val search: SearchTeam?,
    private val favorite: String?,
    private val repository: Repository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailTeamViewModel::class.java)) {
            return DetailTeamViewModel(database, teams, search, favorite, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}