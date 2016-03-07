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
 * Created by yanxin on 16/2/25.
 */
public class ImgText extends View {

    private String[] conts;

    private Bitmap mBitmap;

    private int textSize;

    private int margin;
    private int padding;
    private int textTop;

    private int textColor;

    public ImgText(Context context) {
        super(context);
    }

    public ImgText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.circle_orange);
        textSize = (int)getResources().getDimension(R.dimen.text_size_12);
        margin = (int)getResources().getDimension(R.dimen.margin_step_10);
        padding = (int)getResources().getDimension(R.dimen.margin_step_1);
        textTop = (int)getResources().getDimension(R.dimen.margin_step_10);
        textColor = getResources().getColor(R.color.color_800000);
    }

    public void setConts(String[] conts) {
        this.conts = conts;
        postInvalidate();
    }

    public void onDraw(Canvas canvas) {

        if(conts == null || conts.length == 0) return;

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);

        Paint paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setDither(true);
        paint1.setColor(textColor);
        paint1.setTextSize(textSize);

        int h = getHeight();
        int bitMapTop = (h-mBitmap.getHeight())/2;

        int left = 0;

        for(int i=0;i<conts.length;i++) {
            canvas.drawBitmap(mBitmap,left,bitMapTop,paint);
            left += mBitmap.getWidth()+padding;

            Rect rect = new Rect();
            paint1.getTextBounds(conts[i], 0, conts[i].length(), rect);
            canvas.drawText(conts[i], left, (h - rect.height()) / 2+textTop, paint1);

            left += rect.width()+margin;
        }

    }

}
