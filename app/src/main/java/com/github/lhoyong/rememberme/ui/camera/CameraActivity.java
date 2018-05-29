package com.github.lhoyong.rememberme.ui.camera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.lhoyong.rememberme.R;
import com.github.lhoyong.rememberme.databinding.ActivityCameraBinding;

public class CameraActivity extends AppCompatActivity {

    private ActivityCameraBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

    @Override
    public void onBackPressed() {

    }
}
