package com.github.lhoyong.rememberme.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.github.lhoyong.rememberme.R;
import com.github.lhoyong.rememberme.databinding.ActivityIntroStep2Binding;
import com.github.lhoyong.rememberme.ui.main.MainActivity;

public class IntroStep2Activity extends AppCompatActivity {

    private ActivityIntroStep2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro_step2);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        init();
    }

    private void init() {
        inputNameListener();
    }

    private void inputNameListener() {
        editTextChecker();
        editTextEnter();
    }

    private void editTextChecker() {

        EditText inputFinish = binding.introNameFormEditText;

        inputFinish.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled;

                hideKeyboard(inputFinish);
                handled = true;

                return handled;
            }

            private void hideKeyboard(EditText inputFinish) {
                InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                keyboard.hideSoftInputFromWindow(inputFinish.getWindowToken(),0);
            }
        });

    }

    private void editTextEnter() {
        binding.introNameFormEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setOnButton(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            private void setOnButton(CharSequence s) {
                if (s.length() != 0) {
                    binding.introNameFormUnderBar.setBackgroundColor(Color.rgb(28, 28, 28));
                    binding.linearLayoutButton.setBackgroundColor(Color.rgb(28, 28, 28));
                    buttonClickListener();
                } else {
                    binding.introNameFormUnderBar.setBackgroundColor(Color.rgb(184, 184, 184));
                    binding.linearLayoutButton.setBackgroundColor(Color.rgb(184, 184, 184));
                }
            }

            private void buttonClickListener() {
                binding.linearLayoutButton.setOnClickListener(__->{
                    // TODO : 이 부분 추후에 지우기
                    String userName = binding.introNameFormEditText.getText().toString();
                    setUserData(userName);
                    startActivity(new Intent(IntroStep2Activity.this, MainActivity.class));
                });
            }

            // TODO : 이 부분 추후에 지우기
            public void setUserData(String userName) {
                SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName",userName);
                editor.commit();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}