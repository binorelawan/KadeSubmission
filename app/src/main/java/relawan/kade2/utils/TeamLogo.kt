package relawan.kade2.utils

import android.util.Log
import relawan.kade2.model.DetailTeam
import relawan.kade2.model.DetailTeamResponse
import relawan.kade2.network.LeagueApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// call API detailTeam to get logo
object TeamLogo {

    private val TAG = TeamLogo::class.java.simpleName


    fun getTeamLogo(idTeam: String, callback: TeamItemCallback) {
        LeagueApi.retrofitService.getTeam(idTeam).enqueue(object : Callback<DetailTeamResponse>{
            override fun onFailure(call: Call<DetailTeamResponse>, t: Throwable) {
                callback.onError()
                Log.d(TAG, t.message!!)
            }

            override fun onResponse(call: Call<DetailTeamResponse>, response: Response<DetailTeamResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()?.teams
                    Log.d(TAG, "success")
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

interface TeamItemCallback {
    fun onError()
    fun onSuccess(detailTeam: DetailTeam)
}