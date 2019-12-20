package relawan.kade2.view.detail.match

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import relawan.kade2.getOrAwaitValue
import relawan.kade2.model.Match
import relawan.kade2.model.Search
import relawan.kade2.repository.Repository

class DetailMatchViewModelTest {

    private lateinit var detailMatchViewModel: DetailMatchViewModel

    private lateinit var repository: Repository

    private lateinit var detail: Match

    private var search: Search? = null

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {

        repository = Repository()

        detail = Match(0L, "441613", "2014-12-29", "20:00:00+00:00",
            "133602", "133614", "Liverpool", "Swansea",
            "4", "",
            "69':Own Jonjo Shelvey;61': Adam Lallana;51': Adam Lallana;33': Alberto Moreno;",
            "52': Gylfi Sigurdsson;",
            "49': Martin Skrtel;", "",
            "", "")

        /**
         * search = null, karena hanya melakukan test dengan parameter detail.idEvent
         */
        search = null

        detailMatchViewModel = DetailMatchViewModel(context = null, detail = detail, search = null, repository = repository)

    }


    @Test
    fun getDetailMatch_test() {

        detailMatchViewModel.getDetailMatch(detail.idEvent.toString())

        val result = detailMatchViewModel.detailMatch.getOrAwaitValue()

        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))

        /**
         * TIPS :
         * Untuk Mengetahui result value, uncomment code di bawah ini
         */
//        MatcherAssert.assertThat(result, Matchers.nullValue())
    }
}