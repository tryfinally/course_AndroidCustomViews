package course.com.tryfinally.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class Circles extends View {
    private float mLineWidth;
    private int mLineColor = Color.BLACK;
    private Paint mPaint;
    private TextPaint mTextPaint;

    public Circles(Context context) {
        this(context, null);
    }

    public Circles(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Circles(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.Circles, defStyle, 0);

        mLineWidth = a.getDimension(
                R.styleable.Circles_lineWidth, 2.0F);
        mLineColor = a.getColor(
                R.styleable.Circles_lineColor,
                Color.BLACK);

        a.recycle();

        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mLineColor);
        mPaint.setStrokeWidth(mLineWidth);

        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.GREEN);
        mTextPaint.setStrokeWidth(3);
        mTextPaint.setTextSize(48);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        float rad = Math.min(contentHeight, contentWidth)/3;
        canvas.drawCircle(getWidth()/2, getHeight()/2 , rad, mPaint);


        // Draw the text.
        canvas.drawText("Square in some geometry",
                10,
                getHeight()/5,
                mTextPaint);
    }
}
