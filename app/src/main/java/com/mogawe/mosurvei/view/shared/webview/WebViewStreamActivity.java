package com.mogawe.mosurvei.view.shared.webview;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.model.bean.YoutubeNote;
import com.mogawe.mosurvei.model.db.entity.Fact;
import com.mogawe.mosurvei.model.db.entity.Section;
import com.mogawe.mosurvei.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import im.delight.android.webview.AdvancedWebView;

public class WebViewStreamActivity extends BaseActivity implements AdvancedWebView.Listener {

    private static final String FACT = "fact";
    private static final String SECTION = "section";

    @BindView(R.id.toolbar)
    Toolbar appBar;
    @BindView(R.id.txvFactName)
    public TextView textView;
    @BindView(R.id.linearChild)
    public LinearLayout linearChild;
    @BindView(R.id.txvHint)
    public TextView txvHint;
    @BindView(R.id.btnAddNotes)
    public Button btnAddNotes;
    @BindView(R.id.webview_player_view)
    public AdvancedWebView webView;

    public Fact fact;
    public Section section;
    public String label;
    public String hint;
    public String factId;
    public Boolean is_visible;
    public String input;
    public String selectionValidate;
    public Boolean isDisabled;
    public String urlTutorial;
    public Boolean isMandatory;
    public String videoUrl;
    public String value;
    public List<YoutubeNote> youtubeNoteList = new ArrayList<>();

    public static void startActivityForResult(BaseActivity sourceActivity, int requestCode, Section section, Fact fact) {

        Intent intent = new Intent(sourceActivity, WebViewStreamActivity.class);
        intent.putExtra(FACT, fact);
        intent.putExtra(SECTION, section);
        sourceActivity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected int layout() {
        return R.layout.a_web_view_stream;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(appBar);
        setTitle("Kembali untuk simpan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        appBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fact = (Fact) getIntent().getSerializableExtra(FACT);
        section = (Section) getIntent().getSerializableExtra(SECTION);

        label = fact.getLabel();
        hint = fact.getHint_name();
        factId = fact.getId_fact();
        is_visible = fact.getIs_visible();
        input = fact.getInput();
        selectionValidate = fact.getSelection_validate();
        isDisabled = fact.getDisabled();
        urlTutorial = fact.getPictureUrl();
        isMandatory = fact.getIs_mandatory();
        videoUrl = fact.getVideoUrl();
        value = fact.getValue();

        if (!input.equals("")) {
            Gson gson = new Gson();
            youtubeNoteList = gson.fromJson(fact.getInput(), new TypeToken<List<YoutubeNote>>() {
            }.getType());

            System.out.println("sfdsxxsc " + youtubeNoteList.get(0).getNote());
        }

        webView.setListener(this, this);
        webView.loadUrl(videoUrl);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webView.getSettings().setUseWideViewPort(true);
        webView.setInitialScale(50);
        webView.getSettings().setBuiltInZoomControls(true);

        textView.setText(label);
        txvHint.setText(hint);

        if (youtubeNoteList.size() > 0) {
            addNotes();
        }

        btnAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YoutubeNote youtubeNote = new YoutubeNote();

                // Add Notes View
                View youtubeNoteView = getLayoutInflater().inflate(R.layout.ai_webview_notes, null);
                RelativeLayout rvParent = youtubeNoteView.findViewById(R.id.rvParent);
                EditText edtVideoTime = youtubeNoteView.findViewById(R.id.edtVideoTime);
                EditText edtNote = youtubeNoteView.findViewById(R.id.edtNote);
                ImageButton btnDeleteNotes = youtubeNoteView.findViewById(R.id.btnDeleteNotes);
                HorizontalScrollView templateScroll = youtubeNoteView.findViewById(R.id.templateScroll);
                LinearLayout lnrButtonTemplate = youtubeNoteView.findViewById(R.id.lnrButtonTemplate);
                TextView txvPilih = youtubeNoteView.findViewById(R.id.txvPilih);

                for (String noteValue : value.split(",")) {
                    System.out.println("wafdsd" + noteValue);
                    View templateView = getLayoutInflater().inflate(R.layout.ai_template_note, null);
                    Button btnTemplate = templateView.findViewById(R.id.btnTemplate);
                    btnTemplate.setText(noteValue);
                    btnTemplate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (btnTemplate.getText().equals("Lainnya")) {
                                edtNote.setEnabled(true);
                            } else {
                                edtNote.setEnabled(false);
                                edtNote.setText(noteValue);
                            }

                            txvPilih.setVisibility(View.GONE);
                            templateScroll.setVisibility(View.GONE);
                        }
                    });

                    lnrButtonTemplate.addView(templateView);
                }

                youtubeNoteView.setTag(R.string.item_view_name, edtVideoTime.getText().toString());

                edtNote.setTag(R.string.fact_id, fact.getId_fact());
                edtNote.setTag(R.string.is_mandatory, fact.getIs_mandatory());

                youtubeNote.setTime(edtVideoTime.getText().toString());
                youtubeNote.setNote(edtNote.getText().toString());

//                edtVideoTime.setFocusable(false);
//                edtVideoTime.setFocusableInTouchMode(false);
//                edtVideoTime.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Calendar mcurrentTime = Calendar.getInstance();
//                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//                        int minute = mcurrentTime.get(Calendar.MINUTE);
//                        int second = mcurrentTime.get(Calendar.SECOND);
//                        MyTimePickerDialog mTimePicker = new MyTimePickerDialog(WebViewStreamActivity.this, new MyTimePickerDialog.OnTimeSetListener() {
//
//                            @Override
//                            public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker view, int hourOfDay, int minute, int seconds) {
//                                // TODO Auto-generated method stub
//                                if (hourOfDay > 5) {
//                                    Toast.makeText(WebViewStreamActivity.this, "Waktu tidak boleh lebih dari 5 jam", Toast.LENGTH_LONG).show();
//                                } else {
//                                    edtVideoTime.setText(String.format("%02d", hourOfDay) +
//                                            ":" + String.format("%02d", minute) +
//                                            ":" + String.format("%02d", seconds));
//                                }
//                            }
//
//                        }, hour, minute, second, true);
//                        mTimePicker.setTitle("Pilih waktu");
//                        mTimePicker.show();
//                    }
//                });

                edtVideoTime.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        youtubeNoteView.setTag(R.string.item_view_name, edtVideoTime.getText().toString());

                        youtubeNote.setTime(edtVideoTime.getText().toString());
                        youtubeNote.setNote(edtNote.getText().toString());
                    }
                });

                edtNote.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        youtubeNote.setTime(edtVideoTime.getText().toString());
                        youtubeNote.setNote(edtNote.getText().toString());
                    }
                });

                btnDeleteNotes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        for (int ii = 0; ii < linearChild.getChildCount(); ii++) {
                            View v = linearChild.getChildAt(ii);
                            // Do something with v.
//                            if (v.getTag(R.string.view_position) != null && v.getTag(R.string.view_position).equals(i.getItem_name())) {
//                                linearChild.removeView(v);
//                            }
                            if (v.getTag(R.string.item_view_name) != null && v.getTag(R.string.item_view_name).equals(edtVideoTime.getText().toString())) {
                                ((ViewManager) v.getParent()).removeView(v);
                                youtubeNoteList.remove(youtubeNoteList.indexOf(youtubeNote));
                                break;
                            }

                        }
                        linearChild.requestLayout();
                    }
                });

                rvParent.requestFocus();

                youtubeNoteList.add(youtubeNote);

                linearChild.addView(youtubeNoteView);
            }
        });
    }

    private void checkInputBeforeBack() {
        if (youtubeNoteList.size() > 0) {
            Gson gson = new Gson();

            String youtubeNotesList = gson.toJson(youtubeNoteList);

            Toast.makeText(this, youtubeNotesList, Toast.LENGTH_LONG).show();

            Intent intent = new Intent();
            intent.putExtra("youtubeNotesList", youtubeNotesList);
            intent.putExtra(SECTION, section);
            intent.putExtra(FACT, fact);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            onBackPressed();
        }
    }

    private void addNotes() {
        System.out.println("youfsddfsads " + youtubeNoteList.size());
        for (YoutubeNote youtubeNote : youtubeNoteList) {
            String time = youtubeNote.time;
            String notes = youtubeNote.note;

            // Add Nosfdsxxsctes View
            View youtubeNoteView = getLayoutInflater().inflate(R.layout.ai_webview_notes, null);
            EditText edtVideoTime = youtubeNoteView.findViewById(R.id.edtVideoTime);
            EditText edtNote = youtubeNoteView.findViewById(R.id.edtNote);
            ImageButton btnDeleteNotes = youtubeNoteView.findViewById(R.id.btnDeleteNotes);
            HorizontalScrollView templateScroll = youtubeNoteView.findViewById(R.id.templateScroll);
            LinearLayout lnrButtonTemplate = youtubeNoteView.findViewById(R.id.lnrButtonTemplate);
            TextView txvPilih = youtubeNoteView.findViewById(R.id.txvPilih);

            if (notes != null)
                lnrButtonTemplate.setVisibility(View.GONE);

            for (String noteValue : value.split(",")) {
                View templateView = getLayoutInflater().inflate(R.layout.ai_template_note, null);
                Button btnTemplate = templateView.findViewById(R.id.btnTemplate);
                btnTemplate.setText(noteValue);
                btnTemplate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (btnTemplate.getText().equals("Lainnya")) {
                            edtNote.setEnabled(true);
                        } else {
                            edtNote.setEnabled(false);
                            edtNote.setText(noteValue);
                        }

                        txvPilih.setVisibility(View.GONE);
                        templateScroll.setVisibility(View.GONE);
                    }
                });

                lnrButtonTemplate.addView(templateView);
            }

            youtubeNoteView.setTag(R.string.item_view_name, edtVideoTime.getText().toString());

            edtNote.setTag(R.string.fact_id, fact.getId_fact());
            edtNote.setTag(R.string.is_mandatory, fact.getIs_mandatory());

            youtubeNote.setTime(edtVideoTime.getText().toString());
            youtubeNote.setNote(edtNote.getText().toString());

            edtVideoTime.setText(time);
//            edtVideoTime.setFocusable(false);
//            edtVideoTime.setFocusableInTouchMode(false);
//            edtVideoTime.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Calendar mcurrentTime = Calendar.getInstance();
//                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//                    int minute = mcurrentTime.get(Calendar.MINUTE);
//                    int second = mcurrentTime.get(Calendar.SECOND);
//                    MyTimePickerDialog mTimePicker = new MyTimePickerDialog(WebViewStreamActivity.this, new MyTimePickerDialog.OnTimeSetListener() {
//
//                        @Override
//                        public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker view, int hourOfDay, int minute, int seconds) {
//                            // TODO Auto-generated method stub
//                            if (hourOfDay > 5) {
//                                Toast.makeText(WebViewStreamActivity.this, "Waktu tidak boleh lebih dari 5 jam", Toast.LENGTH_LONG).show();
//                            } else {
//                                edtVideoTime.setText(String.format("%02d", hourOfDay) +
//                                        ":" + String.format("%02d", minute) +
//                                        ":" + String.format("%02d", seconds));
//                            }
//                        }
//
//                    }, hour, minute, second, true);
//                    mTimePicker.setTitle("Pilih waktu");
//                    mTimePicker.show();
//                }
//            });

            edtVideoTime.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    youtubeNoteView.setTag(R.string.item_view_name, edtVideoTime.getText().toString());

                    youtubeNote.setTime(edtVideoTime.getText().toString());
                    youtubeNote.setNote(edtNote.getText().toString());
                }
            });

            edtNote.setText(notes);
            edtNote.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    youtubeNote.setTime(edtVideoTime.getText().toString());
                    youtubeNote.setNote(edtNote.getText().toString());
                }
            });

            btnDeleteNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (int ii = 0; ii < linearChild.getChildCount(); ii++) {
                        View v = linearChild.getChildAt(ii);
                        // Do something with v.
//                            if (v.getTag(R.string.view_position) != null && v.getTag(R.string.view_position).equals(i.getItem_name())) {
//                                linearChild.removeView(v);
//                            }
                        if (v.getTag(R.string.item_view_name) != null && v.getTag(R.string.item_view_name).equals(edtVideoTime.getText().toString())) {
                            ((ViewManager) v.getParent()).removeView(v);
                            youtubeNoteList.remove(youtubeNoteList.indexOf(youtubeNote));

                            break;
                        }

                    }
                    linearChild.requestLayout();
                }
            });

            youtubeNoteList.add(youtubeNote);

            linearChild.addView(youtubeNoteView);
        }
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }

    @Override
    public void onBackPressed() {
        if (youtubeNoteList.size() > 0) {
            Gson gson = new Gson();

            String youtubeNotesList = gson.toJson(youtubeNoteList);

            Intent intent = new Intent();
            intent.putExtra("youtubeNotesList", youtubeNotesList);
            intent.putExtra(SECTION, section);
            intent.putExtra(FACT, fact);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            finish();
        }
    }
}
