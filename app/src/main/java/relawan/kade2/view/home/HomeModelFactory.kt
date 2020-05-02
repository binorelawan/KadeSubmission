package relawan.kade2.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import relawan.kade2.repository.Repository

class HomeModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}