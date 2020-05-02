package relawan.kade2.view.detail.match

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import relawan.kade2.database.FavoriteMatchDao
import relawan.kade2.model.Match
import relawan.kade2.model.SearchMatch
import relawan.kade2.repository.Repository

class DetailMatchModelFactory(
    private val database: FavoriteMatchDao,
    private val detail: Match?,
    private val search: SearchMatch?,
    private val favorite: String?,
    private val repository: Repository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailMatchViewModel::class.java)) {
            return DetailMatchViewModel(database, detail, search, favorite, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}