package relawan.kade2.view.fixture.teams

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import relawan.kade2.getOrAwaitValue
import relawan.kade2.model.League
import relawan.kade2.repository.Repository

class TeamsViewModelTest {

    private lateinit var teamsViewModel: TeamsViewModel

    private lateinit var repository: Repository

    private lateinit var league: League

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {

        repository = Repository()

        league = League(idLeague ="4328", strLeague = "English Premier League",strSport = "Soccer", strLeagueAlternate = "Premier League")

        teamsViewModel = TeamsViewModel(league, repository)

    }

    @Test
    fun getTeams_test() {

        teamsViewModel.getTeams(league.idLeague.toString())

        val result = teamsViewModel.teams.getOrAwaitValue()

        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))

        /**
         * TIPS :
         * Untuk Mengetahui result value, uncomment code di bawah ini
         */
//        MatcherAssert.assertThat(result, Matchers.nullValue())
    }
}