package com.dev.tanphamanh.chiasenhac.base.listener;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

import static android.view.View.GONE;
import static com.dev.tanphamanh.chiasenhac.BuildConfig.DEBUG;


/**
 * Created by pes10 on 3/1/2017.
 * click and fade. move out side an drop then do nothing, only drop inside then do action
 */

public abstract class WidgetTouchListener implements View.OnTouchListener {
    private static final String TAG = WidgetTouchListener.class.getSimpleName();
    public static final int FADE_ANIMATION_DURATION = 50;
    private View view;
    private View parentView;
    private Rect hitRect;
    private boolean fadeAfterClick;

    public WidgetTouchListener(View view, View parentView) {
        this.view = view;
        this.parentView = parentView;
        fadeAfterClick = false;
    }

    public WidgetTouchListener(View view) {
        this.view = view;
        this.parentView = null;
        fadeAfterClick = false;
    }

    public void setFadeAfterClick(boolean fadeAfterClick) {
        this.fadeAfterClick = fadeAfterClick;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        hitRect = new Rect(0, 0, view.getWidth(), view.getHeight());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                AlphaAnimation aa = new AlphaAnimation(1, 0.5f);
                aa.setInterpolator(new AccelerateInterpolator());
                aa.setDuration(FADE_ANIMATION_DURATION);
                aa.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        view.setAlpha(0.5f);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
//                aa.setFillAfter(true);
                view.startAnimation(aa);
                break;
            case MotionEvent.ACTION_UP:
                if (hitRect.contains((int) event.getX(), (int) event.getY())) {
                    if (!fadeAfterClick) {
                        view.clearAnimation();
                        view.setAlpha(1);
                        if (DEBUG) {
                            Log.d(TAG, "fadeAfterClick = false view alpha: " + view.getAlpha());
                        }
                        doAction();
                    } else {
                        if (parentView == null) {
                            AlphaAnimation aa1 = new AlphaAnimation(0.5f, 0);
                            aa1.setInterpolator(new AccelerateInterpolator());
                            aa1.setDuration(FADE_ANIMATION_DURATION);
//                    aa1.setFillAfter(true);
                            view.setAlpha(1);
                            aa1.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    view.setVisibility(GONE);
                                    doAction();
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            view.startAnimation(aa1);
                        } else {
                            AlphaAnimation aa1 = new AlphaAnimation(1, 0);
                            aa1.setInterpolator(new AccelerateInterpolator());
                            aa1.setDuration(FADE_ANIMATION_DURATION);
//                    aa1.setFillAfter(true);
                            aa1.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    parentView.setVisibility(GONE);
                                    view.setAlpha(1);
                                    doAction();
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            parentView.startAnimation(aa1);
                        }
                    }

                } else {
                    AlphaAnimation aa1 = new AlphaAnimation(0.5f, 1);
                    aa1.setInterpolator(new DecelerateInterpolator());
                    aa1.setDuration(FADE_ANIMATION_DURATION);
                    aa1.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            view.setAlpha(1);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    view.startAnimation(aa1);
                }

                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    public abstract void doAction();
}
