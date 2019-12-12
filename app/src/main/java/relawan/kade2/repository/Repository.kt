package relawan.kade2.repository

import androidx.lifecycle.MutableLiveData
import relawan.kade2.model.*
import relawan.kade2.network.LeagueApi
import relawan.kade2.network.LeagueApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private var leagueApiService: LeagueApiService = LeagueApi.retrofitService

    // get Soccer League List
    fun getLeagueRepo(callback: LeagueRepoCallback): MutableLiveData<List<League>> {
        val leagueList = MutableLiveData<List<League>>()
        val call = leagueApiService.getLeague()
        call.enqueue(object : Callback<LeagueResponse> {
            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {

                if (response.isSuccessful) {
                    val result = response.body()?.leagues
                    result?.let {
                        callback.onSuccess(it)
                    } ?: callback.onError()
                } else {
                    callback.onError()
                }

            }
        })

        return leagueList
    }


    // get Detail League
    fun getDetailLeagueRepo(idLeague: String, callback: DetailLeagueRepoCallback): MutableLiveData<List<DetailLeague>> {
        val detailLeagueList = MutableLiveData<List<DetailLeague>>()
        val call = leagueApiService.getDetailLeague(idLeague)
        call.enqueue(object : Callback<DetailLeagueResponse> {
            override fun onFailure(call: Call<DetailLeagueResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<DetailLeagueResponse>, response: Response<DetailLeagueResponse>) {

                if (response.isSuccessful) {
                    val result = response.body()?.leagues
                    result?.let {
                        callback.onSuccess(it)
                    } ?: callback.onError()
                } else {
                    callback.onError()
                }
            }

        })

        return detailLeagueList
    }


    // get Last Match List
    fun getLastMatchRepo(idLeague: String, callback: MatchRepoCallback): MutableLiveData<List<Match>> {
        val lastMatchList = MutableLiveData<List<Match>>()
        val call = leagueApiService.getLastMatch(idLeague)
        call.enqueue(object : Callback<MatchResponse> {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {

                if (response.isSuccessful) {
                    val result = response.body()?.events
                    result?.let {
                        callback.onSuccess(it)
                    } ?: callback.onError()
                } else {
                    callback.onError()
                }
            }

        })

        return lastMatchList
    }


    // get Next Match List
    fun getNextMatchRepo(idLeague: String, callback: MatchRepoCallback): MutableLiveData<List<Match>> {
        val nextMatchList = MutableLiveData<List<Match>>()
        val call = leagueApiService.getNextMatch(idLeague)
        call.enqueue(object : Callback<MatchResponse> {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {

                if (response.isSuccessful) {
                    val result = response.body()?.events
                    result?.let {
                        callback.onSuccess(it)
                    } ?: callback.onError()
                } else {
                    callback.onError()
                }
            }

        })

        return nextMatchList
    }


    // get Detail Match List
    fun getDetailMatchRepo(idEvent: String, callback: DetailMatchRepoCallback): MutableLiveData<List<DetailMatch>> {
        val detailMatchList = MutableLiveData<List<DetailMatch>>()
        val call = leagueApiService.getDetailMatch(idEvent)
        call.enqueue(object : Callback<DetailMatchResponse> {
            override fun onFailure(call: Call<DetailMatchResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<DetailMatchResponse>, response: Response<DetailMatchResponse>) {

                if (response.isSuccessful) {
                    val result = response.body()?.events
                    result?.let {
                        callback.onSuccess(it)
                    } ?: callback.onError()
                } else {
                    callback.onError()
                }
            }

        })

        return detailMatchList
    }


    // get Search Match
    fun getSearchMatchRepo(query: String, callback: SearchRepoCallback): MutableLiveData<List<Search>> {
        val searchList = MutableLiveData<List<Search>>()
        val call = leagueApiService.getSearch(query)
        call.enqueue(object : Callback<SearchResponse?> {
            override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                searchList.value = null
                callback.onError()
            }

            override fun onResponse(call: Call<SearchResponse?>, response: Response<SearchResponse?>) {

                if (response.isSuccessful) {
                    val result = response.body()?.event
                    result?.let {
                        callback.onSuccess(it)
                    } ?: callback.onError()
                } else {
                    callback.onError()
                }

            }

        })

        return searchList
    }

}

interface LeagueRepoCallback {
    fun onError()
    fun onSuccess(league: List<League>)
}

interface DetailLeagueRepoCallback {
    fun onError()
    fun onSuccess(detailLeague: List<DetailLeague>)
}

interface MatchRepoCallback {
    fun onError()
    fun onSuccess(match: List<Match>)
}

interface DetailMatchRepoCallback {
    fun onError()
    fun onSuccess(detailMatch: List<DetailMatch>)
}

interface SearchRepoCallback {
    fun onError()
    fun onSuccess(search: List<Search>)
}
