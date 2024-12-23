package ru.easycode.zerotoheroandroidtdd.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.R

interface Screen {

    fun apply(fragmentManager: FragmentManager)

    abstract class ShowFragment(
        private val fragmentClass: Class<out Fragment>
    ) : Screen {

        override fun apply(fragmentManager: FragmentManager) {
            fragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.rootContainer, fragmentClass.getDeclaredConstructor().newInstance())
                .commit()
        }

    }

    class PopFragment : Screen {
        override fun apply(fragmentManager: FragmentManager) {
            fragmentManager.popBackStack()
        }
    }

}

