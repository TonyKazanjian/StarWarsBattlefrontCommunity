package com.codementor.android.starwarsbattlefrontcommunity.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.codementor.android.starwarsbattlefrontcommunity.R;
import com.codementor.android.starwarsbattlefrontcommunity.utils.OSUtils;

/**
 * Created by tonykazanjian on 5/13/16.
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OSUtils.setWindowNotificationBleed(getWindow());
        setContentView(R.layout.activity_welcome_screen);
    }
}
