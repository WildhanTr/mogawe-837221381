package com.mogawe.mosurvei.util.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

import com.mogawe.mosurvei.R;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * Created by glenrynaldi on 6/22/15.
 */
public class Animator {

    public static final int VERY_SHORT = 200;
    public static final int SHORT = 400;
    public static final int MEDIUM = 800;
    public static final int LONG = 1200;
    public static final int VERY_VERY_SHORT = 75;


    private static ObjectAnimator rotateAnim;

    public static void disappear(final View view, int duration) {

        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0.0f);
        alpha.setDuration(duration);

        alpha.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.INVISIBLE);
                view.setAlpha(0);
            }
        }, duration);
    }

    public static void disappearDelayed(final View view, final int duration, int delayDuration) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0.0f);
                alpha.setDuration(duration);

                alpha.start();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(View.INVISIBLE);
                        view.setAlpha(0);
                    }
                }, duration);
            }
        }, delayDuration);


    }

    public static void disappearFrom(final View view, float currentAlpha, int duration) {
        view.setAlpha(currentAlpha);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0.0f);
        alpha.setDuration(duration);

        alpha.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.INVISIBLE);
                view.setAlpha(0);
            }
        }, duration);
    }

    public static void appear(final View view, int duration) {
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1.0f);
        alpha.setDuration(duration);

        alpha.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                view.setAlpha(1.0f);
            }
        }, duration);
    }


    public static void appearFrom(final View view, float currentAlpha, int duration) {

        view.setAlpha(currentAlpha);

        view.setVisibility(View.VISIBLE);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1.0f);
        alpha.setDuration(duration);

        alpha.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                view.setAlpha(1.0f);
            }
        }, duration);
    }


    public static void rotate(View view, Float fromDegree, Float toDegree, int duration) {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", fromDegree, toDegree);
        rotate.setDuration(duration);

        rotate.start();
    }

    public static void translateVertical(View view, float fromPoint, float toPoint, Integer duration) {
        view.setVisibility(View.VISIBLE);

        view.setY(fromPoint);

        final ObjectAnimator translate = ObjectAnimator.ofFloat(view, "translationY", toPoint);
        translate.setDuration(duration);
        translate.start();
    }

    public static void translateHorizontal(View view, float fromPoint, float toPoint, Integer duration) {
        view.setVisibility(View.VISIBLE);

        view.setX(fromPoint);

        final ObjectAnimator translate = ObjectAnimator.ofFloat(view, "translationX", toPoint);
        translate.setDuration(duration);
        translate.start();
    }

    public static void translateHorizontal(View view, float toPoint, Integer duration) {
        view.setVisibility(View.VISIBLE);

        final ObjectAnimator translate = ObjectAnimator.ofFloat(view, "translationX", toPoint);
        translate.setDuration(duration);
        translate.start();
    }


    public static void scale(final View view, float fromPoint, final float toPoint, Integer duration) {
        view.setVisibility(View.VISIBLE);


        view.setScaleX(fromPoint);
        view.setScaleY(fromPoint);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", toPoint);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", toPoint);

        AnimatorSet scale = new AnimatorSet();
        scale.setDuration(duration);
        scale.play(scaleX).with(scaleY);
        scale.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setScaleX(toPoint);
                view.setScaleY(toPoint);
            }
        }, duration);
    }


    public static void scaleTopRight(final View view, float fromPoint, final float toPoint, Integer duration) {
        view.setVisibility(View.VISIBLE);

        view.setPivotY(0);
        view.setPivotX(view.getWidth());

        view.setScaleX(fromPoint);
        view.setScaleY(fromPoint);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", toPoint);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", toPoint);

        AnimatorSet scale = new AnimatorSet();
        scale.setDuration(duration);
        scale.play(scaleX).with(scaleY);
        scale.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setScaleX(toPoint);
                view.setScaleY(toPoint);
            }
        }, duration);
    }


//    public static void translatePopFromTop(View view) {
//        view.setVisibility(View.VISIBLE);
//
//        view.setY((float) -view.getHeight());
//
//        ObjectAnimator translatePopFromTopp40 = ObjectAnimator.ofFloat( "translationY", 40);
//        translatePopFromTopp40.setDuration(200);
//
//        ObjectAnimator translatePopFromTopm30 = ObjectAnimator.ofFloat( "translationY", -30);
//        translatePopFromTopm30.setDuration(200);
//
//        ObjectAnimator translatePopFromTopp20 = ObjectAnimator.ofFloat( "translationY", 20);
//        translatePopFromTopp20.setDuration(100);
//
//        ObjectAnimator translatePopFromTopm10 = ObjectAnimator.ofFloat( "translationY", -10);
//        translatePopFromTopm10.setDuration(50);
//
//        ObjectAnimator translatePopFromTop = ObjectAnimator.ofFloat( "translationY", 0);
//        translatePopFromTop.setDuration(50);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(translatePopFromTopp40);
//        animatorSet.play(translatePopFromTopm30).after(translatePopFromTopp40);
//        animatorSet.play(translatePopFromTopp20).after(translatePopFromTopm30);
//        animatorSet.play(translatePopFromTopm10).after(translatePopFromTopp20);
//        animatorSet.play(translatePopFromTop).after(translatePopFromTopm10);
//        animatorSet.start();
//    }


    public static void translatePopToTop(final View view) {
        view.setVisibility(View.VISIBLE);

        ObjectAnimator translatePopFromTopp20 = ObjectAnimator.ofFloat(view, "translationY", 20);
        translatePopFromTopp20.setDuration(200);

        ObjectAnimator translatePopFromTopmh = ObjectAnimator.ofFloat(view, "translationY", -view.getHeight());
        translatePopFromTopmh.setDuration(200);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translatePopFromTopmh).after(translatePopFromTopp20);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.INVISIBLE);
            }
        }, 400);
    }

    public static void translateToTopAndAppear(final View view) {
        view.setVisibility(View.VISIBLE);

        view.setAlpha(0);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1.0f);
        alpha.setDuration(200);

        ObjectAnimator translatePopFromTopp20 = ObjectAnimator.ofFloat(view, "translationY", 20);
        translatePopFromTopp20.setDuration(200);

        ObjectAnimator translatePopFromTopmh = ObjectAnimator.ofFloat(view, "translationY", 0);
        translatePopFromTopmh.setDuration(200);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translatePopFromTopmh).after(translatePopFromTopp20).with(alpha);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.start();

    }

    public static void translateToDownAndDisappear(final View view) {
        view.setVisibility(View.VISIBLE);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0.0f);
        alpha.setDuration(200);

        ObjectAnimator translatePopFromTopp20 = ObjectAnimator.ofFloat(view, "translationY", 20);
        translatePopFromTopp20.setDuration(200);

        ObjectAnimator translatePopFromTopmh = ObjectAnimator.ofFloat(view, "translationY", view.getHeight());
        translatePopFromTopmh.setDuration(200);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translatePopFromTopmh).after(translatePopFromTopp20).with(alpha);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.start();

    }

//    public static void circularReveal(View  int x, int y, float startRadius, float finalRadius, int duration) {
//        SupportAnimator animator = ViewAnimationUtils.createCircularReveal( x, y, startRadius, finalRadius);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.setDuration(duration);
//        animator.start();
//    }
//
//    public static void circularDismiss(final View  int x, int y, float finalRadius, int duration) {
//        SupportAnimator animator = ViewAnimationUtils.createCircularReveal( x, y, finalRadius, 0);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.setDuration(duration);
//        animator.start();
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                view.setVisibility(View.INVISIBLE);
//            }
//        }, duration);
//    }

//    public static void popView(final View view, int duration) {
//        view.setVisibility(View.VISIBLE);
//
//        view.setScaleX(0.0f);
//        view.setScaleY(0.0f);
//
//        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat( "scaleX", 1.0f);
//        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat( "scaleY", 1.0f);
//
//        scaleUpX.setDuration(duration);
//        scaleUpY.setDuration(duration);
//
//        AnimatorSet scaleUp = new AnimatorSet();
//        scaleUp.play(scaleUpX).with(scaleUpY);
//        scaleUp.start();
//    }


    public static void popTap(View view) {

        view.setVisibility(View.VISIBLE);
        view.setAlpha(1.0f);

        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight() / 2);

        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 0.7f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 0.7f);

        scaleDownX.setDuration(100);
        scaleDownY.setDuration(100);

        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f);
        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f);

        scaleUpX.setDuration(100);
        scaleUpY.setDuration(100);

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);
        scaleDown.start();

        AnimatorSet scaleUp = new AnimatorSet();
        scaleUp.play(scaleUpX).with(scaleUpY).after(scaleDown);
        scaleUp.start();

    }


    public static void popDisappear(final View view) {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 0.0f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 0.0f);

        scaleDownX.setDuration(200);
        scaleDownY.setDuration(200);

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);
        scaleDown.start();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                disappear(view, 100);
            }
        }, 100);

    }


    public static void shakeView(final View view, long delay) {
        view.setVisibility(View.VISIBLE);

        float origin = view.getX();

        view.setX(origin);

        final ObjectAnimator translate1 = ObjectAnimator.ofFloat(view, "translationX", origin - 10);
        translate1.setDuration(50);
        final ObjectAnimator translate2 = ObjectAnimator.ofFloat(view, "translationX", origin + 10);
        translate2.setDuration(50);
        final ObjectAnimator translate3 = ObjectAnimator.ofFloat(view, "translationX", origin - 5);
        translate3.setDuration(50);
        final ObjectAnimator translate4 = ObjectAnimator.ofFloat(view, "translationX", origin + 5);
        translate4.setDuration(50);
        final ObjectAnimator translate5 = ObjectAnimator.ofFloat(view, "translationX", origin);
        translate5.setDuration(50);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scale(view, 1, 0.7f, 100);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AnimatorSet shake = new AnimatorSet();
                        shake.playSequentially(translate1, translate2, translate3, translate4, translate5);
                        shake.setInterpolator(new AccelerateInterpolator());
                        shake.start();
                    }
                }, 300);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scale(view, 0.7f, 1, 100);
                    }
                }, 550);
            }
        }, delay);


    }


    public static void translateHorizontalCenterParent(View view, View parentView, Integer duration) {
        view.setVisibility(View.VISIBLE);


        float toPoint = (parentView.getWidth() / 2) - (view.getWidth() / 2);

        final ObjectAnimator translate = ObjectAnimator.ofFloat(view, "translationX", -toPoint);
        translate.setDuration(duration);
        translate.start();
    }


    public static void animateRotate(View view) {

        rotateAnim = ObjectAnimator.ofFloat(view, "rotation", 0, 360);
        rotateAnim.setDuration(Animator.MEDIUM);
        rotateAnim.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnim.setRepeatMode(ValueAnimator.RESTART);
        rotateAnim.start();
    }


    public static void stopAnimateRotate(View view) {
        if (rotateAnim != null) {
            rotateAnim.cancel();
        }
    }

    public static void slideUpAnimationView(View view) {

        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(300);
        view.startAnimation(animate);

    }

    public static void slideDownAnimationView(View view) {

        view.setVisibility(View.GONE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(300);
        view.startAnimation(animate);

    }

    public static void slideUpAnimationViewFromTop(View view) {

        view.setVisibility(View.GONE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        view.startAnimation(animate);

    }

    public static void slideDownAnimationViewFromTop(View view) {

        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        view.startAnimation(animate);

    }

    public static void startBlinkingAnimation(View view) {
        Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);
        view.startAnimation(startAnimation);
    }

    public static void stopAnimation(View view) {
        view.clearAnimation();
    }

    public static void expand(final View v, Integer height) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (height != null) {
                    v.getLayoutParams().height = interpolatedTime == 1
                            ? height
                            : (int) (targetHeight * interpolatedTime);
                    v.requestLayout();
                } else {
                    v.getLayoutParams().height = interpolatedTime == 1
                            ? ViewGroup.LayoutParams.WRAP_CONTENT
                            : (int) (targetHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Expansion speed of 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v, Integer height) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    if (height != null) {
                        v.setVisibility(View.VISIBLE);
                    } else {
                        v.setVisibility(View.GONE);
                    }
                } else {
                    if (height != null)
                        v.getLayoutParams().height = initialHeight - (int) (height * interpolatedTime);
                    else
                        v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);

                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Collapse speed of 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }
}
