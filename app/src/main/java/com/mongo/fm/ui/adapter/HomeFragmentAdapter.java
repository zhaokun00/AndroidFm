package com.mongo.fm.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/*
 **************************************************************************************
 * company    : 
 * Filename   : HomeFragmentAdapter
 * Author 	  : zhaokun
 * Description: 
 * Date		  ：2018-03-13
 * Others	  :
 **************************************************************************************
 */
public class HomeFragmentAdapter extends BaseAdapter {

    //final类型数据必须进行初始化操作,一般情况下是在构造函数中进行初始化操作
    private final Context mContext;
    private final String[] mData;

    public HomeFragmentAdapter(Context cx,String[] data) {
        mContext = cx;
        mData = data;
    }

    //继承BaseAdapter类后要实现以下方法
    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView v = null;
        String data = mData[position];

        if(null == convertView) { //可以省略移不断的new操作
            v = new TextView(mContext);
        }
        else {
            v = (TextView)convertView;
        }

        v.setPadding(10,10,10,10);
        v.setTextSize(20);
        v.setText(data);
        return v;
    }
}
