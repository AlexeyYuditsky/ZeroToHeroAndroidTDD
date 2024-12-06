package ru.easycode.zerotoheroandroidtdd.details

import ru.easycode.zerotoheroandroidtdd.core.navigation.Screen

class DetailsScreen(itemId: Long) : Screen.DeleteBottomSheet(DetailsFragment::class.java, itemId)