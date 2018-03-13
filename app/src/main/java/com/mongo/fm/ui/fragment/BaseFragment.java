package com.mongo.fm.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 **************************************************************************************
 * company    : 
 * Filename   : BaseFragment
 * Author 	  : zhaokun
 * Description: 定义fragment的基类
 * Date		  ：2018-03-12
 * Others	  :
 **************************************************************************************
 */
public abstract  class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity(); //获取上下文对象
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return initView(); //返回子类fragment创建的View对象,不同子类可以返回不同的view对象,子类中的view对象一般都是由布局文件转换而来
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    public abstract View initView(); //由于不同的子类,View对象不同,因此这里这个定义为抽象函数,而每个子类必须实现它

    /*
      Function:
      Description:当子类需要初始化数据时,子类可以重写该方法
      Parameters:
      Return:
    */
    public void initData() {

    }
}
