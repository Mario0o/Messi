package com.adu.messi.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.adu.messi.MyApplication;
import com.adu.messi.R;
import com.adu.messi.adapter.WeiXinRecyclerAdapter;
import com.adu.messi.bean.WeinXinBean;
import com.adu.messi.control.NavigateManager;
import com.adu.messi.utils.AppKeyUtils;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 微信精选
 * Created by adu on 2016/10/16.
 */

public class WeiXinActivity extends AppCompatActivity {

    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.swipe_refresh_widget) SwipeRefreshLayout swipeRefreshWidget;
    private LinearLayoutManager mLayoutManager;

    private WeiXinRecyclerAdapter adapter;
    private List<WeinXinBean> list = new ArrayList<>();

    private List<String> urlList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin);
        ButterKnife.bind(this);

        initView();
        initData();
    }


    private void initView() {
        swipeRefreshWidget.setColorSchemeResources(R.color.colorAccent, R.color.add_bg_color, R
            .color.colorPrimary, R.color.colorPrimaryDark, R.color.add_selected_color);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        rv.setLayoutManager(mLayoutManager);

    }

    private void initData(){
        StringRequest request = new StringRequest(Request.Method.GET, AppKeyUtils.WEINXIN,
            new Response.Listener<String>() {
                @Override public void onResponse(String s) {
                    getWeiXinJosn(s);

                }
            }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError volleyError) {

            }
        });
        MyApplication.getHttpQueues().add(request);
    }


    private void getWeiXinJosn(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject object = jsonObject.getJSONObject("result");
            JSONArray array = object.getJSONArray("list");

            for (int i = 0;i<array.length();i++){
                JSONObject jsonObject1 = (JSONObject) array.get(i);
                WeinXinBean bean = new WeinXinBean();
                bean.setTitle(jsonObject1.getString("title"));
                bean.setSource(jsonObject1.getString("source"));
                bean.setFirstImg(jsonObject1.getString("firstImg"));

                list.add(bean);

                urlList.add(jsonObject1.getString("url"));
                titleList.add(jsonObject1.getString("title"));
            }
            adapter = new WeiXinRecyclerAdapter(MyApplication.getContext(),list);
            rv.setAdapter(adapter);
            /**
             * rv的点击事件
             */
            adapter.setmOnItemClickLitener(new WeiXinRecyclerAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    NavigateManager.gotoWeiXinWebViewActivity(WeiXinActivity.this,titleList.get(position),
                        urlList.get(position));
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
