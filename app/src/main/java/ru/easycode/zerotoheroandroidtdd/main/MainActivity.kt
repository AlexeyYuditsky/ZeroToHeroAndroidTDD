package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProvideViewModel {

    private val viewModel by lazy { viewModel(MainViewModel::class.java) }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setInsets()
        viewModel.init(savedInstanceState == null)
        viewModel.livedata().observe(this, ::navigation)
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return (application as ProvideViewModel).viewModel(viewModelClass)
    }

    private fun navigation(screen: Screen) {
        screen.show(supportFragmentManager, binding.fragmentContainer.id)
    }

    private fun setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val statusBarInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            view.updatePadding(
                top = statusBarInsets.top,
                right = systemBarsInsets.right,
                bottom = systemBarsInsets.bottom
            )
            insets
        }
    }

}