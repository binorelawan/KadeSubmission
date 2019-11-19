package relawan.kade2.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import relawan.kade2.model.League
import relawan.kade2.model.LeagueResponse
import relawan.kade2.network.LeagueApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    // league liveData
    private val _leagueName = MutableLiveData<List<League>>()
    val leagueName: LiveData<List<League>>
        get() = _leagueName



    init {
        getLeagueList()
    }

    private fun getLeagueList() {

        LeagueApi.retrofitService.getLeague().enqueue(object: Callback<LeagueResponse> {
            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                Log.d(TAG, t.message!!)
            }

            override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {
                _leagueName.value = response.body()?.leagues
                Log.d(TAG, "success")
            }

        })
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}