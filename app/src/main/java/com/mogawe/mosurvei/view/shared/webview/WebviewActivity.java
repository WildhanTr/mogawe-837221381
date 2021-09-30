package com.mogawe.mosurvei.view.shared.webview;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.util.ExceptionHandler;
import com.mogawe.mosurvei.util.FileHelper;
import com.mogawe.mosurvei.util.ui.Animator;
import com.mogawe.mosurvei.view.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import butterknife.BindView;

public class WebviewActivity extends BaseActivity {

    private static final String REQUEST_CODE = "requestCode";

    /**
     * ===================== STATIC SECTION ============================
     */
    private static final String TITLE = "title";
    private static final String URL = "url";
    private static final String TAG = "WebviewActivity";

    public static void startActivity(BaseActivity sourceActivity, String title, String url) {
        Bundle extras = new Bundle();
        extras.putString(TITLE, title);
        extras.putString(URL, url);
        Intent intent = new Intent(sourceActivity, WebviewActivity.class);
        intent.putExtras(extras);
        sourceActivity.startActivity(intent);
    }

    public static void startActivityForResult(BaseActivity sourceActivity, String title, String url, int requestCode) {
        Bundle extras = new Bundle();
        extras.putString(TITLE, title);
        extras.putString(URL, url);
        extras.putInt(REQUEST_CODE, requestCode);
        Intent intent = new Intent(sourceActivity, WebviewActivity.class);
        intent.putExtras(extras);
        sourceActivity.startActivityForResult(intent, requestCode);
    }


    /**
     * ===================== CLASS-RELATED SECTION ============================
     */

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.webview)
    WebView webView;

    @BindView(R.id.imvScreenCapture)
    ImageView imvScreenCapture;
    @BindView(R.id.lnrButton)
    LinearLayout lnrButton;
    @BindView(R.id.btnCancel)
    ImageButton btnCancel;
    @BindView(R.id.btnCheck)
    ImageButton btnCheck;

    private Bitmap bmp;
    private TextView txvSimpan;

    @Override
    protected int layout() {
        return R.layout.a_webview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_PROGRESS);
        this.setProgressBarVisibility(true);
        super.onCreate(savedInstanceState);

        AppBarLayout.LayoutParams params = new AppBarLayout.LayoutParams(
                AppBarLayout.LayoutParams.MATCH_PARENT,
                AppBarLayout.LayoutParams.WRAP_CONTENT
        );
        System.out.println("getStatusBarHeight " + getStatusBarHeight());
        params.setMargins(0, getStatusBarHeight(), 0, 0);
        toolbar.setLayoutParams(params);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MaterialDialog.Builder(WebviewActivity.this)
//                        .backgroundColor(Color.WHITE)
//                        .title("Mohon Perhatian")
//                        .titleColor(colorPrimary)
//                        .content("Survei akan di BATALKAN.\nAnda yakin ingin mebatalkan survei?")
//                        .contentColor(colorGrey)
//                        .positiveText("Batalkan Survei")
//                        .positiveColor(colorPrimary)
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                dialog.dismiss();
//                                webView.clearCache(true);
//                                setResult(RESULT_CANCELED, new Intent());
//                                finish();
//                            }
//                        })
//                        .negativeText("Lanjutkan Survei")
//                        .negativeColor(colorGrey)
//                        .show();

                txvTitleCostumDialog.setText("Mohon Perhatian");
                txvMessageCostumDialog.setText("Survei akan di BATALKAN.\nAnda yakin ingin mebatalkan survei?");
                btnPositiveCostumDialog.setText("Batalkan Survei");
                btnPositiveCostumDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        costumDialog.dismiss();
                        webView.clearCache(true);
                        setResult(RESULT_CANCELED, new Intent());
                        finish();
                    }
                });
                btnNegativeCostumDialog.setText("Lanjutkan Survei");
                btnNegativeCostumDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        costumDialog.dismiss();
                    }
                });
                showCostumDialog("Oke&Cancel");
            }
        });
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_clear_material);

        if (getIntent().getExtras().getString(TITLE) != null && getIntent().getExtras().getString(URL) != null && getIntent().getIntExtra(REQUEST_CODE, 0) == 0) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    webView.clearCache(true);
                    setResult(RESULT_CANCELED, new Intent());
                    finish();
                }
            });
            toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_clear_material);
        }

        if (getIntent().hasExtra(REQUEST_CODE) && (getIntent().getIntExtra(REQUEST_CODE, 0) == REQUEST_TERMS_AND_CONDS)) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txvTitleCostumDialog.setText("Mohon Perhatian");
                    txvMessageCostumDialog.setText("Apa kamu sudah membaca dan menyetujui perjanjian kerja sama ini?");
                    btnPositiveCostumDialog.setText("Ya, Setuju");
                    btnPositiveCostumDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            costumDialog.dismiss();
                            webView.clearCache(true);
                            setResult(RESULT_CANCELED, new Intent());
                            finish();
                        }
                    });
                    btnNegativeCostumDialog.setText("Tidak");
                    btnNegativeCostumDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            costumDialog.dismiss();
                        }
                    });
                    showCostumDialog("Oke&Cancel");
                }
            });
            toolbar.setNavigationIcon(R.drawable.ic_done_24);
        } else if (getIntent().hasExtra(REQUEST_CODE) && (getIntent().getIntExtra(REQUEST_CODE, 0) == REQUEST_WEBVIEW)) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
//                    onBackPressed();
                }
            });
            toolbar.setNavigationIcon(R.drawable.ic_done_24);
        } else if (getIntent().hasExtra(REQUEST_CODE) && (getIntent().getIntExtra(REQUEST_CODE, 0) == REQUEST_WEB_CAPTURE)) {

            txvSimpan = new TextView(this);
            txvSimpan.setText("Ambil Gambar");
            txvSimpan.setAllCaps(false);
            txvSimpan.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            txvSimpan.setPadding(2, 2, 2, 2);
            txvSimpan.setTextColor(getResources().getColor(R.color.black));
            txvSimpan.setTypeface(txvSimpan.getTypeface(), Typeface.BOLD);
            Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.END;
            layoutParams.setMargins(8, 8, 8, 8);
            txvSimpan.setLayoutParams(layoutParams);

            txvSimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animator.popTap(v);
                    takeScreenshot();
                }
            });

            toolbar.addView(txvSimpan);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            toolbar.setNavigationIcon(R.drawable.abc_ic_clear_material);
        }


        String title = getIntent().getStringExtra(TITLE);
        setTitle(title);

        setupWeb();

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        webView.clearCache(true);
//        setResult(RESULT_CLOSED, new Intent());
        finish();
    }

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            File photoFile = FileHelper.createImageFile(getApplicationContext());
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = photoFile.getAbsolutePath();
            Uri uri = FileProvider.getUriForFile(this,
                    getString(R.string.file_provider_authority),
                    photoFile);

            txvSimpan.setVisibility(View.GONE);
            // create bitmap screen capture
            View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
            View screenView = rootView.getRootView();
            screenView.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
            screenView.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(mPath);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            txvSimpan.setVisibility(View.VISIBLE);
            openScreenshot(photoFile, mPath, bitmap, uri);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile, String filePath, Bitmap bitmap, Uri uri) {
        bmp = bitmap;
        Glide.with(this)
                .load(bmp)
                .error(R.color.grey_200)
                .into(imvScreenCapture);
//        imvScreenCapture.setScaleType(ImageView.ScaleType.CENTER_CROP);

        imvScreenCapture.setVisibility(View.VISIBLE);
        lnrButton.setVisibility(View.VISIBLE);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDatatoActivity(filePath, imageFile, uri);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imvScreenCapture.setVisibility(View.GONE);
                lnrButton.setVisibility(View.GONE);
            }
        });
    }

    private void sendDatatoActivity(String filePath, File imageFile, Uri uri) {
        Intent intent = new Intent();
        intent.putExtra("filePath", filePath);
        intent.putExtra("file", imageFile);
        intent.putExtra("uri", uri.toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    private Bitmap getBitmap(String path) {

        File photoFile = null;
        try {
            photoFile = FileHelper.createImageFile(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        path = photoFile.getAbsolutePath();
        Uri uri = FileProvider.getUriForFile(this,
                getString(R.string.file_provider_authority),
                photoFile);
        InputStream in = null;
        try {
            final int IMAGE_MAX_SIZE = 1200000; // 1.2MP
            in = getContentResolver().openInputStream(uri);

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, o);
            in.close();

            int scale = 1;
            while ((o.outWidth * o.outHeight) * (1 / Math.pow(scale, 2)) >
                    IMAGE_MAX_SIZE) {
                scale++;
            }
            Log.d(TAG, "scale = " + scale + ", orig-width: " + o.outWidth + ", orig-height: " + o.outHeight);

            Bitmap bitmap = null;
            in = getContentResolver().openInputStream(uri);
            if (scale > 1) {
                scale--;
                // scale to max possible inSampleSize that still yields an image
                // larger than target
                o = new BitmapFactory.Options();
                o.inSampleSize = scale;
                bitmap = BitmapFactory.decodeStream(in, null, o);

                // resize to desired dimensions
                int height = bitmap.getHeight();
                int width = bitmap.getWidth();
                Log.d(TAG, "1th scale operation dimenions - width: " + width + ", height: " + height);

                double y = Math.sqrt(IMAGE_MAX_SIZE
                        / (((double) width) / height));
                double x = (y / height) * width;

                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) x,
                        (int) y, true);
                bitmap.recycle();
                bitmap = scaledBitmap;

                System.gc();
            } else {
                bitmap = BitmapFactory.decodeStream(in);
            }
            in.close();

            Log.d(TAG, "bitmap size - width: " + bitmap.getWidth() + ", height: " +
                    bitmap.getHeight());
            return bitmap;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    private void setupWeb() {
        if (getIntent().hasExtra(URL)) {
            String url = getIntent().getStringExtra(URL);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            webSettings.setDomStorageEnabled(true);
            webView.addJavascriptInterface(new WebAppInterface(this), "Android");
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    pb.setVisibility(View.VISIBLE);
                    System.out.println("APA url nya started" + url);

                    if (url.equals("https://www.change.org/")) {
                        webView.clearCache(true);
                        setResult(RESULT_FINISH, new Intent());
                        finish();
                    }
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    pb.setVisibility(View.GONE);
                    System.out.println("APA url nya finished " + url);

                    if (url.equals("https://www.change.org/")) {
                        webView.clearCache(true);
                        setResult(RESULT_FINISH, new Intent());
                        finish();
                    }

//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                        webView.evaluateJavascript("(function() {return window.location.href;})", new ValueCallback<String>() {
//                            @Override
//                            public void onReceiveValue(String url) {
//                                //do your scheme with variable "url"
//                            }
//                        });
//                    } else {
//                        webView.loadUrl("javascript:Android.getURL(window.location.href);");
//                    }

                }

                @Nullable
                @Override
                public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

                    if (request.getUrl().equals("https://www.change.org/")) {
                        webView.clearCache(true);
                        setResult(RESULT_FINISH, new Intent());
                        finish();
                    }

                    return super.shouldInterceptRequest(view, request);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    System.out.println("APA url nya " + url);
                    //System.out.println("OUT >> 2 " + url);
                    if (url.equals("https://mogawe.id/status=complete")) {
                        webView.clearCache(true);
                        setResult(RESULT_COMPLETE, new Intent());
                        finish();
                    } else if (url.equals("https://mogawe.id/status=screenout")) {
                        webView.clearCache(true);
                        setResult(RESULT_SCREENOUT, new Intent());
                        finish();
                    } else if (url.equals("https://mogawe.id/status=quotafull")) {
                        webView.clearCache(true);
                        setResult(RESULT_QUOTAFULL, new Intent());
                        finish();
                    } else if (url.equals("http://global.yougov.com/survey-closed/")) {
                        webView.clearCache(true);
                        setResult(RESULT_CLOSED, new Intent());
                        finish();
                    } else if (url.equals("https://www.google.com/")) {
                        webView.clearCache(true);
                        setResult(RESULT_CLOSED, new Intent());
                        finish();
                    } else if (url.equals("https://www.change.org/p/kemenhub151-tertibkan-perusahaan-transportasi-online-yang-nggak-rahasiakan-nomer-pengguna/psf/promote_or_share")) {
                        webView.clearCache(true);
                        setResult(RESULT_COMPLETE, new Intent());
                        finish();
                    } else if (url.equals("https://www.change.org/")) {
                        webView.clearCache(true);
                        setResult(RESULT_FINISH, new Intent());
                        finish();
                    }
                    return super.shouldOverrideUrlLoading(view, url);
                }

                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    //System.out.println("OUT >> 3 " + request.getUrl().toString());
                    System.out.println("APA url nya " + request.getUrl());
                    if (request.getUrl().toString().equals("https://mogawe.id/status=complete")) {
                        webView.clearCache(true);
                        setResult(RESULT_COMPLETE, new Intent());
                        finish();
                    } else if (request.getUrl().toString().equals("https://mogawe.id/status=screenout")) {
                        webView.clearCache(true);
                        setResult(RESULT_SCREENOUT, new Intent());
                        finish();
                    } else if (request.getUrl().toString().equals("https://mogawe.id/status=quotafull")) {
                        webView.clearCache(true);
                        setResult(RESULT_QUOTAFULL, new Intent());
                        finish();
                    } else if (request.getUrl().toString().equals("http://global.yougov.com/survey-closed/")) {
                        webView.clearCache(true);
                        setResult(RESULT_CLOSED, new Intent());
                        finish();
                    } else if (request.getUrl().toString().equals("https://www.google.com/")) {
                        webView.clearCache(true);
                        setResult(RESULT_CLOSED, new Intent());
                        finish();
                    } else if (url.equals("https://www.change.org/p/kemenhub151-tertibkan-perusahaan-transportasi-online-yang-nggak-rahasiakan-nomer-pengguna/psf/promote_or_share")) {
                        webView.clearCache(true);
                        setResult(RESULT_COMPLETE, new Intent());
                        finish();
                    } else if (url.equals("https://www.change.org/")) {
                        webView.clearCache(true);
                        setResult(RESULT_FINISH, new Intent());
                        finish();
                    }
                    return super.shouldOverrideUrlLoading(view, request);
                }
            });
            webView.loadUrl(url);
        } else {
            finish();
        }
    }

    public class WebAppInterface {
        Activity mContext;

        public WebAppInterface(Activity c) {
            mContext = c;
        }

        @JavascriptInterface
        public void getURL(final String url) {
            mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //do your scheme with variable "url" in UIThread side. Over here you can call any method inside your activity/fragment
                    if (url.equals("https://www.change.org/")) {
                        webView.clearCache(true);
                        setResult(RESULT_FINISH, new Intent());
                        finish();
                    }

                }
            });

        }

    }
}
