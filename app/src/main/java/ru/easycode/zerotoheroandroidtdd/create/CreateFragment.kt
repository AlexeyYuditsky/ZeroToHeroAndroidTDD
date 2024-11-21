package ru.easycode.zerotoheroandroidtdd.create

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateBinding

class CreateFragment : AbstractFragment<FragmentCreateBinding>(R.layout.fragment_create) {

    private val viewModel by lazy { (activity as ProvideViewModel).viewModel(CreateViewModel::class.java) }

    override fun bind(view: View) = FragmentCreateBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                viewModel.comeback()
            }

            inputEditText.addTextChangedListener { text ->
                createButton.isEnabled = text.toString().length >= 3
            }

            createButton.setOnClickListener {
                hideKeyboard()
                viewModel.add(inputEditText.text.toString())
            }
        }
    }

}