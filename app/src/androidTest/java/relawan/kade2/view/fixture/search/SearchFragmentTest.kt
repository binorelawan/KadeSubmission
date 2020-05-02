package relawan.kade2.view.fixture.search

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import relawan.kade2.MainActivity
import relawan.kade2.R
import relawan.kade2.utils.EspressoIdlingResource

class SearchFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun searchMatch(){


        onView(withId(R.id.search_menu)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Arsenal"), pressImeActionButton())
        onView(withId(R.id.match_list)).check(matches(isDisplayed()))

    }



}