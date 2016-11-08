package com.linyuechang.awesome.ctripsearch;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by yuechanglin on 2016-11-7.
 */
public class ScreenHelper {
    public static int dip2px(float dp) {
        return (int)(convertDP(1, dp) + 0.5F);
    }

    private static float convertDP(int unit, float in) {
        return TypedValue.applyDimension(unit, in, MyApplication.getContext().getResources().getDisplayMetrics());
    }

    public static int getScreenWidth() {
        DisplayMetrics dm = MyApplication.getContext().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }
}
