package ru.easycode.zerotoheroandroidtdd.add

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.AbstractBottomSheetDialog
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentAddBinding

class AddFragment : AbstractBottomSheetDialog<FragmentAddBinding>(R.layout.fragment_add) {

    private val viewModel by lazy { (activity as ProvideViewModel).viewModel(AddViewModel::class.java) }

    override fun bind(view: View) = FragmentAddBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) { viewModel.comeback() }

        binding.saveButton.setOnClickListener {
            hideKeyboard()
            viewModel.add(binding.addInputEditText.text.toString())
        }
    }

}