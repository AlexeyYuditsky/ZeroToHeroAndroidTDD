package ru.easycode.zerotoheroandroidtdd.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.create.CreateScreen
import ru.easycode.zerotoheroandroidtdd.main.Navigation

class ListViewModel(
    private val navigation: Navigation.Update,
    private val liveDataWrapper: ListLiveDataWrapper.Mutable
) : ViewModel(), ListLiveDataWrapper.Read {

    override fun livedata(): LiveData<List<CharSequence>> = liveDataWrapper.livedata()

    fun create() = navigation.update(CreateScreen)

    fun save(bundleWrapper: BundleWrapper.Save) = liveDataWrapper.save(bundleWrapper)

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        liveDataWrapper.update(bundleWrapper.restore())
    }

}