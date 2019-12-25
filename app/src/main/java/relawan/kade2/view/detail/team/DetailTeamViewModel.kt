package relawan.kade2.view.detail.team

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import relawan.kade2.R
import relawan.kade2.database.database
import relawan.kade2.model.DetailTeam
import relawan.kade2.model.SearchTeam
import relawan.kade2.model.Teams
import relawan.kade2.repository.DetailTeamRepoCallback
import relawan.kade2.repository.Repository

class DetailTeamViewModel(val context: Context?, val teams: Teams?, val search: SearchTeam?, val repository: Repository) : ViewModel() {

    private val _event = MutableLiveData<String>()
    val event: LiveData<String>
        get() = _event

    // detail team liveData
    private val _detailTeam = MutableLiveData<List<DetailTeam>>()
    val detailTeam: LiveData<List<DetailTeam>>
        get() = _detailTeam

    init {

        _event.value = teams?.idTeam ?: search?.idTeam

        getDetailTeam(event.value.toString())
    }

    private fun getDetailTeam(idTeam: String) {

        repository.getDetailTeamRepo(idTeam, object : DetailTeamRepoCallback {
            override fun onError() {
                Log.d(TAG, "error")
            }

            override fun onSuccess(detailTeam: List<DetailTeam>) {
                _detailTeam.value = detailTeam
                Log.d(TAG, "Success: ${detailTeam.size}")
            }

        })
    }

    fun insertFavorite(data: DetailTeam?) {
        try {
            context?.database?.use {
                insert(
                    Teams.TABLE_FAVORITE_TEAM,
                    Teams.ID_LEAGUE to data?.idLeague,
                    Teams.ID_TEAM to data?.idTeam,
                    Teams.INT_FORMED_YEAR to data?.intFormedYear,
                    Teams.STR_COUNTRY to data?.strCountry,
                    Teams.STR_DESCRIPTION_EN to data?.strDescriptionEN,
                    Teams.STR_STADIUM to data?.strStadium,
                    Teams.STR_TEAM to data?.strTeam,
                    Teams.STR_TEAM_BADGE to data?.strTeamBadge,
                    Teams.STR_TEAM_BANNER to data?.strTeamBanner
                )
            }
            Toast.makeText(context, context?.getString(R.string.add_to_favorite), Toast.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "addFavorite = ${e.message}")
        }
    }

    fun deleteFavorite(data: DetailTeam?) {
        try {
            context?.database?.use {
                delete(
                    Teams.TABLE_FAVORITE_TEAM,
                    Teams.ID_LEAGUE+" ={id}",
                    "id" to data?.idLeague.toString()
                )
            }
            Toast.makeText(context, context?.getString(R.string.remove_from_favorite), Toast.LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "removeFavorite = ${e.message}")
        }
    }

    companion object {
        private val TAG = DetailTeamViewModel::class.java.simpleName
    }
}