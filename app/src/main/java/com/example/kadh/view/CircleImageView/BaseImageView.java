package com.example.kadh.view.CircleImageView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * author: ZhangXiaoWei
 * email : cherno@126.com
 * blog  : http://www.jianshu.com/users/0f532bf88454/latest_articles
 * time  : 2017/5/23
 * desc  :
 */

public abstract class BaseImageView extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG = BaseImageView.class.getSimpleName();

    protected Context mContext;

    private static final Xfermode sXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    private Bitmap mMaskBitmap;
    private Paint mPaint;

    public BaseImageView(Context context) {
        super(context);
        sharedConstructor(context);
    }

    public BaseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructor(context);
    }

    public BaseImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        sharedConstructor(context);
    }

    private void sharedConstructor(Context context) {
        mContext = context;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void invalidate() {
//        mWeakBitmap = null;
        if (mMaskBitmap != null) {
            mMaskBitmap.recycle();
        }
        super.invalidate();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInEditMode()) {
            int i = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(),
                    null, Canvas.ALL_SAVE_FLAG);
            try {
                Bitmap bitmap = null;
                // Bitmap not loaded.
                if (bitmap == null || bitmap.isRecycled()) {
                    Drawable drawable = getDrawable();
                    if (drawable != null) {
                        // Allocation onDraw but it's ok because it will not always be called.
                        bitmap = Bitmap.createBitmap(getWidth(),
                                getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas bitmapCanvas = new Canvas(bitmap);
                        drawable.setBounds(0, 0, getWidth(), getHeight());
                        drawable.draw(bitmapCanvas);

                        // If mask is already set, skip and use cached mask.
                        if (mMaskBitmap == null || mMaskBitmap.isRecycled()) {
                            mMaskBitmap = getBitmap();
                        }

                        // Draw Bitmap.
                        mPaint.reset();
                        mPaint.setFilterBitmap(false);
                        mPaint.setXfermode(sXfermode);
                        bitmapCanvas.drawBitmap(mMaskBitmap, 0.0f, 0.0f, mPaint);

                    }
                }

                // Bitmap already loaded.
                if (bitmap != null) {
                    mPaint.setXfermode(null);
                    canvas.drawBitmap(bitmap, 0.0f, 0.0f, mPaint);
                    return;
                }
            } catch (Exception e) {
                System.gc();
            } finally {
                canvas.restoreToCount(i);
            }
        } else {
            super.onDraw(canvas);
        }
    }

    public abstract Bitmap getBitmap();

}
