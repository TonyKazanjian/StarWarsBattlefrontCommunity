package com.codementor.android.starwarsbattlefrontcommunity.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.codementor.android.starwarsbattlefrontcommunity.R;
import com.codementor.android.starwarsbattlefrontcommunity.utils.OSUtils;

/**
 * Created by tonykazanjian on 5/13/16.
 */
public class WelcomeActivity extends AppCompatActivity {

    private EditText mEmailInput;
    private EditText mPasswordInput;
    private Button mSignInButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OSUtils.setWindowNotificationBleed(getWindow());
        setContentView(R.layout.activity_welcome_screen);

        mEmailInput = (EditText) findViewById(R.id.email_input);
        mPasswordInput = (EditText) findViewById(R.id.password_input);
        mSignInButton = (Button) findViewById(R.id.sign_in_button);
    }
}
