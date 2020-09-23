package com.example.sportapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class BottomBarLayout extends LinearLayout implements ViewPager.OnPageChangeListener {
    private String TAG = "BottomBarLayout";
    private static final String STATE_INSTANCE = "instance_state";
    private static final String STATE_ITEM = "state_item";
    private ViewPager mViewPager;
    private int mChildCount;
    private List<BottomBarItem> mItemViews;
    private int mCurrentItem;
    private boolean mSmoothScroll;
    private BottomBarLayout.OnItemSelectedListener onItemSelectedListener;

    public BottomBarLayout(Context context) {
        this(context, (AttributeSet)null);
    }

    public BottomBarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("WrongConstant")
    public BottomBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mItemViews = new ArrayList();
        this.setOrientation(0);
    }

    public void setOrientation(int orientation) {
        if (1 == orientation) {
            throw new IllegalArgumentException("BottomBarLayout only supports Horizontal Orientation.");
        } else {
            super.setOrientation(orientation);
        }
    }

    public void setViewPager(ViewPager mViewPager) {
        this.mViewPager = mViewPager;
        this.init();
    }

    private void init() {
        if (this.mViewPager == null) {
            throw new IllegalArgumentException("参数不能为空");
        } else {
            this.mChildCount = this.getChildCount();
            if (this.mViewPager.getAdapter().getCount() != this.mChildCount) {
                throw new IllegalArgumentException("LinearLayout的子View数量必须和ViewPager条目数量一致");
            } else {
                for(int i = 0; i < this.mChildCount; ++i) {
                    if (!(this.getChildAt(i) instanceof BottomBarItem)) {
                        throw new IllegalArgumentException("AlphaIndicator的子View必须是AlphaView");
                    }

                    BottomBarItem bottomBarItem = (BottomBarItem)this.getChildAt(i);
                    this.mItemViews.add(bottomBarItem);
                    bottomBarItem.setOnClickListener(new BottomBarLayout.MyOnClickListener(i));
                }

                ((BottomBarItem)this.mItemViews.get(this.mCurrentItem)).setStatus(true);
                this.mViewPager.setOnPageChangeListener(this);
            }
        }
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public void onPageSelected(int position) {
        this.mCurrentItem = position;
        this.resetState();
        ((BottomBarItem)this.mItemViews.get(position)).setStatus(true);
        this.mViewPager.setCurrentItem(position, this.mSmoothScroll);
    }

    public void onPageScrollStateChanged(int state) {
    }

    private void resetState() {
        for(int i = 0; i < this.mChildCount; ++i) {
            ((BottomBarItem)this.mItemViews.get(i)).setStatus(false);
        }

    }

    public void setCurrentItem(int currentItem) {
        this.mCurrentItem = currentItem;
        this.mViewPager.setCurrentItem(this.mCurrentItem, this.mSmoothScroll);
    }

    public int getCurrentItem() {
        return this.mCurrentItem;
    }

    public void setSmoothScroll(boolean mSmoothScroll) {
        this.mSmoothScroll = mSmoothScroll;
    }

    public BottomBarItem getBottomItem(int position) {
        return (BottomBarItem)this.mItemViews.get(position);
    }

    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instance_state", super.onSaveInstanceState());
        bundle.putInt("state_item", this.mCurrentItem);
        return bundle;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle)state;
            this.mCurrentItem = bundle.getInt("state_item");
            this.resetState();
            ((BottomBarItem)this.mItemViews.get(this.mCurrentItem)).setStatus(true);
            super.onRestoreInstanceState(bundle.getParcelable("instance_state"));
        } else {
            super.onRestoreInstanceState(state);
        }

    }

    public void setOnItemSelectedListener(BottomBarLayout.OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(BottomBarItem var1, int var2);
    }

    private class MyOnClickListener implements OnClickListener {
        private int currentIndex;

        public MyOnClickListener(int i) {
            this.currentIndex = i;
        }

        public void onClick(View v) {
            if (BottomBarLayout.this.onItemSelectedListener != null) {
                BottomBarLayout.this.onItemSelectedListener.onItemSelected(BottomBarLayout.this.getBottomItem(this.currentIndex), this.currentIndex);
            }

            BottomBarLayout.this.resetState();
            ((BottomBarItem)BottomBarLayout.this.mItemViews.get(this.currentIndex)).setStatus(true);
            BottomBarLayout.this.mViewPager.setCurrentItem(this.currentIndex, BottomBarLayout.this.mSmoothScroll);
            BottomBarLayout.this.mCurrentItem = this.currentIndex;
        }
    }
}
