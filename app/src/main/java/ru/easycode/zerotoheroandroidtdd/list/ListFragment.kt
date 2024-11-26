package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding

class ListFragment : AbstractFragment<FragmentListBinding>(R.layout.fragment_list) {

    private val viewModel by lazy { (activity as ProvideViewModel).viewModel(ListViewModel::class.java) }
    private val adapter = TextAdapter()

    override fun bind(view: View): FragmentListBinding = FragmentListBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.adapter = adapter
            addButton.setOnClickListener { onAddButtonPressed() }
        }

        viewModel.init()
        viewModel.livedata().observe(viewLifecycleOwner, ::list)
    }

    private fun onAddButtonPressed() = viewModel.create()

    private fun list(list: List<String>) = adapter.submitList(list)

}