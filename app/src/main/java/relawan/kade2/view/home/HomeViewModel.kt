package relawan.kade2.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import relawan.kade2.model.League
import relawan.kade2.repository.LeagueRepoCallback
import relawan.kade2.repository.Repository

class HomeViewModel(val repository: Repository) : ViewModel() {

    // league liveData
    private val _leagueName = MutableLiveData<List<League>>()
    val leagueName: LiveData<List<League>>
        get() = _leagueName



    init {
        getLeagueList()
    }

    fun getLeagueList() {

        repository.getLeagueRepo(object : LeagueRepoCallback {
            override fun onError() {
                Log.d(TAG, "error")
            }

            override fun onSuccess(league: List<League>) {

                _leagueName.value = league
                Log.d(TAG, "Success: ${league.size}")

            }


        })
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}