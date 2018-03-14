package com.mongo.fm.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mongo.fm.R;
import com.mongo.fm.ui.activity.OkHttpActivity;
import com.mongo.fm.ui.adapter.HomeFragmentAdapter;

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
public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();
    private ListView mlv_frameItem;
    private static final String[] mData = new String[]{"OKHttp", "xUtils3","Retrofit2","Fresco","Glide","greenDao","RxJava","volley","Gson","FastJson","picasso","evenBus","jcvideoplayer","pulltorefresh","Expandablelistview","UniversalVideoView"};
    private HomeFragmentAdapter mHomeAdapter;

    @Override
    public View initView() {

        View v = View.inflate(mContext, R.layout.fragment_home,null); //将布局文件转换为View类型对象
        mlv_frameItem = (ListView)v.findViewById(R.id.lv_frame_item);

        mlv_frameItem.setOnItemClickListener(new AdapterView.OnItemClickListener() { //设置listview条目单击事件
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = mData[position].toLowerCase(); //将字符串全部转换为小写字符串
                Intent intent = null;
                switch(data) {
                    case "okhttp":
                        intent = new Intent(mContext, OkHttpActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        return v;
    }

    public void initData() {

        mHomeAdapter = new HomeFragmentAdapter(mContext,mData);
        mlv_frameItem.setAdapter(mHomeAdapter); //设置适配器

    }
}
