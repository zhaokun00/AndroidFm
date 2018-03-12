package com.mongo.fm.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.mongo.fm.R;


/*
 **************************************************************************************
 * company    : 
 * Filename   : MainActivity.java
 * Author 	  : zhaokun
 * Description: 兼容3.0以下的版本,继承了FragmentActivity,如果为3.0以上版本可以直接继承Activity,Activity类继承与Context类
 * Date		  ：2018/3/12
 * Others	  :
 **************************************************************************************
 */

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
