package relawan.kade2.view.fixture.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.apache.maven.model.Repository
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import relawan.kade2.getOrAwaitValue

class SearchViewModelTest {

    private lateinit var searchViewModel: SearchViewModel

    private lateinit var repository: Repository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {

        repository = Repository()

        searchViewModel = SearchViewModel()
    }

    @Test
    fun getSearchMatch_test() {

        val query = "Arsenal"

        searchViewModel.getSearchMatch(query)

        val result = searchViewModel.search.getOrAwaitValue()

        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))

        /**
         * TIPS :
         * Untuk Mengetahui result value, uncomment code di bawah ini
         */
//        MatcherAssert.assertThat(result, Matchers.nullValue())
    }
}