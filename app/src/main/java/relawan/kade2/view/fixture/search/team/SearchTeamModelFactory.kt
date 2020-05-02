package relawan.kade2.view.fixture.search.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import relawan.kade2.repository.Repository

class SearchTeamModelFactory (
    private val repository: Repository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchTeamViewModel::class.java)) {
            return SearchTeamViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}