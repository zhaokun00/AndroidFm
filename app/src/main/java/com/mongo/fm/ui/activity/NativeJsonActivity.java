package com.mongo.fm.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mongo.fm.R;
import com.mongo.fm.bean.Goods;
import com.mongo.fm.bean.GoodsInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
* 原始的只有从字符串转为bean对象
*
* 1.将javabean对象数据取出来,拼接字符串
* 2.将字符串转换为JSONONJECT对象,赋值为javabean对象
* */
public class NativeJsonActivity extends Activity {

    private Button mBtn_transfer1;
    private Button mBtn_transfer2;
    private Button mBtn_transfer3;
    private Button mBtn_transfer4;
    private TextView mTv_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_json);

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
                    break;
            }
        }
    }

    /*
    * xxx getXxx(String name)与xxx optXxx(String name)之间的区别:
    * optXxx方法如果key值不存在会返回一个空字符串或者返回默认的指定的默认值,但是getString方法会出现空指针异常的错误
    *
    * */
    //将json字符串转为对象
    private void transfer1() {

        //json字符串
        String str = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}";

        try {
            JSONObject obj = new JSONObject(str); //将字符串转换为json对象

            int id = obj.optInt("id");
            String name = obj.optString("name");
            double price = obj.optDouble("price");
            String imagePath = obj.optString("imagePath");

            Goods goods = new Goods(id,name,price,imagePath);

            mTv_data.setText(goods.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
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


        List<Goods> list = new ArrayList<Goods>(); //创建list对象,用于存储信息

        try {
            JSONArray array = new JSONArray(str); //将字符串转为json数组

            for(int i = 0;i < array.length();i++) {
                JSONObject obj = array.getJSONObject(i);

                int id = obj.optInt("id");
                String name = obj.optString("name");
                double price = obj.optDouble("price");
                String imagePath = obj.optString("imagePath");
                Goods goods = new Goods(id,name,price,imagePath);

                list.add(goods); //添加到列表中

            }

            mTv_data.setText(list.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void transfer3() {

        String str = "{\n" +
                "\t\"rs_code\": \"1000\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"count\": 5,\n" +
                "\t\t\"items\": [{\n" +
                "\t\t\t\"id\": 45,\n" +
                "\t\t\t\"title\": \"坚果\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"id\": 132,\n" +
                "\t\t\t\"title\": \"炒货\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"id\": 166,\n" +
                "\t\t\t\"title\": \"蜜饯\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"id\": 195,\n" +
                "\t\t\t\"title\": \"果脯\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"id\": 196,\n" +
                "\t\t\t\"title\": \"礼盒\"\n" +
                "\t\t}]\n" +
                "\t},\n" +
                "\t\"rs_msg\": \"success\"\n" +
                "}";


        GoodsInfo goodsInfo = new GoodsInfo();
        try {
            JSONObject obj = new JSONObject(str);

            String rs_code = obj.optString("rs_code");
            String rs_msg = obj.optString("rs_msg");
            JSONObject data = obj.optJSONObject("data");

            goodsInfo.setRs_code(rs_code);
            goodsInfo.setRs_msg(rs_msg);
            GoodsInfo.DataInfo dataInfo = new GoodsInfo.DataInfo(); //创建类
            goodsInfo.setData(dataInfo);

            int count = data.optInt("count");
            dataInfo.setCount(count);
            List<GoodsInfo.DataInfo.Items> items = new ArrayList<GoodsInfo.DataInfo.Items>();
            dataInfo.setItems(items);

            JSONArray item = data.optJSONArray("items");

            for(int i = 0;i < item.length();i++) {

                JSONObject o = item.optJSONObject(i);

                int id = o.optInt("id");
                String title = o.optString("title");

                GoodsInfo.DataInfo.Items it = new GoodsInfo.DataInfo.Items();

                it.setId(id);
                it.setTitle(title);

                items.add(it);
            }

            mTv_data.setText(goodsInfo.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
