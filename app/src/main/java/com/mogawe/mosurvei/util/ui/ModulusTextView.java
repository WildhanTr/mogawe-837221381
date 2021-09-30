package com.mogawe.mosurvei.util.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.util.Typefaces;

import androidx.appcompat.widget.AppCompatTextView;


public class ModulusTextView extends AppCompatTextView {

    public ModulusTextView(Context context) {
        super(context);
    }

    public ModulusTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    public ModulusTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(context, attrs);
    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public ModulusTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        parseAttributes(context, attrs);
//    }

    /**
     * Parsing the given attribute
     *
     * @param context current view context
     * @param attrs   user-defined attrs
     */
    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.ModulusTextView);
        //The value 0 is a default, but shouldn't ever be used since the attr is an enum
        int textStyle = values.getInt(R.styleable.ModulusTextView_android_textStyle, 0);
        switch (textStyle) {
            case Typefaces.NORMAL:
            default:
                //You can instantiate your typeface anywhere, I would suggest as a
                //singleton somewhere to avoid unnecessary copies
                setTypeface(Typefaces.modulus(context));
                break;
            case Typefaces.BOLD:
                setTypeface(Typefaces.modulusBold(context));
                break;
        }
        values.recycle();
    }
}
