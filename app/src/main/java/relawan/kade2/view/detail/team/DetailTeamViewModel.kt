package relawan.kade2.view.detail.team

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import relawan.kade2.R
import relawan.kade2.database.FavoriteTeam
import relawan.kade2.database.FavoriteTeamDao
import relawan.kade2.database.database
import relawan.kade2.model.DetailTeam
import relawan.kade2.model.SearchTeam
import relawan.kade2.model.Teams
import relawan.kade2.repository.DetailTeamRepoCallback
import relawan.kade2.repository.FavoriteTeamRepository
import relawan.kade2.repository.Repository

class DetailTeamViewModel(val database: FavoriteTeamDao, val teams: Teams?, val search: SearchTeam?, val favorite: String?, val repository: Repository) : ViewModel() {

    private val _event = MutableLiveData<String>()
    val event: LiveData<String>
        get() = _event

    // detail team liveData
    private val _detailTeam = MutableLiveData<List<DetailTeam>>()
    val detailTeam: LiveData<List<DetailTeam>>
        get() = _detailTeam

    private val favoriteRepository: FavoriteTeamRepository

    val isCheck: LiveData<Boolean>

    init {

        _event.value = teams?.idTeam ?: search?.idTeam ?:favorite

        getDetailTeam(event.value.toString())

        favoriteRepository = FavoriteTeamRepository(database)


        /**The isFavoriteMatch method returns a LiveData from querying the database. The
         * method can return null in two cases: when the database query is running and if no records
         * are found. In these cases isPlanted is false. If a record is found then isPlanted is
         * true.
         **/
        val check = favoriteRepository.isFavoriteTeam(event.value.toString())
        isCheck = Transformations.map(check) {
            it != null
        }
    }

    fun getDetailTeam(idTeam: String) {

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

    fun insert(favoriteTeam: FavoriteTeam) = viewModelScope.launch {
        favoriteRepository.insert(favoriteTeam)
    }

    fun delete(favoriteTeam: FavoriteTeam) = viewModelScope.launch {
        favoriteRepository.delete(favoriteTeam)
    }

    companion object {
        private val TAG = DetailTeamViewModel::class.java.simpleName
    }
}