package com.haideag.market.CustomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.haideag.market.R;

public class ClearEditText extends androidx.appcompat.widget.AppCompatEditText {
    Drawable crossX,nonecrossX,drawable;
    Boolean visible = false;

    public ClearEditText(Context context) {
        super(context);
        create();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        create();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        create();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        create();
    }
    private void create(){
        crossX = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        nonecrossX = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();



    }
    private void setting(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable =  visible? crossX:nonecrossX;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            if (drawable != null) {  // Add this null check
                if (event.getX() >= (getRight() - drawable.getBounds().width())) {
                    setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if(lengthAfter ==0 && start ==0) {
            visible =false;
            setting();
        }else {
            visible=true;
            setting();
        }
    }
}
