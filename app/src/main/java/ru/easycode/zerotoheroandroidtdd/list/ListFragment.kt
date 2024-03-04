package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding

class ListFragment : AbstractFragment<FragmentListBinding>() {

    override fun bind(view: View): FragmentListBinding = FragmentListBinding.bind(view)

    private val viewModel = (activity as ProvideViewModel).viewModel(ListViewModel::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener { onAddButtonPressed() }
    }

    private fun onAddButtonPressed() = viewModel.create()
}