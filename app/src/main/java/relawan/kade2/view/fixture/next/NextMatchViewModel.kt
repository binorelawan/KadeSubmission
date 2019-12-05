package relawan.kade2.view.fixture.next

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import relawan.kade2.model.League
import relawan.kade2.model.Match
import relawan.kade2.network.LeagueApi

class NextMatchViewModel(league : League, application: Application): AndroidViewModel(application) {

    // get idLeague passing for call getNextMatch()
    private val _idLeague = MutableLiveData<League>()
    private val idLeague: LiveData<League>
        get() = _idLeague

    // next match liveData
    private val _nextMatch = MutableLiveData<List<Match>>()
    val nextMatch: LiveData<List<Match>>
        get() = _nextMatch

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        //get _idLeague value from argument(SafeArgs) sent by DetailLeagueFragment
        _idLeague.value = league

        getNextMatch()
    }

    private fun getNextMatch() {

        viewModelScope.launch {
            val getNextMatchDeferred =
                idLeague.value?.idLeague?.let { LeagueApi.retrofitService.getNextMatchAsync(it) }

            try {

                val listNextMatch = getNextMatchDeferred?.await()
                _nextMatch.value = listNextMatch?.events
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
        private val TAG = NextMatchViewModel::class.java.simpleName
    }
}