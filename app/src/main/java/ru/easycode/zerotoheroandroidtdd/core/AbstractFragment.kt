package ru.easycode.zerotoheroandroidtdd.core

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import ru.easycode.zerotoheroandroidtdd.list.ListViewModel

abstract class AbstractFragment<B : ViewBinding>(@LayoutRes id: Int) : Fragment(id) {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    protected abstract fun bind(view: View): B

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}