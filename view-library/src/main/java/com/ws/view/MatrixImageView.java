package com.ws.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * Created by wangshun3 on 2020-04-19
 * Des :
 */
public class MatrixImageView extends androidx.appcompat.widget.AppCompatImageView {
    private Bitmap mBitmap;
    private Matrix mMatrix;
    private RectF srcRectF = new RectF();
    private RectF dstRectF = new RectF();
    private int animatedValue;

    public MatrixImageView(Context context) {
        this(context, null);
    }

    public MatrixImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.girl);
        mMatrix = new Matrix();

        post(new Runnable() {
            @Override
            public void run() {
                //显示Bitmap的底板
                dstRectF.right = getMeasuredWidth();
                dstRectF.bottom = getMeasuredHeight();
            }
        });

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, mBitmap.getHeight() / 2);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatedValue = (int) animation.getAnimatedValue();
                //截取用来显示的Bitmap有效区域
                srcRectF.top = animatedValue;
                srcRectF.right = mBitmap.getWidth();
                srcRectF.bottom = animatedValue + mBitmap.getHeight() / 2;
                invalidate();
            }
        });
        valueAnimator.start();
    }


    /**
     * 绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制底色 辅助用
        canvas.drawColor(Color.CYAN);
        //设置Bitmap的缩放模式
        mMatrix.setRectToRect(srcRectF, dstRectF, Matrix.ScaleToFit.FILL);
        canvas.drawBitmap(mBitmap, mMatrix, null);
    }
}
