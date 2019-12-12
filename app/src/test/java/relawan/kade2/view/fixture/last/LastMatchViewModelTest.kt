package relawan.kade2.view.fixture.last

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.apache.maven.model.Repository
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import relawan.kade2.getOrAwaitValue
import relawan.kade2.model.League

class LastMatchViewModelTest {

    private lateinit var lastMatchViewModel: LastMatchViewModel

    private lateinit var repository: Repository

    private lateinit var league: League

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {

        repository = Repository()

        league = League(idLeague ="4328", strLeague = "English Premier League",strSport = "Soccer", strLeagueAlternate = "Premier League")

        lastMatchViewModel = LastMatchViewModel(league)
    }

    @Test
    fun getLastMatch_test() {

        lastMatchViewModel.getLastMatch(league.idLeague.toString())

        val result = lastMatchViewModel.lastMatch.getOrAwaitValue()

        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))

        /**
         * TIPS :
         * Untuk Mengetahui result value, uncomment code di bawah ini
         */
//        MatcherAssert.assertThat(result, Matchers.nullValue())
    }
}