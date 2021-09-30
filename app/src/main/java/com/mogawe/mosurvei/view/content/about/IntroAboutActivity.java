package com.mogawe.mosurvei.view.content.about;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mogawe.mosurvei.R;
import com.mogawe.mosurvei.util.ExceptionHandler;
import com.mogawe.mosurvei.view.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WIN10 on 17/12/2017.
 */

public class IntroAboutActivity extends BaseActivity {

    private ViewPager viewPager;
    private IntroAboutActivity.MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private CardView crdLogin, crdButton;
    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
//            if (position == layouts.length - 1) {
//                // last page. make button text to GOT IT
//                btnNext.setText(getString(R.string.start));
//                btnSkip.setVisibility(View.GONE);
//            } else if (position == 0) {
//                btnNext.setText("HOW?");
////                btnSkip.setVisibility(View.VISIBLE);
//                btnNext.setTextColor(getResources().getColor(R.color.white));
//                btnSkip.setTextColor(getResources().getColor(R.color.white));
//            } else if (position == 2) {
//                btnNext.setTextColor(getResources().getColor(R.color.black));
//                btnSkip.setTextColor(getResources().getColor(R.color.black));
//            } else if (position == 1) {
//                btnNext.setTextColor(getResources().getColor(R.color.white));
//                btnSkip.setTextColor(getResources().getColor(R.color.white));
//                btnNext.setText(getString(R.string.next));
//            } else if (position == 3) {
//                btnNext.setTextColor(getResources().getColor(R.color.white));
//                btnSkip.setTextColor(getResources().getColor(R.color.white));
//            } else if (position == 4) {
//                btnNext.setTextColor(getResources().getColor(R.color.black));
//                btnSkip.setTextColor(getResources().getColor(R.color.black));
//                btnNext.setText(getString(R.string.next));
////                btnSkip.setVisibility(View.VISIBLE);
//            } else if (position == 5) {
//                btnNext.setTextColor(getResources().getColor(R.color.white));
//                btnSkip.setTextColor(getResources().getColor(R                                                                                                     .color.white));
//            } else {
//                // still pages are left
//                btnNext.setText(getString(R.string.next));
////                btnSkip.setVisibility(View.VISIBLE);
//            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public static void startActivity(Activity sourceActivity) {
        sourceActivity.startActivity(new Intent(sourceActivity, IntroAboutActivity.class));
        sourceActivity.finish();
    }
    public static void startActivityWithNoFinish(Activity sourceActivity) {
        sourceActivity.startActivity(new Intent(sourceActivity, IntroAboutActivity.class));
    }

    @Override
    protected int layout() {
        return R.layout.a_intro;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        crdLogin = (CardView) findViewById(R.id.crdLogin);
        crdButton = (CardView) findViewById(R.id.crdButton);

        crdLogin.setVisibility(View.GONE);
        crdButton.setVisibility(View.GONE);

//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) dotsLayout.getLayoutParams();
//        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//
//        dotsLayout.setLayoutParams(params);

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.intro_slide2,
                R.layout.intro_slide3,
                R.layout.intro_slide4,};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

//        btnSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                launchHomeScreen();
//            }
//        });
//
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // checking for last page
//                // if last page home screen will be launched
//                int current = getItem(+1);
//                if (current < layouts.length) {
//                    // move to next screen
//                    viewPager.setCurrentItem(current);
//                } else {
//                    launchHomeScreen();
//                }
//            }
//        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 3000, 5000);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
//        startActivity(new Intent(IntroAboutActivity.this, MainActivity.class));
        finish();
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            IntroAboutActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < layouts.length - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1 );
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}
