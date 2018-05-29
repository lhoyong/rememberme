package com.github.lhoyong.rememberme.ui.camera;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.github.lhoyong.rememberme.R;
import com.github.lhoyong.rememberme.databinding.ActivityCameraBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnTouchListener {

    private ActivityCameraBinding binding;
    private SurfaceHolder surfaceHolder;
    private Camera camera;

    // dragging
    private boolean firstDragging = true;
    private boolean dragging = false;
    private float startPosition = 0;
    private float endPosition = 0;


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
    }

    private void init() {

        checkPermission();

        binding.imageView.setOnTouchListener(this);

        binding.cameraPressed.setOnClickListener(v -> {
            camera.takePicture(shutterCallback, pictureCallback_Raw, pictureCallback_JPG);

        });
    }

    private Camera.ShutterCallback shutterCallback = () -> {

    };

    private Camera.PictureCallback pictureCallback_Raw = (data, camera) -> {

    };

    private Camera.PictureCallback pictureCallback_JPG = (data, camera) -> {
        Log.e("tt", "tt");
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());

        OutputStream outputStream;

        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "tt.png");
            FileOutputStream fileOutputStream = openFileOutput("aa.png", 0);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
          /*  outputStream = getContentResolver().openOutputStream(uri);
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();*/
            Log.e("uri", uri.toString());

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    };

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
                    if ((startPosition < endPosition) && (endPosition - startPosition) > 300) {
                        finish();
                        overridePendingTransition(R.anim.slide_down, R.anim.slide_down_out);
                    }

                }
                startPosition = 0;
                endPosition = 0;
                dragging = false;
                break;
        }
        return true;
    }
}
