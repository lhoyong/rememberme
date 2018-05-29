package com.github.lhoyong.rememberme.ui.camera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;

import com.github.lhoyong.rememberme.R;
import com.github.lhoyong.rememberme.databinding.ActivityCameraBinding;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private ActivityCameraBinding binding;
    private SurfaceHolder surfaceHolder;
    private Camera camera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_camera);

        init();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onResume() {
        super.onResume();

      /*  if (textureView.isAvailable()) {

            openCamera();
        } else {
            textureView.setSurfaceTextureListener(this);
        }*/
    }

    private void init() {

        checkPermission();

    }

    // 권한 확인
    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
            }
        } else {
            surfaceHolder = binding.cameraSurfaceview.getHolder();
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
        }
    }

    private void openCamera() {

        if (camera != null) {
            try {
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
            } catch (IOException e) {

            }

        }
    }

    private void closeCamera() {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        openCamera();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
