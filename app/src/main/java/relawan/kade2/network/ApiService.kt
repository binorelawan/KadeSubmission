package relawan.kade2.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import relawan.kade2.BuildConfig.BASE_URL
import relawan.kade2.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface LeagueApiService {

    // get list of League
    @GET("all_leagues.php")
    fun getLeagueAsync():
            Deferred<LeagueResponse>

//    lookupleague.php?id={idLeague}
    // get detail of league
    @GET("lookupleague.php")
    fun getDetailLeagueAsync(
        @Query("id") idLeague: String):
            Deferred<DetailLeagueResponse>

//    eventspastleague.php?id={idLeague}
    // get Last Match fixture
    @GET("eventspastleague.php")
    fun getLastMatchAsync(
        @Query("id") idLeague: String):
            Deferred<MatchResponse>

//    eventsnextleague.php?id={idLeague}
    // get Next Match fixture
    @GET("eventsnextleague.php")
    fun getNextMatchAsync(
        @Query("id") idLeague: String):
            Deferred<MatchResponse>

//    lookupevent.php?id={idEvent}
    // get Detail match fixture
    @GET("lookupevent.php")
    fun getDetailMatchAsync(
        @Query("id") idEvent: String):
            Deferred<DetailMatchResponse>

//    searchevents.php?e={query}
    // get Search match fixture
    @GET("searchevents.php")
    fun getSearchAsync(
        @Query("e") query: String):
            Deferred<SearchResponse?>

//    lookupteam.php?id={idTeam}
    // get team detail (logo)
    @GET("lookupteam.php")
    fun getTeam(
        @Query("id") idTeam: String):
            Call<DetailTeamResponse>

//    lookupleague.php?id={idLeague}
    // get league logo
    @GET("lookupleague.php")
    fun getDetailLeague(
        @Query("id") idLeague: String):
            Call<DetailLeagueResponse>

}

object LeagueApi {
    val retrofitService : LeagueApiService by lazy {
        retrofit.create(LeagueApiService::class.java)
    }
}