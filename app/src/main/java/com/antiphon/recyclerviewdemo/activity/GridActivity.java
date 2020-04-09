package com.antiphon.recyclerviewdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.antiphon.recyclerviewdemo.R;
import com.antiphon.recyclerviewdemo.adapter.GridAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 网格布局
 */
public class GridActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private List<String> mStringList;
    private GridLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_recyclerview);
        //1.查找控件
        Button btn_vertical = findViewById(R.id.btn_vertical);
        btn_vertical.setOnClickListener(this);
        findViewById(R.id.btn_horizontal).setOnClickListener(this);
        mRecyclerView = findViewById(R.id.recyclerView);

        //3.设置数据
        mStringList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mStringList.add("第 " + i + " 个item");
        }

        btn_vertical.performClick();
    }

    @Override
    public void onClick(View v) {
        //2.创建布局管理器,列数为3,垂直方向
        switch (v.getId()) {
            case R.id.btn_vertical://垂直方向
                mManager = new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false);
                break;
            case R.id.btn_horizontal://水平方向
                mManager = new GridLayoutManager(this, 3, RecyclerView.HORIZONTAL, false);
                break;
        }

        //设置布局布局管理器到recyclerView
        mRecyclerView.setLayoutManager(mManager);

        //4.数据适配器
        GridAdapter adapter = new GridAdapter(this, mStringList);
        //设置适配器到recyclerView
        mRecyclerView.setAdapter(adapter);
    }
}
