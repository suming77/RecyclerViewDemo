package com.antiphon.recyclerviewdemo.weight;

import android.graphics.Color;

import com.antiphon.recyclerviewdemo.adapter.LinearVerticalAdapter;

import java.util.Collections;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 自定义拖拽回调
 */
public class MyItemTouchCallback extends ItemTouchHelper.Callback {

    private final LinearVerticalAdapter adapter;

    public MyItemTouchCallback(LinearVerticalAdapter adapter) {
        this.adapter = adapter;
    }

    //设置支持滑动和拖拽的方向
    //ItemTouchHelper.UP：上移，ItemTouchHelper.DOWN：下移，ItemTouchHelper.LEFT：左移，ItemTouchHelper.RIGHT：右移
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag;//拖拽方向
        int swipeFlag;//滑动方向
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {//网格布局时可以左右上下拖拽
            dragFlag = ItemTouchHelper.DOWN | ItemTouchHelper.UP
                    | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
            swipeFlag = 0;
        } else {//其他布局时，只能上下拖拽
            dragFlag = ItemTouchHelper.DOWN | ItemTouchHelper.UP;
            swipeFlag = ItemTouchHelper.END;//滑动结束
        }
        return makeMovementFlags(dragFlag, swipeFlag);//返回拖拽和滑动的方向
    }

    //拖拽时回调，在拖拽时不断回调这个方法
    //我们需要将正在拖拽的item和集合的item进行元素交换，然后通知适配器不断更新数据
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //当前拖拽item的position
        int fromPosition = viewHolder.getAdapterPosition();
        //拖拽到位置
        int toPosition = target.getAdapterPosition();

        //根据位置重新排序更新数据
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(adapter.getData(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(adapter.getData(), i, i - 1);
            }
        }
        recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    //滑动时回调
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.END) {//滑动结束后移出数据，这里演示的是侧滑删除
            adapter.getData().remove(position);
            adapter.notifyItemRemoved(position);
        }
    }

    //状态变化时回调
    //ACTION_STATE_DRAG(拖拽状态) ACTION_STATE_IDLE(空闲状态) ACTION_STATE_SWIPE(滑动状态)
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {//拖拽时设置背景颜色
            viewHolder.itemView.setBackgroundColor(Color.BLUE);
        }
    }

    //用户交互结束时回调，即手指松开时
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(0);
    }

    //是否支持长按拖拽，默认返回true
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }
}