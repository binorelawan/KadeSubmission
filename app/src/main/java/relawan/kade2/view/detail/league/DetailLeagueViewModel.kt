package relawan.kade2.view.detail.league

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import relawan.kade2.model.DetailLeague
import relawan.kade2.model.DetailLeagueResponse
import relawan.kade2.model.League
import relawan.kade2.network.LeagueApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailLeagueViewModel(league : League, application: Application): AndroidViewModel(application) {

    // get idLeague passing for call getDetailLeague()
    private val _idLeague = MutableLiveData<League>()
    private val idLeague: LiveData<League>
        get() = _idLeague

    // detail league liveData
    private val _detail = MutableLiveData<List<DetailLeague>>()
    val detail: LiveData<List<DetailLeague>>
        get() = _detail


    init {
        //get _idLeague value from argument(SafeArgs) sent by HomeFragment
        _idLeague.value = league

        getDetailLeague()
    }

    private fun getDetailLeague() {

        idLeague.value?.idLeague?.let {
            LeagueApi.retrofitService.getDetailLeague(it).enqueue(object : Callback<DetailLeagueResponse>{
                override fun onFailure(call: Call<DetailLeagueResponse>, t: Throwable) {
                    Log.d(TAG, t.message!!)
                }

                override fun onResponse(call: Call<DetailLeagueResponse>, response: Response<DetailLeagueResponse>) {
                    _detail.value = response.body()?.leagues
                    Log.d(TAG, "success")
                }

            })
        }
    }


    companion object {
        private val TAG = DetailLeagueViewModel::class.java.simpleName
    }
}