package com.example.sportapp.utils;

import android.content.Context;

public class UIUtils {
    public UIUtils() {
    }

    public static int dip2Px(Context context, int dip) {
        float density = context.getResources().getDisplayMetrics().density;
        int px = (int)((float)dip * density + 0.5F);
        return px;
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * fontScale + 0.5F);
    }
}