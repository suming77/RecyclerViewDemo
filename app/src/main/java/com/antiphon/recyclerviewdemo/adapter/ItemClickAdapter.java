package com.antiphon.recyclerviewdemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.antiphon.recyclerviewdemo.Interface.OnItemChildClickListener;
import com.antiphon.recyclerviewdemo.R;
import com.antiphon.recyclerviewdemo.base.BaseListAdapter;
import com.antiphon.recyclerviewdemo.base.BaseViewHolder;
import com.antiphon.recyclerviewdemo.model.Goods;

import java.util.List;

/**
 * @创建者 mingyan.su
 * @创建时间 2019/12/23 16:11
 * @类描述 {TODO}自定义点击事件
 */
public class ItemClickAdapter extends BaseListAdapter<Goods> {
    //item子类点击事件
    private OnItemChildClickListener mOnItemChildClickListener;

    public ItemClickAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_normal;
    }

    @Override
    public void onBindItemHolder(BaseViewHolder holder, final int position) {
        List<Goods> dataList = getDataList();
        Goods goods = dataList.get(position);

        TextView tv_name = holder.getView(R.id.tv_name);
        tv_name.setText(goods.getName());

        holder.setText(R.id.tv_price, "$：" + goods.getPrice())
                .setText(R.id.tv_des, goods.getDes())
                .setOnClickListener(R.id.iv_head, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemChildClickListener != null)
                            mOnItemChildClickListener.onItemChildClick(v, position);
                    }
                });
    }

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }



}
