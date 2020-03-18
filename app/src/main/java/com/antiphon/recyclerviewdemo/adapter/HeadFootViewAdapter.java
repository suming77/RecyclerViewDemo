package com.antiphon.recyclerviewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.antiphon.recyclerviewdemo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 mingyan.su
 * @创建时间 2019/12/23 16:11
 * @类描述 {TODO}头尾布局适配器
 */
public class HeadFootViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> mData;

    private View mHeadView;//头布局
    private View mFootView;//尾部局

    private static final int ITEM_TYPE_NORMAL = 0;//普通类型
    private static final int ITEM_TYPE_HEAD = 1;//头布局类型
    private static final int ITEM_TYPE_FOOT = 2;//尾部局类型

    public HeadFootViewAdapter(Context context, List<String> stringList) {
        this.mContext = context;
        this.mData = stringList;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeadView != null && position == 0) {//头布局
            return ITEM_TYPE_HEAD;
        } else if (mFootView != null && position == mData.size() - 1) {//尾部局
            return ITEM_TYPE_FOOT;
        }
        return ITEM_TYPE_NORMAL;//普通类型
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //根据不同类型来获取不同的ViewHolder，里面装载不同的布局
        if (viewType == ITEM_TYPE_HEAD) {//头布局
            return new RecyclerView.ViewHolder(mHeadView) {
            };
        } else if (viewType == ITEM_TYPE_FOOT) {//尾部局
            return new RecyclerView.ViewHolder(mFootView) {
            };
        } else {
            return new NormalHolder(inflater.inflate(R.layout.item_linear, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NormalHolder) {//普通类型ViewHolder
            NormalHolder viewHolder = (NormalHolder) holder;
            viewHolder.mTv_name.setText(mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //普通类型ViewHolder
    public class NormalHolder extends RecyclerView.ViewHolder {
        TextView mTv_name;

        NormalHolder(@NonNull View itemView) {
            super(itemView);
            mTv_name = itemView.findViewById(R.id.tv_name);
        }
    }

    public void setHeadView(View headView) {
        mHeadView = headView;
        if (mData == null) return;
        mData.add(0, "头布局");
    }

    public void setFootView(View footView) {
        mFootView = footView;
        if (mData == null) return;
        mData.add(mData.size(), "尾布局");
    }
}
