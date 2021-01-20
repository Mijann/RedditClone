package com.mijandev.com.redditclone.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ViewCompat
import com.mijandev.com.redditclone.R
import com.mijandev.com.redditclone.core.util.contentView
import com.mijandev.com.redditclone.core.util.makeStatusBarTransparent
import com.mijandev.com.redditclone.core.util.setMarginTop
import com.mijandev.com.redditclone.databinding.ActivityTopicBinding
import com.mijandev.com.redditclone.ui.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

/**
 * Created by Mohammad Hamizan on 20/1/2021.
 */
/**
 * Topic Activity (Display Topic Details Screen)
 */
class TopicActivity : AppCompatActivity() {

    private val binding by contentView<TopicActivity, ActivityTopicBinding>(
        R.layout.activity_topic
    )

    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.topic = mainViewModel.topic
        makeStatusBarTransparent()

        ViewCompat.setOnApplyWindowInsetsListener(binding.constraintLayout) { _, insets ->
            binding.constraintLayout.setMarginTop(insets.systemWindowInsetTop)
            insets.consumeSystemWindowInsets()
        }

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

    }
}