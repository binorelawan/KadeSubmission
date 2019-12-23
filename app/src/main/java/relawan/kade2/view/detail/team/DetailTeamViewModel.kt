package relawan.kade2.view.detail.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import relawan.kade2.model.DetailTeam
import relawan.kade2.model.Teams
import relawan.kade2.repository.DetailTeamRepoCallback
import relawan.kade2.repository.Repository

class DetailTeamViewModel(val teams: Teams, val repository: Repository) : ViewModel() {

    // detail team liveData
    private val _detailTeam = MutableLiveData<List<DetailTeam>>()
    val detailTeam: LiveData<List<DetailTeam>>
        get() = _detailTeam

    init {

        getDetailTeam(teams.idTeam.toString())
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

    companion object {
        private val TAG = DetailTeamViewModel::class.java.simpleName
    }
}