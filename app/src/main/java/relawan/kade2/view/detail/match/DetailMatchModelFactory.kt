package relawan.kade2.view.detail.match

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import relawan.kade2.model.Match
import relawan.kade2.model.Search

class DetailMatchModelFactory(
    private val detail: Match?,
    private val search: Search?,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailMatchViewModel::class.java)) {
            return DetailMatchViewModel(detail, search, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}