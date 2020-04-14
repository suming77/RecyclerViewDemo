package com.antiphon.recyclerviewdemo.activity;

import android.os.Bundle;

import com.antiphon.recyclerviewdemo.R;
import com.antiphon.recyclerviewdemo.adapter.LinearVerticalAdapter;
import com.antiphon.recyclerviewdemo.base.MySelfLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 自定义LayoutManager
 */
public class MyLayoutManagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //3.设置数据
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("第 " + i + " 个item");
        }

        recyclerView.setLayoutManager(new MySelfLayoutManager());
        recyclerView.setAdapter(new LinearVerticalAdapter(this, stringList));
    }

}
