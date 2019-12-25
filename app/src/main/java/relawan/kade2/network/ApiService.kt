package relawan.kade2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    .baseUrl(BASE_URL)
    .build()


interface LeagueApiService {

    // get list of League
    @GET("all_leagues.php")
    fun getLeague():
            Call<LeagueResponse>

//    lookupleague.php?id={idLeague}
    // get detail of league
    @GET("lookupleague.php")
    fun getDetailLeague(
        @Query("id") idLeague: String):
            Call<DetailLeagueResponse>

//    eventspastleague.php?id={idLeague}
    // get Last Match fixture
    @GET("eventspastleague.php")
    fun getLastMatch(
        @Query("id") idLeague: String):
            Call<MatchResponse>

//    eventsnextleague.php?id={idLeague}
    // get Next Match fixture
    @GET("eventsnextleague.php")
    fun getNextMatch(
        @Query("id") idLeague: String):
            Call<MatchResponse>

//    lookupevent.php?id={idEvent}
    // get Detail match fixture
    @GET("lookupevent.php")
    fun getDetailMatch(
        @Query("id") idEvent: String):
            Call<DetailMatchResponse>

//    searchevents.php?e={query}
    // get Search match fixture
    @GET("searchevents.php")
    fun getSearchMatch(
        @Query("e") query: String):
            Call<SearchMatchResponse?>

//    lookuptable.php?l={idLeague}
    // get league tables
    @GET("lookuptable.php")
    fun getTable(
        @Query("l") idLeague: String):
            Call<TableResponse>

//    lookup_all_teams.php?id={idLeague}
    // get league teams
    @GET("lookup_all_teams.php")
    fun getTeams(
        @Query("id") idLeague: String):
            Call<TeamsResponse>

//    lookupteam.php?id={idTeam}
    // get team detail
    @GET("lookupteam.php")
    fun getDetailTeam(
        @Query("id") idTeam: String):
            Call<DetailTeamResponse>

//    searchteams.php?t={query}
    // get Search team
    @GET("searchteams.php")
    fun getSearchTeam(
        @Query("t") query: String):
            Call<SearchTeamResponse>

}

object LeagueApi {
    val retrofitService : LeagueApiService by lazy {
        retrofit.create(LeagueApiService::class.java)
    }
}