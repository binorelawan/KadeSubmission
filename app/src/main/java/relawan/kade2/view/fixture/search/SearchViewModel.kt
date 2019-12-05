package relawan.kade2.view.fixture.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import relawan.kade2.model.Search
import relawan.kade2.network.LeagueApi

class SearchViewModel : ViewModel() {

    companion object {
        private val TAG = SearchViewModel::class.java.simpleName
    }

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    fun getSearchMatch(query: String): LiveData<List<Search>> {
        return getSearch(query)
    }

    private fun getSearch(query: String): MutableLiveData<List<Search>> {
        val searchMatch = MutableLiveData<List<Search>>()

        viewModelScope.launch {
            val getSearchDeferred = LeagueApi.retrofitService.getSearchAsync(query)

            try {

                val listSearch = getSearchDeferred.await()
                searchMatch.value = listSearch?.event
                Log.d(TAG, "success")


            } catch (e: Exception) {

                Log.d(TAG, e.message!!)

            }
        }

        return searchMatch
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}