package com.qtt.jinrong.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by yanxin on 16/4/5.
 */
public class BitmapUtil {

    /**
     * 压缩
     * @param path   图片路径
     * @param width  压缩后的宽度
     */
    public static boolean scale(String path,int width,int height,int quality) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path,options);

            if(options.outWidth < options.outHeight) { //竖屏牌照
                options.inSampleSize = calculateSampleSize(options,height,width);
            } else { //横屏牌照
                options.inSampleSize = calculateSampleSize(options,width,height);
            }
            options.inJustDecodeBounds = false;

            Bitmap bitmap = BitmapFactory.decodeFile(path,options);
            boolean success = saveBitmap(bitmap,path,quality);
            bitmap.recycle();
            bitmap = null;
            return success;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static int calculateSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSizeW = 1;
        if(width>reqWidth) {
            int halfWidth = width/2;
            while((halfWidth/inSampleSizeW) > reqWidth) {
                inSampleSizeW *= 2;
            }
        }

        int inSampleSizeH = 1;
        if(height>reqHeight) {
            int halfHeight = height/2;
            while((halfHeight/inSampleSizeH) > reqHeight) {
                inSampleSizeH *= 2;
            }
        }

        return inSampleSizeW>inSampleSizeH?inSampleSizeW:inSampleSizeH;
    }

    public static boolean saveBitmap(Bitmap bitmap,String path,int quality) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
        FileOutputStream fos = null;
        try {
            File file = new File(path);
            fos = new FileOutputStream(file);
            fos.write(out.toByteArray());
            fos.flush();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
            } catch (Exception e) {

            }
            try {
                if (fos != null) fos.close();
            } catch (Exception e) {

            }
        }
        return false;
    }



    /**
     * 判断Bitmap对象是否有效
     *
     * @param bmp Bitmap对象
     * @return true if bitmap is not null and not be recycled
     */
    public static boolean isBitmapAvailable(Bitmap bmp) {
        return bmp != null && !bmp.isRecycled();
    }

    /**
     * 缩放Bitmap对象
     *
     * @param srcBmp Bitmap对象
     * @param width  缩放后的宽度
     * @param height 缩放后的高度
     * @return 缩放后的Bitmap对象
     */
    public static Bitmap resizeBitmap(Bitmap srcBmp, int width, int height) {
        Bitmap dstBmp = null;
        if (isBitmapAvailable(srcBmp)) {
            int w = srcBmp.getWidth();
            int h = srcBmp.getHeight();
            Matrix matrix = new Matrix();
            matrix.postScale(((float) width / w), ((float) height / h));
            dstBmp = Bitmap.createBitmap(srcBmp, 0, 0, w, h, matrix, true);
        }
        return dstBmp;
    }

}
