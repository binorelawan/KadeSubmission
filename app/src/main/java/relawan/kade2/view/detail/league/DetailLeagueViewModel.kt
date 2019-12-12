package relawan.kade2.view.detail.league

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import relawan.kade2.model.DetailLeague
import relawan.kade2.model.League
import relawan.kade2.repository.DetailLeagueRepoCallback
import relawan.kade2.repository.Repository

class DetailLeagueViewModel(val league : League): ViewModel() {

    // detail league liveData
    private val _detail = MutableLiveData<List<DetailLeague>>()
    val detail: LiveData<List<DetailLeague>>
        get() = _detail

    private val repository = Repository()

    init {

        getDetailLeague(league.idLeague.toString())

    }


    fun getDetailLeague(idLeague: String) {

        repository.getDetailLeagueRepo(idLeague, object : DetailLeagueRepoCallback {
            override fun onError() {
                Log.d(TAG, "error")

            }

            override fun onSuccess(detailLeague: List<DetailLeague>) {
                _detail.value = detailLeague
                Log.d(TAG, "Success: ${detailLeague.size}")
            }

        })
    }


    companion object {
        private val TAG = DetailLeagueViewModel::class.java.simpleName
    }
}