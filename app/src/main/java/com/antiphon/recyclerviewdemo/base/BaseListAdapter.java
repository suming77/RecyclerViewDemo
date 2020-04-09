package com.antiphon.recyclerviewdemo.base;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antiphon.recyclerviewdemo.Interface.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * BaseAdapter
 */
public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected int mLastPosition = -1;

    protected List<T> mDataList = new ArrayList<>();

    public BaseListAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext != null && mInflater != null) {
            return new BaseViewHolder(mInflater.inflate(getLayoutId(), parent, false));
        } else {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
        onBindItemHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(v, position);
            }
        });
    }

    //局部刷新关键：带payload的这个onBindViewHolder方法必须实现
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            onBindItemHolder(holder, position, payloads);
        }
    }

    //item布局文件
    public abstract int getLayoutId();

    //绑定item数据
    public abstract void onBindItemHolder(BaseViewHolder holder, int position);

    public void onBindItemHolder(BaseViewHolder holder, int position, List<Object> payloads) {
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 获取List列表数据
     *
     * @return
     */
    public List<T> getDataList() {
        return mDataList;
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setDataList(Collection<T> list) {
        mLastPosition = -1;
        clear();
        mDataList.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param list
     */
    public void addAll(Collection<T> list) {
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    /**
     * 移除某条数据
     *
     * @param position
     */
    public void remove(int position) {
        this.mDataList.remove(position);
        notifyItemRemoved(position);

        if (position != (getDataList().size())) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, this.mDataList.size() - position);
        }
    }

    /**
     * 清空列表
     */
    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

}
