package com.firebasekorea.chat.activities;

import android.content.Intent;
import android.os.Bundle;

import com.firebasekorea.chat.R;
import com.firebasekorea.chat.utils.SharedPreferenceUtil;
import com.firebasekorea.chat.utils.UserUtil;

public class StartActivity extends BaseActivity {
    private final String TAG = StartActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        SharedPreferenceUtil.getInstance().init(getApplicationContext());

        String userName = UserUtil.loadUserName();
        switchActivity(userName);
    }


    private void switchActivity(String userName) {
        if (userName == null || userName.length() < 1) {
            callUserActivity();
        }
        else {
            callMainActivity();
        }
    }


    private void callMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void callUserActivity() {
        startActivity(new Intent(this, UserActivity.class));
        finish();
    }

}
