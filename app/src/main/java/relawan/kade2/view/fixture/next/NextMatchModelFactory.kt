package relawan.kade2.view.fixture.next

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import relawan.kade2.model.League
import relawan.kade2.repository.Repository

class NextMatchModelFactory(
    private val league: League?,
    private val repository: Repository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NextMatchViewModel::class.java)) {
            return league?.let { NextMatchViewModel(it, repository) } as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}