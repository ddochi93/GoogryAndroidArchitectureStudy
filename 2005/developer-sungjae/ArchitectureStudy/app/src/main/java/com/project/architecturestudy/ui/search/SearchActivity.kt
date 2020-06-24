package com.project.architecturestudy.ui.search

import android.os.Bundle
import androidx.lifecycle.Observer
import com.project.architecturestudy.R
import com.project.architecturestudy.base.BaseActivity
import com.project.architecturestudy.data.model.MovieItem
import com.project.architecturestudy.databinding.ActivitySearchBinding
import com.project.architecturestudy.databinding.MovieItemBinding
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>(R.layout.activity_search) {

    override val vm: SearchViewModel by viewModel()
    private val adapter = SearchAdapter<MovieItemBinding, MovieItem>(R.layout.movie_item)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            vm = vm
            lifecycleOwner = this@SearchActivity
            listviewMovie.adapter = adapter
        }
        vm.searchWord.observe(this, Observer {
            vm.invokeTextChanged()
        })

        vm.showToast.observe(this, Observer {
            toast(it)
        })
    }
}