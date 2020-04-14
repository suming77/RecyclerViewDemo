package com.antiphon.recyclerviewdemo.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.antiphon.recyclerviewdemo.R;
import com.antiphon.recyclerviewdemo.adapter.ItemDecorationAnimatorAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 添加下划线和增加动画效果
 */
public class ItemDecorationAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ItemDecorationAnimatorAdapter mAdapter;
    private DividerItemDecoration mDecoration;
    private Drawable mDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoration_animator_recyclerview);
        mRecyclerView = findViewById(R.id.recyclerView);
        findViewById(R.id.btn_decoration_normal).setOnClickListener(this);
        findViewById(R.id.btn_decoration_DIY).setOnClickListener(this);
        findViewById(R.id.btn_animator_add).setOnClickListener(this);
        findViewById(R.id.btn_animator_remove).setOnClickListener(this);
        findViewById(R.id.btn_animator_change).setOnClickListener(this);

        //创建布局管理器-线性布局
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        //设置分割线
        mDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mDrawable = ContextCompat.getDrawable(this, R.drawable.divider_item_decoration);
//        if (mDrawable != null) {
//            mDecoration.setDrawable(mDrawable);
//        }
        mRecyclerView.addItemDecoration(mDecoration);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置数据
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            stringList.add("第 " + i + " 个item");
        }

        mAdapter = new ItemDecorationAnimatorAdapter(this, stringList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_decoration_normal://默认分割线
                mRecyclerView.removeItemDecoration(mDecoration);
                mDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
                mRecyclerView.addItemDecoration(mDecoration);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_decoration_DIY://自定义分割线
                mRecyclerView.removeItemDecoration(mDecoration);
                if (mDrawable != null) {
                    mDecoration.setDrawable(mDrawable);
                }
                mRecyclerView.addItemDecoration(mDecoration);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_animator_add://添加item时动画
                mAdapter.setItem_Animator_type(ItemDecorationAnimatorAdapter.ITEM_ADD);
                break;
            case R.id.btn_animator_remove://移除item时动画
                mAdapter.setItem_Animator_type(ItemDecorationAnimatorAdapter.ITEM_REMOVE);
                break;
            case R.id.btn_animator_change://改变item数据
                mAdapter.setItem_Animator_type(ItemDecorationAnimatorAdapter.ITEM_NOTIFY);
                break;
        }
    }



}
