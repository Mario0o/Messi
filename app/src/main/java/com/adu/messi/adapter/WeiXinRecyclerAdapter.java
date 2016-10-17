package com.adu.messi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.adu.messi.MyApplication;
import com.adu.messi.R;
import com.adu.messi.bean.WeinXinBean;
import com.adu.messi.utils.GlideUtils;
import java.util.List;

/**
 * Created by Administrator on 2016/10/16.
 */

public class WeiXinRecyclerAdapter extends RecyclerView.Adapter<WeiXinRecyclerAdapter.MyHolder>{


    private Context context;
    private List<WeinXinBean> list;
    private OnItemClickLitener mOnItemClickLitener;


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }


    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public WeiXinRecyclerAdapter(Context context, List<WeinXinBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(MyApplication.getContext()).inflate(
            R.layout.recyclerview_item_weixin, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }


    @Override public void onBindViewHolder(final MyHolder holder, int position) {
        holder.itemTitleTv.setText(list.get(position).getTitle());
        holder.itemContentTv.setText(list.get(position).getSource());

        if(!TextUtils.isEmpty(list.get(position).getFirstImg())){
            GlideUtils.getInstance().LoadContextBitmap(MyApplication.getContext(),list.get(position).getFirstImg(),holder.itemIv,
                R.drawable.lod,R.drawable.iv_error,GlideUtils.LOAD_BITMAP);
        }

        if(mOnItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView,pos);
                }
            });
        }
    }


    @Override public int getItemCount() {
        return list == null ? null :list.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_iv) ImageView itemIv;
        @BindView(R.id.item_title_tv) TextView itemTitleTv;
        @BindView(R.id.item_content_tv) TextView itemContentTv;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

}
