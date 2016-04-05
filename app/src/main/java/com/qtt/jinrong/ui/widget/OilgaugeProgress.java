package com.qtt.jinrong.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.qtt.jinrong.R;

/**
 * Created by yanxin on 16/3/3.
 */
public class OilgaugeProgress extends View {

    private int progress;

    private String title = "完善度";

    public OilgaugeProgress(Context context) {
        super(context);
    }

    public OilgaugeProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setProgress(int progress) {
        if(progress < 0) progress = 0;
        if(progress > 100) progress = 100;
        this.progress = progress;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();
        int height = getHeight();

        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.loan_first_finished_degree);
        canvas.drawBitmap(bitmap,(width-bitmap.getWidth())/2,height-bitmap.getHeight(),mPaint);

        Paint mPaintLine = new Paint();
        mPaintLine.setAntiAlias(true);
        mPaintLine.setDither(true);
        mPaintLine.setColor(getResources().getColor(R.color.white));

        int padding = getResources().getDimensionPixelOffset(R.dimen.margin_step_20);
        int left = width/2-7;
        int top = getResources().getDimensionPixelOffset(R.dimen.margin_step_28);
        int right = width/2+7;
        int bottom = top+getResources().getDimensionPixelOffset(R.dimen.margin_step_12);

        float startDeg = -360/48*2-90;
        float deg = 180+180/24*4;

        //canvas.drawLine(0,height -padding,width,height -padding,mPaintText);

        float num = (float)(22*progress/100.00);
        int count = 22;

        for (int i = 0; i < count; i++) {
            canvas.save();

            canvas.rotate((deg / (count - 1)) * i + startDeg, width / 2, height - padding);
            if((int)num==i) {
                float dec = num-i;
                float mRight = (right-left)*dec+left;
                canvas.drawRect(left, top, mRight, bottom, mPaintLine);
                mPaintLine.setColor(getResources().getColor(R.color.color_22000000));
                canvas.drawRect(mRight, top, right, bottom, mPaintLine);
            } else {
                canvas.drawRect(left, top, right, bottom, mPaintLine);
            }
            canvas.restore();
        }

        Paint mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setDither(true);
        mPaintText.setColor(getResources().getColor(R.color.color_f2ffffff));
        mPaintText.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size_13));

        Rect rect = new Rect();
        mPaintText.getTextBounds(title, 0, title.length(), rect);
        canvas.drawText(title, (width - rect.width()) / 2, height-rect.height(), mPaintText);

        float marginBottom1 = getResources().getDimension(R.dimen.margin_step_2);
        Rect rect1 = new Rect();
        mPaintText.setColor(getResources().getColor(R.color.white));
        mPaintText.setFakeBoldText(true);
        mPaintText.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size_20));
        mPaintText.getTextBounds(progress+"%", 0, (progress+"%").length(), rect1);
        canvas.drawText(progress + "%", (width - rect1.width()) / 2, height - marginBottom1 - rect1.height() - rect.height(), mPaintText);

    }
}
