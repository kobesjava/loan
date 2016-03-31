package com.qtt.jinrong.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.qtt.framework.util.FileUtils;
import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.app.MyApplication;
import com.qtt.jinrong.config.Constants;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by yanxin on 2015/6/24.
 */
public class FileUtil {

    private static final String TAG = "FileUtil";

    /**
     * 创建app内部存储文件
     * /data/data/packagename/file
     */
    public static File createInnerFile(String fileName) {
        File file = new File(MyApplication.getInstance().getFilesDir(), fileName);
        try {
            if (!file.exists()) file.createNewFile();
            Log.d(TAG, "创建内部文件" + fileName + "成功");
            return file;
        } catch (Exception e) {
            Log.d(TAG, "创建内部文件" + fileName + "失败 " + e);
        }
        return null;
    }

    /**
     * 向app 内部存储文件写入内容
     *
     * @param fileName
     * @param cont
     * @return
     */
    public static boolean writeToInnerFile(String fileName, String cont) {
        cont = cont == null ? "" : cont;
        File file = createInnerFile(fileName);
        if (file == null) return false;

        FileOutputStream outputStream = null;
        try {
            outputStream = MyApplication.getInstance().openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(cont.getBytes());
            outputStream.flush();
            outputStream.close();
            outputStream = null;
            Log.d(TAG, "向内部文件" + fileName + "写入成功");
            return true;
        } catch (Exception e) {
            Log.d(TAG, "向内部文件" + fileName + "写入" + cont + "失败 " + e);
        } finally {
            try {
                if (outputStream != null) outputStream.close();
            } catch (Exception e) {

            }
        }
        return false;
    }

    /**
     * 获取app 内部存储文件内容
     *
     * @param fileName
     * @return
     */
    public static String readFromInnerFile(String fileName) {
        FileInputStream inputStream = null;
        try {
            inputStream = MyApplication.getInstance().openFileInput(fileName);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();
            inputStream = null;
            String cont = new String(bytes);
            Log.d(TAG, "获取内部文件" + fileName + "内容 成功 " + cont);
            return cont;
        } catch (Exception e) {
            Log.d(TAG, "获取内部文件" + fileName + "内容 失败 " + e);
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (Exception e) {

            }
        }
        return "";
    }

    public static String readFromOutFile(String dir, String fileName) {
        try {
            byte[] bt = FileUtils.readFromSD(Constants.FILE_ROOT_DIR + Constants.SEPARATOR + dir, fileName);
            if (bt == null) return "";
            String cont = new String(bt);
            Log.d(TAG, "获取内部文件" + fileName + "内容 成功 " + cont);
            return cont;
        } catch (Exception e) {
            Log.d(TAG, "获取内部文件" + fileName + "内容 失败 " + e);
        }
        return "";
    }

    public static boolean bufferedWriterToOutFile(String dir, String fileName, String count) {
        FileOutputStream outputStream = null;
        try {
            File file = FileUtils.createFileInSDCard(Constants.FILE_ROOT_DIR + Constants.SEPARATOR + dir, fileName);
            outputStream = new FileOutputStream(file, true);
            outputStream.write(count.getBytes());
            outputStream.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean writeToOutFile(String dir, String fileName, String cont) {
        FileOutputStream outputStream = null;
        try {
            File file = FileUtils.createFileInSDCard(Constants.FILE_ROOT_DIR + Constants.SEPARATOR + dir, fileName);
            outputStream = new FileOutputStream(file);
            outputStream.write(cont.getBytes());
            outputStream.flush();
            outputStream.close();
            outputStream = null;
            Log.d(TAG, "向内部文件" + fileName + "写入成功");
            return true;
        } catch (Exception e) {
            Log.d(TAG, "向内部文件" + fileName + "写入" + cont + "失败 " + e);
        } finally {
            try {
                if (outputStream != null) outputStream.close();
            } catch (Exception e) {

            }
        }
        return false;
    }


    public static boolean writeToOutFile(String dir, String fileName, Bitmap bitmap, int size) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, out);
        while (options > 10 && out.toByteArray().length / 1024 > size) {
            out.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, out);
            options -= 5;
        }
        FileOutputStream fos = null;
        try {
            File file = FileUtils.createFileInSDCard(Constants.FILE_ROOT_DIR + Constants.SEPARATOR + dir, fileName);
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
     * 指定文件压缩比
     *
     * @param dir
     * @param fileName
     * @param bitmap
     * @param options
     * @return
     */
    public static boolean writePicOutFile(String dir, String fileName, Bitmap bitmap, int options) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, out);
        FileOutputStream fos = null;
        try {
            File file = FileUtils.createFileInSDCard(Constants.FILE_ROOT_DIR + Constants.SEPARATOR + dir, fileName);
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

    public static boolean writePicOutFile(String dir, int options) {
        Bitmap bitmap = BitmapFactory.decodeFile(dir);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, out);
        FileOutputStream fos = null;
        try {
            File file = new File(dir);
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

    public static boolean writeToOutFile(String dir, String fileName, Bitmap bitmap) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            File file = FileUtils.createFileInSDCard(Constants.FILE_ROOT_DIR + Constants.SEPARATOR + dir, fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            fos.flush();
            return true;
        } catch (IOException e) {
            LogUtil.d(TAG, e.getMessage());
        } finally {
            try {
                if (bos != null) bos.close();
                if (fos != null) fos.close();
            } catch (Exception e) {
            }
        }
        return false;
    }


    /**
     * 读取文件的字节流
     *
     * @param filePath String
     * @return byte[]
     */
    public static byte[] getBytesFromFile(String filePath) {
        File file = new File(filePath);
        byte[] ret = null;
        try {
            if (file == null) {
                return null;
            }
            FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
            byte[] b = new byte[4096];
            int n;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            in.close();
            out.close();
            ret = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtil.e("FileSize", FormetFileSize(getFileSizes(file)));
        return ret;
    }

    public static long getFileSizes(File f) {//取得文件大小
        long s = 0;
        FileInputStream fis = null;
        try {
            if (f.exists()) {
                fis = new FileInputStream(f);
                s = fis.available();
            } else {
                f.createNewFile();
                System.out.println("文件不存在");
            }
        } catch (Exception e) {

        }
        return s;
    }

    public static String FormetFileSize(long fileS) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 删除目录下所有的文件
     * @param dir
     */
    public static void delete(String dir) {
        if (TextUtils.isEmpty(dir)) return;

        try {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(dir,filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                    } else if (delfile.isDirectory()) {
                        delete(dir + File.separator + filelist[i]);
                    }
                }
                file.delete();
            }

        } catch (Exception e) {
            Log.d(TAG, "删除文件夹" + dir + " 异常: " + e.getMessage());
        }
    }

    /**
     * 判断文件是否存在
     * @param filePath
     * @return
     */
    public static boolean isExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 获取文件夹下 所有文件(文件length>0)
     * @param dir
     * @return
     */
    public static File[] getFiles(String dir) {
        File mDir = new File(dir);
        if (!mDir.isDirectory()) return null;

        String[] filelist = mDir.list();
        File[] files = new File[filelist.length];
        File file;
        int index = 0;
        for (int i = 0; i < filelist.length; i++) {
            file = new File(dir,filelist[i]);
            if (file.isFile() && file.length() > 0) {
                files[index++] = file;
            }
        }

        return files;
    }

}
