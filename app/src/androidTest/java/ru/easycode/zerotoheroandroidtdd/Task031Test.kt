package ru.easycode.zerotoheroandroidtdd

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
<<<<<<<< HEAD:app/src/androidTest/java/ru/easycode/zerotoheroandroidtdd/Task030Test.kt
class Task030Test {
========
class Task031Test {
>>>>>>>> refs/remotes/upstream/task/031-click-me:app/src/androidTest/java/ru/easycode/zerotoheroandroidtdd/Task031Test.kt

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
<<<<<<<< HEAD:app/src/androidTest/java/ru/easycode/zerotoheroandroidtdd/Task030Test.kt
    fun test_hello_world() {
        composeTestRule.onNodeWithText("Hello World!").assertExists()
========
    fun test_click_me() {
        composeTestRule.onNodeWithText("Click me!").assertHasClickAction()
>>>>>>>> refs/remotes/upstream/task/031-click-me:app/src/androidTest/java/ru/easycode/zerotoheroandroidtdd/Task031Test.kt
    }
}