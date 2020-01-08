package kai.schieren.android_eksamen_tictactoe


import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule

import androidx.test.runner.AndroidJUnit4


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class InstrumentedTests {

    @Rule @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun shouldEnterName(){
        val correctName = "Elon Musk"
        onView(withId(R.id.p1_insert)).perform(typeTextIntoFocusedView("Elon Musk"))
        onView(withId(R.id.p1_insert)).check(matches(withText(correctName)))
    }

    @Test
    @Throws(Exception::class)
    fun shouldEnterGameWithTwoPlayers(){
        val player1 = "Flacko"
        val player2 = "Felicia"

        onView(withId(R.id.p1_insert)).perform(typeTextIntoFocusedView(player1))
        onView(withId(R.id.p2_insert)).perform(click())
        onView(withId(R.id.p2_insert)).perform(typeTextIntoFocusedView(player2))
        onView(withId(R.id.play_btn)).perform(click())
    }

    @Test
    fun shouldPlayUntilPlayerWins(){
        val correctWinner = "Flacko WON!"

        shouldEnterGameWithTwoPlayers()
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btn6)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.btn7)).perform(click())
        onView(withId(R.id.btn4)).perform(click())

        onView(withId(R.id.winnerAnnouncement)).check(matches(withText(correctWinner)))

    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("kai.schieren.android_eksamen_tictactoe", appContext.packageName)
    }
}
