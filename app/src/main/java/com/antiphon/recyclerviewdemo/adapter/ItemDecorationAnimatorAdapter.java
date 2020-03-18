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
 * @类描述 {TODO}适配器
 */
public class ItemDecorationAnimatorAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> mData;

    private int mItem_Animator_type = 0;
    public static final int ITEM_ADD = 0;//增加item
    public static final int ITEM_REMOVE = 1;//移出item
    public static final int ITEM_NOTIFY = 2;//改变item

    public ItemDecorationAnimatorAdapter(Context context, List<String> stringList) {
        this.mContext = context;
        this.mData = stringList;
    }

    //用于得到ViewHolder，通过ViewHolder承载视图中的元素，会为每一个item inflate出一个view，封装到ViewHolder中
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext != null) {
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_decoration, parent, false));
        }
        //这里注意，返回的holder不能为null，否则会报错
        return null;
    }

    //将指定位置的数据和视图绑定起来，适配渲染数据到View中，因为这里ViewHolder，里面包含了item的View
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final TextView tv_name = viewHolder.mTv_name;
        tv_name.setText(mData.get(position));
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, tv_name.getText().toString(), Toast.LENGTH_SHORT).show();
                setItemAnimator(position);
            }
        });
    }

    /**
     * 设置item的增加，删除，改变的动画
     * @param position
     */
    private void setItemAnimator(int position) {
        if (mData == null || mData.isEmpty()) return;

        switch (mItem_Animator_type) {
            case ITEM_ADD://增加
                mData.add(position, "new item " + position);
                notifyItemInserted(position);//增加动画使用notifyItemInserted()更新数据，否则没有效果
                break;
            case ITEM_REMOVE://移出
                mData.remove(position);
                notifyItemRemoved(position);//增加动画使用notifyItemRemoved()更新数据，否则没有效果
                break;
            case ITEM_NOTIFY://改变
                mData.remove(position);
                mData.add(position, "change item " + position);
                notifyItemChanged(position);
                break;
        }
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
        }
    }

    /**
     * 设置类型
     *
     * @param item_Animator_type
     */
    public void setItem_Animator_type(int item_Animator_type) {
        mItem_Animator_type = item_Animator_type;
    }
}
