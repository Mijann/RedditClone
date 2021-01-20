package com.mijandev.com.redditclone.core

import androidx.test.platform.app.InstrumentationRegistry
import com.mijandev.com.redditclone.core.data.AppDatabase
import com.mijandev.com.redditclone.core.data.dao.TopicDao
import com.mijandev.com.redditclone.core.data.entity.TopicEntity
import com.mijandev.com.redditclone.core.data.repository.TopicRepository
import org.junit.Assert
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

/**
 * Created by Mohammad Hamizan on 20/1/2021.
 */
/**
 * Topic DAO tests (Eg. insert, deleteTopic, update, findAll, findById)
 */
class TopicDAOTest : KoinTest {

    private val topicDao: TopicDao by inject()
    private val topicRepository: TopicRepository by inject()
    private val appDatabase: AppDatabase by inject()

    init {
        configureKoin()
    }

    @Test
    fun testInsertNewTopics() {

        var topics = mockTopics()

        //Save topics
        topicDao.insert(topics)

        //Request topics list
        val allTopics = topicRepository.findAll()

        //Compare results
        Assert.assertEquals(topics, allTopics)

        //Close database
        appDatabase.close()
    }

    @Test
    fun testDeleteTopics() {

        var topics = mockTopics()

        //Save topics
        topicDao.insert(topics)

        //Request topics list
        val allTopics = topicRepository.findAll()

        //Delete topic
        topicDao.deleteTopic(allTopics.first().id!!)

        //Request again topics list
        val latestTopics = topicRepository.findAll()

        //Compare results
        Assert.assertNotEquals(allTopics, latestTopics)

        //Close database
        appDatabase.close()

    }

    @Test
    fun testUpdateTopic() {

        var topics = mockTopics()

        //Save topics
        topicDao.insert(topics)

        //Request topics list
        val allTopics = topicRepository.findAll()

        //Update topic
        var oldTopic = allTopics.first()
        var updatedTopic = allTopics.first()
        updatedTopic.upvote++
        topicDao.update(updatedTopic)

        //Request topic filter by id
        val latestUpdatedTopic = topicRepository.findById(updatedTopic.id!!)

        //Compare results
        Assert.assertNotEquals(oldTopic, latestUpdatedTopic)

        //Close database
        appDatabase.close()

    }

    //Start Koin configuration
    private fun configureKoin() {
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
            modules(roomTestModule)
        }
    }

    //Create the list of topic
    private fun mockTopics(): List<TopicEntity> {
        //Create mock topics
        val topic = TopicEntity("Topic Test 1")
        val topic2 = TopicEntity("Topic Test 2")

        //Create the list of topics
        return listOf(topic, topic2)
    }


}