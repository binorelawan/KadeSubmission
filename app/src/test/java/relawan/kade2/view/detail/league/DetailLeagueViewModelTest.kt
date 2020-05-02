package relawan.kade2.view.detail.league

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import relawan.kade2.getOrAwaitValue
import relawan.kade2.model.League
import relawan.kade2.repository.Repository

class DetailLeagueViewModelTest {

    private lateinit var detailLeagueViewModel: DetailLeagueViewModel

    private lateinit var repository: Repository

    private lateinit var league: League

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {

        repository = Repository()

        league = League(idLeague ="4328", strLeague = "English Premier League",strSport = "Soccer", strLeagueAlternate = "Premier League")

        detailLeagueViewModel = DetailLeagueViewModel(league, repository)
    }

    @Test
    fun getDetailLeague_test() {

        detailLeagueViewModel.getDetailLeague(league.idLeague.toString())

        val result = detailLeagueViewModel.detail.getOrAwaitValue()

        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))

        /**
         * TIPS :
         * Untuk Mengetahui result value, uncomment code di bawah ini
         */
//        MatcherAssert.assertThat(result, Matchers.nullValue())
    }

    @Test
    fun getTableLeague_test() {

        detailLeagueViewModel.getTableLeague(league.idLeague.toString())

        val result = detailLeagueViewModel.table.getOrAwaitValue()

        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))

        /**
         * TIPS :
         * Untuk Mengetahui result value, uncomment code di bawah ini
         */
//        MatcherAssert.assertThat(result, Matchers.nullValue())
    }
}