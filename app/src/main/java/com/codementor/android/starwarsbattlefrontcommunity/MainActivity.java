package com.codementor.android.starwarsbattlefrontcommunity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by tonyk_000 on 12/13/2015.
 */
public class MainActivity extends FragmentActivity {

    CommunityFragment mCommunityFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCommunityFragment = new CommunityFragment();
    }
}
