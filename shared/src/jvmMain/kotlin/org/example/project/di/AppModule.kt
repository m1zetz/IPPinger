package org.example.project.di


import org.example.project.domain.IPingerService
import org.example.project.repository.PingerService
import org.example.project.ui.main.AppViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<IPingerService> { PingerService() }
    factory { AppViewModel(get()) }
    viewModel { AppViewModel(get()) }
}