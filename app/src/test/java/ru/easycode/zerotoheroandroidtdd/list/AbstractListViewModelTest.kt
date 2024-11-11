package ru.easycode.zerotoheroandroidtdd.list

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.main.FakeNavigation

abstract class AbstractListViewModelTest {

    protected val navigation = FakeNavigation.Base()
    protected val liveDataWrapper = FakeListLiveDataWrapper.Base()
    protected val viewModel = ListViewModel(
        navigation = navigation,
        liveDataWrapper = liveDataWrapper
    )

    interface FakeListLiveDataWrapper : ListLiveDataWrapper.All {

        fun fetchDataList(): List<CharSequence>

        class Base : FakeListLiveDataWrapper {

            private val dataList = ArrayList<CharSequence>()

            override fun fetchDataList(): List<CharSequence> = dataList

            override fun add(source: CharSequence) {
                dataList.add(source)
            }

            override fun save(bundleWrapper: BundleWrapper.Save) {
                bundleWrapper.save(dataList)
            }

            override fun livedata(): LiveData<List<CharSequence>> {
                throw IllegalStateException("not used in tests")
            }

            override fun update(value: List<CharSequence>) {
                dataList.addAll(value)
            }

        }

    }

    protected interface FakeBundleWrapper : BundleWrapper.Mutable {

        class Base : FakeBundleWrapper {

            private val cached = ArrayList<CharSequence>()

            override fun restore(): List<CharSequence> = cached

            override fun save(list: ArrayList<CharSequence>) {
                cached.addAll(list)
            }

        }

    }

}