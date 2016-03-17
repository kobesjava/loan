package com.qtt.jinrong.http.downloader;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.qtt.framework.util.FileUtils;
import com.qtt.jinrong.R;

import java.io.File;

public class FileDownloadListener {
	// 进度条
	private ProgressDialog mProgressBar;
	// 文件名字
	private String mName;

	private FileDownloader mDownloader;
	// 文件大小

	private Context mContext;

	private String mAppName;

	private String mFILE_DIR;
	private DownloadCallBack mCallBack;

	private NotificationManager mNotificationManager;

	private Notification mNotification;

	private static final int DOWN_LOAD_ERROR = 0x0022;// 下载链接异常

	private int mIcLauncher;

	private PendingIntent pi;

	public interface DownloadCallBack {
		/**
		 * 下载成功
		 */
		public void downloadSuccess(File file);

		/**
		 * 下载失败
		 */
		public void downloadFail();
	}

	public FileDownloadListener(Context context, ProgressDialog bar, final String path, DownloadCallBack callBack, int icLauncher,
			String AppName, String FILE_DIR) {
		this.mFILE_DIR = FILE_DIR;
		this.mAppName = AppName;
		this.mProgressBar = bar;
		this.mContext = context;
		this.mName = path.substring(path.lastIndexOf("/") + 1);
		this.mCallBack = callBack;
		this.mIcLauncher = icLauncher;
		notificationHandle();
		mDownloader = new FileDownloader(context, handler, mProgressBar, mFILE_DIR);
		try {
			mProgressBar.show();
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						mDownloader.download(path, 1);
					} catch (Exception e) {
						e.printStackTrace();
						// 下载失败的回调
						handler.sendEmptyMessage(DOWN_LOAD_ERROR);
					}
				}
			});
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
			// 下载失败的回调
			mCallBack.downloadFail();
		}
	}

	// 通知栏的处理
	public void notificationHandle() {
		pi = PendingIntent.getActivity(mContext, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
		if (mNotificationManager == null) {
			mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
		}
		mNotification = new Notification();
		mNotification.icon = mIcLauncher;
		mNotification.contentView = new RemoteViews(mContext.getPackageName(), R.layout.download_notice_notification);
		mNotification.contentIntent = pi;
	}

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {

		@SuppressLint("NewApi")
		@Override
		public void handleMessage(Message msg) {
			int progress = msg.what;
			if (progress != 1000) {
				if (progress == 100) {
					File file = new File(FileUtils.getFilePath(mFILE_DIR, mName));
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mNotification.contentIntent = pi;
					mNotification.contentView.setTextViewText(R.id.tvSize, progress + "%");
					mNotification.contentView.setTextViewText(R.id.tvTitle,
							mContext.getResources().getString(R.string.download_over, mAppName));
					mNotification.tickerText = mContext.getResources().getString(R.string.download_over, mAppName);
					mNotificationManager.notify(0, mNotification);
					mCallBack.downloadSuccess(file);
					return;
				} else if (progress == DOWN_LOAD_ERROR) {
					mCallBack.downloadFail();
				} else {
					mNotification.contentView.setTextViewText(R.id.tvSize, progress + "%");
					mNotification.contentView.setTextViewText(R.id.tvTitle,
							mContext.getResources().getString(R.string.download_text, mAppName));
					mNotification.contentView.setProgressBar(R.id.pbDownLoad, 100, progress, false);
					mNotificationManager.notify(0, mNotification);
				}
			} else {

				Toast.makeText(mContext, R.string.storage_unable, Toast.LENGTH_SHORT).show();

				return;

			}
			super.handleMessage(msg);

		}

	};

}
