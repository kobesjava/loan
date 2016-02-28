package com.android.finance.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.android.finance.R;

/**
 * Created by yanxin on 16/2/23.
 */
public class LineVerticalView extends View {

    public LineVerticalView(Context context) {
        super(context);
    }

    public LineVerticalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //int specMode = MeasureSpec.getMode(heightMeasureSpec);
        //int specSize = MeasureSpec.getSize(heightMeasureSpec);
        //LogUtil.d("TEST","mode="+specMode+" size="+specSize);
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.color_9e9e0e));

        int h = getMeasuredHeight();
        int w = getMeasuredWidth();

        canvas.drawLine(0,0,0,h,paint);
    }

}
