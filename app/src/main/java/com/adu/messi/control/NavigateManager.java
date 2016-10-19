package com.adu.messi.control;

import android.app.Activity;
import android.content.Intent;
import com.adu.messi.ui.activity.WeiXinWebViewActivity;
import com.adu.messi.ui.fragment.HistoryTodayFragment;
import com.adu.messi.ui.fragment.InquireFragment;
import com.adu.messi.ui.fragment.JokeFragment;
import com.adu.messi.ui.activity.MainActivity;
import com.adu.messi.ui.activity.RobotActivity;
import com.adu.messi.ui.activity.WhetherActivity;
import com.adu.messi.ui.fragment.WeiXinFragment;

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
        Intent intent = new Intent(activity, WeiXinFragment.class);
        activity.startActivity(intent);
    }
    //跳转微信精选详情
    public static void gotoWeiXinWebViewActivity(Activity activity,String title,String url){
        Intent intent = new Intent(activity, WeiXinWebViewActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
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
