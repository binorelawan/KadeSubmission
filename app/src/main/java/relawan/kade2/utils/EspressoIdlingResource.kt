package relawan.kade2.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource


object EspressoIdlingResource {

    private const val resource = "GLOBAL"
    private val countingIdlingResource = CountingIdlingResource(resource)

    val idlingResource: IdlingResource
        get() = countingIdlingResource

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        countingIdlingResource.decrement()
    }
}