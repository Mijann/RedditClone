package com.mijandev.com.redditclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mijandev.com.redditclone.core.data.entity.TopicEntity
import com.mijandev.com.redditclone.core.util.TopicSorter
import com.mijandev.com.redditclone.databinding.TopicLayoutBinding
import com.mijandev.com.redditclone.ui.listener.MainHandlerListener
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Recyclerview Adapter for topics
 * Populate topics data to view
 */
class TopicAdapter(private val mainHandlerListener: MainHandlerListener) :
    RecyclerView.Adapter<TopicAdapter.ViewHolder>(), AutoUpdatableAdapter {

    var topics: List<TopicEntity> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o == n }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent, mainHandlerListener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getTopic(position))
    }

    private fun getTopic(position: Int): TopicEntity {
        return topics[position]
    }

    override fun getItemCount(): Int = topics.size

    class ViewHolder private constructor(
        val binding: TopicLayoutBinding,
        private val mainHandlerListener: MainHandlerListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: TopicEntity) {
            binding.topic = entity
            binding.currentPosition = adapterPosition
            binding.mainHandlerListener = mainHandlerListener
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup, mainHandlerListener: MainHandlerListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TopicLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, mainHandlerListener)
            }
        }
    }

}