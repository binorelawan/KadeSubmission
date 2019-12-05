package relawan.kade2.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import relawan.kade2.model.League
import relawan.kade2.network.LeagueApi

class HomeViewModel : ViewModel() {

    // league liveData
    private val _leagueName = MutableLiveData<List<League>>()
    val leagueName: LiveData<List<League>>
        get() = _leagueName


    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getLeagueList()
    }

    private fun getLeagueList() {

        viewModelScope.launch {
            val getLeagueDeferred = LeagueApi.retrofitService.getLeagueAsync()

            try {

                val listLeague = getLeagueDeferred.await()
                _leagueName.value = listLeague.leagues
                Log.d(TAG, "success")


            } catch (e: Exception) {

                Log.d(TAG, e.message!!)

            }


        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}