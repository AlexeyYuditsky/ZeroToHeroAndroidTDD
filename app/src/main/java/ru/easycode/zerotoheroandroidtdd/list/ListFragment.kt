package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.core.log
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding

class ListFragment : AbstractFragment<FragmentListBinding>(R.layout.fragment_list) {

    private val viewModel by lazy { (activity as ProvideViewModel).viewModel(ListViewModel::class.java) }

    override fun bind(view: View): FragmentListBinding = FragmentListBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TextAdapter()
        with(binding) {
            recyclerView.adapter = adapter
            addButton.setOnClickListener { onAddButtonPressed() }
        }

        viewModel.livedata().observe(viewLifecycleOwner) { list -> adapter.submitList(list) }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { viewModel.restore(BundleWrapper.Base(it)) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }

    private fun onAddButtonPressed() = viewModel.create()

}