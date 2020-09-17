package com.example.sportapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.sportapp.R;
import com.example.sportapp.utils.UIUtils;

public class BottomBarItem extends LinearLayout {
    private Context mContext;
    private int mIconNormalResourceId;
    private int mIconSelectedResourceId;
    private String mText;
    private int mTextSize;
    private int mTextColorNormal;
    private int mTextColorSelected;
    private int mMarginTop;
    private boolean mOpenTouchBg;
    private Drawable mTouchDrawable;
    private TextView mTextView;
    private ImageView mImageView;

    public BottomBarItem(Context context) {
        this(context, (AttributeSet)null);
    }

    public BottomBarItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBarItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTextSize = 12;
        this.mTextColorNormal = -6710887;
        this.mTextColorSelected = -12140517;
        this.mMarginTop = 0;
        this.mOpenTouchBg = false;
        this.mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomBarItem);
        this.mIconNormalResourceId = ta.getResourceId(R.styleable.BottomBarItem_iconNormal, -1);
        this.mIconSelectedResourceId = ta.getResourceId(R.styleable.BottomBarItem_iconSelected, -1);
        this.mText = ta.getString(R.styleable.BottomBarItem_itemText);
        this.mTextSize = ta.getDimensionPixelSize(R.styleable.BottomBarItem_itemTextSize, UIUtils.sp2px(this.mContext, (float)this.mTextSize));
        this.mTextColorNormal = ta.getColor(R.styleable.BottomBarItem_textColorNormal, this.mTextColorNormal);
        this.mTextColorSelected = ta.getColor(R.styleable.BottomBarItem_textColorSelected, this.mTextColorSelected);
        this.mMarginTop = ta.getDimensionPixelSize(R.styleable.BottomBarItem_itemMarginTop, UIUtils.dip2Px(this.mContext, this.mMarginTop));
        this.mOpenTouchBg = ta.getBoolean(R.styleable.BottomBarItem_openTouchBg, this.mOpenTouchBg);
        this.mTouchDrawable = ta.getDrawable(R.styleable.BottomBarItem_touchDrawable);
        ta.recycle();
        this.checkValues();
        this.init();
    }

    private void checkValues() {
        if (this.mIconNormalResourceId == -1) {
            throw new IllegalStateException("您还没有设置默认状态下的图标，请指定iconNormal的图标");
        } else if (this.mIconSelectedResourceId == -1) {
            throw new IllegalStateException("您还没有设置选中状态下的图标，请指定iconSelected的图标");
        } else if (this.mOpenTouchBg && this.mTouchDrawable == null) {
            throw new IllegalStateException("开启了触摸效果，但是没有指定touchDrawable");
        }
    }

    private void init() {
        this.setOrientation(1);
        this.setGravity(17);
        View view = View.inflate(this.mContext, R.layout.item_bottom_bar, (ViewGroup)null);
        this.mImageView = (ImageView)view.findViewById(R.id.iv_icon);
        this.mTextView = (TextView)view.findViewById(R.id.tv_text);
        this.mImageView.setImageResource(this.mIconNormalResourceId);
        this.mTextView.getPaint().setTextSize((float)this.mTextSize);
        this.mTextView.setText(this.mText);
        this.mTextView.setTextColor(this.mTextColorNormal);
        LayoutParams layoutParams = (LayoutParams)this.mTextView.getLayoutParams();
        layoutParams.topMargin = this.mMarginTop;
        this.mTextView.setLayoutParams(layoutParams);
        if (this.mOpenTouchBg) {
            this.setBackground(this.mTouchDrawable);
        }

        this.addView(view);
    }

    public ImageView getImageView() {
        return this.mImageView;
    }

    public TextView getTextView() {
        return this.mTextView;
    }

    public void setIconNormalResourceId(int mIconNormalResourceId) {
        this.mIconNormalResourceId = mIconNormalResourceId;
    }

    public void setIconSelectedResourceId(int mIconSelectedResourceId) {
        this.mIconSelectedResourceId = mIconSelectedResourceId;
    }

    public void setStatus(boolean isSelected) {
        this.mImageView.setImageResource(isSelected ? this.mIconSelectedResourceId : this.mIconNormalResourceId);
        this.mTextView.setTextColor(isSelected ? this.mTextColorSelected : this.mTextColorNormal);
    }
}
