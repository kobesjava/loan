package com.qtt.jinrong.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库中文件下载记录的操作，包括：插入，删除，更�?
 * 
 * @author shenyamin
 * 
 */
public class DownloadFileDB {
	// 数据库字�?path, thid, done
	public static final String COL_PATH = "File_Path";
	public static final String COL_THID = "File_Thid";
	public static final String COL_DONE = "File_Done";
	public static final String TABLE_NAME = "DownloadFileRecord";// 数据表名
	private static DatabaseUtil helper;

	public DownloadFileDB(Context context) {
		helper = new DatabaseUtil(context);
	}

	/**
	 * 插入数据
	 * 
	 * @param model
	 *            DownLoadFileModel实体�?
	 */
	public boolean insert(DownloadFileModel model) {
		try {
			SQLiteDatabase db = helper.getWritableDatabase();
			db.execSQL(
					"INSERT INTO " + TABLE_NAME + "(" + COL_PATH + ", "
							+ COL_THID + ", " + COL_DONE + ") VALUES(?, ?, ?)",
					new Object[] { model.getPath(), model.getThid(),
							model.getDone() });
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除记录
	 * 
	 * @param path
	 *            URL路径
	 * @param thid
	 *            线程id
	 */
	// public void delete(String path, int thid) {
	// SQLiteDatabase db = helper.getWritableDatabase();
	// db.execSQL("DELETE FROM " + TABLE_NAME
	// + " WHERE " + COL_PATH + "=? AND " + COL_THID + "=?", new Object[] {
	// path, thid });
	// }

	/**
	 * 更新记录
	 * 
	 * @param model
	 *            DownLoadFileModel实体�?
	 */
	public boolean update(DownloadFileModel model) {
		try {
			SQLiteDatabase db = helper.getWritableDatabase();
			db.execSQL(
					"UPDATE " + TABLE_NAME + " SET " + COL_DONE + "=? WHERE "
							+ COL_PATH + "=? AND " + COL_THID + "=?",
					new Object[] { model.getDone(), model.getPath(),
							model.getThid() });
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 查询表中的某�?��记录
	 * 
	 * @param path
	 *            URL路径
	 * @param thid
	 *            线程Id
	 * @return
	 */
	public DownloadFileModel query(String path, int thid) {
		DownloadFileModel model = null;
		Cursor c = null;
		try {
			SQLiteDatabase db = helper.getWritableDatabase();
			String sql = "SELECT " + COL_PATH + ", " + COL_THID + ", "
					+ COL_DONE + " FROM " + TABLE_NAME + " WHERE " + COL_PATH
					+ "=? AND " + COL_THID + "=?";
			c = db.rawQuery(sql, new String[] { path, String.valueOf(thid) });
			if (c.moveToNext()) {
				model = new DownloadFileModel(c.getString(0), c.getInt(1),
						c.getInt(2));
				return model;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return null;
	}

	/**
	 * 查询APK下载是否完成
	 * 
	 * @param path
	 *            URL路径
	 * @param thid
	 *            线程Id
	 * @return
	 */
//	public int queryDownloadOver(String path) {
//		Cursor c = null;
//		try {
//			SQLiteDatabase db = helper.getWritableDatabase();
//			String sql = "SELECT " + COL_DONE + " FROM " + TABLE_NAME
//					+ " WHERE " + COL_PATH + "=? AND " + COL_THID + " = -9999";// -9999已经下载完成的标�?
//			c = db.rawQuery(sql, new String[] { path });
//			if (c.moveToNext()) {
//				return c.getInt(0);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return 0;
//		} finally {
//			if (c != null)
//				c.close();
//		}
//
//		return 0;
//	}

	/**
	 * 删除下载记录
	 * 
	 * @param path
	 *            文件路径
	 * @param len
	 */
	public boolean deleteAll(String path, long len) {
		Cursor c = null;
		try {
			SQLiteDatabase db = helper.getWritableDatabase();
			String sel = "SELECT SUM(" + COL_DONE + ") FROM " + TABLE_NAME
					+ " WHERE " + COL_PATH + "=?";
			c = db.rawQuery(sel, new String[] { path });
			if (c.moveToNext()) {
				int result = c.getInt(0);
				if (result == len) {
					db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE "
							+ COL_PATH + "=? ", new Object[] { path });
					// 删除下载记录后，插入�?��新的记录包含本次下载的url,apk的大小，-9999作为标识以便下次提取这条记录
//					insert(new DownloadFileModel(path, -9999, len));
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return false;
	}

	/**
	 * 删除�?��路径为path的下载记�?
	 * 
	 * @param path
	 *            文件路径
	 */
	public boolean deleteDownloadRecord(String path) {
		Cursor c = null;
		try {
			SQLiteDatabase db = helper.getWritableDatabase();
			String sel = "SELECT 1 FROM " + TABLE_NAME + " WHERE " + COL_PATH
					+ "=? ";// + COL_THID +"= -9999"
			c = db.rawQuery(sel, new String[] { path });
			if (c.moveToNext()) {
				db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COL_PATH
						+ "=? ", new Object[] { path });// AND "+ COL_THID +"=
														// -9999"
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return false;
	}

	/**
	 * 查询是否有未完成的记�?
	 * �?true;没有:false;
	 * @param path
	 * @return
	 */
	public boolean queryUndone(String path) {
		Cursor c = null;
		try {
			SQLiteDatabase db = helper.getWritableDatabase();
			String undo = "SELECT " + COL_PATH + " FROM " + TABLE_NAME + " WHERE "+ COL_PATH +" = '"+ path +"'";//+ COL_THID +"=-9999
			c = db.rawQuery(undo, null);
			if(c.moveToNext()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally{
			if(c != null){
				c.close();
			}
		}
		return false;
	}

}
