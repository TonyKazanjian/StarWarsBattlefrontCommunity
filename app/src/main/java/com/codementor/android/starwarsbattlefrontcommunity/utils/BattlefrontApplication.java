package com.codementor.android.starwarsbattlefrontcommunity.utils;

import android.content.Context;

/**
 * Created by tonyk_000 on 3/3/2016.
 */
public class BattlefrontApplication extends android.app.Application {

    private static BattlefrontApplication singleton;

    public static BattlefrontApplication getSingleton(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    public static Context getContext(){
        return singleton.getBaseContext();
    }
}
