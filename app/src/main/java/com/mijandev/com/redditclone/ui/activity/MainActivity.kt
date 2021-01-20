package com.mijandev.com.redditclone.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mijandev.com.redditclone.R
import com.mijandev.com.redditclone.adapter.TopicAdapter
import com.mijandev.com.redditclone.core.data.entity.TopicEntity
import com.mijandev.com.redditclone.core.util.*
import com.mijandev.com.redditclone.databinding.ActivityMainBinding
import com.mijandev.com.redditclone.ui.listener.MainHandlerListener
import com.mijandev.com.redditclone.ui.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import java.util.*

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Main Activity (Home Screen)
 */

class MainActivity : AppCompatActivity(), MainHandlerListener {

    private val binding by contentView<MainActivity, ActivityMainBinding>(
        R.layout.activity_main
    )

    private val mainViewModel: MainViewModel by inject()

    private var topicAdapter: TopicAdapter = TopicAdapter(this)

    private var addTopicDialog: AlertDialog? = null

    private var showChoiceDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()

        ViewCompat.setOnApplyWindowInsetsListener(binding.constraintLayout) { _, insets ->
            binding.constraintLayout.setMarginTop(insets.systemWindowInsetTop)
            insets.consumeSystemWindowInsets()
        }

        val viewManager = LinearLayoutManager(this)

        binding.topicRecyclerview.apply {
            layoutManager = viewManager
            adapter = topicAdapter
        }

        mainViewModel.topics.observe(this, {
            topicAdapter.topics = it
        })

        binding.addTopicButton.setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        hideAddDialog()
        addTopicDialog = showAddTopicDialogHelper {
            addButtonClickListener {
                if (checkValidField()) {
                    addTopic(TopicEntity(topicEditText.text.toString().trim()))
                }
            }
        }
        addTopicDialog!!.show()
    }

    private fun hideAddDialog() {
        if (addTopicDialog != null)
            addTopicDialog!!.dismiss()

        addTopicDialog = null
    }

    private fun showChoiceDialog(
        title: String,
        subtitle: String,
        topicEntity: TopicEntity,
    ) {
        hideChoiceDialog()
        showChoiceDialog = showChoiceDialogHelper {
            setTitle(title)
            setSubTitle(subtitle)
            okayButtonClickListener {
                mainViewModel.deleteTopic(topicEntity)
                val newTopics = topicAdapter.topics.toMutableList()
                newTopics.remove(topicEntity)
                updateTopics(newTopics)
            }
            cancelButtonClickListener { }
        }
        showChoiceDialog!!.show()
    }

    private fun hideChoiceDialog() {
        if (showChoiceDialog != null)
            showChoiceDialog!!.dismiss()

        showChoiceDialog = null
    }

    override fun upvoteTopic(topicEntity: TopicEntity, position: Int) {
        topicEntity.upvote++
        mainViewModel.updateTopic(topicEntity)
        val newTopics = topicAdapter.topics.toMutableList()
        newTopics[position] = topicEntity
        updateTopics(newTopics)
        topicAdapter.notifyDataSetChanged()
    }

    override fun downvoteTopic(topicEntity: TopicEntity, position: Int) {
        topicEntity.downvote++
        mainViewModel.updateTopic(topicEntity)
        val newTopics = topicAdapter.topics.toMutableList()
        newTopics[position] = topicEntity
        updateTopics(newTopics)
        topicAdapter.notifyDataSetChanged()
    }

    override fun deleteTopic(topicEntity: TopicEntity) {
        showChoiceDialog(
            getString(R.string.delete_topic_titlle),
            getString(R.string.delete_topic_description),
            topicEntity
        )
    }

    override fun viewTopic(topicEntity: TopicEntity) {
        mainViewModel.topic = topicEntity
        startActivity(Intent(this, TopicActivity::class.java))
    }

    private fun addTopic(topicEntity: TopicEntity) {
        mainViewModel.insertTopic(topicEntity)
        val newTopics = topicAdapter.topics.toMutableList()
        newTopics.add(topicEntity)
        updateTopics(newTopics)
        binding.topicRecyclerview.smoothScrollToPosition(topicAdapter.itemCount - 1)
    }

    private fun updateTopics(newTopics: MutableList<TopicEntity>) {
        Collections.sort(newTopics, TopicSorter())
        topicAdapter.topics = newTopics
    }

    override fun onStop() {
        super.onStop()
        hideAddDialog()
        hideChoiceDialog()
    }
}