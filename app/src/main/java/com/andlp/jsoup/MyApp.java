package com.andlp.jsoup;

import com.andlib.lp.AndApp;

import org.xutils.x;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MyApp extends AndApp {

    @Override public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
