package com.linyuechang.awesome.ctripsearch;

import android.app.Application;
import android.content.Context;

/**
 * Created by yuechanglin on 2016-11-7.
 */
public class MyApplication extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
