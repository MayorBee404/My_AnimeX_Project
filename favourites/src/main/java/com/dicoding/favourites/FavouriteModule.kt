package com.dicoding.favourites

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavouriteViewModel(get()) }
}