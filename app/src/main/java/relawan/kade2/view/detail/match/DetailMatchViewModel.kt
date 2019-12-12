package relawan.kade2.view.detail.match

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import relawan.kade2.database.database
import relawan.kade2.model.DetailMatch
import relawan.kade2.model.Match
import relawan.kade2.model.Search
import relawan.kade2.repository.DetailMatchRepoCallback
import relawan.kade2.repository.Repository

class DetailMatchViewModel(val context: Context?, val detail: Match?, val search: Search?) : ViewModel() {

    private val _event = MutableLiveData<String>()
    val event: LiveData<String>
        get() = _event

    private val _detailMatch = MutableLiveData<List<DetailMatch>>()
    val detailMatch: LiveData<List<DetailMatch>>
        get() = _detailMatch


    private val repository = Repository()


    init {

        _event.value = detail?.idEvent ?: search?.idEvent

        getDetailMatch(event.value.toString())
    }

    fun getDetailMatch(idEvent: String) {


        repository.getDetailMatchRepo(idEvent, object : DetailMatchRepoCallback {
            override fun onError() {
                Log.d(TAG, "error")

            }

            override fun onSuccess(detailMatch: List<DetailMatch>) {

                _detailMatch.value = detailMatch
                Log.d(TAG, "Success: ${detailMatch.size}")
            }

        })



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

    companion object {
        private val TAG = DetailMatchViewModel::class.java.simpleName
    }

}