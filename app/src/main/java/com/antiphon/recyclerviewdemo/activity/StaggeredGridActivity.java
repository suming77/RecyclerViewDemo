package com.antiphon.recyclerviewdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.antiphon.recyclerviewdemo.R;
import com.antiphon.recyclerviewdemo.adapter.StaggeredGridAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * 瀑布流布局
 */
public class StaggeredGridActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private List<String> mStringList;
    private StaggeredGridLayoutManager mManager;
    private StaggeredGridAdapter mAdapter;

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

        //4.数据适配器
        mAdapter = new StaggeredGridAdapter(this, mStringList);
        btn_vertical.performClick();
    }

    @Override
    public void onClick(View v) {
        //2.创建布局管理器
        switch (v.getId()) {
            case R.id.btn_vertical://垂直方向，列数为3
                mManager = new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL);
                mAdapter.setLinear(RecyclerView.VERTICAL);
                break;
            case R.id.btn_horizontal://水平方向，行数为3
                mManager = new StaggeredGridLayoutManager(3, RecyclerView.HORIZONTAL);
                mAdapter.setLinear(RecyclerView.HORIZONTAL);
                break;
        }

        //设置布局布局管理器到recyclerView
        mRecyclerView.setLayoutManager(mManager);
        //设置适配器到recyclerView
        mRecyclerView.setAdapter(mAdapter);
    }
}
