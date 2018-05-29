package com.github.lhoyong.rememberme.ui.splash;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.lhoyong.rememberme.R;
import com.github.lhoyong.rememberme.databinding.ActivityIntroStep1Binding;

public class IntroStep1Activity extends AppCompatActivity {

    private ActivityIntroStep1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro_step1);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        init();
    }

    private void init() {
        hideButton();
        waitTime();
        nextButtonListener();
    }

    private void waitTime() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showButton();
            }
        },2000);
    }

    private void hideButton() {
        binding.introNextButton.setVisibility(View.GONE);
    }

    private void showButton() {
        binding.introNextButton.setVisibility(View.VISIBLE);
    }

    private void nextButtonListener() {
        binding.introNextButton.setOnClickListener(__->{
            startActivity(new Intent(IntroStep1Activity.this, IntroStep2Activity.class));
            finish();
        });
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
