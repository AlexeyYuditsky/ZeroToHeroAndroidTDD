package ru.easycode.zerotoheroandroidtdd.main

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

interface Screen {

    fun show(supportFragmentManager: FragmentManager, containerId: Int)

    abstract class Replace(
        private val fragmentClass: Class<out Fragment>
    ) : Screen {

        override fun show(
            supportFragmentManager: FragmentManager,
            containerId: Int
        ) {
            supportFragmentManager.beginTransaction()
                .replace(containerId, fragmentClass.getDeclaredConstructor().newInstance())
                .commit()
        }

    }

    abstract class Add(
        private val fragmentClass: Class<out Fragment>
    ) : Screen {

        override fun show(
            supportFragmentManager: FragmentManager,
            containerId: Int
        ) {
            val addFragment = fragmentClass.getDeclaredConstructor().newInstance() as DialogFragment
            addFragment.show(supportFragmentManager, "AddBottomSheet")
        }

    }

    object PopBottomSheet : Screen {

        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            val fragment =
                supportFragmentManager.findFragmentByTag("AddBottomSheet") as BottomSheetDialogFragment
            fragment.dismiss()
        }

    }

}