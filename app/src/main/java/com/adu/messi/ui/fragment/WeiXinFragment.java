package com.adu.messi.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/19.
 */

public class WeiXinFragment extends Fragment {

    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.swipe_refresh_widget) SwipeRefreshLayout swipeRefreshWidget;
    private LinearLayoutManager mLayoutManager;

    private WeiXinRecyclerAdapter adapter;
    private List<WeinXinBean> list = new ArrayList<>();

    private List<String> urlList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View veiw = inflater.inflate(R.layout.fragment_weixin, container, false);

        ButterKnife.bind(this, veiw);
        initView();
        initData();
        return veiw;
    }

    private void initView() {


        swipeRefreshWidget.setColorSchemeResources(R.color.colorAccent, R.color.add_bg_color, R
            .color.colorPrimary, R.color.colorPrimaryDark, R.color.add_selected_color);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        rv.setLayoutManager(mLayoutManager);
        swipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                swipeRefreshWidget.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshWidget.setRefreshing(false);
                    }
                }, 3000);
            }
        });
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
                    NavigateManager.gotoWeiXinWebViewActivity(getActivity(),titleList.get(position),
                        urlList.get(position));
                    //LogUtils.showToast("nihaoya ");
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
