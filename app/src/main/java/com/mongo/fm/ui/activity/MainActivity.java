package com.mongo.fm.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.mongo.fm.R;
import com.mongo.fm.ui.fragment.BaseFragment;
import com.mongo.fm.ui.fragment.CustomerFragment;
import com.mongo.fm.ui.fragment.HomeFragment;
import com.mongo.fm.ui.fragment.OtherFragment;
import com.mongo.fm.ui.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

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

    private static final String TAG = MainActivity.class.getSimpleName();
    private RadioGroup mRg_content;
    private List<BaseFragment> mListFragment;
    private BaseFragment mOldFragment;
    private static final int POSITION_HOME = 0;
    private static final int POSITION_THIRD = 1;
    private static final int POSITION_CUSTOMER = 2;
    private static final int POSITION_OTHER = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mRg_content = (RadioGroup) findViewById(R.id.rg_content);
    }

    private void initData() {

        mListFragment = new ArrayList<BaseFragment>();

        //向列表中添加需要的Fragment对象
        mListFragment.add(new HomeFragment());
        mListFragment.add(new ThirdFragment());
        mListFragment.add(new CustomerFragment());
        mListFragment.add(new OtherFragment());

        //RadioGroup设置选择监听,用户需要重写当选中某一项时的回调方法
        mRg_content.setOnCheckedChangeListener(new RgContentListener());
        mRg_content.check(R.id.rb_home); //最开始时没有被选中的项,当调用该函数时会使某一项被选中,会导致调用RadioGroup.OnCheckedChangeListener里面的方法,如果直接在xml文件中设置 android:checked="true"该属性,不会调用RadioGroup.OnCheckedChangeListener里面的方法
    }

    private class RgContentListener implements RadioGroup.OnCheckedChangeListener {

        //返还的参数为RadioGroup对象和选中的RadioButton对象的id值,这些id值是在R文件中定义的
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            int position = 0;
            switch(checkedId) {
                case R.id.rb_home:
                    position = POSITION_HOME;
                    break;
                case R.id.rb_third:
                    position = POSITION_THIRD;
                    break;
                case R.id.rb_customer:
                    position = POSITION_CUSTOMER;
                    break;
                case R.id.rb_other:
                    position = POSITION_OTHER;
                    break;
                default:
            }

            BaseFragment fg = getFragment(position);
            jumpToFragment(mOldFragment,fg);
        }
    }

    private BaseFragment getFragment(int position) {

        return mListFragment.get(position);
    }

    /*
      Function:
      Description:采用这种方式时,在未添加时走这几个方法,如果添加了将来就不会再次创建
        onAttach
        onCreate
        onCreateView
        onActivityCreated
        onStart
        onResume
      Parameters:
      Return:
    */
    private void jumpToFragment(BaseFragment src, BaseFragment dst) {

        if(src != dst) {
            mOldFragment = dst;
            FragmentManager fm = getSupportFragmentManager(); //获取FragmentManager对象
            FragmentTransaction transaction = fm.beginTransaction(); //开启事务,同一个事务中不能提交两次,否则会运行错误

            //判断该fragment是否被添加,如果没有添加,则先添加
            if(!dst.isAdded()) {

                if(null != src) {
                    transaction.hide(src); //隐藏之前的fragment
                }

                if(null != dst) {
                    transaction.add(R.id.fl_content, dst); //添加新的fragment
                    transaction.commit();
                }
            }
            else {
                if(null != src) {
                    transaction.hide(src);
                }
                if(null != dst) {
                    transaction.show(dst); //直接显示切换的fragment
                    transaction.commit();
                }
            }
        }

    }

    /*
    当fragment发生替换时fragment会重新走一遍生命周期

        onAttach
        onCreate
        onCreateView
        onActivityCreated
        onStart
        onResume
        onPause
        onStop
        onDestroyView
        onDestroy
        onDetach

    */
    /*
    private void jumpToFragment(BaseFragment fg) {

        FragmentManager fm = getSupportFragmentManager(); //获取FragmentManager对象
        FragmentTransaction transaction = fm.beginTransaction(); //开启事务

        transaction.replace(R.id.fl_content,fg);
        transaction.commit(); //提交事务
    }
    */

}
