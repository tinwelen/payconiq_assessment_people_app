package com.example.tinw.test.helpers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.core.AllOf.allOf

fun people (f: PeopleHelper.() -> Unit): PeopleHelper =
    PeopleHelper().also(f)

class PeopleHelper {
    val longMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
            "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
            "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

    val catName = "Cat"
    val dogName = "Dog"
    val parrotName = "Parrot"
    val sheepName = "Sheep"

    val catMessage = "Hello, cat!"

    val catReply = "Meow"
    val dogReply = "Woof woof!!"
    val sheepReply = "Look at me!"

    fun openChatByName(name: String) {
        onView(allOf(withText(name), isDisplayed())).perform(click())
    }

    fun checkMessageByText(reply: String) {
        onView(withText(reply)).check(matches(isDisplayed()))
    }

    fun typeMessage(message: String) {
        onView(withHint("Type a message…")).perform(typeText(message))
    }

    fun sendMessage() {
        onView(withContentDescription("Send")).perform(click())
    }

    fun tapButtonVoiceCall() {
        onView(withContentDescription("Make a voice call (dummy)")).perform(click())
    }
}