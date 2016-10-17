package com.adu.messi.control;

import android.app.Activity;
import android.content.Intent;
import com.adu.messi.ui.activity.HistoryTodayActivity;
import com.adu.messi.ui.activity.InquireActivity;
import com.adu.messi.ui.activity.JokeActivity;
import com.adu.messi.ui.activity.MainActivity;
import com.adu.messi.ui.activity.RobotActivity;
import com.adu.messi.ui.activity.WeiXinActivity;
import com.adu.messi.ui.activity.WhetherActivity;

/**
 * 页面跳转
 * Created by adu on 2016/10/16.
 */

public class NavigateManager {


    //跳转主页面
    public static void gotoMainActivity(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
    //跳转微信精选
    public static void gotoWeiXinActivity(Activity activity){
        Intent intent = new Intent(activity, WeiXinActivity.class);
        activity.startActivity(intent);
    }
    //跳转微信精选
    public static void gotoWeiXinWebViewActivity(Activity activity,String title,String url){
        Intent intent = new Intent(activity, WeiXinActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        activity.startActivity(intent);
    }

    //跳转笑话大全
    public static void gotoJokeActivity(Activity activity){
        Intent intent = new Intent(activity, JokeActivity.class);
        activity.startActivity(intent);
    }
    //跳转历史上的今天
    public static void gotoHistoryTodayActivity(Activity activity){
        Intent intent = new Intent(activity, HistoryTodayActivity.class);
        activity.startActivity(intent);
    }
    //跳转查询信息
    public static void gotoInquireActivity(Activity activity){
        Intent intent = new Intent(activity, InquireActivity.class);
        activity.startActivity(intent);
    }
    //跳转到问答机器人
    public static void gotoRobotActivity(Activity activity){
        Intent intent = new Intent(activity,RobotActivity.class);
        activity.startActivity(intent);
    }
    //跳转到天气预报
    public static void gotoWhetherActivity(Activity activity){
        Intent intent = new Intent(activity, WhetherActivity.class);
        activity.startActivity(intent);
    }
}
