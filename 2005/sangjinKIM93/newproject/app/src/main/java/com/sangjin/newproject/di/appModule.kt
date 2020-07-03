package com.sangjin.newproject.di

import com.sangjin.newproject.activity.MovieListViewModel
import com.example.data.repository.NaverMoviesRepository
import com.example.data.repository.NaverMoviesRepositoryImpl
import com.example.data.source.local.LocalDataSource
import com.example.data.source.local.LocalDataSourceImpl
import com.example.data.source.remote.RemoteDataSource
import com.example.data.source.remote.RemoteDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        MovieListViewModel(get(), get())
    }

}