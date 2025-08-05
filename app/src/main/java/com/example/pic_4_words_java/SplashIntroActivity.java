package com.example.pic_4_words_java;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_intro);

        LottieAnimationView appStartAnimation = findViewById(R.id.splashAnimation);
        appStartAnimation.setSpeed(2.5f);


        appStartAnimation.addAnimatorListener(new Animator.AnimatorListener() {


            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


                Intent intent = new Intent(SplashIntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
