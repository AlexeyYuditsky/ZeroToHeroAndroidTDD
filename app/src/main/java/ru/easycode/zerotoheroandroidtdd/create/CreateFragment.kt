package ru.easycode.zerotoheroandroidtdd.create

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateBinding

class CreateFragment : AbstractFragment<FragmentCreateBinding>(R.layout.fragment_create) {

    override fun bind(view: View): FragmentCreateBinding = FragmentCreateBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}