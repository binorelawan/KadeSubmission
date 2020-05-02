package relawan.kade2.repository

import androidx.lifecycle.MutableLiveData
import relawan.kade2.model.*
import relawan.kade2.network.LeagueApi
import relawan.kade2.network.LeagueApiService
import relawan.kade2.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private var leagueApiService: LeagueApiService = LeagueApi.retrofitService

    // get Soccer League List
    fun getLeagueRepo(callback: LeagueRepoCallback): MutableLiveData<List<League>> {
        EspressoIdlingResource.increment()
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
                EspressoIdlingResource.decrement()
            }
        })

        return leagueList
    }


    // get Detail League
    fun getDetailLeagueRepo(idLeague: String, callback: DetailLeagueRepoCallback): MutableLiveData<List<DetailLeague>> {
        EspressoIdlingResource.increment()
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
                EspressoIdlingResource.decrement()
            }

        })

        return detailLeagueList
    }


    // get Last Match List
    fun getLastMatchRepo(idLeague: String, callback: MatchRepoCallback): MutableLiveData<List<Match>> {
        EspressoIdlingResource.increment()
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
                EspressoIdlingResource.decrement()
            }

        })

        return lastMatchList
    }


    // get Next Match List
    fun getNextMatchRepo(idLeague: String, callback: MatchRepoCallback): MutableLiveData<List<Match>> {
        EspressoIdlingResource.increment()
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
                EspressoIdlingResource.decrement()
            }

        })

        return nextMatchList
    }


    // get Detail Match List
    fun getDetailMatchRepo(idEvent: String, callback: DetailMatchRepoCallback): MutableLiveData<List<DetailMatch>> {
        EspressoIdlingResource.increment()
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
                EspressoIdlingResource.decrement()
            }

        })

        return detailMatchList
    }


    // get Search Match
    fun getSearchMatchRepo(query: String, callback: SearchMatchRepoCallback): MutableLiveData<List<SearchMatch>> {
        EspressoIdlingResource.increment()
        val searchList = MutableLiveData<List<SearchMatch>>()
        val call = leagueApiService.getSearchMatch(query)
        call.enqueue(object : Callback<SearchMatchResponse?> {
            override fun onFailure(call: Call<SearchMatchResponse?>, t: Throwable) {
                searchList.value = null
                callback.onError()
            }

            override fun onResponse(call: Call<SearchMatchResponse?>, response: Response<SearchMatchResponse?>) {

                if (response.isSuccessful) {
                    val result = response.body()?.event
                    result?.let {
                        callback.onSuccess(it)
                    } ?: callback.onError()
                } else {
                    callback.onError()
                }
                EspressoIdlingResource.decrement()
            }

        })

        return searchList
    }


    // get Table League
    fun getTableLeagueRepo(idLeague: String, callback: TableLeagueRepoCallback): MutableLiveData<List<Table>> {
        EspressoIdlingResource.increment()
        val tableList = MutableLiveData<List<Table>>()
        val call = leagueApiService.getTable(idLeague)
        call.enqueue(object : Callback<TableResponse> {
            override fun onFailure(call: Call<TableResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<TableResponse>, response: Response<TableResponse>) {

                if (response.isSuccessful) {
                    val result = response.body()?.table
                    result?.let {
                        callback.onSuccess(it)
                    } ?: callback.onError()
                } else {
                    callback.onError()
                }
                EspressoIdlingResource.decrement()
            }

        })

        return tableList
    }


    // get Teams
    fun getTeamsRepo(idLeague: String, callback: TeamsRepoCallback): MutableLiveData<List<Teams>> {
        EspressoIdlingResource.increment()
        val teamList = MutableLiveData<List<Teams>>()
        val call = leagueApiService.getTeams(idLeague)
        call.enqueue(object :Callback<TeamsResponse> {
            override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<TeamsResponse>, response: Response<TeamsResponse>) {

                if (response.isSuccessful) {
                    val result = response.body()?.teams
                    result?.let {
                        callback.onSuccess(it)
                    } ?: callback.onError()
                } else {
                    callback.onError()
                }
                EspressoIdlingResource.decrement()
            }

        })

        return teamList
    }


    // get Detail Team
    fun getDetailTeamRepo(idTeam: String, callback: DetailTeamRepoCallback): MutableLiveData<List<DetailTeam>> {
        EspressoIdlingResource.increment()
        val detailTeamList = MutableLiveData<List<DetailTeam>>()
        val call = leagueApiService.getDetailTeam(idTeam)
        call.enqueue(object :Callback<DetailTeamResponse> {
            override fun onFailure(call: Call<DetailTeamResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<DetailTeamResponse>, response: Response<DetailTeamResponse>) {

                if (response.isSuccessful) {
                    val result = response.body()?.teams
                    result?.let {
                        callback.onSuccess(it)
                    } ?: callback.onError()
                } else {
                    callback.onError()
                }
                EspressoIdlingResource.decrement()
            }

        })

        return detailTeamList
    }


    // get Search Team
    fun getSearchTeamRepo(query: String, callback: SearchTeamRepoCallback): MutableLiveData<List<SearchTeam>> {
        EspressoIdlingResource.increment()
        val searchList = MutableLiveData<List<SearchTeam>>()
        val call = leagueApiService.getSearchTeam(query)
        call.enqueue(object : Callback<SearchTeamResponse?> {
            override fun onFailure(call: Call<SearchTeamResponse?>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<SearchTeamResponse?>, response: Response<SearchTeamResponse?>) {

                if (response.isSuccessful) {
                    val result = response.body()?.teams
                    result?.let {
                        callback.onSuccess(it)
                    } ?: callback.onError()
                } else {
                    callback.onError()
                }
                EspressoIdlingResource.decrement()
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

interface SearchMatchRepoCallback {
    fun onError()
    fun onSuccess(search: List<SearchMatch>)
}

interface TableLeagueRepoCallback {
    fun onError()
    fun onSuccess(table: List<Table>)
}

interface TeamsRepoCallback {
    fun onError()
    fun onSuccess(teams: List<Teams>)
}

interface DetailTeamRepoCallback {
    fun onError()
    fun onSuccess(detailTeam: List<DetailTeam>)
}

interface SearchTeamRepoCallback {
    fun onError()
    fun onSuccess(search: List<SearchTeam>)
}
