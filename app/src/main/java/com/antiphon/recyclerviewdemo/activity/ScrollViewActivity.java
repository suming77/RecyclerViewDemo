package com.antiphon.recyclerviewdemo.activity;

import android.os.Bundle;
import android.view.View;

import com.antiphon.recyclerviewdemo.R;
import com.antiphon.recyclerviewdemo.adapter.LinearVerticalAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView滑动时Behavior的处理
 */
public class ScrollViewActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("第 " + i + " 个item");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearVerticalAdapter adapter = new LinearVerticalAdapter(this, stringList);
        recyclerView.setAdapter(adapter);

        recyclerView.setNestedScrollingEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nested_scroll:

                break;
        }
    }

}
