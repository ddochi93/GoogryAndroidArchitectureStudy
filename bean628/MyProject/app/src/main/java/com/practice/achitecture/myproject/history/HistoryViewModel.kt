package com.practice.achitecture.myproject.history

import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.base.BaseNaverSearchViewModel
import com.practice.achitecture.myproject.data.source.NaverDataSource
import com.practice.achitecture.myproject.data.source.NaverRepository
import com.practice.achitecture.myproject.enums.SearchType
import com.practice.achitecture.myproject.model.SearchedItem

class HistoryViewModel constructor(private val naverRepository: NaverRepository) :
    BaseNaverSearchViewModel() {

    fun loadHistory(searchType: SearchType) {
        lastSearchType.value = searchType
        loadHistory()
    }

    fun loadHistory() {
        if (lastSearchType.value != null) {
            naverRepository.loadHistoryOfSearch(
                lastSearchType.value!!,
                object : NaverDataSource.LoadHistoryOfSearchCallback {
                    override fun onLoadSuccess(items: List<SearchedItem>) {
                        movieOrBookItems.value = items
                    }

                    override fun onEmptyData() {
                        if (lastSearchType.value != null) {
                            eventStringMessageId.value = when (lastSearchType.value!!) {
                                SearchType.MOVIE -> R.string.toast_empty_movie_search_history
                                SearchType.BOOK -> R.string.toast_empty_book_search_history
                                SearchType.BLOG -> R.string.toast_empty_blog_search_history
                                SearchType.NEWS -> R.string.toast_empty_news_search_history
                            }
                        }

                    }
                })
        }
    }
}