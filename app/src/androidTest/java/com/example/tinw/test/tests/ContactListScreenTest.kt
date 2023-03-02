package com.example.tinw.test.tests

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.people.MainActivity
import com.example.tinw.test.helpers.people
import org.hamcrest.core.AllOf.allOf
import org.junit.Test

class ContactListScreenTest {

    @Test
    fun checkHeaderIsShown() {
        ActivityScenario.launch(MainActivity::class.java).use {
            onView(withText("People")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun checkPersonalInfoIsShown() {
        ActivityScenario.launch(MainActivity::class.java).use {
            val names = listOf("Cat", "Dog", "Parrot", "Sheep")

            for (name in names) {
                onView(allOf((withText(name)), hasSibling(withContentDescription("Profile icon"))))
                    .check(matches(isDisplayed()));
            }
        }
    }

    @Test
    fun scrollList() {
        ActivityScenario.launch(MainActivity::class.java).use {
            onView(withText("Sheep")).perform(scrollTo())
        }
    }

    @Test
    fun checkHighlightOnFocusIsAvailable() {
        ActivityScenario.launch(MainActivity::class.java).use {
            people { onView(withChild(withText(catName))).check(matches(isFocusable())) }
        }
    }

    @Test
    // Only works if permission was not granted on the device
    fun grantNotificationPermission() {
        ActivityScenario.launch(MainActivity::class.java).use {
            onView(withText("Grant")).perform(click())
        }
    }
}