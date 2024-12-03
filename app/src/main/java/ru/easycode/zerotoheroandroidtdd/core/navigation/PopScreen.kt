package ru.easycode.zerotoheroandroidtdd.core.navigation

import androidx.fragment.app.Fragment

class PopScreen(fragmentClass: Class<out Fragment>) : Screen.PopBottomSheet(fragmentClass)