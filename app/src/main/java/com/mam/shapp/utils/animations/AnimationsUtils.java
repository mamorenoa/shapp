package com.mam.shapp.utils.animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.mam.shapp.R;

public class AnimationsUtils {

    public static final String MATERIAL_IN_BLOCK = "materialInBlock";
    public static final String MATERIAL_IN_BLOCK_WITHOUT_SLIDE = "materialInNoSlide";


    public static void animateRecycler(final View view) {
        animateRecycler(view, Gravity.BOTTOM, Gravity.BOTTOM);
    }

    public static void animateRecycler(final View view, final int delayDirection, final int slideDirection) {
        if (view != null) {
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    initAnimation(view, 0, 0, convertGravity(view, delayDirection),
                            convertGravity(view, slideDirection));
                    return true;
                }
            });
            view.invalidate();
        }
    }

    private static int convertGravity(View view, int gravity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            boolean isRtl = view.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
            if (gravity == Gravity.START) {
                gravity = isRtl ? Gravity.RIGHT : Gravity.LEFT;
            } else if (gravity == Gravity.END) {
                gravity = isRtl ? Gravity.LEFT : Gravity.RIGHT;
            }
        }
        return gravity;
    }

    private static void initAnimation(View view, int offsetX, int offsetY, int delayDir, int slideDir) {
        if (offsetX < 0) {
            offsetX = 0;
        }
        if (offsetY < 0) {
            offsetY = 0;
        }
        if (view instanceof ViewGroup && ((ViewGroup) view).getChildCount() > 0 &&
                !MATERIAL_IN_BLOCK.equals(view.getTag()) &&
                !MATERIAL_IN_BLOCK_WITHOUT_SLIDE.equals(view.getTag())) {
            ViewGroup viewGroup = (ViewGroup) view;
            int viewHeight = viewGroup.getHeight();
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                int nextOffsetX = offsetX + ((delayDir == Gravity.RIGHT) ? child.getLeft() :
                        (delayDir == Gravity.LEFT ? viewHeight - child.getRight() : 0));
                int nextOffsetY = offsetY + ((delayDir == Gravity.BOTTOM) ? child.getTop() :
                        (delayDir == Gravity.TOP ? viewHeight - child.getBottom() : 0));
                initAnimation(child, nextOffsetX, nextOffsetY, delayDir, slideDir);
            }
        } else {
            final Resources res = view.getResources();
            int slideTranslation = res.getDimensionPixelSize(R.dimen.material_in_anim_slide_offset);
            if (MATERIAL_IN_BLOCK_WITHOUT_SLIDE.equals(view.getTag())) {
                slideTranslation = 0;
            }
            int multY = 0;
            if (slideDir == Gravity.TOP) {
                multY = 1;
            } else if (slideDir == Gravity.BOTTOM) {
                multY = -1;
            }
            int multX = 0;
            if (slideDir == Gravity.LEFT) {
                multX = 1;
            } else if (slideDir == Gravity.RIGHT) {
                multX = -1;
            }
            int delayOffset = delayDir == Gravity.TOP || delayDir == Gravity.BOTTOM ? offsetY : offsetX;
            float delayDenominator = res.getDimension(R.dimen.material_in_delay_denominator);
            long delay = (long) (delayOffset / delayDenominator);
            startAnimators(view, slideTranslation * multX, slideTranslation * multY, delay);
        }
    }

    private static void startAnimators(final View view, int startOffsetX, int startOffsetY, long delay) {
        if (view.getVisibility() == View.VISIBLE && view.getAlpha() != 0f) {
            view.clearAnimation();
            view.animate().cancel();
            final Resources res = view.getResources();
            final float endAlpha = view.getAlpha();
            final float endTranslateX = view.getTranslationX();
            final float endTranslateY = view.getTranslationY();
            view.setAlpha(0);
            final Animator fade = ObjectAnimator.ofFloat(view, View.ALPHA, endAlpha);
            fade.setDuration(res.getInteger(R.integer.material_in_fade_anim_duration));
            fade.setInterpolator(new AccelerateInterpolator());
            fade.setStartDelay(delay);
            fade.start();
            ViewPropertyAnimator slide = view.animate();
            if (startOffsetY != 0) {
                view.setTranslationY(startOffsetY);
                slide.translationY(endTranslateY);
            } else {
                view.setTranslationX(startOffsetX);
                slide.translationX(endTranslateX);
            }
            slide.setInterpolator(new DecelerateInterpolator(2));
            slide.setDuration(res.getInteger(R.integer.material_in_slide_anim_duration));
            slide.setStartDelay(delay);
            slide.setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationCancel(Animator animation) {
                    if (fade.isStarted()) {
                        fade.cancel();
                    }
                    view.setAlpha(endAlpha);
                    view.setTranslationX(endTranslateX);
                    view.setTranslationY(endTranslateY);
                }
            });
            slide.start();
        }
    }

    public static void showViewAnimate(final View view) {
        view.animate()
                .alpha(1.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        view.setVisibility(View.VISIBLE);
                        view.setAlpha(0.0f);
                    }
                });
    }

    public static void hideViewAnimate(final View view) {
        view.animate()
                .alpha(0.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.GONE);
                    }
                });
    }

}
