package relawan.kade2


import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import relawan.kade2.utils.EspressoIdlingResource


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun waitForNetworkCallToCompleteAndCheckViews(){

        onView(withId(R.id.league_list)).check(matches(isDisplayed()))
        onView(withId(R.id.search_menu)).perform(ViewActions.click())
        onView(ViewMatchers.isAssignableFrom(EditText::class.java)).perform(
            ViewActions.typeText("Arsenal"),
            ViewActions.pressImeActionButton()
        )
        onView(withId(R.id.match_list)).check(matches(isDisplayed()))
    }
}