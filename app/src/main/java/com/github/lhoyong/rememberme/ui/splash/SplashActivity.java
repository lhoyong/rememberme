package com.github.lhoyong.rememberme.ui.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.lhoyong.rememberme.R;
import com.github.lhoyong.rememberme.databinding.ActivitySplashBinding;
import com.github.lhoyong.rememberme.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private Boolean isCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        init();
    }

    private void init() {
        activityManager();
    }

    private void activityManager() {
        thread.start();
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            startActivity(new Intent(SplashActivity.this, IntroStep1Activity.class));
//    TODO : 나중에 이 부분 지우기
            isCurrent = checkingUserData();
            changeActivity(isCurrent);
            finish();
        }
    });

    //    TODO : 나중에 이 부분 지우기
    public void changeActivity(Boolean isCurrent) {
        if (isCurrent) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));

        } else {
            startActivity(new Intent(SplashActivity.this, IntroStep1Activity.class));
        }
        finish();
    }

    //    TODO : 나중에 이 부분 지우기
    public Boolean checkingUserData() {
        SharedPreferences pref = getSharedPreferences("userData", MODE_PRIVATE);
        String userName = pref.getString("userName", "");

        if (userName != "") {
            return true;
        } else {
            return false;
        }
    }
}
