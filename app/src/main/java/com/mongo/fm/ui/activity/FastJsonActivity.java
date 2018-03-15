package com.mongo.fm.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mongo.fm.R;
import com.mongo.fm.bean.Goods;

import java.util.ArrayList;
import java.util.List;

public class FastJsonActivity extends Activity {

    private Button mBtn_transfer1;
    private Button mBtn_transfer2;
    private Button mBtn_transfer3;
    private Button mBtn_transfer4;
    private TextView mTv_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_json);

        initView();
        initListener();
    }

    private void initView() {

        mBtn_transfer1 = (Button) findViewById(R.id.btn_transfer1);
        mBtn_transfer2 = (Button) findViewById(R.id.btn_transfer2);
        mBtn_transfer3 = (Button) findViewById(R.id.btn_transfer3);
        mBtn_transfer4 = (Button) findViewById(R.id.btn_transfer4);

        mTv_data = (TextView) findViewById(R.id.tv_data);
    }

    private void initListener() {

        btnClickListener listener = new btnClickListener();
        mBtn_transfer1.setOnClickListener(listener);
        mBtn_transfer2.setOnClickListener(listener);
        mBtn_transfer3.setOnClickListener(listener);
        mBtn_transfer4.setOnClickListener(listener);

    }

    class btnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int btnId = v.getId();

            switch(btnId) {
                case R.id.btn_transfer1:
                    transfer1();
                    break;
                case R.id.btn_transfer2:
                    transfer2();
                    break;
                case R.id.btn_transfer3:
                    transfer3();
                    break;
                case R.id.btn_transfer4:
                    transfer4();
                    break;
            }
        }
    }

    private void transfer1() {

        String str = "{\n" +
                "\t\"id\": 1,\n" +
                "\t\"price\": 12.3,\n" +
                "\t\"data\": {\n" +
                "\t\t\"name\": \"xiaomi\"\n" +
                "\t}\n" +
                "}";

        Goods goods = JSON.parseObject(str, Goods.class);

        mTv_data.setText(goods.toString());
    }

    private void transfer2() {

        String str = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f1.jpg\",\n" +
                "        \"name\": \"大虾1\",\n" +
                "        \"price\": 12.3\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f2.jpg\",\n" +
                "        \"name\": \"大虾2\",\n" +
                "        \"price\": 12.5\n" +
                "    }\n" +
                "]";

        List<Goods> goods = JSON.parseArray(str, Goods.class);
        mTv_data.setText(goods.toString());

    }

    private void transfer3() {

        Goods goods = new Goods(1, "name1", 1.0, "path1");

        String str = JSON.toJSONString(goods);

        mTv_data.setText(str);
    }

    private void transfer4() {

        Goods goods1 = new Goods(1, "name1", 1.0, "path1");
        Goods goods2 = new Goods(2, "name2", 2.0, "path2");

        List<Goods> list = new ArrayList<Goods>();

        list.add(goods1);
        list.add(goods2);

        String str = JSON.toJSONString(list);

        mTv_data.setText(str);

    }

}
