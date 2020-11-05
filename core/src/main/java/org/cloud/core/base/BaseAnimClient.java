package org.cloud.core.base;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class BaseAnimClient {
    public static final String ALPHA = "alpha";
    public static final int TIME = 500;
    public static final String TRABSLATION_X = "translationX";
    public static final String TRABSLATION_Y = "translationY";

    public interface AnimatorCallBack {
        void onEnd();
    }

    public static void objectAnimator(View view, String str, float... fArr) {
        ObjectAnimator.ofFloat(view, str, fArr).setDuration(TIME).start();
    }

    public static void objectAnimator(View view, String str, final AnimatorCallBack animatorCallBack, float... fArr) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, str, fArr);
        ofFloat.setDuration(500).start();
        ofFloat.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (animatorCallBack != null) {
                    animatorCallBack.onEnd();
                }
            }
        });
    }

}
