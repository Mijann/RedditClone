package com.mijandev.com.redditclone.core.di

import android.content.Context
import com.mijandev.com.redditclone.core.data.AppDatabase
import com.mijandev.com.redditclone.core.data.repository.TopicRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Core data module
 * To inject AppDatabase, TopicDao, TopicRepository
 */
val coreDataModule = module {
    single { createDatabase(androidContext()) }
    single { provideTopicDao(get()) }
    single { TopicRepository(get()) }
}

fun createDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)

fun provideTopicDao(appDatabase: AppDatabase) = appDatabase.topicDao()