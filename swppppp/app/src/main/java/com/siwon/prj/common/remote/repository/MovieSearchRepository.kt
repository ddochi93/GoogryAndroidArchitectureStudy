package com.siwon.prj.repository

import com.siwon.prj.common.model.Movie

interface MovieSearchRepository {
    fun searchMovies(query: String, success: (ArrayList<Movie>) -> Unit, fail: (Throwable) -> Unit)
}
