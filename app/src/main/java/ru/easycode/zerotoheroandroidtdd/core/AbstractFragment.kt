package ru.easycode.zerotoheroandroidtdd.core

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class AbstractFragment<T : ViewBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    private var _binding: T? = null
    val binding get() = _binding!!

    abstract fun bind(view: View): T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}