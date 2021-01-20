package com.mijandev.com.redditclone

import android.app.Application
import com.mijandev.com.redditclone.core.di.coreDataModule
import com.mijandev.com.redditclone.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * MyApp Application class
 * Start Koin configuration
 * Inject Core Data module and Main module
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(coreDataModule, mainModule))
        }
    }
}