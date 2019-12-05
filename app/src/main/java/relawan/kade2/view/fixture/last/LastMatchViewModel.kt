package relawan.kade2.view.fixture.last

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

class LastMatchViewModel(league : League, application: Application): AndroidViewModel(application) {

    // get idLeague passing for call getMatch()
    private val _idLeague = MutableLiveData<League>()
    private val idLeague: LiveData<League>
        get() = _idLeague

    // last match liveData
    private val _lastMatch = MutableLiveData<List<Match>>()
    val lastMatch: LiveData<List<Match>>
        get() = _lastMatch

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    init {
        //get _idLeague value from argument(SafeArgs) sent by DetailLeagueFragment
        _idLeague.value = league

        getLastMatch()
    }

    fun getLastMatch() {

        viewModelScope.launch {
            val getLastMatchDeferred =
                idLeague.value?.idLeague?.let { LeagueApi.retrofitService.getLastMatchAsync(it) }

            try {

                val listLastMatch = getLastMatchDeferred?.await()
                _lastMatch.value = listLastMatch?.events
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
        private val TAG = LastMatchViewModel::class.java.simpleName
    }
}