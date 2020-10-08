package com.ws.hugs.common.ani;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.List;

public class LoadingAnimation {


    private static final int DEFAULT_MARGIN = 10;

    public static ValueAnimator getLoadingTranslate(List<ImageView> viewList, int nums, int screenWidth){

        if(nums>9) nums = 9;
        final int startX = 0;
        final int deltaX = screenWidth;
        viewList.stream().forEach(e->{
            e.setMaxWidth(20);
            e.setMinimumWidth(20);
        });

        ValueAnimator animator = ValueAnimator.ofInt(0,1).setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        final boolean[] start = {true};
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (start[0]){
                    animation.setInterpolator(new AccelerateInterpolator());
                }else {
                    animation.setInterpolator(new DecelerateInterpolator());
                }
                start[0] = !start[0];
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        return animator;
    }

    public static void getLoadingTranslate1(ImageView img, int screenWidth){

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(img, "translationX", 0, screenWidth / 2);
        translationX.setInterpolator(new DecelerateInterpolator());


        ObjectAnimator translationX1 = ObjectAnimator.ofFloat(img, "translationX", screenWidth / 2, screenWidth);
        translationX1.setInterpolator(new AccelerateInterpolator());
        translationX1.setStartDelay(5000);
        animatorSet.playSequentially(
                translationX,
                translationX1
        );
        animatorSet.start();
    }
}
