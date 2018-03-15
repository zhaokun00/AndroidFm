package com.mongo.fm.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mongo.fm.R;
import com.mongo.fm.ui.activity.FastJsonActivity;
import com.mongo.fm.ui.activity.GsonActivity;
import com.mongo.fm.ui.activity.NativeJsonActivity;
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
    private static final String[] mData = new String[]{"OkHttp","NativeJson","Gson","FastJson","xUtils3","Retrofit2","Fresco","Glide","greenDao","RxJava","volley","picasso","evenBus","jcvideoplayer","pulltorefresh","Expandablelistview","UniversalVideoView"};
    private HomeFragmentAdapter mHomeAdapter;

    @Override
    public View initView() {

        View v = View.inflate(mContext, R.layout.fragment_home,null); //将布局文件转换为View类型对象
        mlv_frameItem = (ListView)v.findViewById(R.id.lv_frame_item);

        mlv_frameItem.setOnItemClickListener(new AdapterView.OnItemClickListener() { //设置listview条目单击事件
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = mData[position];
                Intent intent = null;
                switch(data) {
                    case "OkHttp":
                        intent = new Intent(mContext, OkHttpActivity.class);
                        break;
                    case "NativeJson":
                        intent = new Intent(mContext, NativeJsonActivity.class);
                        break;
                    case "Gson":
                        intent = new Intent(mContext, GsonActivity.class);
                        break;
                    case "FastJson":
                        intent = new Intent(mContext, FastJsonActivity.class);
                        break;
                }

                if(intent != null) {
                    startActivity(intent);
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
