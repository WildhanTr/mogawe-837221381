package com.mogawe.mosurvei.view.shared.camera;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.mogawe.mosurvei.R;

import java.io.OutputStream;

public class CameraActivity extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private Camera.PictureCallback mPicture;
    private Button capture, switchCamera;
    private Context myContext;
    private LinearLayout cameraPreview;
    private boolean cameraFront = false;
    public static Bitmap bitmap;

    private OrientationEventListener mOrientationEventListener;
    private int mOrientation = -1;

    private static final int ORIENTATION_PORTRAIT_NORMAL = 1;
    private static final int ORIENTATION_PORTRAIT_INVERTED = 2;
    private static final int ORIENTATION_LANDSCAPE_NORMAL = 3;
    private static final int ORIENTATION_LANDSCAPE_INVERTED = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_camera);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        myContext = this;

        mCamera = Camera.open();
        cameraPreview = (LinearLayout) findViewById(R.id.camerapreview);
        mPreview = new CameraPreview(myContext, mCamera);
        cameraPreview.addView(mPreview);
        mPicture = getPictureCallback();


        capture = (Button) findViewById(R.id.btnAmbilFoto);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("OUT >> test3 " + mPicture);

                mCamera.takePicture(null, null, mPicture);
            }
        });

        switchCamera = (Button) findViewById(R.id.btnSwitchCamera);
        switchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the number of cameras
                int camerasNumber = Camera.getNumberOfCameras();
                if (camerasNumber > 1) {
                    //release the old camera instance
                    //switch camera, from the front and the back and vice versa

                    releaseCamera();
                    chooseCamera();
                } else {

                }
            }
        });

        mCamera.startPreview();

    }

    private int findFrontFacingCamera() {

        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                cameraFront = true;
                break;
            }
        }
        return cameraId;

    }

    private int findBackFacingCamera() {
        int cameraId = -1;
        //Search for the back facing camera
        //get the number of cameras
        int numberOfCameras = Camera.getNumberOfCameras();
        //for every camera check
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                cameraFront = false;
                break;

            }

        }
        return cameraId;
    }

    @Override
    public void onResume() {

        super.onResume();
        if (mCamera == null) {
            mCamera = Camera.open();
            mPicture = getPictureCallback();
            mPreview.refreshCamera(mCamera);
            Log.d("nu", "null");
        } else {
            Log.d("nu", "no null");
        }

        if (mOrientationEventListener == null) {
            mOrientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {

                @Override
                public void onOrientationChanged(int orientation) {

                    // determine our orientation based on sensor response
                    int lastOrientation = mOrientation;

                    Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

                    if (display.getOrientation() == Surface.ROTATION_0) {   // landscape oriented devices
                        if (orientation >= 315 || orientation < 45) {
                            if (mOrientation != ORIENTATION_LANDSCAPE_NORMAL) {
                                mOrientation = ORIENTATION_LANDSCAPE_NORMAL;
                            }
                        } else if (orientation < 315 && orientation >= 225) {
                            if (mOrientation != ORIENTATION_PORTRAIT_INVERTED) {
                                mOrientation = ORIENTATION_PORTRAIT_INVERTED;
                            }
                        } else if (orientation < 225 && orientation >= 135) {
                            if (mOrientation != ORIENTATION_LANDSCAPE_INVERTED) {
                                mOrientation = ORIENTATION_LANDSCAPE_INVERTED;
                            }
                        } else if (orientation < 135 && orientation > 45) {
                            if (mOrientation != ORIENTATION_PORTRAIT_NORMAL) {
                                mOrientation = ORIENTATION_PORTRAIT_NORMAL;
                            }
                        }
                    } else {  // portrait oriented devices
                        if (orientation >= 315 || orientation < 45) {
                            if (mOrientation != ORIENTATION_PORTRAIT_NORMAL) {
                                mOrientation = ORIENTATION_PORTRAIT_NORMAL;
                            }
                        } else if (orientation < 315 && orientation >= 225) {
                            if (mOrientation != ORIENTATION_LANDSCAPE_NORMAL) {
                                mOrientation = ORIENTATION_LANDSCAPE_NORMAL;
                            }
                        } else if (orientation < 225 && orientation >= 135) {
                            if (mOrientation != ORIENTATION_PORTRAIT_INVERTED) {
                                mOrientation = ORIENTATION_PORTRAIT_INVERTED;
                            }
                        } else if (orientation < 135 && orientation > 45) {
                            if (mOrientation != ORIENTATION_LANDSCAPE_INVERTED) {
                                mOrientation = ORIENTATION_LANDSCAPE_INVERTED;
                            }
                        }
                    }

                    if (lastOrientation != mOrientation) {
                        changeRotation(mOrientation, lastOrientation);
                    }
                }
            };
        }
        if (mOrientationEventListener.canDetectOrientation()) {
            mOrientationEventListener.enable();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mOrientationEventListener.disable();
        releaseCamera();
    }

    /**
     * Performs required action to accommodate new orientation
     *
     * @param orientation
     * @param lastOrientation
     */
    private void changeRotation(int orientation, int lastOrientation) {
        switch (orientation) {
            case ORIENTATION_PORTRAIT_NORMAL:
//                mSnapButton.setImageDrawable(getRotatedImage(android.R.drawable.ic_menu_camera, 270));
//                mBackButton.setImageDrawable(getRotatedImage(android.R.drawable.ic_menu_revert, 270));
                Log.v("CameraActivity", "Orientation = 90");
                break;
            case ORIENTATION_LANDSCAPE_NORMAL:
//                mSnapButton.setImageResource(android.R.drawable.ic_menu_camera);
//                mBackButton.setImageResource(android.R.drawable.ic_menu_revert);
                Log.v("CameraActivity", "Orientation = 0");
                break;
            case ORIENTATION_PORTRAIT_INVERTED:
//                mSnapButton.setImageDrawable(getRotatedImage(android.R.drawable.ic_menu_camera, 90));
//                mBackButton.setImageDrawable(getRotatedImage(android.R.drawable.ic_menu_revert, 90));
                Log.v("CameraActivity", "Orientation = 270");
                break;
            case ORIENTATION_LANDSCAPE_INVERTED:
//                mSnapButton.setImageDrawable(getRotatedImage(android.R.drawable.ic_menu_camera, 180));
//                mBackButton.setImageDrawable(getRotatedImage(android.R.drawable.ic_menu_revert, 180));
                Log.v("CameraActivity", "Orientation = 180");
                break;
        }
    }

    /**
     * Rotates given Drawable
     *
     * @param drawableId Drawable Id to rotate
     * @param degrees    Rotate drawable by Degrees
     * @return Rotated Drawable
     */
    private Drawable getRotatedImage(int drawableId, int degrees) {
        Bitmap original = BitmapFactory.decodeResource(getResources(), drawableId);
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);

        Bitmap rotated = Bitmap.createBitmap(original, 0, 0, original.getWidth(), original.getHeight(), matrix, true);
        return new BitmapDrawable(rotated);
    }


    public void chooseCamera() {
        //if the camera preview is the front
        if (cameraFront) {
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview

                mCamera = Camera.open(cameraId);
                mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera);
            }
        } else {
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview
                mCamera = Camera.open(cameraId);
                mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera);
            }
        }
    }


    private void releaseCamera() {
        // stop and release camera
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private Camera.PictureCallback getPictureCallback() {
        Camera.PictureCallback picture = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                try {
                    // Populate image metadata

                    ContentValues image = new ContentValues();
                    // additional picture metadata
                    image.put(MediaStore.Images.Media.DISPLAY_NAME, "foto");
                    image.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");

                    switch (mOrientation) {
                        case ORIENTATION_PORTRAIT_NORMAL:
                            image.put(MediaStore.Images.Media.ORIENTATION, 90);
                            break;
                        case ORIENTATION_LANDSCAPE_NORMAL:
                            image.put(MediaStore.Images.Media.ORIENTATION, 0);
                            break;
                        case ORIENTATION_PORTRAIT_INVERTED:
                            image.put(MediaStore.Images.Media.ORIENTATION, 270);
                            break;
                        case ORIENTATION_LANDSCAPE_INVERTED:
                            image.put(MediaStore.Images.Media.ORIENTATION, 180);
                            break;
                    }

                    // store the picture
                    Uri uri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, image);

                    try {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
                                data.length);
                        OutputStream out = getContentResolver().openOutputStream(
                                uri);
                        boolean success = bitmap.compress(
                                Bitmap.CompressFormat.JPEG, 75, out);
                        out.close();
                        if (!success) {
                            finish(); // image output failed without any error,
                            // silently finish
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        // handle exceptions
                    }


                    Intent intent = new Intent();
                    intent.putExtra("image", uri);

                    intent.setData(uri);
                    setResult(Activity.RESULT_OK, intent);


                } catch (Exception e) {
                    e.printStackTrace();
                }

                finish();
            }

        };
        return picture;
    }


}

