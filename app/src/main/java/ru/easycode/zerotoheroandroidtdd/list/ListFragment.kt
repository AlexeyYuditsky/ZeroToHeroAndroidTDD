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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            val bundleWrapper = BundleWrapper.Base(savedInstanceState)
            viewModel.restore(bundleWrapper)
        }
        parentFragmentManager.setFragmentResultListener("key", this) { key, result ->
            val text = result.getString("key", "")
            viewModel.add(text)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener { onAddButtonPressed() }

        binding.recyclerView.adapter = adapter

        viewModel.livedata().observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundleWrapper = BundleWrapper.Base(outState)
        viewModel.save(bundleWrapper)
    }

    private fun onAddButtonPressed() = viewModel.create()

}