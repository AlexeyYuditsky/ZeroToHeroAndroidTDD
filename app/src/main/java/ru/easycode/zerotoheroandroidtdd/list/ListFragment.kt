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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            val bundleWrapper = BundleWrapper.Base(savedInstanceState)
            viewModel.restore(bundleWrapper)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener { onAddButtonPressed() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundleWrapper = BundleWrapper.Base(outState)
        viewModel.save(bundleWrapper)
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onPause() {
        super.onPause()
        log("onPause")
    }

    private fun onAddButtonPressed() = viewModel.create()

    override fun onDestroyView() {
        super.onDestroyView()
        log("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

}