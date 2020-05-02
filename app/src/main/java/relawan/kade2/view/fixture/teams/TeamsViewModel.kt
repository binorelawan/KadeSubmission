package relawan.kade2.view.fixture.teams

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import relawan.kade2.model.League
import relawan.kade2.model.Teams
import relawan.kade2.repository.Repository
import relawan.kade2.repository.TeamsRepoCallback

class TeamsViewModel(val league : League, val repository: Repository): ViewModel() {

    // teams liveData
    private val _teams = MutableLiveData<List<Teams>>()
    val teams: LiveData<List<Teams>>
        get() = _teams


    init {

        getTeams(league.idLeague.toString())

    }

    fun getTeams(idLeague: String) {

        repository.getTeamsRepo(idLeague, object : TeamsRepoCallback {
            override fun onError() {
                Log.d(TAG, "error")
            }

            override fun onSuccess(teams: List<Teams>) {
                _teams.value = teams
                Log.d(TAG, "Success: ${teams.size}")
            }

        })
    }

    companion object {
        private val TAG = TeamsViewModel::class.java.simpleName
    }
}