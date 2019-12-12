package relawan.kade2.view.fixture.last

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import relawan.kade2.model.League
import relawan.kade2.model.Match
import relawan.kade2.repository.MatchRepoCallback
import relawan.kade2.repository.Repository

class LastMatchViewModel(val league : League): ViewModel() {

    // last match liveData
    private val _lastMatch = MutableLiveData<List<Match>>()
    val lastMatch: LiveData<List<Match>>
        get() = _lastMatch


    private val repository = Repository()

    init {

        getLastMatch(league.idLeague.toString())
    }

    fun getLastMatch(idLeague: String) {

        repository.getLastMatchRepo(idLeague, object : MatchRepoCallback {
            override fun onError() {
                Log.d(TAG, "error")
            }

            override fun onSuccess(match: List<Match>) {
                _lastMatch.value = match
                Log.d(TAG, "Success: ${match.size}")
            }

        })
    }

    companion object {
        private val TAG = LastMatchViewModel::class.java.simpleName
    }
}