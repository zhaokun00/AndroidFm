package com.mongo.fm.utils;

import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 **************************************************************************************
 * company    : 
 * Filename   : TimeUtils
 * Author 	  : zhaokun
 * Description: 
 * Date		  ：2018-03-12
 * Others	  :
 **************************************************************************************
 */
public class TimeUtils {

    /*
      Function:
      Description:返回距离1970年1月1日 00:00:00.0的毫秒数
      Parameters:
      Return:返回
    */
    public static long getTimeMillis() {

        return System.currentTimeMillis(); //获取系统时间,返回时间的单位位ms
    }

    public static String getNowTime() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"); //创建格式化类对象

        Date date = new Date(getTimeMillis()); //传入毫秒数,将毫秒数转化为Date类对象

        return simpleDateFormat.format(date); //格式化Date类对象

    }

    public static int getNowYear() {

        Time time = new Time();

        time.setToNow();

        return time.year;
    }

    public static int getNowMonth() {

        Time time = new Time();

        time.setToNow();

        return time.month + 1; //Month [0-11]
    }

    public static int getNowDay() {

        Time time = new Time();

        time.setToNow();

        return time.monthDay;
    }

    public static int getNowHour() {

        Time time = new Time();

        time.setToNow();

        return time.hour;
    }

    public static int getNowMinute() {

        Time time = new Time();

        time.setToNow();

        return time.minute;
    }

    public static int getNowSecond() {

        Time time = new Time();

        time.setToNow();

        return time.second;
    }
}
