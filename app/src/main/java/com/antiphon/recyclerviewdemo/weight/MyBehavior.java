package com.antiphon.recyclerviewdemo.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

/**
 * 自定义Behavior验证依赖View
 */
public class MyBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = "MyBehavior";

    public MyBehavior() {
    }

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //确定提供的子视图是否有另一个特定的同级视图作为布局依赖项
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return false;
//        return dependency instanceof DependencyView;
    }

    //子View依赖的View的改变做出响应，当依赖视图在标准布局流之外的大小或者位置发生变化，此方法被调用。
    //Behavior可以使用此方法更新子视图，如果子视图大小或者位置发生变化则返回true。
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
//        int dependBottom = dependency.getBottom();
//
//        child.setY(dependBottom + 50);
//        child.setX(dependency.getLeft());
        //child坐标
        float childX = child.getX();
        float childY = child.getY();

        //dependency顶部底部坐标
        int dependencyTop = dependency.getTop();
        int dependencyBottom = dependency.getBottom();

        childX = dependency.getX();

        if (child instanceof TextView) {//如果是TextView则显示在dependency上面，否则显示在下面
            childY = dependencyTop - child.getHeight();
        } else {
            childY = dependencyBottom;
        }

        child.setX(childX);
        child.setY(childY);

        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild,
                                       View target, int axes, int type) {
        Log.e(TAG, "onStartNestedScroll：axes == " + axes);
        //子View是 ImageView并且在垂直方向滑动
        return child instanceof ImageView && axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View child, View directTargetChild,
                                       View target, int axes, int type) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type);
        Log.e(TAG, "onNestedScrollAccepted：axes == " + axes + " | type == " + type);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed,
                               int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.e(TAG, "onNestedScrollAccepted：dxConsumed == " + dxConsumed + " | dyConsumed == "
                + dyConsumed + " | dxUnconsumed == " + dxUnconsumed + " | dyUnconsumed == " + dyUnconsumed);
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx,
                                  int dy, int[] consumed, int type) {
        Log.e(TAG, "onNestedScrollAccepted：dx == " + dx + " | dy == " + dy + " | type == " + type);
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        ViewCompat.offsetTopAndBottom(child, dy);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int type) {
        Log.e(TAG, "onNestedScrollAccepted：type == " + type);
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target,
                                 float velocityX, float velocityY, boolean consumed) {
        Log.e(TAG, "onNestedScrollAccepted：velocityX == " + velocityX + " | velocityY == "
                + velocityY + " | consumed == " + consumed);
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target,
                                    float velocityX, float velocityY) {
        Log.e(TAG, "onNestedScrollAccepted：velocityX == " + velocityX + " | velocityY == " + velocityY);
        if (velocityY > 0) {//向下惯性滑动
            child.animate().scaleX(2f).scaleY(2f).setDuration(2000).start();
        } else {//向上惯性滑动
            child.animate().scaleX(1f).scaleY(1f).setDuration(2000).start();
        }
        return false;
//        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }
}
