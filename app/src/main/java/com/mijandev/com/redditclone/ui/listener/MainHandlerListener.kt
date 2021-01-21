package com.mijandev.com.redditclone.ui.listener

import com.mijandev.com.redditclone.core.data.entity.TopicEntity

/**
 * Created by Mohammad Hamizan on 19/1/2021.
 */
interface MainHandlerListener {
    fun upvoteTopic(topicEntity: TopicEntity)
    fun downvoteTopic(topicEntity: TopicEntity)
    fun deleteTopic(topicEntity: TopicEntity)
    fun viewTopic(topicEntity: TopicEntity)
}