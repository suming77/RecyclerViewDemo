package com.antiphon.recyclerviewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.antiphon.recyclerviewdemo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @创建者 mingyan.su
 * @创建时间 2019/12/23 16:11
 * @类描述 {TODO}线性水平适配器
 */
public class LinearHorizontalAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> mData;

    public LinearHorizontalAdapter(Context context, List<String> stringList) {
        this.mContext = context;
        this.mData = stringList;
    }

    //用于得到ViewHolder，通过ViewHolder承载视图中的元素，会为每一个item inflate出一个view，封装到ViewHolder中
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext != null) {
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_horizontal, parent, false));
        }
        //这里注意，返回的holder不能为null，否则会报错
        return null;
    }

    //将指定位置的数据和视图绑定起来，适配渲染数据到View中，因为这里ViewHolder，里面包含了item的View
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mTv_name.setText(mData.get(position));
    }

    //指定RecyclerView有多少个Item
    @Override
    public int getItemCount() {
        return mData.size();
    }

    //创建ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTv_name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv_name = itemView.findViewById(R.id.tv_name);
            mTv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mTv_name.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
