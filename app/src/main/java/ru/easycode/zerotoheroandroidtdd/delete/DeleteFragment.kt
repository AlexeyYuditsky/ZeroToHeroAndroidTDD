package ru.easycode.zerotoheroandroidtdd.delete

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.core.ui.AbstractBottomSheetDialog
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentDeleteBinding

class DeleteFragment : AbstractBottomSheetDialog<FragmentDeleteBinding>(R.layout.fragment_delete) {

    private val viewModel by lazy { (activity as ProvideViewModel).viewModel(DeleteViewModel::class.java) }

    override fun bind(view: View) = FragmentDeleteBinding.bind(view)

    override fun comeback() = viewModel.comeback()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemId = requireArguments().getLong(KEY)
        binding.deleteButton.setOnClickListener { viewModel.delete(itemId) }
        viewModel.liveData().observe(viewLifecycleOwner, ::itemTitle)
        viewModel.init(itemId)
    }

    private fun itemTitle(title: String) {
        binding.itemTitleTextView.text = title
    }

    companion object {
        private const val KEY = "text"
        fun newInstance(itemId: Long): DeleteFragment {
            return DeleteFragment().apply {
                arguments = bundleOf(KEY to itemId)
            }
        }
    }

}