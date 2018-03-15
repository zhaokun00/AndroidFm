package com.mongo.fm.ui.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mongo.fm.R;
import com.mongo.fm.base.BaseHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends Activity {

    private static final String TAG = OkHttpActivity.class.getSimpleName();
    private static final int OKHTTP_GET_SYNC =  0;
    private static final int OKHTTP_GET_ASYNC = 1;
    private static final int OKHTTP_POST_SYNC =  2;
    private OkHttpClient mOkHttpClient;
    private Button mBtn_okHttpGet;
    private Button mBtn_okHttpPost;
    private Button mBtn_utilsGet;
    private Button mBtn_utilsPost;
    private Button mBtn_utilsDownload;
    private Button mBtn_utilsUpload;
    private Button mBtn_utilsImage;
    private ProgressBar mPb_progress;
    private ImageView mIv_icon;
    private TextView mtv_data;

    private BaseHandler mHandler = new BaseHandler(new BaseHandler.BaseHandlerCallBack() {
        @Override
        public void callBack(Message msg) {

            mtv_data.setText(((String)msg.obj));
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
        initListener();
    }

    private void initView() {

        setContentView(R.layout.activity_ok_http);

        mBtn_okHttpGet = (Button) findViewById(R.id.btn_okhttp_get);
        mBtn_okHttpPost = (Button) findViewById(R.id.btn_okhttp_post);
        mBtn_utilsGet = (Button) findViewById(R.id.btn_utils_get);
        mBtn_utilsPost = (Button) findViewById(R.id.btn_utils_post);
        mBtn_utilsDownload = (Button) findViewById(R.id.btn_utils_download);
        mBtn_utilsUpload = (Button) findViewById(R.id.btn_utils_upload);
        mBtn_utilsImage = (Button) findViewById(R.id.btn_utils_image);
        mPb_progress = (ProgressBar) findViewById(R.id.pb_progressbar);
        mIv_icon = (ImageView) findViewById(R.id.iv_icon);
        mtv_data = (TextView)findViewById(R.id.tv_data);

    }

    private void initData() {
        //创建OkHttpClient的第一种方式,这种方式采取参数采取的为默认值
        mOkHttpClient = new OkHttpClient(); //创建OkHttpClient对象

        /*创建OkHttpClient的第二种方式
         *这种方式可以自定义连接超时时间、读取数据时间、写数据时间
         * 先创建一个Build类对象,通过Build类对象设置应用的参数,再通过Build对象创建OkHttpClient类型对象,具体可以查看源码过程
         * */
        /*
         mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        */
    }

    private void initListener() {
        mBtn_okHttpGet.setOnClickListener(new btnClickListener());
        mBtn_okHttpPost.setOnClickListener(new btnClickListener());
        mBtn_utilsGet.setOnClickListener(new btnClickListener());
        mBtn_utilsPost.setOnClickListener(new btnClickListener());
        mBtn_utilsDownload.setOnClickListener(new btnClickListener());
        mBtn_utilsUpload.setOnClickListener(new btnClickListener());
        mBtn_utilsImage.setOnClickListener(new btnClickListener());
    }

    class btnClickListener implements View.OnClickListener { //声明了内部类,继承某个接口,因此要实现接口里面的方法
        @Override
        public void onClick(View v) {
            int btnId = v.getId();

            switch(btnId) {
                case R.id.btn_okhttp_get:
//                    okHttpGetSync();
//                    okHttpGetAsync();
                    break;
                case R.id.btn_okhttp_post:
                    okHttpPostSync();
                    break;
                case R.id.btn_utils_get:
                    utilsGet();
                    break;
                case R.id.btn_utils_post:
                    utilsPost();
                    break;
                case R.id.btn_utils_download:
                    utilsDownFile();
                    break;
                case R.id.btn_utils_upload:
                    utilsUploadFile();
                    break;
                case R.id.btn_utils_image:
                    utilsDownImage();
                    break;
            }
        }
    }

    /*okHttp的同步get请求,对于请求时需要开启子线程,请求成功后需要跳转到UI线程修改UI*
      1.Response.code是http响应行中的code,如果访问成功则返回200,这个不是服务器设置的,而是http协议中自带的.
      2.response.body().string()本质是输入流的读操作,所以它是网络请求的一部分,所以这行代码必须放在子线程中执行
      3.response.body().string()只能被调用一次,在第一时有返回值,第二次再调用时会返回null.原因是:response.body().string()的本质是
        输入流的读操作,必须有服务器的输出流的写操作时客户端的读操作才能得到数据,而服务端的写操作只执行一次,所以客户端的读操作也只能执行一次,
        第二次返回null
    /
     */
    private void okHttpGetSync() {
        new Thread() { //网络请求必须在子线程中执行,如果在主线程执行,在运行期间会出现错误
            @Override
            public void run() {

                //通过请求对象可以添加url、头信息等内容,发送的数据直接连接到url后面即可
                Request request = new Request.Builder().url("https://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1").build();

                try {
                    Response response = mOkHttpClient.newCall(request).execute(); //请求服务器

                    if(response.isSuccessful()) { //判断服务器确实响应了
                        if(200 == response.code()) { //判断服务器响应成功了

                            Message msg = Message.obtain(); //直接获取消息
                            msg.what = OKHTTP_GET_SYNC;
                            msg.obj = (String)response.body().string();

                            mHandler.sendMessage(msg);
                            //response.body().string()将返回的数据转换为字符串
                            //IOUtils.debugShow(TAG, response.body().string());
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

/*                try {
                    URL url = new URL("https://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1"); //生成URL对象
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //生成连接对象
                    conn.setRequestMethod("GET"); //设置请求方法
                    conn.setConnectTimeout(5000); //设置连接超时时间
                    conn.setReadTimeout(5000); //设置读取超时时间

                    if(conn.getResponseCode() == 200){ //获取响应码
                        InputStream is =conn.getInputStream(); //获取输入流,读取服务器返回的数据
                        String text = IOUtils.getStringFromStream(is); //将输入流的数据转换为字符串

                        IOUtils.debugShow(TAG,text);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
*/
            }
        }.start();
    }

    /*
    * okHttp的异步get请求
    * 异步请求的与同步请求的最大不同点在于:异步请求不需要开启子线程,enqueue方法会自动将网络请求部分放入子线程中执行
    *
    * 注意事项:
    *   1.回调接口的onFailure方法和onResponse执行在子线中中
    *   2.response.body().string()方法必须放在子线中,当执行这行代码得到结果后,再跳转到UI线程中修改UI
    * */
    private void okHttpGetAsync() {

        Request request = new Request.Builder()
                .url("https://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1")
                .build();

        //异步形式,里面设置回调方法即可
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { //失败的回调函数

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException { //成功的回调函数
                if(response.isSuccessful()) { //判断服务器确实响应了
                    if(200 == response.code()) { //判断服务器响应成功了

                        Message msg = Message.obtain(); //直接获取消息
                        msg.what = OKHTTP_GET_ASYNC;
                        msg.obj = (String)response.body().string();

                        mHandler.sendMessage(msg);
                        //response.body().string()将返回的数据转换为字符串
                        //IOUtils.debugShow(TAG, response.body().string());
                    }
                }
            }
        });

    }
    //OkHttp的post同步请求,异步请求不再演示,和get请求的异步请求相同
    private void okHttpPostSync() {

        new Thread(new Runnable() { //同步方式,必须创建一个子线程进行,否则运行保存
            @Override
            public void run() {

                //传递json字符串的形式,使用RequestBody传递Json对象
                /*
                MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                String jsonStr = "{\"key\":\"bd_hyrzjjfb4modhj\",\"size\":10,\"page\",1}"; //构造json字符串

                //上传json串形式
                RequestBody body = RequestBody.create(mediaType,jsonStr); //传递json串
                //通过post函数来与get请求进行区分
                Request request = new Request.Builder()
                        .url("https://www.391k.com/api/xapi.ashx/info.json")
                        .post(body)
                        .build();

                try {
                    Response response = mOkHttpClient.newCall(request).execute(); //请求服务器

                    if(response.isSuccessful()) { //判断服务器确实响应了
                        if(200 == response.code()) { //判断服务器响应成功了

                            Message msg = Message.obtain(); //直接获取消息
                            msg.what = OKHTTP_POST_SYNC;
                            msg.obj = (String)response.body().string();

                            mHandler.sendMessage(msg);
                            //response.body().string()将返回的数据转换为字符串
                            //IOUtils.debugShow(TAG, response.body().string());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                */

                //传递文本形式,使用FormBody传递键值对参数
                FormBody.Builder formBody = new FormBody.Builder();

                formBody.add("key","bd_hyrzjjfb4modhj");
                formBody.add("size","1");
                formBody.add("page","1");

                Request request = new Request.Builder()
                        .url("https://www.391k.com/api/xapi.ashx/info.json")
                        .post(formBody.build())
                        .build();

                try {
                    Response response = mOkHttpClient.newCall(request).execute(); //请求服务器

                    if(response.isSuccessful()) { //判断服务器确实响应了
                        if(200 == response.code()) { //判断服务器响应成功了

                            Message msg = Message.obtain(); //直接获取消息
                            msg.what = OKHTTP_POST_SYNC;
                            msg.obj = (String)response.body().string();

                            mHandler.sendMessage(msg);
                            //response.body().string()将返回的数据转换为字符串
                            //IOUtils.debugShow(TAG, response.body().string());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void utilsGet() {

        String url = "https://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1";

        //
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new HttpUtilsCallBack());

    }

    private void utilsPost() {

        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

        //简单的post请求
        OkHttpUtils
                .post()
                .url(url)
                .id(100)
                .build()
                .execute(new HttpUtilsCallBack());

        //传递json数据设置的形式
        /*
        * String json = new Gson().toJson(tempSubscribes);
        OkHttpUtils
                .postString() //使用该函数
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8")) //设置传递的类型
                .content(json) //传递的json字符串
                .build()
                .execute(callback);
        * */
    }

    /*okhttputils下载文件*/
    private void utilsDownFile() {

        String url = "http://vfx.mtime.cn/Video/2016/07/24/mp4/160724055620533327_480.mp4";

        Log.e(TAG,Environment.getExternalStorageDirectory().getAbsolutePath());
        OkHttpUtils
                .get() //下载时用get函数,用post函数下载不成功
                .url(url)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(),"utils.mp4") { //将文件存储的路径和文件名设置进去

                    @Override
                    public void inProgress(float progress, long total, int id) { //下载进度,progress:下载的百分比,total:整个文件的大小

                        Log.e(TAG, "Progress :" + progress);
                        Log.e(TAG, "total :" + total);

                        mPb_progress.setProgress((int) (progress * 100));
                    }

                    @Override
                    public void onError(Call call, Exception e, int i) {
                        mtv_data.setText(e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int i) { //当下载成功时最终会调用该函数

                        Log.e(TAG,"path = " + file.getAbsolutePath()); //打印最终的下载文件的路径
                    }
                });
    }

    //可以上传单个文件也可以上传多个文件
    private void utilsUploadFile() {

        String url = "http://10.100.24.228:8080/FileUpload/FileUploadServlet";

        File file1 = new File(Environment.getExternalStorageDirectory(), "1.png");
        File file2 = new File(Environment.getExternalStorageDirectory(), "1.txt");

        Map<String,String> params = new HashMap<>();

        params.put("name","zhaokun");

        OkHttpUtils
                .post()
                .addFile("file","1.png",file1)
                .addFile("file","1.txt",file2)
                .url(url)
                .params(params) //可可以添加参数
                .build()
                .execute(new HttpUtilsCallBack());

    }

    private void utilsDownImage() {

        String url = "http://images.csdn.net/20150817/1.jpg";

        OkHttpUtils
                .get()
                .url(url)
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int i) {
                        mIv_icon.setImageBitmap(bitmap);
                    }
                });

    }
    public class HttpUtilsCallBack extends StringCallback {

        @Override
        public void onBefore(Request request, int id) { //开始请求时执行该函数
            Log.e(TAG,"onBefore");
        }

        @Override
        public void onAfter(int id) { //请求完成后执行该函数,是在onResponse后面
            Log.e(TAG,"onAfter");
        }

        @Override
        public void inProgress(float progress, long total, int id) { //文件下载执行该函数
            Log.e(TAG,"inProgress");
            super.inProgress(progress, total, id);
        }

        @Override
        public void onError(Call call, Exception e, int i) { //执行失败时返回该参数
            mtv_data.setText(e.getMessage());
        }

        @Override
        public void onResponse(String s, int id) { //执行成功时调用该函数,id值即是初始化时传递进来的值,通过该值可以区分不同的应用请求

            Log.e(TAG,"id = " + id);
            mtv_data.setText(s);
        }
    };
}

