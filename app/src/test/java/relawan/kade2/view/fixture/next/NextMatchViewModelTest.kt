package relawan.kade2.view.fixture.next

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.apache.maven.model.Repository
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import relawan.kade2.getOrAwaitValue
import relawan.kade2.model.League

class NextMatchViewModelTest {

    private lateinit var nextMatchViewModel: NextMatchViewModel

    private lateinit var repository: Repository

    private lateinit var league: League

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {

        repository = Repository()

        league = League(idLeague ="4328", strLeague = "English Premier League",strSport = "Soccer", strLeagueAlternate = "Premier League")

        nextMatchViewModel = NextMatchViewModel(league)
    }

    @Test
    fun getNextMatch_text() {

        nextMatchViewModel.getNextMatch(league.idLeague.toString())

        val result = nextMatchViewModel.nextMatch.getOrAwaitValue()

        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))

        /**
         * TIPS :
         * Untuk Mengetahui result value, uncomment code di bawah ini
         */
//        MatcherAssert.assertThat(result, Matchers.nullValue())
    }
}