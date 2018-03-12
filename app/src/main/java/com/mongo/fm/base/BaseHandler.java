package com.mongo.fm.base;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/*
 **************************************************************************************
 * company    : 
 * Filename   : BaseHandler
 * Author 	  : zhaokun
 * Description: 
 * Date		  ：2018-03-12
 * Others	  :
 **************************************************************************************
 */
public class BaseHandler<T extends BaseHandler.BaseHandlerCallBack> extends Handler {

    WeakReference<T> wr;

    public BaseHandler(T t) {
        wr = new WeakReference<T>(t);
    }

    @Override
    public void handleMessage(Message msg) { //接收消息会调用该函数
        super.handleMessage(msg); //父类中的消息处理函数为空
        T t = wr.get();
        if (t != null) {
            t.callBack(msg); //调用回调函数
        }
    }

    public interface BaseHandlerCallBack {
        public void callBack(Message msg);
    }
}
