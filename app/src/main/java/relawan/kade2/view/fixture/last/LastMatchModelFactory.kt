package relawan.kade2.view.fixture.last

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import relawan.kade2.model.League

class LastMatchModelFactory(
    private val league: League) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LastMatchViewModel::class.java)) {
            return LastMatchViewModel(league) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}