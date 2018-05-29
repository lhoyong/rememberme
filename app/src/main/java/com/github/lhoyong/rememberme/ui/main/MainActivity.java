package com.github.lhoyong.rememberme.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                dragging = true;
                if (firstDragging) {
                    startPosition = event.getY();
                    Log.e("y", String.valueOf(event.getY()));
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
                    }
                    //시작 Y가 끝 보다 작다면 터치가 위에서 아래로 이러우졌다는 것이고, 스크롤이 올라갔다는 뜻이다.
                    else if ((startPosition < endPosition) && (endPosition - startPosition) > 500) {


                    }

                }
                startPosition = 0;
                endPosition = 0;
                dragging = false;
                break;
            case MotionEvent.ACTION_SCROLL:
                Log.e("dee", "ee");
                break;

        }
        return false;
    }
}
