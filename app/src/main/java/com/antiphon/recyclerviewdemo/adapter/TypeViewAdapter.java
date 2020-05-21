package com.antiphon.recyclerviewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antiphon.recyclerviewdemo.R;
import com.antiphon.recyclerviewdemo.model.Goods;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 mingyan.su
 * @创建时间 2019/12/23 16:11
 * @类描述 {TODO}不同类型item适配器
 */
public class TypeViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Goods> mData;
    public static final int ITEM_TYPE_NORMAL = 0;//普通类型
    public static final int ITEM_TYPE_SECTION = 1;//特殊类型

    public TypeViewAdapter(Context context, List<Goods> stringList) {
        this.mContext = context;
        this.mData = stringList;
    }

    @Override
    public int getItemViewType(int position) {
        Goods goods = mData.get(position);
        return goods.getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //根据不同类型来获取不同的ViewHolder，里面装载不同的布局
        if (viewType == ITEM_TYPE_SECTION) {
            return new SectionHolder(inflater.inflate(R.layout.item_section, parent, false));
        } else {
            return new NormalHolder(inflater.inflate(R.layout.item_normal, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NormalHolder) {//普通类型ViewHolder
            NormalHolder viewHolder = (NormalHolder) holder;
            viewHolder.mTv_name.setText(mData.get(position).getName());
            viewHolder.mTv_price.setText("$：" + position * 100);
            viewHolder.mCl_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "NormalHolder == " + position, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof SectionHolder) {//特殊类型ViewHolder
            SectionHolder sectionHolder = (SectionHolder) holder;
            sectionHolder.mIv_bg.setImageResource(R.mipmap.flower);
            sectionHolder.mIv_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "SectionHolder == " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //普通类型ViewHolder
    public class NormalHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mCl_root;
        ImageView mIv_head;
        TextView mTv_name;
        TextView mTv_price;
        TextView mTv_des;

        NormalHolder(@NonNull View itemView) {
            super(itemView);
            mCl_root = itemView.findViewById(R.id.cl_root);
            mIv_head = itemView.findViewById(R.id.iv_head);
            mTv_name = itemView.findViewById(R.id.tv_name);
            mTv_price = itemView.findViewById(R.id.tv_price);
            mTv_des = itemView.findViewById(R.id.tv_des);
        }
    }

    //特殊类型ViewHolder
    public class SectionHolder extends RecyclerView.ViewHolder {
        ImageView mIv_bg;

        SectionHolder(@NonNull View itemView) {
            super(itemView);
            mIv_bg = itemView.findViewById(R.id.iv_bg);
        }
    }
}
