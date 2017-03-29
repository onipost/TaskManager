package com.example.developer.taskmanagerv05;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class AnimateBuilder {
    private Context context;
    public AnimateBuilder(Context c) {
        context = c;
    }
    public void objectAnimator(View view, int animatorId) {
        Animator anim = AnimatorInflater.loadAnimator(context, animatorId);
        anim.setTarget(view);
        anim.start();
    }
    public void objectAnimator(View view, int animatorId, Animator.AnimatorListener listener) {
        Animator anim = AnimatorInflater.loadAnimator(context, animatorId);
        anim.addListener(listener);
        anim.setTarget(view);
        anim.start();
    }
    public void animatorUtils(View view, int animatorId){
        Animation anim = AnimationUtils.loadAnimation(context, animatorId);
        view.startAnimation(anim);
    }
}
