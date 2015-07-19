package shem.customviewpager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by shem on 7/17/15.
 */
public class ProgressView extends ViewGroup {

    private Paint paint;
    private int index;
    private float prec;
    private int[] colors;

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        colors = new int[] {Color.parseColor("#FFD600"),
                Color.parseColor("#FF6D00"),
                Color.parseColor("#0091EA"),
                Color.parseColor("#64DD17")};

        paint = new Paint();
        paint.setColor(colors[0]);

        index = 0;
        prec = 0;

        addView(getTextView("1"));
        addView(getTextView("2"));
        addView(getTextView("3"));
        addView(getTextView("4"));

        setWillNotDraw(false);
    }

    private TextView getTextView(String text) {
        TextView v = new TextView(getContext());
        v.setText(text);
        v.setTextSize(30);
        v.setGravity(Gravity.CENTER);
        return v;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(evaluateColor(prec, colors[index], colors[Math.min(index + 1, colors.length - 1)]));
        canvas.drawRect((index + prec) * canvas.getWidth() / 4, 0, (1 + index + prec) * canvas.getWidth() / 4, canvas.getHeight(), paint);

        for (int i = 0; i < getChildCount(); i++) {
            final View view = getChildAt(i);
            if (i == index) {
                view.setScaleX(1 - Math.abs(prec) * 0.3f);
                view.setScaleY(1 - Math.abs(prec) * 0.3f);
                view.setAlpha(1 - Math.abs(prec) * 0.5f);
            } else if (i == index + 1) {
                view.setScaleX(0.7f + Math.abs(prec) * 0.3f);
                view.setScaleY(0.7f + Math.abs(prec) * 0.3f);
                view.setAlpha(0.5f + Math.abs(prec) * 0.5f);
            } else {
                view.setScaleX(0.7f);
                view.setScaleY(0.7f);
                view.setAlpha(0.5f);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int childW = (right - left) / 4;
        final int childH = (bottom - top);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(i * childW, 0, (i + 1) * childW, childH);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int childWidth = width / 4;
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        }

        setMeasuredDimension(width, height);
    }

    public void setProgress(int index, float prec) {
        this.index = index;
        this.prec = prec;
        invalidate();
    }

    public static int evaluateColor(float fraction, int startInt, int endInt) {
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (int)((startA + (int)(fraction * (endA - startA))) << 24) |
                (int)((startR + (int)(fraction * (endR - startR))) << 16) |
                (int)((startG + (int)(fraction * (endG - startG))) << 8) |
                (int)((startB + (int)(fraction * (endB - startB))));
    }
}
