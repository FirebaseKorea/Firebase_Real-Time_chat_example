package com.firebasekorea.chat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebasekorea.chat.R;
import com.firebasekorea.chat.utils.ToastUtil;
import com.firebasekorea.chat.utils.UserUtil;

/**
 * Created by namhoonkim on 20/02/2017.
 */

public class UserActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = UserActivity.class.getSimpleName();

    /* View Component */
    private EditText mUserInputName;
    private Button mUserButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        init();
    }

    private void init() {
        mUserInputName = (EditText) findViewById(R.id.user_input_name);
        mUserButton = (Button) findViewById(R.id.user_button);
        mUserButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_button:
                onClickUserButton();
                break;
            default:
                break;
        }
    }

    private void onClickUserButton() {
        String userName = mUserInputName.getText().toString();
        if (userName == null || userName.length() < 1) {
            ToastUtil.makeShortToast(this, "Please input your name");
        }
        else {
            ToastUtil.makeShortToast(this, "Your name is : " + userName);
            UserUtil.saveUserName(userName);
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
