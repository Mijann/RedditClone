package com.mijandev.com.redditclone.core

import android.app.Instrumentation
import android.content.Context
import com.mijandev.com.redditclone.core.data.AppDatabase
import com.mijandev.com.redditclone.core.data.repository.TopicRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Mohammad Hamizan on 19/1/2021.
 */
val roomTestModule = module {
    single { createDatabase(androidContext()) }
    single { provideTopicDao(get()) }
    single { TopicRepository(get()) }
}

fun createDatabase(context: Context): AppDatabase =
    AppDatabase.getInstance(context, testMode = true)

fun provideTopicDao(appDatabase: AppDatabase) = appDatabase.topicDao()