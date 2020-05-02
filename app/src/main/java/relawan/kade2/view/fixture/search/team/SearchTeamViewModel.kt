package relawan.kade2.view.fixture.search.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import relawan.kade2.model.SearchTeam
import relawan.kade2.repository.Repository
import relawan.kade2.repository.SearchTeamRepoCallback

class SearchTeamViewModel(val repository: Repository) : ViewModel() {

    // search liveData
    private val _search = MutableLiveData<List<SearchTeam>>()
    val search: LiveData<List<SearchTeam>>
        get() = _search

    fun getSearchTeam(query: String): LiveData<List<SearchTeam>> {

        return repository.getSearchTeamRepo(query, object : SearchTeamRepoCallback {
            override fun onError() {
                _search.value = null
                Log.d(TAG, "$query error")
            }

            override fun onSuccess(search: List<SearchTeam>) {

                _search.value = search
                Log.d(TAG, "Success: $query ${search.size}")
            }

        })
    }

    companion object {
        private val TAG = SearchTeamViewModel::class.java.simpleName
    }
}