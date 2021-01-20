package com.mijandev.com.redditclone.core.data.repository

import com.mijandev.com.redditclone.core.data.AppDatabase
import com.mijandev.com.redditclone.core.data.dao.TopicDao
import com.mijandev.com.redditclone.core.data.entity.TopicEntity

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Topic Repository with extra function (Eg. deleteTopic)
 */
class TopicRepository(private val appDatabase: AppDatabase) : BaseRepository<TopicEntity>(TopicEntity::class.java) {

    override val dao: TopicDao
        get() = appDatabase.topicDao()

    suspend fun deleteTopic(id : String) = dao.deleteTopic(id)

}