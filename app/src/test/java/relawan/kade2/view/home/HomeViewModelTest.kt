package relawan.kade2.view.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import relawan.kade2.getOrAwaitValue
import relawan.kade2.repository.Repository

class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel


    private lateinit var repository: Repository


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {

        repository = Repository()


        homeViewModel = HomeViewModel()
    }

    @Test
    fun getLeagueList_test() {

        homeViewModel.getLeagueList()

        val result = homeViewModel.leagueName.getOrAwaitValue()


        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))

        /**
         * TIPS :
         * Untuk Mengetahui result value, uncomment code di bawah ini
         */
//        MatcherAssert.assertThat(result, Matchers.nullValue())

    }
}