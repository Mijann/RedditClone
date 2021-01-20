package com.mijandev.com.redditclone.core.ui;

import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import android.content.Context;
import com.mijandev.com.redditclone.core.R;

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Custom TextView Topic with Montserrat font
 */
public class MyTextView_Topic extends AppCompatTextView {

    public MyTextView_Topic(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextView_Topic(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView_Topic(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), getContext().getString(R.string.textview_topic_font));
            setTypeface(tf);
        }
    }
}