package com.mijandev.com.redditclone.di

import com.mijandev.com.redditclone.ui.viewmodel.MainViewModel
import org.koin.dsl.module

/**
 * Created by Mohammad Hamizan on 19/1/2021.
 */
/**
 * Main Module
 * Inject Main ViewModel
 */
val mainModule = module {
    single { MainViewModel(get()) }
}