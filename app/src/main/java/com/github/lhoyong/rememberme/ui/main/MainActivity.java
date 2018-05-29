package com.github.lhoyong.rememberme.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.github.lhoyong.rememberme.R;
import com.github.lhoyong.rememberme.adapter.MainPageAdapter;
import com.github.lhoyong.rememberme.databinding.ActivityMainBinding;
import com.github.lhoyong.rememberme.ui.camera.CameraActivity;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ActivityMainBinding binding;

    // dragging
    private boolean firstDragging = true;
    private boolean dragging = false;
    private float startPosition = 0;
    private float endPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    private void init() {

        MainPageAdapter adapter = new MainPageAdapter(this);
        binding.mainViewpager.setAdapter(adapter);

        binding.mainViewpager.setOnTouchListener(this);

    }

    // 터치 이벤트
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                dragging = true;
                if (firstDragging) {
                    startPosition = event.getY();
                    firstDragging = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                endPosition = event.getY();
                firstDragging = true;
                if (dragging) {
                    if ((startPosition > endPosition) && (startPosition - endPosition) > 500) {
                        Intent intent = new Intent(this, CameraActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_up, R.anim.slide_up_out);
                    } else if ((startPosition < endPosition) && (endPosition - startPosition) > 500) {


                    }

                }
                startPosition = 0;
                endPosition = 0;
                dragging = false;
                break;
        }
        return false;
    }
}
