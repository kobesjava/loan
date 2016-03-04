package com.qtt.framework.util;

import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 文件的工具类
 *
 * @author shenyamin
 */
public class FileUtils {
    // 得到当前外部存储设备的目录
    public static final String SDK_ROOT_PATH = Environment.getExternalStorageDirectory()
            + File.separator;
    ;
    // 获取扩展SD卡设备状态
    private static String SDStateString = Environment.getExternalStorageState();
    ;

    public FileUtils() {
    }


    /**
     * 在SD卡上创建文件
     *
     * @param dir      目录路径
     * @param fileName
     * @return
     * @throws IOException
     **/
    public static File createFileInSDCard(String dir, String fileName)
            throws IOException {
        creatSDDir(dir);
        File file = new File(getFilePath(dir, fileName));
        file.createNewFile();
        return file;
    }

    /**
     * 在SD卡上创建目录
     *
     * @param dir 目录路径
     * @return
     **/
    public static File creatSDDir(String dir) {
        File dirFile = new File(getFilePath(dir, null));
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return dirFile;
    }

    /**
     * 判断目录是否存在<br>
     * 存在:true,不存在:false
     *
     * @param dir
     * @return
     */
    public static boolean dirExist(String dir) {
        File file = new File(getFilePath(dir, null));
        if (file.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * 获取文件大小
     *
     * @param dir
     * @param fileName
     * @return
     */
    public static int fileLen(String dir, String fileName) {
        FileInputStream fis = null;
        try {
            if (dirExist(dir) && fileExist(dir, fileName)) {
                File file = new File(getFilePath(dir, fileName));
                fis = new FileInputStream(file);
                return fis.available();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 文件是否存在<br>
     * 存在：ture,不存在:false
     *
     * @param dir
     * @param fileName
     * @return
     */
    public static boolean fileExist(String dir, String fileName) {
        if (dirExist(dir)) {
            File file = new File(getFilePath(dir, fileName));
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param dir
     * @param fileName
     * @return
     */
    public static boolean delFile(String dir, String fileName) {
        if (fileExist(dir, fileName)) {
            File file = new File(getFilePath(dir, fileName));
            return file.delete();
        }
        return false;
    }

    /**
     * 文件是否存在<br>
     * 存在：ture,不存在:false
     *
     * @param filePath
     * @return
     */
    public static boolean fileExists(String filePath) {
        if (StringUtil.isEmptyNull(filePath)) {
            return false;
        }
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public static boolean delFile(String filePath) {
        if (StringUtil.isEmptyNull(filePath)) {
            return false;
        }
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * sd卡的文件路径（包含文件）或者文件的目录<br>
     * fileName的长度>0,返回文件路径 ,例:AAA/BBB/C.txt<br>
     * fileName=null或者长度为0,返回文件目录,例:AAA/BBB/<br>
     *
     * @param dir      目录路径
     * @param fileName 文件名称
     * @param dir
     * @param fileName
     * @return
     **/
    public static String getFilePath(String dir, String fileName) {
        if (fileName != null && fileName.length() > 0) {
            return SDK_ROOT_PATH + dir + File.separator + fileName;
        } else {
            return SDK_ROOT_PATH + dir + File.separator;
        }
    }

    /***
     * 获取SD卡的剩余容量,单位是Byte
     *
     * @return
     **/
    @SuppressWarnings("deprecation")
    public static long getSDAvailableSize() {
        if (SDStateString.equals(Environment.MEDIA_MOUNTED)) {
            // 取得sdcard文件路径
            File pathFile = Environment
                    .getExternalStorageDirectory();
            android.os.StatFs statfs = new android.os.StatFs(pathFile.getPath());
            // 获取SDCard上每个block的SIZE
            long nBlocSize = statfs.getBlockSize();
            // 获取可供程序使用的Block的数量
            long nAvailaBlock = statfs.getAvailableBlocks();
            // 计算 SDCard 剩余大小Byte
            long nSDFreeSize = nAvailaBlock * nBlocSize;
            return nSDFreeSize;
        }
        return 0;
    }

    /**
     * 将一个字节数组数据写入到SD卡中
     **/
    public boolean write2SD(String dir, String fileName, byte[] bytes) {

        if (bytes == null) {
            return false;
        }

        OutputStream output = null;
        try {
            // 拥有可读可写权限，并且有足够的容量
            if (SDStateString.equals(Environment.MEDIA_MOUNTED)
                    && bytes.length < getSDAvailableSize()) {
                File file = null;
                creatSDDir(dir);
                file = createFileInSDCard(dir, fileName);
                output = new BufferedOutputStream(new FileOutputStream(file));
                output.write(bytes);
                output.flush();
                return true;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /***
     * 从sd卡中读取文件，并且以字节流返回
     *
     * @param dir
     * @param fileName
     * @return
     **/
    public static byte[] readFromSD(String dir, String fileName) {
        File file = new File(getFilePath(dir, fileName));
        if (!file.exists()) {
            return null;
        }
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中 ,从网络上读取图片
     **/
    public static File write2SDFromInput(String dir, String fileName, InputStream input) {

        File file = null;
        OutputStream output = null;
        try {
            int size = input.available();
            // 拥有可读可写权限，并且有足够的容量
            if (SDStateString.equals(Environment.MEDIA_MOUNTED)
                    && size < getSDAvailableSize()) {
                creatSDDir(dir);
                file = createFileInSDCard(dir, fileName);
                output = new BufferedOutputStream(new FileOutputStream(file));
                byte buffer[] = new byte[4 * 1024];
                int temp;
                while ((temp = input.read(buffer)) != -1) {
                    output.write(buffer, 0, temp);
                }
                output.flush();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 解压一个压缩文档 到指定位置
     *
     * @param zipFileString 压缩包的名字
     * @param outPathString 指定的路径
     * @throws Exception
     */
    @SuppressWarnings("resource")
    public static String UnZipFolder(String zipFileString, String outPathString)
            throws Exception {
//        System.out.println("UnZipFolder(String, String)"+zipFileString+">>"+outPathString);
        java.util.zip.ZipInputStream inZip = new java.util.zip.ZipInputStream(
                new FileInputStream(zipFileString));
        java.util.zip.ZipEntry zipEntry;
        String szName = "";
        String returnZipFileSavefloder = "";
        while ((zipEntry = inZip.getNextEntry()) != null) {
            szName = zipEntry.getName();

            if (zipEntry.isDirectory()) {

                // get the folder name of the widget
                szName = szName.substring(0, szName.length() - 1);
                File folder = new File(outPathString
                        + File.separator + szName);
                folder.mkdirs();
//                System.out.println("zip has folder:"+folder.getAbsolutePath());
                returnZipFileSavefloder = folder.getAbsolutePath();
            } else {
                File parentfile = new File(outPathString);
                if (!parentfile.exists()) {
                    if (!parentfile.mkdirs()) {
                        System.err.println("mkdirs fail");
                        return returnZipFileSavefloder;
                    }
                } else if (!parentfile.isDirectory()) {
                    System.err.println("create mkdirs ");
                    if (!parentfile.mkdirs()) {
                        System.err.println("mkdirs fail");
                        return returnZipFileSavefloder;
                    }
                }

                File file = new File(outPathString
                        + File.separator + szName);
                file.createNewFile();
                // get the output stream of the file
                FileOutputStream out = new FileOutputStream(
                        file);
                int len;
                byte[] buffer = new byte[1024];
                // read (len) bytes into buffer
                while ((len = inZip.read(buffer)) != -1) {
                    // write (len) byte from buffer at the position 0
                    out.write(buffer, 0, len);
                    out.flush();
                }
                out.close();
//                System.out.println("zip has file:"+file.getAbsolutePath());
                if (TextUtils.isEmpty(returnZipFileSavefloder)) {
                    returnZipFileSavefloder = file.getParent();
                }
            }
        }//end of while

        inZip.close();
        return returnZipFileSavefloder;
    }

    /**
     * 压缩文件,文件夹
     *
     * @param srcFileString 要压缩的文件/文件夹名字
     * @param zipFileString 指定压缩的目的和名字
     * @throws Exception
     */
    public static void ZipFolder(String srcFileString, String zipFileString)
            throws Exception {
        android.util.Log.v("XZip", "ZipFolder(String, String)");

        //创建Zip包
        java.util.zip.ZipOutputStream outZip = new java.util.zip.ZipOutputStream(
                new FileOutputStream(zipFileString));

        //打开要输出的文件
        File file = new File(srcFileString);

        //压缩
        ZipFiles(file.getParent() + File.separator,
                file.getName(),
                outZip);

        //完成,关闭
        outZip.finish();
        outZip.close();

    }

    /**
     * 压缩文件
     *
     * @param folderString
     * @param fileString
     * @param zipOutputSteam
     * @throws Exception
     */
    private static void ZipFiles(String folderString, String fileString,
                                 java.util.zip.ZipOutputStream zipOutputSteam) throws Exception {
        android.util.Log.v("XZip", "ZipFiles(String, String, ZipOutputStream)");

        if (zipOutputSteam == null)
            return;

        File file = new File(folderString + fileString);

        //判断是不是文件
        if (file.isFile()) {

            java.util.zip.ZipEntry zipEntry = new java.util.zip.ZipEntry(
                    fileString);
            FileInputStream inputStream = new FileInputStream(
                    file);
            zipOutputSteam.putNextEntry(zipEntry);

            int len;
            byte[] buffer = new byte[4096];

            while ((len = inputStream.read(buffer)) != -1) {
                zipOutputSteam.write(buffer, 0, len);
            }

            inputStream.close();

            zipOutputSteam.closeEntry();
        } else {

            //文件夹的方式,获取文件夹下的子文件
            String fileList[] = file.list();

            //如果没有子文件, 则添加进去即可
            if (fileList.length <= 0) {
                java.util.zip.ZipEntry zipEntry = new java.util.zip.ZipEntry(
                        fileString + File.separator);
                zipOutputSteam.putNextEntry(zipEntry);
                zipOutputSteam.closeEntry();
            }

            //如果有子文件, 遍历子文件
            for (int i = 0; i < fileList.length; i++) {
                ZipFiles(folderString, fileString + File.separator
                        + fileList[i], zipOutputSteam);
            }//end of for

        }//end of if

    }//end of func

    public static void deleteFile(File file) {
        if (!file.exists()) {
            return;
        } else {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] childFile = file.listFiles();
                if (childFile == null || childFile.length == 0) {
                    file.delete();
                    return;
                }
                for (File f : childFile) {
                    deleteFile(f);
                }
                file.delete();
            }
        }
    }

    public static boolean isPhotoComplete(String houseIdPath, int livingRoomSum, int bedRoomSum, int wcRoomSum) {
        if (houseIdPath == null || houseIdPath.length() == 0) {
            return false;
        }

        File houseIdFile = new File(houseIdPath);

        if (!houseIdFile.exists()) {
            return false;
        }
        ArrayList<File> fileList = new ArrayList<File>();
        getFile(houseIdFile, fileList);
        if (fileList.size() >= getTotalNumbers(livingRoomSum, bedRoomSum, wcRoomSum)) {
            return true;
        }
        return false;
    }

    public static int getTotalNumbers(int livingRoomSum, int bedRoomSum, int wcRoomSum) {
        int totalNumbers = 0;
        totalNumbers += livingRoomSum * 2;

        totalNumbers += bedRoomSum * 2;

        totalNumbers += wcRoomSum;

        //邮箱，电梯间,门牌，外景，阳台，厨房
        totalNumbers += 5;
        return totalNumbers;
    }

    public static ArrayList<File> getFile(File file, ArrayList<File> list) {
        if (!file.exists()) {
            return null;
        }
        File[] fileArray = file.listFiles();
        for (File f : fileArray) {
            if (f.isFile()) {
                list.add(f);
            } else {
                getFile(f, list);
            }
        }
        return list;
    }


    public static boolean copyFile(File oldFile, File newFile) {
        boolean isOk = true;
        try {
            int bytesum = 0;
            int byteread = 0;
            if (oldFile.exists()) {
                InputStream inStream = new FileInputStream(oldFile); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newFile);
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                fs.flush();
                fs.close();
                inStream.close();
            } else {
                isOk = false;
            }
        } catch (Exception e) {
            isOk = false;
        }
        return isOk;
    }

    public static String getLastestFileInDirectory(String filePath) {
        if (filePath == null || filePath.length() == 0) {
            return null;
        }
        File file = new File(filePath);
        File[] items = file.listFiles();
        if (items.length == 0) {
            return null;
        }
        Arrays.sort(items, new Comparator<File>() {
            @Override
            public int compare(File lhs, File rhs) {
                if (lhs.lastModified() < rhs.lastModified()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        if (items.length > 0) {
            return items[0].getAbsolutePath();
        }

        return null;
    }


    public static String readFileContentStr(FileInputStream p_stream, long len) {
        String readOutStr = null;

        try {
            DataInputStream dis = new DataInputStream(p_stream);
            try {
                //long len = new File(fullFilename).length();   file.
                //if (len > Integer.MAX_VALUE) throw new IOException("File "+fullFilename+" too large, was "+len+" bytes.");
                byte[] bytes = new byte[(int) len];
                dis.readFully(bytes);
//                int len = 0;
//                int temp=0;          //所有读取的内容都使用temp接收
//                while((temp=dis.read())!=-1){    //当没有读取完时，继续读取
//                    bytes[len]=(byte)temp;
//                    len++;
//                }
//                dis.close();
                readOutStr = new String(bytes, "UTF-8");
            } finally {
                dis.close();
            }

            //Log.d("readFileContentStr", "Successfully to read out string from file "+ fullFilename);
        } catch (IOException e) {
            readOutStr = null;

            //e.printStackTrace();
            //Log.d("readFileContentStr", "Fail to read out string from file "+ fullFilename);
        }

        return readOutStr;
    }
}
