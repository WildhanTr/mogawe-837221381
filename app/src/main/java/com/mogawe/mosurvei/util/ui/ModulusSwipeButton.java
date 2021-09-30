package com.mogawe.mosurvei.util.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.ebanx.swipebtn.SwipeButton;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.util.Typefaces;

public class ModulusSwipeButton extends SwipeButton {

    public ModulusSwipeButton(Context context) {
        super(context);
    }

    public ModulusSwipeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    public ModulusSwipeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(context, attrs);
    }

    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.ModulusSwipeButton);
        //The value 0 is a default, but shouldn't ever be used since the attr is an enum
        int textStyle = values.getInt(R.styleable.ModulusSwipeButton_android_textStyle, 0);
        switch (textStyle) {
            case Typefaces.NORMAL:
            default:
                //You can instantiate your typeface anywhere, I would suggest as a
                //singleton somewhere to avoid unnecessary copies
//                getCenterText().setTypeface(Typefaces.modulus(context));
                break;
            case Typefaces.BOLD:
//                getCenterText().setTypeface(Typefaces.modulusBold(context));
                break;
        }
        values.recycle();
    }
}
