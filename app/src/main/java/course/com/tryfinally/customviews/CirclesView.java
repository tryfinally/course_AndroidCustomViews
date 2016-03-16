package course.com.tryfinally.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CirclesView extends View {
    private float mLineWidth;
    private int mLineColor = Color.BLACK;
    private int mRadius;
    private Paint mPaint;

    private List<Point> mPoints = new ArrayList<>();

    public CirclesView(Context context) {
        this(context, null);
    }

    public CirclesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclesView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        TypedArray a = null;
        try {
            a = getContext().obtainStyledAttributes(
                    attrs, R.styleable.Circles, defStyle, 0);


            mLineWidth = a.getDimension(
                    R.styleable.Circles_lineWidth, 2.0F);
            mLineColor = a.getColor(
                    R.styleable.Circles_lineColor,
                    Color.BLACK);
            mRadius = a.getInt(R.styleable.Circles_circleRadius, 1) * 10;
        }finally {
            if(a != null) a.recycle();
        }


        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mLineColor);
        mPaint.setStrokeWidth(mLineWidth);

        setSaveEnabled(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final float r = mRadius;
        for (Point point : mPoints) {
            canvas.drawCircle(point.x, point.y, r, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean r = super.onTouchEvent(event);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            mPoints.add(new Point(x, y));
        }

        invalidate();

        return r;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        SavedState ss = new SavedState(superState);
        ss.points = mPoints;
        ss.color = mLineColor;
        ss.width = mLineWidth;

        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        mPoints = ss.points;
        mLineWidth = ss.width;
        mLineColor = ss.color;
    }

    public void setRadius(int radius){
        mRadius = radius;
        invalidate();
    }

    public int getRadius(){
        return mRadius;
    }

    private static class SavedState extends BaseSavedState {
        List<Point> points;
        int color;
        float width;


        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);

            int n = in.readInt();
            points = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                int x = in.readInt();
                int y = in.readInt();
                points.add(new Point(x, y));
            }

            color = in.readInt();
            width = in.readFloat();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);

            out.writeInt(points.size());
            for (Point point : points) {
                out.writeInt(point.x);
                out.writeInt(point.y);
            }

            out.writeInt(color);
            out.writeFloat(width);
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
















