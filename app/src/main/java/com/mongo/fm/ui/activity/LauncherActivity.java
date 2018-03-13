package com.mongo.fm.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mongo.fm.R;

/*
 **************************************************************************************
 * company    : 
 * Filename   : LauncherActivity.java
 * Author 	  : zhaokun
 * Description: App启动界面
 * Date		  ：2018/3/10
 * Others	  :
 **************************************************************************************
 */
 
public class LauncherActivity extends Activity {

    private Handler mHandler;
    private int mDelayTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate方法执行的时机是在主线程中执行的
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        mHandler = new Handler();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        },mDelayTime); //延时mDelayTime时间跳转到主界面
    }

    private void startMainActivity() {

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent); //跳转到主页面

        finish(); //销毁该Activity
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mHandler.removeCallbacks(null);
    }
}
