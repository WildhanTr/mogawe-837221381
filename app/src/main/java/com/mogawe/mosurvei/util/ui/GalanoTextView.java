package com.mogawe.mosurvei.util.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.text.Html;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.util.Typefaces;

public class GalanoTextView extends AppCompatTextView {
    private CharSequence defaultText;
    private CharSequence additionalText;
    private int index;
    private long delay = 25; // in ms
    private Handler handler = new Handler();

    public GalanoTextView(Context context) {
        super(context);
    }

    public GalanoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    public GalanoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.GalanoTextView);
        //The value 0 is a default, but shouldn't ever be used since the attr is an enum
        int textStyle = values.getInt(R.styleable.GalanoTextView_android_textStyle, 0);
        switch (textStyle) {
            case Typefaces.NORMAL:
            default:
                //You can instantiate your typeface anywhere, I would suggest as a
                //singleton somewhere to avoid unnecessary copies
                setTypeface(Typefaces.galanoMedium(context));
                break;
            case Typefaces.BOLD:
                setTypeface(Typefaces.galanoBold(context));
                break;
            case Typefaces.ITALIC:
                setTypeface(Typefaces.galanoMediumItalic(context));
                break;
            case Typefaces.BOLD_ITALIC:
                setTypeface(Typefaces.galanoBoldItalic(context));
                break;
        }
        values.recycle();
    }

    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            String def = "";
            if (defaultText != null)
                def = defaultText.toString();

            String t = def + additionalText.subSequence(0, index++);
            setText(Html.fromHtml(t));
            if (index <= additionalText.length()) {
                handler.postDelayed(characterAdder, delay);
            }
        }
    };

    public void animateText(CharSequence additionalText) {
        this.defaultText = getText();
        this.additionalText = additionalText;
        index = 0;
        handler.removeCallbacks(characterAdder);
        handler.postDelayed(characterAdder, delay);
    }

    public void animateText(CharSequence additionalText, CharSequence defaultText) {
        this.defaultText = defaultText;
        this.additionalText = additionalText;
        index = 0;
        handler.removeCallbacks(characterAdder);
        handler.postDelayed(characterAdder, delay);
    }



    public void setCharacterDelay(long m) {
        delay = m;
    }

}
