package relawan.kade2.view.detail.league

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import relawan.kade2.model.DetailLeague
import relawan.kade2.model.League
import relawan.kade2.network.LeagueApi

class DetailLeagueViewModel(league : League, application: Application): AndroidViewModel(application) {

    // get idLeague passing for call getDetailLeague()
    private val _idLeague = MutableLiveData<League>()
    private val idLeague: LiveData<League>
        get() = _idLeague

    // detail league liveData
    private val _detail = MutableLiveData<List<DetailLeague>>()
    val detail: LiveData<List<DetailLeague>>
        get() = _detail

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        //get _idLeague value from argument(SafeArgs) sent by HomeFragment
        _idLeague.value = league

        getDetailLeague()
    }

    private fun getDetailLeague() {

        viewModelScope.launch {
            val getDetailLeagueDeferred = idLeague.value?.idLeague?.let { LeagueApi.retrofitService.getDetailLeagueAsync(it) }

            try {

                val listDetailLeague = getDetailLeagueDeferred?.await()
                _detail.value = listDetailLeague?.leagues
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
        private val TAG = DetailLeagueViewModel::class.java.simpleName
    }
}