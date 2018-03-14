package com.mongo.fm.utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 **************************************************************************************
 * company    : 
 * Filename   : IOUtils
 * Author 	  : zhaokun
 * Description: 
 * Date		  ：2018-03-13
 * Others	  :
 **************************************************************************************
 */
public class IOUtils {

    public static String getStringFromStream(InputStream is){

        byte[] b = new byte[102400];
        int len = 0;
        //创建字节数组输出流，读取输入流的文本数据时，同步把数据写入数组输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while((len = is.read(b)) != -1){ //将输入流的数据读入到字节数组b中
                bos.write(b, 0, len); //将字节数组中的数据写入到数组输出流中
            }

            String text = new String(bos.toByteArray());  //把字节数组输出流里的数据转换成字节数组
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* logcat在实现上对于message的内存分配大概是在4k左右,超过的部分直接被丢弃,因此输出时要截断输出*/
    public static void debugShow(String tag,String msg) {

        int segmentSize = 1024;
        long length = msg.length();

        Log.e(tag, "length = " + length);
        if (length <= segmentSize ) {// 长度小于等于限制直接打印
            Log.e(tag, msg);
        }else {
            while (msg.length() > segmentSize ) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize );
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志
        }
    }
}
