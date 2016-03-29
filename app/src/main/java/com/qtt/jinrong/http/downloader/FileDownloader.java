package com.qtt.jinrong.http.downloader;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.qtt.framework.util.FileUtils;
import com.qtt.jinrong.db.DownloadFileDB;
import com.qtt.jinrong.db.DownloadFileModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 文件下载
 * 
 * @author shenyamin
 * 
 */
public class FileDownloader {

	private ProgressDialog mProgressBar;

	private URL mUrl;

	private String mName;
	private boolean finished;

	private boolean paused;

	private long current;
	private int mPartLen;

	private int mCount = 1;
	private int progress;
	private Handler mHandler;
	// private Context mContext;
	private DownloadFileDB mDownloadFileDB;
	/**
	 * 文件目录
	 */
	public String mFILE_DIR;	
	
	private	MyAsyncTask asyncTask = new MyAsyncTask();
	/**
	 * 已下载完成部分
	 */
	@SuppressWarnings("unused")
	private int mDone;
	/**
	 * 是否停止
	 */
	private boolean mIsPause;
	/**
	 * 文件长度
	 */
	private long mFileLen;

	private Context mContext;

	public FileDownloader(Context context, Handler handler, ProgressDialog progressBar, String FILE_DIR) {
		 mFILE_DIR = FILE_DIR;
		mProgressBar = progressBar;
		mContext = context;
		mHandler = handler;
		mDownloadFileDB = new DownloadFileDB(context);

	}

	/**
	 * 多线程下载
	 * 
	 * @param path
	 *            下载路径
	 *
	 * @throws Exception
	 */
	public void download(final String path, final int count) throws Exception {

		try {

			mUrl = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
			// 设置超时时间
			conn.setConnectTimeout(3000);
			if (conn.getResponseCode() == 200) {
				mFileLen = conn.getContentLength();
				// 文件名称
				mName = path.substring(path.lastIndexOf("/") + 1);

				// 文件的路径
				File file = null;
				boolean bFileExist = false;
				boolean bCheckAPk = false;
				try {
					file = FileUtils.createFileInSDCard(mFILE_DIR, mName);
					bFileExist = FileUtils.fileExist(mFILE_DIR, mName);
					// 判断apk是否是否可用:可用返回true,否返回false
					bCheckAPk = checkAPK(mName);
				} catch (Exception e) {
					Message message = new Message();
					message.what = 1000;
					mHandler.sendMessage(message);
					mProgressBar.dismiss();
					e.printStackTrace();
					return;
				}

				// 标识整个文件的大小
				// sendMessage(0, mFileLen, "fileLen");
				// 判断文件是否存在

				// 是否已经下载完成
				// 已经完成就发送消息通知Handler，没有完成开始下载文件

				if (bFileExist && bCheckAPk) {
					Message msg = new Message();
					msg.what = 100;
					mHandler.handleMessage(msg);
					return;
				}
				// 下载记录不存在，删除文件，重新下载
				if (existUndo(path) && bCheckAPk) { // !dbOverRecordExist(path) &&! bCheckAPk
					FileUtils.delFile(mFILE_DIR, mName);
				}
				RandomAccessFile raf = new RandomAccessFile(file, "rws");
				raf.setLength(mFileLen);
				raf.close();
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						asyncTask.execute(path);
					}
				});

			} else {
				throw new IllegalArgumentException("404 path: " + path);
			}
		} catch (Exception e) {

			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 查询是否有未完成的记录有：true;没有:false
	 * 
	 * @param path
	 * @return
	 */
	private boolean existUndo(String path) {
		return mDownloadFileDB.queryUndone(path);
	}

	/**
	 * 判断apk文件是否可用 可用：true;不可�?false
	 * 
	 * @param name
	 * @return
	 */
	private boolean checkAPK(String name) {
		if (!FileUtils.fileExist(mFILE_DIR, name)) {
			return false;
		}
		try {
			PackageManager pm = mContext.getPackageManager();
			PackageInfo info = pm.getPackageArchiveInfo(FileUtils.getFilePath(mFILE_DIR, name), PackageManager.GET_ACTIVITIES);
			if (info != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 暂停下载
	public void pause() {
		mIsPause = true;
	}

	// 继续下载
	public void resume() {
		mIsPause = false;
		synchronized (mDownloadFileDB) {
			mDownloadFileDB.notifyAll();
		}
	}

	private final class MyAsyncTask extends AsyncTask<String, Integer, Integer> {

		private OutputStream os;

		@Override
		protected Integer doInBackground(String... params) {
			try {
				DownloadFileModel model = mDownloadFileDB.query(mUrl.toString(), mCount);
				if (model != null) {
					mDone += model.getDone();
				} else {
					model = new DownloadFileModel(mUrl.toString(), mCount, 0);
					mDownloadFileDB.insert(model);
				}
				int start = mCount * mPartLen + model.getDone();// �?��位置 + 已下载的�?
				@SuppressWarnings("unused")
				int end = (mCount + 1) * mPartLen - 1;
				//

				byte[] buffer = new byte[1024 * 10];
				int len;
				@SuppressWarnings("unused")
				int w = 0;
				URL url = new URL(params[0]);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(3000);
				if (conn.getResponseCode() == 200) {
					File file = FileUtils.createFileInSDCard(mFILE_DIR, mName);
					mFileLen = conn.getContentLength();
					InputStream is = conn.getInputStream();
					os = new FileOutputStream(file);
					int times = 0;
					RandomAccessFile raf = new RandomAccessFile(file, "rws");
					raf.seek(start);
					while (!finished) {
						while (!paused && (len = is.read(buffer)) != -1) {

							current += len;
							os.write(buffer, 0, len);
							progress = (int)(current * 100l / mFileLen);

							publishProgress(progress);

							raf.write(buffer, 0, len);
							mDone += len;

							if (progress - times >= 3) {
								Message msg = new Message();
								msg.what = progress;
								mHandler.sendMessage(msg);
								times = progress;
							} else if (progress == 100) {
								Message msg = new Message();
								msg.what = progress;
								mHandler.sendMessage(msg);
								times = progress;
							}
						}

						is.close();
						raf.close();
					}

				}

				mDownloadFileDB.deleteAll(model.getPath(), mFileLen);

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return progress;
		}

		@Override
		protected void onPostExecute(Integer result) {
			Log.i("onPostExecute ", result + "");
			if(result == null || result.intValue() != 100) {
				Message msg = new Message();
				msg.what = -100;
				mHandler.handleMessage(msg);
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {

			if (values[0] == 100) {
				finished = true;

			}

			mProgressBar.setProgress(progress);

			super.onProgressUpdate(values);
		}

		@Override
		protected void onPreExecute() {

			if(mProgressBar != null && !mProgressBar.isShowing())mProgressBar.show();
			super.onPreExecute();
		}

	}
}