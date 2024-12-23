package ru.easycode.zerotoheroandroidtdd.folder

import android.os.Bundle
import android.view.View
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.core.AbstractFragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFoldersListBinding
import ru.easycode.zerotoheroandroidtdd.folder.adapter.FolderAdapter

class FoldersListFragment :
    AbstractFragment<FragmentFoldersListBinding>(R.layout.fragment_folders_list) {

    override fun bind(view: View) = FragmentFoldersListBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FolderAdapter()
        binding.foldersRecyclerView.adapter = adapter
    }

}