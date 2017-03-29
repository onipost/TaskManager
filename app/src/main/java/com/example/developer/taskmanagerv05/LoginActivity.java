package com.example.developer.taskmanagerv05;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends Activity {
    private final int APILEVEL = Build.VERSION.SDK_INT;
    private Button mLoginButton;
    private ImageView mButtonRing;
    private DataBase database;
    private SQLiteDatabase instance;
    private EditText mUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        animateBgGradient(findViewById(R.id.gradient_login));
        database = new DataBase(this);
        instance = database.getWritableDatabase();
        Cursor usersData = database.selectAll(instance,"Users");
        if(usersData.getCount()!=0){
            Intent intent = new Intent(getApplicationContext(), TaskGroupsActivity.class);
            startActivity(intent);
        }
        mLoginButton = (Button) findViewById(R.id.login_button);
        mButtonRing = (ImageView) findViewById(R.id.button_login_ring);
        mUsername = (EditText) findViewById(R.id.login_editview);
        mLoginButton.setOnClickListener(clickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }

    private void animatorButton(View view, View view_ring){
        Animator anim =  AnimatorInflater.loadAnimator(this, R.animator.button_login);
        anim.setTarget(view);
        anim.start();
        Animator anim_ring = AnimatorInflater.loadAnimator(this, R.animator.button_login_ring);
        anim_ring.setTarget(view_ring);
        anim_ring.start();
        anim_ring.addListener(buttonListener);
    }
    private void animateBgGradient(View view){
        AnimationDrawable animationDrawable =(AnimationDrawable)view.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
    }

    /* LISTENERS */
    Animator.AnimatorListener buttonListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            Intent intent = new Intent(getApplicationContext(), TaskGroupsActivity.class);
            startActivity(intent);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    };
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mUsername.getText().toString().trim().length()==0){
                if(APILEVEL<16){
                    mUsername.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_bottom_red));
                }else{
                    mUsername.setBackgroundResource(R.drawable.border_bottom_red);
                }
                mUsername.setPadding(2, 0, 2, 5);
            }else{
                animatorButton(mLoginButton, mButtonRing);
                ContentValues cv = new ContentValues();
                cv.put("name", mUsername.getText().toString());
                database.insert(instance, "Users", cv);
            }
        }
    };
}
