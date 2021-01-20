package com.mijandev.com.redditclone.core.util

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.mijandev.com.redditclone.core.R

/**
 * Created by Mohammad Hamizan on 20/1/2021.
 */
/**
 * Add Choice Dialog Helper class
 * To populate choices for a prompt use case (Eg. delete topic)
 */
class ChoiceDialogHelper(var context: Context) : BaseDialogHelper() {

    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.layout_choice_dialog, null)
    }

    private val cancelButton: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.cancelButton)
    }

    private val titleTextView: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.title_text_view)
    }

    private val subTitleTextView: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.subtitle_text_view)
    }

    fun setTitle(text : String) {
        titleTextView.text = text
    }

    fun setSubTitle(text : String) {
        subTitleTextView.text = text
    }

    fun setCancelText(text : String) {
        cancelButton.text = text
    }

    fun setOkayText(text : String) {
        okayButton.text = text
    }

    fun setTitleTextColor(colorID: Int) {
        titleTextView.setTextColor(context.getColor(colorID))
    }

    private val okayButton: TextView by lazy {
        val okayTextView =
            dialogView.findViewById<TextView>(R.id.okayButton)
        okayTextView
    }

    fun cancelButtonClickListener(func: (() -> Unit)? = null) =
        with(cancelButton) {
            setClickListenerToDialogIcon(func)
        }

    fun okayButtonClickListener(func: (() -> Unit)? = null) =
        with(okayButton) {
            setClickListenerToDialogIcon(func)
        }

    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    private fun View.setClickListenerToDialogIcon(func: (() -> Unit)?) =
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }


}

inline fun Activity.showChoiceDialogHelper(func: ChoiceDialogHelper.() -> Unit): AlertDialog =
    ChoiceDialogHelper(this).apply {
        func()
    }.create()