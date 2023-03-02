package com.example.tinw.test.tests

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.people.MainActivity
import org.junit.Test

class VoiceCallScreenTest {

    @Test
    fun checkDummyVoiceScreenText() {
        ActivityScenario.launch(MainActivity::class.java).use {
            // all calls can be hidden behind the methods from the Helper,
            // but for the sake of time i'm not spawning these entities
            onView(withText("Cat")).check(matches(isDisplayed())).perform(ViewActions.click())
            onView(withContentDescription("Make a voice call (dummy)"))
                .check(matches(isDisplayed())).perform(ViewActions.click())

            onView(withText("Cat")).check(matches(isDisplayed()))
            onView(withText("This is a dummy voice call screen.")).check(matches(isDisplayed()))
        }
    }
}