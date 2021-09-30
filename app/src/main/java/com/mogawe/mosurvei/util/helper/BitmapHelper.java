package com.mogawe.mosurvei.util.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by mobiletech on 6/29/17.
 */

public class BitmapHelper {

    public static Bitmap resizedBitmap(Bitmap bitmap) {
        //Boolean isLandscape = false;

        float width = 480;
        float height;

        float ratio;
        ratio = bitmap.getWidth() / width;
        height = bitmap.getHeight() / ratio;

        Bitmap background = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.RGB_565);

        float originalWidth = bitmap.getWidth();
        float originalHeight = bitmap.getHeight();

        Canvas canvas = new Canvas(background);

        float scale = width / originalWidth;

        float xTranslation = 0.0f;
        float yTranslation = (height - originalHeight * scale) / 2.0f;

        Matrix transformation = new Matrix();
        transformation.postTranslate(xTranslation, yTranslation);
        transformation.preScale(scale, scale);

        Paint paint = new Paint();
        paint.setFilterBitmap(true);

        canvas.drawBitmap(bitmap, transformation, paint);


        return background;
    }


    public static Bitmap reduceResolution(String filePath, int viewWidth, int viewHeight) {
        int reqHeight = viewHeight;
        int reqWidth = viewWidth;

        BitmapFactory.Options options = new BitmapFactory.Options();

        // First decode with inJustDecodeBounds=true to check dimensions
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        double viewAspectRatio = 1.0 * viewWidth/viewHeight;
        double bitmapAspectRatio = 1.0 * options.outWidth/options.outHeight;

        if (bitmapAspectRatio > viewAspectRatio)
            reqHeight = (int) (viewWidth/bitmapAspectRatio);
        else
            reqWidth = (int) (viewHeight * bitmapAspectRatio);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        System.gc();                                        // TODO - remove explicit gc calls
        return BitmapFactory.decodeFile(filePath, options);
    }


    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

}
