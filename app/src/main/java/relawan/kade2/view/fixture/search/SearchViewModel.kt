package relawan.kade2.view.fixture.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import relawan.kade2.model.Search
import relawan.kade2.model.SearchResponse
import relawan.kade2.network.LeagueApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    companion object {
        private val TAG = SearchViewModel::class.java.simpleName
    }

    fun getSearchMatch(query: String): LiveData<List<Search>> {
        return getSearch(query)
    }

    private fun getSearch(query: String): MutableLiveData<List<Search>> {
        val searchMatch = MutableLiveData<List<Search>>()
        val call = LeagueApi.retrofitService.getSearch(query)
        call.enqueue(object : Callback<SearchResponse?> {
            override fun onResponse(call: Call<SearchResponse?>, response: Response<SearchResponse?>) {
                Log.d(TAG, "onResponse response:: $response")

                if (response.body() != null) {
                    searchMatch.value = response.body()?.event
                }
                Log.d(TAG, "success")
            }

            override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                searchMatch.value = ArrayList()
                Log.d(TAG, t.message!!)
                Log.d(TAG, searchMatch.value.toString())
            }
        })

        return searchMatch
    }


}