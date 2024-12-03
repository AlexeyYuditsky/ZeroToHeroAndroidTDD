package ru.easycode.zerotoheroandroidtdd

import androidx.test.espresso.Espresso
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.easycode.zerotoheroandroidtdd.main.MainActivity

@RunWith(AndroidJUnit4::class)
class Task026Test {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val listPage = ListPage()
    private val addPage = AddPage()
    private val deletePage = DeletePage()

    @Test
    fun test_add() {
        listPage.checkVisibleNow()
        listPage.clickAddButton()

        addPage.checkVisibleNow()
        addPage.inputText("first item in the list")
        addPage.clickSaveButton()
        addPage.checkNotVisibleNow()

        listPage.checkItem(position = 0, text = "first item in the list")

        listPage.clickAddButton()
        addPage.checkVisibleNow()
        addPage.inputText("second item in the list")
        addPage.clickSaveButton()
        addPage.checkNotVisibleNow()
        listPage.checkItem(position = 1, text = "second item in the list")

        activityScenarioRule.scenario.recreate()

        listPage.checkItem(position = 0, text = "first item in the list")
        listPage.checkItem(position = 1, text = "second item in the list")
    }

    @Test
    fun test_delete() {
        test_add()
        listPage.clickItem(1)
        deletePage.checkVisibleNow()
        deletePage.checkText("second item in the list")
        Espresso.pressBack()
        deletePage.checkNotVisibleNow()
        listPage.checkItem(position = 0, text = "first item in the list")
        listPage.checkItem(position = 1, text = "second item in the list")

        listPage.clickItem(0)
        deletePage.checkVisibleNow()
        deletePage.checkText("first item in the list")
        deletePage.clickDeleteButton()
        deletePage.checkNotVisibleNow()

        listPage.checkItem(position = 0, text = "second item in the list")
        activityScenarioRule.scenario.recreate()
        listPage.checkItem(position = 0, text = "second item in the list")
    }
}