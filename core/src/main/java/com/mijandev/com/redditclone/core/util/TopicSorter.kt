package com.mijandev.com.redditclone.core.util

import com.mijandev.com.redditclone.core.data.entity.TopicEntity
/**
 * Created by Mohammad Hamizan on 19/1/2021.
 */
/**
 * Topic Sorter class
 * To sort topics by upvote in descending order
 */
class TopicSorter : Comparator<TopicEntity?> {
    override fun compare(o1: TopicEntity?, o2: TopicEntity?): Int = o2?.upvote!!.compareTo(o1!!.upvote)
}