package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding

class ListFragment : AbstractFragment<FragmentListBinding>(R.layout.fragment_list) {

    private val viewModel by lazy { (activity as ProvideViewModel).viewModel(ListViewModel::class.java) }

    override fun bind(view: View): FragmentListBinding = FragmentListBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener { onAddButtonPressed() }
    }

    private fun onAddButtonPressed() = viewModel.create()

}