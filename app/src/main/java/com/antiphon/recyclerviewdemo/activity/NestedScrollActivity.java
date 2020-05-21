package com.antiphon.recyclerviewdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.antiphon.recyclerviewdemo.R;
import com.antiphon.recyclerviewdemo.adapter.LinearVerticalAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 嵌套滑动
 */
public class NestedScrollActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("第 " + i + " 个item");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearVerticalAdapter adapter = new LinearVerticalAdapter(this, stringList);
        recyclerView.setAdapter(adapter);

        recyclerView.setNestedScrollingEnabled(true);

        findViewById(R.id.btn_dependency).setOnClickListener(this);
        findViewById(R.id.btn_move_scroll).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Class<?> cls = null;
        switch (v.getId()) {
            case R.id.btn_dependency:
                cls = DependencyViewActivity.class;
                break;
            case R.id.btn_move_scroll:
                cls = ScrollViewActivity.class;
                break;
        }

        if (cls != null) {
            startActivity(new Intent(this, cls));
        }
    }
}
