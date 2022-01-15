package com.ws.lib.util;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.WindowManager;


import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Dpi750 {

    public static volatile int H_HEIGHT;
    public static volatile int H_WIDTH;
    public static Context mContext;
    private static final int MAX_SIZE = 128;
    private static SparseIntArray mCacheSize = new SparseIntArray();
    private static volatile int[] mCacheArr = new int[MAX_SIZE];

    private static ReadWriteLock mSizeLock = new ReentrantReadWriteLock();

    static {
        screenWidthChanged(getScreen().x, getScreen().y);
    }


    /**
     * 屏幕宽度是否发生变化
     */
    public static boolean screenWidthChanged(int width, int height) {
        if (width == H_WIDTH && height == H_HEIGHT) {
            return false;
        }
        try {
            mSizeLock.writeLock().lock();
            boolean widthChanged = width != H_WIDTH;
            H_WIDTH = width;
            H_HEIGHT = width != DPIUtil.getWidth(AppGlobals.INSTANCE.get()) ? height : DPIUtil.getHeight(AppGlobals.INSTANCE.get());
            if (widthChanged) {
                mCacheArr = new int[MAX_SIZE];
                mCacheSize.clear();
            }
            return widthChanged;
        } finally {
            mSizeLock.writeLock().unlock();
        }
    }


    public static Point getScreen() {
        try {
            if (mContext == null) {
                mContext = AppGlobals.INSTANCE.get();
            }
            WindowManager wm = convert(mContext.getSystemService(Context.WINDOW_SERVICE));
            if (null != wm) {
                Point point = new Point();
                wm.getDefaultDisplay().getSize(point);
                return point;
            }
        } catch (Throwable throwable) {

        }
        return new Point(DPIUtil.getWidth(AppGlobals.INSTANCE.get()), DPIUtil.getHeight(AppGlobals.INSTANCE.get()));
    }

    private static <T> T convert(Object in) {
        return (T) in;
    }

    public static int percentHeight(float percent) {
        return (int) ((float) H_HEIGHT * percent);
    }

    /**
     * 750的宽度等比转换至当前宽度对应值
     */
    static int get750SizeValue(int width, int screenWidth) {
        return (int) ((float) (screenWidth * width) / (float) 750 + 0.5F);
    }

    /**
     * 根据屏幕宽度适配750下的size
     */
    public static int getSizeBy750(int size) {
        try {
            mSizeLock.readLock().lock();
            int reSize = size < MAX_SIZE && size > 0 ? mCacheArr[size] : mCacheSize.get(size);
            if (reSize > 0) {
                return reSize;
            }
        } catch (Exception e) {//偶现越界异常

        } finally {
            mSizeLock.readLock().unlock();
        }
        int value = get750SizeValue(size, H_WIDTH);
        try {
            mSizeLock.writeLock().lock();
            if (size < MAX_SIZE && size > 0) {
                mCacheArr[size] = value;
            } else {
                mCacheSize.put(size, value);
            }
        } catch (Exception e) {//偶现越界异常

        } finally {
            mSizeLock.writeLock().unlock();
        }
        return value;
    }

}
