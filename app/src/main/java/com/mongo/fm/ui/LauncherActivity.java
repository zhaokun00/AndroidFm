package com.mongo.fm.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate方法执行的时机是在主线程中执行的
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        //适应场景:只执行一次发送消息的操作
        Handler handler = new Handler();

        //第一步:发送消息,可以立即发送消息,也可以延时发送消息
        handler.postDelayed(new Runnable() {
            //第二步:写发送消息的具体操作
            @Override
            public void run() {
                //该函数的执行是在主线程中执行的
                Toast.makeText(LauncherActivity.this,"Hello World",Toast.LENGTH_LONG).show();
            }
        },2000);


    }

    private void startMainActivity() {

        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);


    }

}
