package relawan.kade2.view.fixture.next

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import relawan.kade2.model.League
import relawan.kade2.model.Match
import relawan.kade2.model.MatchResponse
import relawan.kade2.network.LeagueApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextMatchViewModel(league : League, application: Application): AndroidViewModel(application) {

    // get idLeague passing for call getNextMatch()
    private val _idLeague = MutableLiveData<League>()
    private val idLeague: LiveData<League>
        get() = _idLeague

    // next match liveData
    private val _nextMatch = MutableLiveData<List<Match>>()
    val nextMatch: LiveData<List<Match>>
        get() = _nextMatch

    init {
        //get _idLeague value from argument(SafeArgs) sent by DetailLeagueFragment
        _idLeague.value = league

        getNextMatch()
    }

    private fun getNextMatch() {

        idLeague.value?.idLeague?.let {
            LeagueApi.retrofitService.getNextMatch(it).enqueue(object : Callback<MatchResponse>{
                override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                    Log.d(TAG, t.message!!)
                }

                override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                    _nextMatch.value = response.body()?.events
                    Log.d(TAG, "success")
                }

            })
        }
    }

    companion object {
        private val TAG = NextMatchViewModel::class.java.simpleName
    }
}