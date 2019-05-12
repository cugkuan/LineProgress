package com.cugkuan.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LineIndicatorView extends View {


    private int mDividerWidth = 4;
    private int mDividerColor = Color.TRANSPARENT;
    private int mIndicatorColor = Color.parseColor("#66cc33");
    private int mUnIndicatorColor = Color.parseColor("#cfcfcf");
    private int mMax = 10;
    private int progress = 5;
    private Paint mIndicatorPen;
    private Paint mUnIndicatorPen;
    private Paint mDividerPen;
    /**
     * 绘制的辅助工具
     */
    private Rect rect = new Rect();

    public LineIndicatorView(Context context) {
        this(context, null);
    }

    public LineIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LineIndicatorView);
            mIndicatorColor = array.getColor(R.styleable.LineIndicatorView_ll_indicatorColor
                    , Color.parseColor("#66cc33"));
            mUnIndicatorColor = array.getColor(R.styleable.LineIndicatorView_ll_color, Color.parseColor("#cfcfcf"));
            mDividerColor = array.getColor(R.styleable.LineIndicatorView_ll_dividerColor, Color.TRANSPARENT);
            mMax = array.getColor(R.styleable.LineIndicatorView_ll_max, 10);
            progress = array.getColor(R.styleable.LineIndicatorView_ll_progress, 5);
            mDividerWidth = array.getDimensionPixelOffset(R.styleable.LineIndicatorView_ll_dividerWidth, 4);
            if (mMax <= 0) {
                mMax = 1;
            }
            if (progress < 0) {
                progress = 0;
            }
            if (progress > mMax) {
                progress = mMax;
            }
            array.recycle();
        }
        mUnIndicatorPen = new Paint();
        mUnIndicatorPen.setColor(mUnIndicatorColor);
        mUnIndicatorPen.setAntiAlias(true);
        mUnIndicatorPen.setStyle(Paint.Style.FILL);

        mIndicatorPen = new Paint();
        mIndicatorPen.setColor(mIndicatorColor);
        mIndicatorPen.setAntiAlias(true);
        mIndicatorPen.setStyle(Paint.Style.FILL);

        if (mDividerColor != Color.TRANSPARENT) {
            mDividerPen = new Paint();
            mDividerPen.setColor(mDividerColor);
            mDividerPen.setAntiAlias(true);
            mDividerPen.setStyle(Paint.Style.FILL);
        }
    }

    /**
     * 设置的顺序不能颠倒
     * @param max
     */
    public void setMax(int max){
        mMax = max;
        if (mMax <= 0){
            mMax = 1;
        }
    }

    public void setProgress(int progress){
        this.progress =progress;
    }

    public void setDividerWidth(int dividerWidth){
        mDividerWidth = dividerWidth;
        if (mDividerWidth < 0){
            mDividerWidth = 0;
        }
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, heightSize);
        } else {
            setMeasuredDimension(widthSize, 20);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int each = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - (mMax - 1) * mDividerWidth) / mMax;
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        int x = 0;
        int y = getMeasuredHeight() - getPaddingBottom();
        for (int i = 0; i < mMax; i++) {
            if (i == mMax - 1) {
                rect.set(x, 0, getMeasuredWidth() - getPaddingRight(), y);
            } else {
                rect.set(x, 0, x + each, y);
            }
            if (i < progress) {
                canvas.drawRect(rect, mIndicatorPen);
            } else {
                canvas.drawRect(rect, mUnIndicatorPen);
            }
            //绘制分割线
            if (mDividerPen != null) {
                if (i < mMax - 1) {
                    rect.set(x + each, 0, x + each + mDividerWidth, y);
                    canvas.drawRect(rect, mDividerPen);
                }
            }
            x = x + each + mDividerWidth;
        }
        canvas.restore();
    }

}
