package relawan.kade2.view.fixture.search.match

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import relawan.kade2.model.SearchMatch
import relawan.kade2.repository.Repository
import relawan.kade2.repository.SearchMatchRepoCallback

class SearchMatchViewModel(val repository: Repository) : ViewModel() {

    // search liveData
    private val _search = MutableLiveData<List<SearchMatch>>()
    val search: LiveData<List<SearchMatch>>
        get() = _search



    fun getSearchMatch(query: String): LiveData<List<SearchMatch>> {

        return repository.getSearchMatchRepo(query, object : SearchMatchRepoCallback {
            override fun onError() {
                _search.value = null
                Log.d(TAG, "$query error")
            }

            override fun onSuccess(search: List<SearchMatch>) {

                _search.value = search
                Log.d(TAG, "Success: $query ${search.size}")

            }

        })

    }


    companion object {
        private val TAG = SearchMatchViewModel::class.java.simpleName
    }


}