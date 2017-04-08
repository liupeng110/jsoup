package com.andlp.jsoup.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import org.xutils.x;

/**
 * Created by Administrator on 2017/3/31.
 */

public class Activity_Base extends FragmentActivity {
    boolean inject=false;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!inject){
            x.view().inject(this); inject=true;
        }

    }
}
