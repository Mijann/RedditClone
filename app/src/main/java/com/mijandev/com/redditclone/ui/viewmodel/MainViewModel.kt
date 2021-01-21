package com.mijandev.com.redditclone.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mijandev.com.redditclone.core.data.entity.TopicEntity
import com.mijandev.com.redditclone.core.data.repository.TopicRepository
import com.mijandev.com.redditclone.core.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Mohammad Hamizan on 19/1/2021.
 */
/**
 * Main ViewModel
 * Handle topics functions (Eg, retrieve topics, add new topic, upvote/downvote topic, delete topic)
 */
class MainViewModel(private val topicRepository: TopicRepository) : ViewModel() {
    private val _topics = MutableLiveData<List<TopicEntity>>()
    val topics: LiveData<List<TopicEntity>>
        get() = _topics

    var topic: TopicEntity?= null

    init {
        getTopics()
    }

    private fun getTopics() {
        viewModelScope.launch(Dispatchers.IO) {
            val topicsNew = topicRepository.findAll(Constants.TOPIC_UPVOTE_COLUMN, sort_type = Constants.DECREASING_SQL, limit = 20)
            _topics.postValue(topicsNew)
        }
    }

   fun insertTopic(newTopic: TopicEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            topicRepository.insert(newTopic)
        }
    }

    fun updateTopic(topic: TopicEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            topicRepository.update(topic)
        }
    }

    fun deleteTopic(topic: TopicEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            topicRepository.deleteTopic(topic.id.toString())
        }
    }

    fun updateTopics(newTopics : List<TopicEntity>) {
        _topics.postValue(newTopics)
    }
}