package ru.easycode.zerotoheroandroidtdd.delete

import ru.easycode.zerotoheroandroidtdd.core.navigation.Screen

class DeleteScreen(itemId: Long) : Screen.DeleteBottomSheet(DeleteFragment::class.java, itemId)