package com.adu.messi.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.adu.messi.MyApplication;
import com.adu.messi.R;
import com.adu.messi.control.NavigateManager;
import com.adu.messi.utils.GlideUtils;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Administrator on 2016/10/14.
 */

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.mImageView) ImageView mImageView;
    @BindView(R.id.mTextView) TextView mTextView;
    //知乎日报接口
    private String url = "http://news-at.zhihu.com/api/4/start-image/1080*1776";

    private Handler handler = new Handler(){
        @Override public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NavigateManager.gotoMainActivity(WelcomeActivity.this);
            finish();
        }
    };

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        initView();
        new Thread(){
            @Override public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = handler.obtainMessage();
                handler.sendMessage(message);

            }
        }.start();
    }


    private void initView() {
        StringRequest request = new StringRequest(Request.Method.GET, url,
            new com.android.volley.Response.Listener<String>() {
                @Override public void onResponse(String s) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        mTextView.setText(jsonObject.getString("text"));
                        //GlideUtils.getInstance().LoadContextBitmap(MyApplication.getContext(),jsonObject.getString("img"),mImageView,
                        //    R.drawable.lod,R.drawable.iv_error,GlideUtils.LOAD_BITMAP);
                        GlideUtils.loadImageView(MyApplication.getContext(),jsonObject.getString("img"),mImageView);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new com.android.volley.Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError volleyError) {

            }
        });
        MyApplication.getHttpQueues().add(request);
    }



}
