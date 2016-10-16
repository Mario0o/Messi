package com.adu.messi.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.adu.messi.MyApplication;
import com.adu.messi.R;

/**
 * 微信精选
 * Created by adu on 2016/10/16.
 */

public class WeiXinActivity extends AppCompatActivity {

    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.swipe_refresh_widget) SwipeRefreshLayout swipeRefreshWidget;
    private LinearLayoutManager mLayoutManager;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin);
        ButterKnife.bind(this);

        initView();
    }


    private void initView() {
        swipeRefreshWidget.setColorSchemeResources(R.color.colorAccent, R.color.add_bg_color, R
            .color.colorPrimary, R.color.colorPrimaryDark, R.color.add_selected_color);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        rv.setLayoutManager(mLayoutManager);
        //创建并设置Adapter

    }
}
