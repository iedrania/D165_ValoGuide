package com.iedrania.valoguide.di

import com.iedrania.valoguide.core.domain.usecase.AgentInteractor
import com.iedrania.valoguide.core.domain.usecase.AgentUseCase
import com.iedrania.valoguide.detail.DetailViewModel
import com.iedrania.valoguide.favorite.FavoriteViewModel
import com.iedrania.valoguide.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<com.iedrania.valoguide.core.domain.usecase.AgentUseCase> {
        com.iedrania.valoguide.core.domain.usecase.AgentInteractor(
            get()
        )
    }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}