package com.adu.messi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.adu.messi.R;
import com.adu.messi.bean.WeinXinBean;
import java.util.List;

/**
 * Created by Administrator on 2016/10/16.
 */

public class WeiXinRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NORMAL_ITEM = 0;  //普通Item
    private static final int TYPE_FOOTER_ITEM = 1;  //底部FooterView
    private List<WeinXinBean> list;

    public WeiXinRecyclerAdapter(List<WeinXinBean> list){
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //如果viewType是普通item返回普通的布局，否则是底部布局并返回
        if(viewType == TYPE_NORMAL_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_weixin,parent,false);

        }

        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }


}
