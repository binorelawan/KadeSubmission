package relawan.kade2.view.detail.league

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import relawan.kade2.model.League

class DetailLeagueModelFactory(
    private val league: League) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailLeagueViewModel::class.java)) {
            return DetailLeagueViewModel(league) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}