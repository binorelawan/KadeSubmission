package relawan.kade2.view.fixture.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import relawan.kade2.model.Search
import relawan.kade2.repository.Repository
import relawan.kade2.repository.SearchRepoCallback

class SearchViewModel(val repository: Repository) : ViewModel() {

    // search liveData
    private val _search = MutableLiveData<List<Search>>()
    val search: LiveData<List<Search>>
        get() = _search



    fun getSearchMatch(query: String): LiveData<List<Search>> {

        return repository.getSearchMatchRepo(query, object : SearchRepoCallback {
            override fun onError() {
                _search.value = null
                Log.d(TAG, "$query error")
            }

            override fun onSuccess(search: List<Search>) {

                _search.value = search
                Log.d(TAG, "Success: $query ${search.size}")

            }

        })

    }


    companion object {
        private val TAG = SearchViewModel::class.java.simpleName
    }


}