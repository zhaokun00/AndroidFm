package com.mongo.fm.ui.fragment;

import android.view.View;

import com.mongo.fm.R;

/*
 **************************************************************************************
 * company    : 
 * Filename   : HomeFragment
 * Author 	  : zhaokun
 * Description: 
 * Date		  ：2018-03-12
 * Others	  :
 **************************************************************************************
 */
public class CustomerFragment extends BaseFragment {

    @Override
    public View initView() {

        View v = View.inflate(mContext, R.layout.fragment_customer,null); //将布局文件转换为View类型对象

        return v;
    }
}
