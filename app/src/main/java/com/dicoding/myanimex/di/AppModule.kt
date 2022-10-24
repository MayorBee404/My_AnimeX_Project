package com.dicoding.myanimex.di

import com.dicoding.core.domain.usecase.AnimeInteractor
import com.dicoding.core.domain.usecase.AnimeUseCase
import com.dicoding.myanimex.detail.DetailAnimeViewModel
import com.dicoding.myanimex.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module


val useCaseModule = module {
    factory<AnimeUseCase> { AnimeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailAnimeViewModel(get()) }
}