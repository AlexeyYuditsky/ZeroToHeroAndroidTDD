package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.core.ui.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding
import ru.easycode.zerotoheroandroidtdd.list.adapter.TextAdapter

class ListFragment : AbstractFragment<FragmentListBinding>(R.layout.fragment_list) {

    private val viewModel by lazy { (activity as ProvideViewModel).viewModel(ListViewModel::class.java) }

    override fun bind(view: View) = FragmentListBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TextAdapter(viewModel)
        with(binding) {
            recyclerView.adapter = adapter
            addButton.setOnClickListener { onAddButtonPressed() }
        }

        viewModel.liveData().observe(viewLifecycleOwner) { list -> adapter.submitList(list) }
        viewModel.init()
    }

    private fun onAddButtonPressed() = viewModel.create()

}