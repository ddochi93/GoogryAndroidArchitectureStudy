package com.jay.architecturestudy.ui.movie

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Movie
import com.jay.architecturestudy.ui.BaseFragment
import com.jay.architecturestudy.util.then
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : BaseFragment(R.layout.fragment_movie), MovieContract.View {
    override val presenter: MovieContract.Presenter by lazy {
        MoviePresenter(this, naverSearchRepository)
    }

    private lateinit var movieAdapter: MovieAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { activity ->
            movieAdapter = MovieAdapter()
            recycler_view.run {
                adapter = movieAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        activity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }

        search_bar.onClickAction = { keyword ->
            search(keyword)
        }
        presenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun updateUi(keyword: String, movies: List<Movie>) {
        keyword.isNotBlank().then {
            search_bar.keyword = keyword

            if (movies.isEmpty()) {
                hideResultListView()
                showEmptyResultView()
            } else {
                hideEmptyResultView()
                showResultListView()
                movieAdapter.setData(movies)
            }
        }
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun showEmptyResultView() {
        empty_result_view.visibility = View.VISIBLE
    }

    override fun showResultListView() {
        recycler_view.visibility = View.VISIBLE
    }

    override fun hideEmptyResultView() {
        empty_result_view.visibility = View.GONE
    }

    override fun hideResultListView() {
        recycler_view.visibility = View.GONE
    }

    override fun updateResult(result: List<Movie>) {
        if (result.isEmpty()) {
            movieAdapter.clear()
        } else {
            movieAdapter.setData(result)
        }
    }
}