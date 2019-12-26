package relawan.kade2.view.fixture.search.match

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import relawan.kade2.getOrAwaitValue
import relawan.kade2.repository.Repository

class SearchMatchViewModelTest {

    private lateinit var searchMatchViewModel: SearchMatchViewModel

    private lateinit var repository: Repository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {

        repository = Repository()

        searchMatchViewModel = SearchMatchViewModel(repository)
    }

    @Test
    fun getSearchMatch_test() {

        val query = "Arsenal"

        searchMatchViewModel.getSearchMatch(query)

        val result = searchMatchViewModel.search.getOrAwaitValue()

        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))

        /**
         * TIPS :
         * Untuk Mengetahui result value, uncomment code di bawah ini
         */
//        MatcherAssert.assertThat(result, Matchers.nullValue())
    }
}