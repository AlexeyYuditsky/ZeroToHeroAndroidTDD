package ru.easycode.zerotoheroandroidtdd.core.navigation

import androidx.fragment.app.Fragment

data class PopScreen(
    private val fragmentClass: Class<out Fragment>
) : Screen.PopBottomSheet(fragmentClass)