package relawan.kade2.view.detail.match

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import relawan.kade2.database.database
import relawan.kade2.model.DetailMatch
import relawan.kade2.model.Match
import relawan.kade2.model.Search
import relawan.kade2.network.LeagueApi

class DetailMatchViewModel(val context: Context?, detail: Match?, search: Search?, application: Application) : AndroidViewModel(application) {

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


    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        _idDetail.value = detail
        _idSearch.value = search

        _event.value = idDetail.value?.idEvent ?: idSearch.value?.idEvent

        getDetailMatch()
    }

    private fun getDetailMatch() {

        viewModelScope.launch {
            val getDetailMatchDeferred = event.value?.let { LeagueApi.retrofitService.getDetailMatchAsync(it) }

            try {

                val listDetailMatch = getDetailMatchDeferred?.await()
                _detail.value = listDetailMatch?.events
                Log.d(TAG, "success")

            } catch (e: Exception) {

                Log.d(TAG, e.message!!)
            }
        }



    }

    fun insertFavorite(data: DetailMatch?) {
        try {
            context?.database?.use {
                insert(
                    Match.TABLE_FAVORITE,
                    Match.ID_EVENT to data?.idEvent,
                    Match.DATE_EVENT to data?.dateEvent,
                    Match.STR_TIME to data?.strTime,
                    Match.ID_HOME_TEAM to data?.idHomeTeam,
                    Match.ID_AWAY_TEAM to data?.idAwayTeam,
                    Match.STR_HOME_TEAM to data?.strHomeTeam,
                    Match.STR_AWAY_TEAM to data?.strAwayTeam,
                    Match.INT_HOME_SCORE to data?.intHomeScore,
                    Match.INT_AWAY_SCORE to data?.intAwayScore,
                    Match.STR_HOME_GOAL_DETAILS to data?.strHomeGoalDetails,
                    Match.STR_AWAY_GOAL_DETAILS to data?.strAwayGoalDetails,
                    Match.STR_HOME_YELLOW_CARDS to data?.strHomeYellowCards,
                    Match.STR_AWAY_YELLOW_CARDS to data?.strAwayYellowCards,
                    Match.STR_HOME_RED_CARDS to data?.strHomeRedCards,
                    Match.STR_AWAY_RED_CARDS to data?.strAwayRedCards
                )
            }
            Toast.makeText(context, "Add To Favorite", Toast.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "addFavorite = ${e.message}")
        }
    }

    fun deleteFavorite(data: DetailMatch?){
        try {
            context?.database?.use {
                delete(
                    Match.TABLE_FAVORITE,
                    Match.ID_EVENT+" ={id}",
                    "id" to data?.idEvent.toString()

                )
            }
            Toast.makeText(context, "Remove From Favorite", Toast.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "removeFavorite = ${e.message}")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    companion object {
        private val TAG = DetailMatchViewModel::class.java.simpleName
    }

}