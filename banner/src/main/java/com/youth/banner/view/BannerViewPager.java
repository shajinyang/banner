package com.youth.banner.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.lang.reflect.Field;


public class BannerViewPager extends ViewPager {
    private boolean scrollable = true;

    public BannerViewPager(Context context) {
        super(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(this.scrollable) {
            if (getCurrentItem() == 0 && getChildCount() == 0) {
                return false;
            }
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(this.scrollable) {
            if (getCurrentItem() == 0 && getChildCount() == 0) {
                return false;
            }
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 解决recycleview嵌套banner时  view被回收后,再次进入banner,动画效果不显示的问题
        try {
            Field mFirstLayout = ViewPager.class.getDeclaredField("mFirstLayout");
            mFirstLayout.setAccessible(true);
            mFirstLayout.set(this, false);
//          getAdapter().notifyDataSetChanged();
            setCurrentItem(getCurrentItem(),false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        //解决recycleview嵌套banner时,动画被强制停止导致,ui错位的问题
        postDelayed(new Runnable() {
            @Override
            public void run() {
                BannerViewPager.super.onDetachedFromWindow();
            }
        },3000);


    }





    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }
}
