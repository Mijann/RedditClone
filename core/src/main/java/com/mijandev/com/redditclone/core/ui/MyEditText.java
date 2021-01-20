package com.mijandev.com.redditclone.core.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import com.mijandev.com.redditclone.core.R;

/**
 * Created by Mohammad Hamizan on 18/1/2021.
 */
/**
 * Custom EditText with Montserrat font
 */
public class MyEditText extends AppCompatEditText {

    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), getContext().getString(R.string.textview_title_font));
            setTypeface(tf);
        }
    }
}