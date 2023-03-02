package com.example.tinw.test.tests

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.people.BubbleActivity
import com.example.android.people.MainActivity
import com.example.tinw.test.helpers.people
import org.junit.Test

class ChatScreenTest {

    @Test
    fun checkHeaderInfoIsShown() {
        ActivityScenario.launch(MainActivity::class.java).use {
            people {
                val names = listOf(catName, dogName, parrotName, sheepName)

                for (name in names) {
                    openChatByName(name)
                    onView((withText(name))).check(matches(isDisplayed()))
                    onView(withContentDescription("Profile icon")).check(matches(isDisplayed()))
                    Espresso.pressBack()
                }
            }
        }
    }

    @Test
    fun checkHintMessageIsShown() {
        ActivityScenario.launch<BubbleActivity>(
            Intent(ApplicationProvider.getApplicationContext<Application>(), BubbleActivity::class.java)
                .setAction(Intent.ACTION_VIEW)
                .setData(Uri.parse("https://android.example.com/chat/1"))
        ).use {
            onView((withText("Send me a message"))).check(matches(isDisplayed()))
            onView((withText("I will reply in 5 seconds"))).check(matches(isDisplayed()))
        }
    }

    @Test
    fun checkVoiceCallButtonIsShown() {
        ActivityScenario.launch<BubbleActivity>(
            Intent(ApplicationProvider.getApplicationContext<Application>(), BubbleActivity::class.java)
                .setAction(Intent.ACTION_VIEW)
                .setData(Uri.parse("https://android.example.com/chat/1"))
        ).use {
            onView(withContentDescription("Make a voice call (dummy)")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun checkInputAndSendButtonIsShown() {
        ActivityScenario.launch<BubbleActivity>(
            Intent(ApplicationProvider.getApplicationContext<Application>(), BubbleActivity::class.java)
                .setAction(Intent.ACTION_VIEW)
                .setData(Uri.parse("https://android.example.com/chat/1"))
        ).use {
            onView(withHint("Type a message…")).check(matches(isDisplayed()))
            onView(withContentDescription("Send")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun sendMessage() {
        ActivityScenario.launch(MainActivity::class.java).use {
            people {
                openChatByName(catName)
                typeMessage(catMessage)
                sendMessage()
            }
        }
    }

    @Test
    fun sendMessageCheckReply() {
        ActivityScenario.launch(MainActivity::class.java).use {
            people {
                val names = listOf(
                    listOf(catName, catReply),
                    listOf(dogName, dogReply),
                    listOf(parrotName, ""),
                    listOf(sheepName, sheepReply))

                for (name in names) {
                    val message = "Hello, " + name[0] + "!"
                    val reply: String

                    if (name[0].equals(parrotName)) {
                        reply = message
                    } else {
                        reply = name[1]
                    }

                    openChatByName(name[0])
                    typeMessage(message)
                    sendMessage()

                    Espresso.closeSoftKeyboard()
                    Thread.sleep(5000)
// TODO: fix checking specifically second item found by text for parrot chat -- need to use R.id for the RecyclerView
// The same applies for the case: Empty message can not be sent
                    checkMessageByText(reply)
                    Espresso.pressBack()
                }
            }
        }
    }

    @Test
    fun checkInputTextIsSavedVoiceScreen() {
        ActivityScenario.launch(MainActivity::class.java).use {
            people { openChatByName(catName)
                typeMessage(catMessage)
                tapButtonVoiceCall()
                Espresso.pressBack()
                sendMessage()
                checkMessageByText(catMessage)
            }
        }
    }

    @Test
    fun checkInputTextIsNotSavedContactScreen() {
        ActivityScenario.launch(MainActivity::class.java).use {
            people {
                openChatByName(catName)
                typeMessage(catMessage)
                Espresso.closeSoftKeyboard()
                Espresso.pressBack()
                openChatByName(catName)
                sendMessage()
                onView(withText(catMessage)).check(doesNotExist())
            }
        }
    }

    @Test
    fun checkImageIsShown() {
        ActivityScenario.launch(MainActivity::class.java).use {
            val message = "Hello, Sheep!"

            people {
                openChatByName(sheepName)
                typeMessage(message)
                sendMessage()
                Thread.sleep(5000)
                checkMessageByText(sheepReply)
                onView(withText(sheepReply)).perform(click())
                onView(withContentDescription("Photo")).check(matches(isDisplayed()))
                Espresso.pressBack()
                checkMessageByText(sheepReply)
            }
        }
    }

    //    @Test
    fun checkImageSentFromGoogleKeyboard() {}

    //    @Test
    fun checkMultipleReplyMultipleMessage() {}

    @Test
    fun checkScrollLongChat() {
        ActivityScenario.launch(MainActivity::class.java).use {
            people {
                openChatByName(catName)
                typeMessage(catMessage)
                sendMessage()
                for (i in 1..2) {
                    typeMessage(longMessage)
                    sendMessage()
                }
                Espresso.closeSoftKeyboard()

                // TODO:
                // onView(withId(R.id.messages)).perform(swipeUp())
                // checkMessageByText(catMessage)
            }
        }
    }
}