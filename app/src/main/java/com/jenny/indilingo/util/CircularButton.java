package com.jenny.indilingo.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;

import com.jenny.indilingo.R;

/**
 * Created by jenny on 17/01/18.
 */

public class CircularButton extends Button {

    public CircularButton(Context context) {
        super(context);
        setButtonStyle();
        setButtonBackground(getResources().getColor(R.color.blue_E0), getResources().getColor(R.color.blue_36), getResources().getColor(R.color.disable));
    }

    public CircularButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setButtonStyle();
        setButtonBackground(getResources().getColor(R.color.blue_E0), getResources().getColor(R.color.blue_36), getResources().getColor(R.color.disable));

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CircularButton);
        int count = typedArray.getIndexCount();
        try {

            for (int i = 0; i < count; ++i) {
                int attr = typedArray.getIndex(i);
                if (attr == R.styleable.CircularButton_text) {
                    String text = typedArray.getString(attr);
                    setButtonText(text);
                } else if (attr == R.styleable.CircularButton_maxLines) {
                    int maxLines = typedArray.getInteger(attr, 0);
                    setButtonMaxLine(maxLines);
                } else if (attr == R.styleable.CircularButton_textSize) {
                    int textSize = typedArray.getInteger(attr, 0);
                    setButtonTextSize(textSize);
                }
            }
        }

        // the recycle() will be executed obligatorily
        finally {
            // for reuse
            typedArray.recycle();
        }
    }

    public void setButtonText(String text) {
        setText(text);
    }

    public void setButtonBackground(int enabled, int pressed, int disabled) {
        StateListDrawable stateListDrawable = new StateListDrawable();

        GradientDrawable bgShape = (GradientDrawable)getResources().getDrawable(R.drawable.circle).mutate();
        bgShape.setColor(enabled);
        stateListDrawable.addState(new int[] { -android.R.attr.state_pressed, android.R.attr.state_enabled }, bgShape);

        bgShape = (GradientDrawable)getResources().getDrawable(R.drawable.circle).mutate();
        bgShape.setColor(pressed);
        stateListDrawable.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled }, bgShape);

        bgShape = (GradientDrawable)getResources().getDrawable(R.drawable.circle).mutate();
        bgShape.setColor(disabled);
        stateListDrawable.addState(new int[] { -android.R.attr.state_enabled }, bgShape);

        setBackgroundDrawable(stateListDrawable);
    }

    private void setButtonStyle() {
        setGravity(Gravity.CENTER);
        setPadding(8, 8, 8, 8);
        setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
    }

    public void setButtonMaxLine(int maxLines) {
        if (maxLines > 0) {
            setMaxLines(maxLines);
        } else {
            setMaxLines(1);
        }
    }

    public void setButtonTextSize(int textSize) {
        if (textSize > 0) {
            setTextSize(textSize);
        } else {
            setTextSize(14);
        }
    }
}
