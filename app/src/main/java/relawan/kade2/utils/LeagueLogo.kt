package relawan.kade2.utils

import relawan.kade2.model.DetailLeague
import relawan.kade2.model.DetailLeagueResponse
import relawan.kade2.network.LeagueApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// call API to get Logo League
object LeagueLogo {

    fun getLeagueLogo(idLeague: String, callback: LogoLeagueCallback) {
        LeagueApi.retrofitService.getDetailLeague(idLeague).enqueue(object : Callback<DetailLeagueResponse> {
            override fun onFailure(call: Call<DetailLeagueResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<DetailLeagueResponse>, response: Response<DetailLeagueResponse>) {

                if (response.isSuccessful) {
                    val result = response.body()?.leagues
                    result?.let {
                        callback.onSuccess(it[0])
                    } ?: callback.onError()
                } else {
                    callback.onError()
                }
            }

        })
    }
}


interface LogoLeagueCallback {
    fun onError()
    fun onSuccess(detailLeague: DetailLeague)
}