package com.mogawe.mosurvei.playground;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.view.BaseActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MSQuestActivity extends BaseActivity implements Callback {

    private static final String TAG = "MSQuestActivity";

    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private Camera mCamera;
    private boolean previewRunning;
    private MediaRecorder mMediaRecorder;
    private final int maxDurationInMs = 20000;
    private final long maxFileSizeInBytes = 500000;
    private final int videoFramesPerSecond = 20;
    Button btn_record;
    boolean mInitSuccesful = false;
    File file;

    List<String> questions = new ArrayList<>();

    @Override
    protected int layout() {
        return R.layout.a_ms_quest;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layoutIG = findViewById(R.id.layoutIG);

        questions.add("Apakah anda disapa dengan senyuman ketika masuk ke toko?");
        questions.add("Apakah penjaga toko memakai seragam dan berpenampilan menarik dan bersih?");
        questions.add("Apakah store Manager hadir dan menyambut Anda?");
        questions.add("Saat Anda berbelanja, apakah penjaga toko berada dalam jarak maksimal 5 meter dan siap memberikan bantuan?");
        questions.add("Apakah Penjaga toko memberikan solusi terhadap spek handphone yang sedang anda cari?");
        questions.add("Apakah penjaga toko juga menawarkan aksesoris xiaomi lainnya?");


        mSurfaceView = (SurfaceView) findViewById(R.id.surface_camera);
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
//        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        Button btnStart = findViewById(R.id.btnStartIG);
        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!checkPermission()) {
                        requestPermission();
                    } else {
                        layoutIG.setVisibility(View.VISIBLE);
                        mMediaRecorder.start();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnStop = findViewById(R.id.btnStop);
        btnStop.setVisibility(View.GONE);
        btnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    layoutIG.setVisibility(View.GONE);
                    mMediaRecorder.stop();
                    mMediaRecorder.reset();
                    try {
                        initRecorder(mHolder.getSurface());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        //add the view via xml or programmatically
        SwipeFlingAdapterView flingContainer = findViewById(R.id.frame);


        //choose your favorite adapter
        ArrayAdapter<String> arrayAdapter = new MSQuestIGArrayAdapter(this, questions);
        //ArrayAdapter<String> arrayAdapter = null;

        //set the listener and the adapter
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                questions.remove(0);
                arrayAdapter.notifyDataSetChanged();

                if (questions.size() == 0) {
                    btnStop.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(MSQuestActivity.this, "Tidak", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MSQuestActivity.this, "Ya", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                System.out.println("OUT >> EMPTY");
                // Toast.makeText(MSQuestActivity.this, "Habis", Toast.LENGTH_SHORT).show();
//                al.add("XML ".concat(String.valueOf(i)));
//                arrayAdapter.notifyDataSetChanged();
//                Log.d("LIST", "notified");
//                i++;
            }

            @Override
            public void onScroll(float v) {

            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                //makeToast(MyActivity.this, "Clicked!");
            }
        });

    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(),
                CAMERA);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission() {
        ActivityCompat.requestPermissions(this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO, CAMERA}, REQUEST_TAKE_VIDEO);
    }

    private void initRecorder(Surface surface) throws IOException {
        // It is very important to unlock the camera before doing setCamera
        // or it will results in a black preview
        if (mCamera == null) {
            mCamera = Camera.open();
            mCamera.unlock();
        }

        if (mMediaRecorder == null)
            mMediaRecorder = new MediaRecorder();

        mMediaRecorder.setPreviewDisplay(surface);
        mMediaRecorder.setCamera(mCamera);

        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);

        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);

        mMediaRecorder.setOutputFile(this.initFile().getAbsolutePath());

        // No limit. Don't forget to check the space on disk.
        mMediaRecorder.setMaxDuration(50000);
        mMediaRecorder.setVideoFrameRate(24);
        mMediaRecorder.setVideoSize(1280, 720);
        mMediaRecorder.setVideoEncodingBitRate(3000000);
        mMediaRecorder.setAudioEncodingBitRate(8000);

        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            // This is thrown if the previous calls are not called with the
            // proper order
            e.printStackTrace();
        }

        mInitSuccesful = true;
    }

    private File initFile() {
        // File dir = new
        // File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES),
        // this
        File dir = new File(Environment.getExternalStorageDirectory(), this
                .getClass().getPackage().getName());


        if (!dir.exists() && !dir.mkdirs()) {
            Log.wtf(TAG,
                    "Failed to create storage directory: "
                            + dir.getAbsolutePath());
            Toast.makeText(MSQuestActivity.this, "not record", Toast.LENGTH_SHORT);
            file = null;
        } else {
            file = new File(dir.getAbsolutePath(), new SimpleDateFormat(
                    "'IMG_'yyyyMMddHHmmss'.mp4'").format(new Date()));
        }
        return file;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            if (!mInitSuccesful)
                initRecorder(mHolder.getSurface());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void shutdown() {
        // Release MediaRecorder and especially the Camera as it's a shared
        // object that can be used by other applications
        mMediaRecorder.reset();
        mMediaRecorder.release();
        mCamera.release();

        // once the objects have been released they can't be reused
        mMediaRecorder = null;
        mCamera = null;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        shutdown();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }
}
