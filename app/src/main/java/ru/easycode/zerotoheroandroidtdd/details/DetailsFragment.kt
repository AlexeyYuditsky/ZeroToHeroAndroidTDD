package ru.easycode.zerotoheroandroidtdd.details

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.core.ui.AbstractBottomSheetDialog
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentDetailsBinding

class DetailsFragment :
    AbstractBottomSheetDialog<FragmentDetailsBinding>(R.layout.fragment_details) {

    private val viewModel by lazy { (activity as ProvideViewModel).viewModel(DetailsViewModel::class.java) }

    override fun bind(view: View) = FragmentDetailsBinding.bind(view)

    override fun comeback() = viewModel.comeback()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemId = requireArguments().getLong(KEY)

        with(binding) {
            updateButton.setOnClickListener {
                viewModel.update(itemId, itemEditText.text.toString())
            }
            deleteButton.setOnClickListener { viewModel.delete(itemId) }
        }

        viewModel.liveData().observe(viewLifecycleOwner, ::itemTitle)
        viewModel.init(itemId)
    }

    private fun itemTitle(title: String) = with(binding) {
        itemTextView.text = title
        itemEditText.setText(title)
        itemEditText.setSelection(title.length)
    }

    companion object {
        private const val KEY = "text"
        fun newInstance(itemId: Long): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundleOf(KEY to itemId)
            }
        }
    }

}