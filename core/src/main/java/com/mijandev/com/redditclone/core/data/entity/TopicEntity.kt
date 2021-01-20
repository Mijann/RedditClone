package com.mijandev.com.redditclone.core.data.entity

import androidx.room.Entity
import com.mijandev.com.redditclone.core.util.DateTimeUtils
import java.util.*

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Topic Entity with extra fields (Eg. topic, upvote, downvote)
 */
@Entity
data class TopicEntity(
    var topic: String,
    var upvote: Int = 0,
    var downvote: Int = 0
) : BaseEntity<String>() {
    init {
        id = UUID.randomUUID().toString()
        createdAt = DateTimeUtils.getFormattedDateTime(DateTimeUtils.getCurrentDateTimeString())
        modifiedAt = DateTimeUtils.getFormattedDateTime(DateTimeUtils.getCurrentDateTimeString())
    }
}
