package com.haideag.market.CustomView;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.haideag.market.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordEditText extends androidx.appcompat.widget.AppCompatEditText {

    Drawable eye, eyeStrike;
    Boolean visible = false;
    Boolean useStrike = false;
    Boolean useValidate = false;
    Drawable drawable;

    int ALPHA = (int) (255 * .55f);

    String MATCHER_PATTERN = "((?=.*[a-z])(?=.*[A-Z]).{8,20})";
    Pattern pattern;
    Matcher matcher;

    public PasswordEditText(Context context) {
        super(context);
        createone(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        createone(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createone(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        createone(attrs);
    }

    private void createone(AttributeSet attrs) {
        this.pattern = Pattern.compile(MATCHER_PATTERN);

        if (attrs != null) {
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PasswordEditText, 0, 0);

            this.useStrike = array.getBoolean(R.styleable.PasswordEditText_useStrike, false);
            this.useValidate = array.getBoolean(R.styleable.PasswordEditText_useValidate, false);
        }

        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();

        if (this.useValidate) {
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (!hasFocus) {
                        String text = getText().toString();
                        View parentView = (View) view.getParent();
                        while (parentView != null && !(parentView instanceof TextInputLayout)) {
                            parentView = (View) parentView.getParent();
                        }
                        if (parentView instanceof TextInputLayout) {
                            TextInputLayout textInputLayout = (TextInputLayout) parentView;
                            matcher = pattern.matcher(text);
                            if (!matcher.matches()) {
                                textInputLayout.setErrorEnabled(true);
                                textInputLayout.setError("Mật khẩu phải bao gồm ít nhất một chữ thường, một chữ hoa và có từ 8 đến 20 ký tự.");
                            } else {
                                textInputLayout.setErrorEnabled(false);
                            }
                        }
                    }
                }
            });
        }
        setting();
    }

    private void setting() {
        setInputType(InputType.TYPE_CLASS_TEXT | (visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = useStrike && !visible ? eyeStrike : eye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width())) {
            visible = !visible;
            setting();
            invalidate();
        }

        return super.onTouchEvent(event);
    }
}