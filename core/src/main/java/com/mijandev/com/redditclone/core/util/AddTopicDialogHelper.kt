package com.mijandev.com.redditclone.core.util

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.mijandev.com.redditclone.core.R

/**
 * Created by Mohammad Hamizan on 19/1/2021.
 */
/**
 * Add Topic Dialog Helper class
 * To populate add topic dialog to cater creation of new topic
 */
class AddTopicDialogHelper(var context: Context) : BaseDialogHelper() {

    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.layout_add_topic_dialog, null)
    }
    val topicEditText: EditText by lazy {
        dialogView.findViewById<EditText>(R.id.topic_edit_text)
    }

    private val charTextView: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.char_text_view)
    }

    private val addButton: Button by lazy {
        dialogView.findViewById<Button>(R.id.add_button)
    }

    init {
        charTextView.text = context.getString(R.string.character_left, 255)
        addButton.isEnabled = topicEditText.text.isNotEmpty()
        editTextListenerFunction()
    }

    fun addButtonClickListener(func: (() -> Unit)? = null) =
        with(addButton) {
            setClickListenerToDialogIcon(func)
        }

    fun checkValidField(): Boolean {
        return if (topicEditText.text.isNotBlank()) true else {
            topicEditText.error = context.getString(R.string.topic_is_required)
            topicEditText.requestFocus()
            false
        }
    }

    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    private fun View.setClickListenerToDialogIcon(func: (() -> Unit)?) =
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }

    private fun editTextListenerFunction() {
        topicEditText.addTextChangedListener {
            var maxChar = 255
            maxChar -= it!!.length
            charTextView.text = context.getString(R.string.character_left, maxChar)

            addButton.isEnabled = it!!.isNotEmpty()
        }
    }
}

inline fun Activity.showAddTopicDialogHelper(func: AddTopicDialogHelper.() -> Unit): AlertDialog =
    AddTopicDialogHelper(this).apply {
        func()
    }.create()