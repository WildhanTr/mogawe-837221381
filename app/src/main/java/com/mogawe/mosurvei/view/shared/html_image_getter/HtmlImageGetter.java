package com.mogawe.mosurvei.view.shared.html_image_getter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class HtmlImageGetter implements Html.ImageGetter {
    private Context context;
    private TextView textView;

    public HtmlImageGetter(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    @Override
    public Drawable getDrawable(String source) {
        final UrlDrawable drawable = new UrlDrawable();
        Glide.with(context)
                .asBitmap()
                .load(source)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        drawable.setBitmap(resource);
                        drawable.setBounds(0, 0, resource.getWidth(), resource.getHeight());

                        textView.invalidate();
                        textView.setText(textView.getText());
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }

                });
        return drawable;
    }

    private class UrlDrawable extends BitmapDrawable {
        private Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }

        void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }
    }
}
