package com.antiphon.recyclerviewdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.antiphon.recyclerviewdemo.R;
import com.antiphon.recyclerviewdemo.adapter.HeadFootViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 头布局尾部局
 */
public class HeadFootViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //创建布局管理器-线性布局
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        //设置数据
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringList.add("第 " + i + " 个item");
        }

        //数据适配器
        HeadFootViewAdapter adapter = new HeadFootViewAdapter(this, stringList);

        //头布局
        View headView = LayoutInflater.from(this).inflate(R.layout.head_banner, recyclerView, false);
        //尾部局
        View footView = LayoutInflater.from(this).inflate(R.layout.foot_loadmore, recyclerView, false);

        adapter.setHeadView(headView);//添加头布局
        adapter.setFootView(footView);//添加尾布局

        recyclerView.setAdapter(adapter);
    }
}
