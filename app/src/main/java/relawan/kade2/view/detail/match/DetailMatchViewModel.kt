package relawan.kade2.view.detail.match

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import relawan.kade2.model.DetailMatch
import relawan.kade2.model.DetailMatchResponse
import relawan.kade2.model.Match
import relawan.kade2.model.Search
import relawan.kade2.network.LeagueApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMatchViewModel(detail: Match?, search: Search?, application: Application) : AndroidViewModel(application) {

//    private val _idEventLast = MutableLiveData<LastMatch?>()
//    private val idEventLast: LiveData<LastMatch?>
//        get() = _idEventLast
//
//    private val _idEventNext = MutableLiveData<NextMatch?>()
//    private val idEventNext: LiveData<NextMatch?>
//        get() = _idEventNext


    private val _idDetail = MutableLiveData<Match>()
    private val idDetail: LiveData<Match>
        get() = _idDetail

    private val _idSearch = MutableLiveData<Search>()
    private val idSearch: LiveData<Search>
        get() = _idSearch

    private val _event = MutableLiveData<String>()
    val event: LiveData<String>
        get() = _event

    private val _detail = MutableLiveData<List<DetailMatch>>()
    val detail: LiveData<List<DetailMatch>>
        get() = _detail



    init {
//        _idEventLast.value = detailLast
//        _idEventNext.value = detailNext
        _idDetail.value = detail
        _idSearch.value = search

//        _event.value = idEventLast.value?.idEvent ?: idEventNext.value?.idEvent
        _event.value = idDetail.value?.idEvent ?: idSearch.value?.idEvent

        getDetailMatch()
    }

    private fun getDetailMatch() {

        event.value?.let {
            LeagueApi.retrofitService.getDetailMatch(it).enqueue(object : Callback<DetailMatchResponse>{
                override fun onFailure(call: Call<DetailMatchResponse>, t: Throwable) {
                    Log.d(TAG, t.message!!)
                    Log.d(TAG, event.value!!)
                }

                override fun onResponse(call: Call<DetailMatchResponse>, response: Response<DetailMatchResponse>) {
                    _detail.value = response.body()?.events
                    Log.d(TAG, "${event.value} success")
                }

            })
        }



    }

    companion object {
        private val TAG = DetailMatchViewModel::class.java.simpleName
    }

}