package relawan.kade2.view.detail.team

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import relawan.kade2.getOrAwaitValue
import relawan.kade2.model.SearchTeam
import relawan.kade2.model.Teams
import relawan.kade2.repository.Repository

class DetailTeamViewModelTest {

    private lateinit var detailTeamViewModel: DetailTeamViewModel

    private lateinit var repository: Repository

    private lateinit var teams: Teams

    private var search: SearchTeam? = null

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {

        repository = Repository()

        teams = Teams(0L, "4328", "133604", "1892", "England",
            null, "Emirates Stadium", "Arsenal", null,
            null)

        /**
         * search = null, karena hanya melakukan test dengan parameter teams.idTeam
         */
        search = null

        detailTeamViewModel = DetailTeamViewModel(context = null, teams = teams, search = null, repository = repository)
    }

    @Test
    fun getDetailTeam_test() {

        detailTeamViewModel.getDetailTeam(teams.idTeam.toString())

        val result = detailTeamViewModel.detailTeam.getOrAwaitValue()

        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))

        /**
         * TIPS :
         * Untuk Mengetahui result value, uncomment code di bawah ini
         */
//        MatcherAssert.assertThat(result, Matchers.nullValue())
    }
}