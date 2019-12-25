package com.antiphon.recyclerviewdemo.activity;

import android.os.Bundle;

import com.antiphon.recyclerviewdemo.R;
import com.antiphon.recyclerviewdemo.adapter.TypeViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 不同类型的item
 */
public class TypeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        //1.查找控件
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //2.创建布局管理器-线性布局
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置管理器的水平方向，RecyclerView.VERTICAL垂直方向，RecyclerView.HORIZONTAL水平方向
        manager.setOrientation(RecyclerView.VERTICAL);
        //设置布局布局管理器到recyclerView
        recyclerView.setLayoutManager(manager);

        //3.设置数据
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            stringList.add("球队名称： " + i);
        }

        //4.数据适配器n
        TypeViewAdapter adapter = new TypeViewAdapter(this, stringList);
        //设置适配器到recyclerView
        recyclerView.setAdapter(adapter);
    }
}
