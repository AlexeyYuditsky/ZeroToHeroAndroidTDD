package ru.easycode.zerotoheroandroidtdd.core

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class AbstractBottomSheetDialog<B : ViewBinding>(@LayoutRes id: Int) :
    BottomSheetDialogFragment(id) {

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

    protected fun hideKeyboard() {
        (requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(view?.windowToken, 0)
    }

}