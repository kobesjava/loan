package com.finance.framework.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author bestar
 * @CreateDate 2014/6/19 图片处理 处理完的图片会保存到原来的图片路径
 */
public class AdvancedBitmapUtils {

    public static final int MAX_SIZE = 1920;// 图片的最大边长
    public static final int COMPRESS_QUALITY = 40;// 图片输出时的最大压缩比率
    public static final int ROTATE_QUALITY = 100;// 旋转图片时的最大压缩比率
    public static final int COMPRESS_FACTOR = 150 * 1024;// 当图片大小小于这个数时， 不压缩

    public static final int TOP_LEFT_SIDE = 1; // "Top, left side (Horizontal / normal)";
    public static final int TOP_RIGHT_SIDE = 2; // "Top, right side (Mirror horizontal)";
    public static final int BOTTOM_RIGHT_SIDE = 3; // "Bottom, right side (Rotate 180)";
    public static final int BOTTOM_LEFT_SIDE = 4; // "Bottom, left side (Mirror vertical)";
    public static final int LEFT_SIDE_TOP = 5;// "Left side, top (Mirror horizontal and rotate 270 CW)";
    public static final int RIGHT_SIDE_TOP = 6; // "Right side, top (Rotate 90 CW)";
    public static final int RIGHT_SIDE_BOTTOM = 7; // "Right side, bottom (Mirror horizontal and rotate 90 CW)";
    public static final int LEFT_SIDE_BOTTOM = 8;// "Left side, bottom (Rotate 270 CW)";


    public static void processImage(String imagePath) {
        File file = new File(imagePath);
        if (file == null || !file.exists() || file.length() < COMPRESS_FACTOR) {
            return;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bmp = null;
            options.inJustDecodeBounds = true;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            BitmapFactory.decodeFile(imagePath, options);
            int height = options.outHeight;
            int width = options.outWidth;
            int reqHeight = 0;
            int reqWidth = 0;
            if (height > width) {
                if (height > MAX_SIZE) {
                    reqHeight = MAX_SIZE;
                } else {
                    reqHeight = height;
                }
                reqWidth = (reqHeight * width) / height;
            } else {
                if (width > MAX_SIZE) {
                    reqWidth = MAX_SIZE;
                } else {
                    reqWidth = width;
                }
                reqHeight = (reqWidth * height) / width;
            }
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            options.inJustDecodeBounds = false;
            bmp = BitmapFactory.decodeFile(imagePath, options);
            if (bmp != null) {
                bmp = Bitmap.createScaledBitmap(bmp, reqWidth, reqHeight, true);
                if (file.exists()) {// 将原文件删除
                    file.delete();
                }
                outputImage(imagePath, bmp, COMPRESS_QUALITY);// 将图片保存到原来文件中
                if (bmp != null && !bmp.isRecycled()) {
                    bmp.recycle();
                    bmp = null;
                    System.gc();
                }
            }
        } catch (OutOfMemoryError e) {
            System.err.println("out of memory error!!!!!!!!");
        }
    }


    public static void getSubmitString(String oriImagePath, String outImagePath, int orientation) {
        File file = new File(oriImagePath);
        if (file == null || !file.exists()) {
            return;
        }

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bmp = null;
            options.inJustDecodeBounds = true;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            BitmapFactory.decodeFile(oriImagePath, options);
            int height = options.outHeight;
            int width = options.outWidth;
            int reqHeight = 0;
            int reqWidth = 0;
            if (height > width) {
                if (height > MAX_SIZE) {
                    reqHeight = MAX_SIZE;
                } else {
                    reqHeight = height;
                }
                reqWidth = (reqHeight * width) / height;
            } else {
                if (width > MAX_SIZE) {
                    reqWidth = MAX_SIZE;
                } else {
                    reqWidth = width;
                }
                reqHeight = (reqWidth * height) / width;
            }
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            options.inJustDecodeBounds = false;
            bmp = BitmapFactory.decodeFile(oriImagePath, options);
            if (bmp != null) {
                bmp = Bitmap.createScaledBitmap(bmp, reqWidth, reqHeight, true);
                Bitmap rotateBitmap = getRotateBitmap(bmp, orientation);
                if (file.length() < COMPRESS_FACTOR) {
                    outputImage(outImagePath, rotateBitmap, ROTATE_QUALITY);
                } else {
                    outputImage(outImagePath, rotateBitmap, COMPRESS_QUALITY);
                }
                recycleBitmap(bmp);
                recycleBitmap(rotateBitmap);
            }
        } catch (OutOfMemoryError e) {
            System.err.println("out of memory error!!!!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getRotateBitmap(Bitmap srcBitmap, int oritation) {
        int width = srcBitmap.getWidth();
        int height = srcBitmap.getHeight();
        Matrix matrix = new Matrix();
        switch (oritation) {
            case TOP_LEFT_SIDE:
                return srcBitmap;
            case TOP_RIGHT_SIDE:
                matrix.postScale(-1, 1);
                break;
            case BOTTOM_RIGHT_SIDE:
                matrix.postRotate(180);
                break;
            case BOTTOM_LEFT_SIDE:
                matrix.postScale(1, -1);
                break;
            case LEFT_SIDE_TOP:
                matrix.postScale(-1, 1);
                matrix.postRotate(270);
                break;
            case RIGHT_SIDE_TOP:
                matrix.postRotate(90);
                break;
            case RIGHT_SIDE_BOTTOM:
                matrix.postScale(-1, 1);
                matrix.postRotate(90);
                break;
            case LEFT_SIDE_BOTTOM:
                matrix.postRotate(270);
                break;
            default:
                return srcBitmap;
        }
        Bitmap rotateBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, width, height, matrix, true);
        return rotateBitmap;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    /**
     * @param degree rotate degree
     * @param path   the image path
     */
    public static void rotatePicture(int degree, String path) {
        try {
            Matrix matrix = new Matrix();
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            matrix.postRotate(degree);
            Bitmap rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            outputImage(path, rotateBitmap, ROTATE_QUALITY, Bitmap.CompressFormat.PNG);
            recycleBitmap(bitmap);
            recycleBitmap(rotateBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param bitmap
     */
    public static void recycleBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
    }

    public static void outputImage(String filePath, Bitmap bitmap, int quality) {
        outputImage(filePath, bitmap, quality, Bitmap.CompressFormat.JPEG);
    }

    public static void outputImage(String filePath, Bitmap bitmap, int quality, Bitmap.CompressFormat format) {

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(filePath);
            bos = new BufferedOutputStream(fos);
            if (bos != null) {
                bitmap.compress(format, quality, bos);
                bos.flush();
                fos.flush();
            }
        } catch (IOException e) {

        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
