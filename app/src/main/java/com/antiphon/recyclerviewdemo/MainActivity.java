package com.antiphon.recyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.antiphon.recyclerviewdemo.activity.GridActivity;
import com.antiphon.recyclerviewdemo.activity.HeadFootViewActivity;
import com.antiphon.recyclerviewdemo.activity.ItemClickActivity;
import com.antiphon.recyclerviewdemo.activity.ItemDecorationAnimatorActivity;
import com.antiphon.recyclerviewdemo.activity.LinearHorizontalActivity;
import com.antiphon.recyclerviewdemo.activity.LinearVerticalActivity;
import com.antiphon.recyclerviewdemo.activity.MyLayoutManagerActivity;
import com.antiphon.recyclerviewdemo.activity.NestedScrollActivity;
import com.antiphon.recyclerviewdemo.activity.StaggeredGridActivity;
import com.antiphon.recyclerviewdemo.activity.TouchHelperActivity;
import com.antiphon.recyclerviewdemo.activity.TypeViewActivity;

import androidx.appcompat.app.AppCompatActivity;

/**
 * RecyclerView的使用
 * 我的博客地址：https://blog.csdn.net/m0_37796683
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_linear_vertical).setOnClickListener(this);
        findViewById(R.id.btn_linear_horizontal).setOnClickListener(this);
        findViewById(R.id.btn_grid).setOnClickListener(this);
        findViewById(R.id.btn_staggered).setOnClickListener(this);
        findViewById(R.id.btn_type_view).setOnClickListener(this);
        findViewById(R.id.btn_itemDecorationAnimator).setOnClickListener(this);
        findViewById(R.id.btn_head_foot).setOnClickListener(this);
        findViewById(R.id.btn_touchHelper).setOnClickListener(this);
        findViewById(R.id.btn_item_click).setOnClickListener(this);
        findViewById(R.id.btn_my_layoutmanager).setOnClickListener(this);
        findViewById(R.id.btn_nest_scroll).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class<?> cls = null;
        switch (v.getId()) {
            case R.id.btn_linear_vertical://线性布局-垂直方向
                cls = LinearVerticalActivity.class;
                break;
            case R.id.btn_linear_horizontal://线性布局-水平方向
                cls = LinearHorizontalActivity.class;
                break;
            case R.id.btn_grid://网格布局
                cls = GridActivity.class;
                break;
            case R.id.btn_staggered://瀑布流布局
                cls = StaggeredGridActivity.class;
                break;
            case R.id.btn_type_view://不同类型item
                cls = TypeViewActivity.class;
                break;
            case R.id.btn_itemDecorationAnimator://添加分割线和动画
                cls = ItemDecorationAnimatorActivity.class;
                break;
            case R.id.btn_head_foot://添加头尾布局
                cls = HeadFootViewActivity.class;
                break;
            case R.id.btn_touchHelper://拖拽
                cls = TouchHelperActivity.class;
                break;
            case R.id.btn_item_click://item点击事件
                cls = ItemClickActivity.class;
                break;
            case R.id.btn_my_layoutmanager://自定义layoutmanager
                cls = MyLayoutManagerActivity.class;
                break;
            case R.id.btn_nest_scroll://嵌套滑动
                cls = NestedScrollActivity.class;
                break;
        }

        if (cls != null) {
            startActivity(new Intent(this, cls));
        }
    }
}
