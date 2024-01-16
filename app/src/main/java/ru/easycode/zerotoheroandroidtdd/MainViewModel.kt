package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel(
    val listLiveDataWrapper: ListLiveDataWrapper
) : ViewModel() {

    fun add(text: CharSequence) = listLiveDataWrapper.add(text)

    fun liveData(): LiveData<List<CharSequence>> = listLiveDataWrapper.liveData()

    fun save(bundle: BundleWrapper.Save) = listLiveDataWrapper.save(bundle)

    fun restore(bundle: BundleWrapper.Restore) {
        val restoredList = bundle.restore()
        listLiveDataWrapper.update(restoredList)
    }
}