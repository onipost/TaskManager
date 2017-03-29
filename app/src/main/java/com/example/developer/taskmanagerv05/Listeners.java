package com.example.developer.taskmanagerv05;

import android.animation.Animator;
import android.view.View;

public class Listeners {
    public Animator.AnimatorListener reverseModalOverlayListener(final View overlay) {
        Animator.AnimatorListener listener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                overlay.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        };
        return listener;
    }

}
