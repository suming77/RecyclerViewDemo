package com.antiphon.recyclerviewdemo.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;

/**
 * CoordinatorLayout与Behavior中的依赖View关系
 */
public class DependencyView extends AppCompatTextView {

    private int mTouchSlop;
    private float mLastY;
    private float mLastX;

    public DependencyView(Context context) {
        this(context, null);
    }

    public DependencyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DependencyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);
        //在用户发生滚动之前，以像素为单位的触摸距离可能会发生飘移
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下
                mLastX = event.getX();
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE://移动
                float moveX = event.getX() - mLastX;
                float moveY = event.getY() - mLastY;
                if (Math.abs(moveX) > mTouchSlop || Math.abs(moveY) > mTouchSlop) {
                    ViewCompat.offsetLeftAndRight(this, (int) moveX);
                    ViewCompat.offsetTopAndBottom(this, (int) moveY);
                    mLastX = event.getX();
                    mLastY = event.getY();
                }
                break;
            case MotionEvent.ACTION_UP://抬起
                mLastX = event.getX();
                mLastY = event.getY();
                break;
        }
        return true;
    }
}
