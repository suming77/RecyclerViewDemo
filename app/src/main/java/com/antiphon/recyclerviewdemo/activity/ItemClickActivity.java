package com.antiphon.recyclerviewdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.antiphon.recyclerviewdemo.Interface.OnItemChildClickListener;
import com.antiphon.recyclerviewdemo.Interface.OnItemClickListener;
import com.antiphon.recyclerviewdemo.R;
import com.antiphon.recyclerviewdemo.adapter.ItemClickAdapter;
import com.antiphon.recyclerviewdemo.model.Goods;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 高度自定义点击事件
 */
public class ItemClickActivity extends AppCompatActivity implements OnItemClickListener, OnItemChildClickListener {

    private ItemClickAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //分支创建

        List<Goods> goodsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Goods goods = new Goods("第 " + i + " 个item", i * 100, "商品描述");
            goodsList.add(goods);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ItemClickAdapter(this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setDataList(goodsList);

        //设置item点击事件
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "item点击事件", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemChildClick(View view, int position) {
        switch (view.getId()) {
            case R.id.iv_head:
                mAdapter.notifyDataSetChanged();
                Toast.makeText(this, "item子类点击事件: 头像", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
