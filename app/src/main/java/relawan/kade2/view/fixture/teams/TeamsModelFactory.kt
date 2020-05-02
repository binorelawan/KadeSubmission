package relawan.kade2.view.fixture.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import relawan.kade2.model.League
import relawan.kade2.repository.Repository

class TeamsModelFactory(
    private val league: League?,
    private val repository: Repository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamsViewModel::class.java)) {
            return league?.let { TeamsViewModel(it, repository) } as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}