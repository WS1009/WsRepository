package com.ws.lib.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class DPIUtil {
    private static float mDensity = 160.0F;
    private static Display defaultDisplay;
    private static Point outSize = null;

    public DPIUtil() {
    }

    public static float getDensity() {
        return mDensity;
    }

    public static void setDensity(float var0) {
        mDensity = var0;
    }

    public static Display getDefaultDisplay(Context var0) {
        if (null == defaultDisplay) {
            WindowManager var1 = (WindowManager) var0.getApplicationContext().getSystemService("window");
            defaultDisplay = var1.getDefaultDisplay();
        }
        return defaultDisplay;
    }

    public static int percentWidth(Context var0, float var1) {
        return (int) ((float) getWidth(var0) * var1);
    }

    public static int percentHeight(Context var0, float var1) {
        return (int) ((float) getHeight(var0) * var1);
    }

    public static int dip2px(float var0) {
        return (int) (var0 * mDensity + 0.5F);
    }

    public static int px2dip(float var0) {
        return (int) (var0 / mDensity + 0.5F);
    }

    public static int getWidth(Context var0) {
        if (outSize == null) {
            Class var1 = DPIUtil.class;
            synchronized (DPIUtil.class) {
                if (outSize == null) {
                    getPxSize(var0);
                }
            }
        }
        return outSize.x;
    }

    public static int getAppWidth(Activity var0) {
        if (var0 != null) {
            try {
                Point var5 = new Point();
                var0.getWindowManager().getDefaultDisplay().getSize(var5);
                return var5.x;
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        if (outSize == null) {
            Class var1 = DPIUtil.class;
            synchronized (DPIUtil.class) {
                if (outSize == null) {
                    getPxSize(var0.getApplication());
                }
            }
        }
        return outSize.x;
    }

    public static int getHeight(Context var0) {
        Display var1 = getDefaultDisplay(var0);
        Point var2 = new Point();
        var1.getSize(var2);
        return var2.y;
    }

    public static int getAppHeight(Activity var0) {
        if (var0 != null) {
            try {
                Point var5 = new Point();
                var0.getWindowManager().getDefaultDisplay().getSize(var5);
                return var5.y;
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        if (outSize == null) {
            Class var1 = DPIUtil.class;
            synchronized (DPIUtil.class) {
                if (outSize == null) {
                    getPxSize(var0.getApplicationContext());
                }
            }
        }
        return outSize.y;
    }

    public static int px2sp(Context var0, float var1) {
        float var2 = var0.getResources().getDisplayMetrics().scaledDensity;
        return (int) (var1 / var2 + 0.5F);
    }

    public static int sp2px(Context var0, float var1) {
        float var2 = var0.getResources().getDisplayMetrics().scaledDensity;
        return (int) ((var1 - 0.5F) * var2);
    }

    public static int dip2px(Context var0, float var1) {
        float var2 = var0.getResources().getDisplayMetrics().density;
        return (int) (var1 * var2 + 0.5F);
    }

    public static int getWidthByDesignValue(Context var0, int var1, int var2) {
        return (int) ((float) (getWidth(var0) * var1) / (float) var2 + 0.5F);
    }

    public static int getWidthByDesignValue(Activity var0, int var1, int var2) {
        return (int) ((float) (getAppWidth(var0) * var1) / (float) var2 + 0.5F);
    }

    public static int getWidthByDesignValue720(Context var0, int var1) {
        return getWidthByDesignValue((Context) var0, var1, 720);
    }

    public static int getWidthByDesignValue720(Activity var0, int var1) {
        return getWidthByDesignValue((Activity) var0, var1, 720);
    }

    public static int getWidthByDesignValue750(Context var0, int var1) {
        return getWidthByDesignValue((Context) var0, var1, 750);
    }

    public static int getWidthByDesignValue750(Activity var0, int var1) {
        return getWidthByDesignValue((Activity) var0, var1, 750);
    }

    public static void getPxSize(Context var0) {
        Display var1 = getDefaultDisplay(var0);
        outSize = new Point();
        var1.getSize(outSize);
    }
}