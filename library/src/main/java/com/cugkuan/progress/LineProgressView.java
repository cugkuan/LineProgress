package com.cugkuan.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LineProgressView extends View {


    private int mDividerWidth = 4;
    private int mDividerColor = Color.TRANSPARENT;
    private int mProgressColor = Color.parseColor("#66cc33");
    private int mUnProgressColor = Color.parseColor("#cfcfcf");
    private int mMax = 10;
    private int progress = 5;
    private Paint mProgressPen;
    private Paint mUnProgressPen;
    private Paint mDividerPen;
    /**
     * 绘制的辅助工具
     */
    private Rect rect = new Rect();

    public LineProgressView(Context context) {
        this(context, null);
    }

    public LineProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LineProgressView);
            mProgressColor = array.getColor(R.styleable.LineProgressView_ll_progressColor
                    , Color.parseColor("#66cc33"));
            mUnProgressColor = array.getColor(R.styleable.LineProgressView_ll_color, Color.parseColor("#cfcfcf"));
            mDividerColor = array.getColor(R.styleable.LineProgressView_ll_dividerColor, Color.TRANSPARENT);
            mMax = array.getColor(R.styleable.LineProgressView_ll_max, 10);
            progress = array.getColor(R.styleable.LineProgressView_ll_progress, 5);
            mDividerWidth = array.getDimensionPixelOffset(R.styleable.LineProgressView_ll_dividerWidth, 4);
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
        mUnProgressPen = new Paint();
        mUnProgressPen.setColor(mUnProgressColor);
        mUnProgressPen.setAntiAlias(true);
        mUnProgressPen.setStyle(Paint.Style.FILL);

        mProgressPen = new Paint();
        mProgressPen.setColor(mProgressColor);
        mProgressPen.setAntiAlias(true);
        mProgressPen.setStyle(Paint.Style.FILL);

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
                canvas.drawRect(rect, mProgressPen);
            } else {
                canvas.drawRect(rect, mUnProgressPen);
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
