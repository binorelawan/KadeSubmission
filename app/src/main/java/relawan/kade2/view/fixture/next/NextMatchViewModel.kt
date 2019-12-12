package relawan.kade2.view.fixture.next

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import relawan.kade2.model.League
import relawan.kade2.model.Match
import relawan.kade2.repository.MatchRepoCallback
import relawan.kade2.repository.Repository

class NextMatchViewModel(val league : League): ViewModel() {

    // next match liveData
    private val _nextMatch = MutableLiveData<List<Match>>()
    val nextMatch: LiveData<List<Match>>
        get() = _nextMatch

    private val repository = Repository()

    init {

        getNextMatch(league.idLeague.toString())
    }

    fun getNextMatch(idLeague: String) {

        repository.getNextMatchRepo(idLeague, object : MatchRepoCallback {
            override fun onError() {
                Log.d(TAG, "error")
            }

            override fun onSuccess(match: List<Match>) {
                _nextMatch.value = match
                Log.d(TAG, "Success: ${match.size}")
            }

        })
    }

    companion object {
        private val TAG = NextMatchViewModel::class.java.simpleName
    }
}