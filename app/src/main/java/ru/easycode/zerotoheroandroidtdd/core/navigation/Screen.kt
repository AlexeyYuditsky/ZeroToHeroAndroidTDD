package ru.easycode.zerotoheroandroidtdd.core.navigation

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.delete.DeleteFragment

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

    abstract class AddBottomSheet(
        private val fragmentClass: Class<out Fragment>
    ) : Screen {

        override fun show(
            supportFragmentManager: FragmentManager,
            containerId: Int
        ) {
            val addFragment = fragmentClass.getDeclaredConstructor().newInstance() as DialogFragment
            addFragment.show(supportFragmentManager, fragmentClass.simpleName)
        }

    }

    abstract class DeleteBottomSheet(
        private val fragmentClass: Class<out Fragment>,
        private val itemId: Long
    ) : Screen {

        override fun show(
            supportFragmentManager: FragmentManager,
            containerId: Int
        ) {
            val deleteBottomSheet = DeleteFragment.newInstance(itemId)
            deleteBottomSheet.show(supportFragmentManager, fragmentClass.simpleName)
        }

    }

    abstract class PopBottomSheet(
        private val fragmentClass: Class<out Fragment>
    ) : Screen {

        override fun show(
            supportFragmentManager: FragmentManager,
            containerId: Int
        ) {
            val fragment =
                supportFragmentManager.findFragmentByTag(fragmentClass.simpleName) as BottomSheetDialogFragment
            fragment.dismiss()
        }

    }

}