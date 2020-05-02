package relawan.kade2.view.detail.match

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import relawan.kade2.R
import relawan.kade2.database.FavoriteMatch
import relawan.kade2.database.FavoriteMatchDao
import relawan.kade2.database.database
import relawan.kade2.model.DetailMatch
import relawan.kade2.model.Match
import relawan.kade2.model.SearchMatch
import relawan.kade2.repository.DetailMatchRepoCallback
import relawan.kade2.repository.FavoriteMatchRepository
import relawan.kade2.repository.Repository

class DetailMatchViewModel(val database: FavoriteMatchDao, val detail: Match?, val search: SearchMatch?, val favorite: String?, val repository: Repository) : ViewModel() {

    private val _event = MutableLiveData<String>()
    val event: LiveData<String>
        get() = _event

    // detail match liveData
    private val _detailMatch = MutableLiveData<List<DetailMatch>>()
    val detailMatch: LiveData<List<DetailMatch>>
        get() = _detailMatch

    private val favoriteRepository: FavoriteMatchRepository

    val isCheck: LiveData<Boolean>

    init {

        _event.value = detail?.idEvent ?: search?.idEvent ?: favorite

        getDetailMatch(event.value.toString())

        favoriteRepository = FavoriteMatchRepository(database)


        /**The isFavoriteMatch method returns a LiveData from querying the database. The
         * method can return null in two cases: when the database query is running and if no records
         * are found. In these cases isPlanted is false. If a record is found then isPlanted is
         * true.
         **/
        val check = favoriteRepository.isFavoriteMatch(event.value.toString())
        isCheck = Transformations.map(check) {
            it != null
        }
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

    fun insert(favoriteMatch: FavoriteMatch) = viewModelScope.launch {
        favoriteRepository.insert(favoriteMatch)


    }

    fun delete(favoriteMatch: FavoriteMatch) = viewModelScope.launch {
        favoriteRepository.delete(favoriteMatch)


    }



    companion object {
        private val TAG = DetailMatchViewModel::class.java.simpleName
    }

}