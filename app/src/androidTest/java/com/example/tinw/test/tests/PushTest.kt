package com.example.tinw.test.tests

import androidx.test.espresso.Espresso
import androidx.test.rule.GrantPermissionRule
import com.example.tinw.test.helpers.people
import org.junit.Rule
import org.junit.Test

class PushTest {
    @get:Rule
    var permissionRule = GrantPermissionRule.grant(android.Manifest.permission.POST_NOTIFICATIONS)
    // "UiAutomation.grantRuntimePermission() is more robust and should be used instead of 'pm grant'"
    // Added UiAutomator dependency into the project for illustrative reasons, no realisation for now

    //    @Test
    fun checkPushIsNotSentChatScreen() {}

    @Test
    fun checkPushIsSentContactListScreen() {
        people {
            openChatByName(catName)
            typeMessage(catMessage)
            sendMessage()
            Espresso.pressBack()
            // TODO:
            // Thread.sleep(5000)
            // checkPushByText(catReply)
        }
    }

    @Test
    fun checkPushIsSentBackToChatScreen() {
        people {
            openChatByName(catName)
            typeMessage(catMessage)
            sendMessage()
            tapButtonVoiceCall()
            Espresso.pressBack()
            // TODO:
            // checkPushByText(catReply)
        }
    }
}