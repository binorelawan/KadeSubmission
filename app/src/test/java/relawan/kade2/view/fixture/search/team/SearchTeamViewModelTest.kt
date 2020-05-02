package relawan.kade2.view.fixture.search.team

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import relawan.kade2.getOrAwaitValue
import relawan.kade2.repository.Repository

class SearchTeamViewModelTest {

    private lateinit var searchTeamViewModel: SearchTeamViewModel

    private lateinit var repository: Repository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {

        repository = Repository()

        searchTeamViewModel = SearchTeamViewModel(repository)
    }

    @Test
    fun getSearchTeam_test() {

        val query = "Arsenal"

        searchTeamViewModel.getSearchTeam(query)

        val result = searchTeamViewModel.search.getOrAwaitValue()

        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))

        /**
         * TIPS :
         * Untuk Mengetahui result value, uncomment code di bawah ini
         */
//        MatcherAssert.assertThat(result, Matchers.nullValue())
    }
}