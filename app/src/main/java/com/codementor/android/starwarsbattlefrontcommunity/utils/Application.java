package com.codementor.android.starwarsbattlefrontcommunity.utils;

import android.content.Context;

/**
 * Created by tonyk_000 on 3/3/2016.
 */
public class Application extends android.app.Application {

    private static Application singleton;

    public static Application getSingleton(){
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
