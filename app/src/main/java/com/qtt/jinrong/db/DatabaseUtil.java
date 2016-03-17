package com.qtt.jinrong.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseUtil extends SQLiteOpenHelper {
	
	public static final int VERSION = 1;
	public static final String DB_NAME = "fybao.db";
	private SQLiteDatabase mSqLiteDatabase;
	
	private static final String SQL_CREATE_SEARCH = "create table " + " if not exists " + SearchDB.TABLE_NAME + " (" + 
			SearchDB.SEARCH_ID + " integer primary key, " + 
			SearchDB.SEARCH_CONTENT + " text, " + 
			SearchDB.USER_ID + " text, " + 
			SearchDB.SEARCH_DATE + " text);";
	
	private static final String SQL_CREATE_DOWNLOAD_RECODE = "CREATE TABLE if not exists " + DownloadFileDB.TABLE_NAME + "("+ DownloadFileDB.COL_PATH + " VARCHAR(1024), "
			+ DownloadFileDB.COL_THID +" INTEGER, " + DownloadFileDB.COL_DONE + " INTEGER, PRIMARY KEY("+ DownloadFileDB.COL_PATH +", "+ DownloadFileDB.COL_THID +"))";
	
	public DatabaseUtil(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	public static DatabaseUtil get(Context context) {
		DatabaseUtil instance = new DatabaseUtil(context);
		return instance;
	}
	
	private static DatabaseUtil ins = null;
	public static DatabaseUtil getInstance(Context context) {
		if (ins == null) {
			ins = new DatabaseUtil(context);
		}
		return ins;
	}
	
	public static void closePool() {
		if (ins != null) {
			ins.close();
			ins = null;
		}
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		this.mSqLiteDatabase = db;
		createTables();
	}

	private void createTables() {
		mSqLiteDatabase.execSQL(SQL_CREATE_SEARCH);
		mSqLiteDatabase.execSQL(SQL_CREATE_DOWNLOAD_RECODE);
//		db.execSQL(sql_create_mucgroup);
//		db.execSQL(sql_create_mucmember);
//		db.execSQL(sql_create_message);
//		db.execSQL(sql_create_collection);
//		db.execSQL(sql_create_file);
//		db.execSQL(sql_create_headicon);
//		db.execSQL(sql_create_friendlist);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			String sql_drop_user = "drop table if exists " + SearchDB.TABLE_NAME;
			db.execSQL(sql_drop_user);
//			
//			String sql_drop_mucgroup = "drop table if exists " + MultichatGroupDB.TABLE_NAME;
//			db.execSQL(sql_drop_mucgroup);
//			
//			String sql_drop_mucmember = "drop table if exists " + MultichatMemberDB.TABLE_NAME;
//			db.execSQL(sql_drop_mucmember);
//			
//			String sql_drop_message = "drop table if exists " + MessageDB.TABLE_NAME;
//			db.execSQL(sql_drop_message);
//			
//			String sql_drop_collect = "drop table if exists " + CollectionDB.TABLE_NAME;
//			db.execSQL(sql_drop_collect);
//			
//			String sql_drop_file = "drop table if exists " + FileDB.TABLE_NAME;
//			db.execSQL(sql_drop_file);
//			
//			String sql_drop_headericon = "drop table if exists " + FriendPhotoDB.TABLE_NAME;
//			db.execSQL(sql_drop_headericon);
			
//			String sql_drop_friendlist = "drop table if exists " + FriendDB.TABLE_NAME;
//			db.execSQL(sql_drop_friendlist);
			
			this.mSqLiteDatabase = db;
			createTables();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int insert(String table, ContentValues values) {
		SQLiteDatabase db = getWritableDatabase();
		try {
			int newRowID = (int) db.insert(table, null, values);
			db.close();
			return newRowID;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public void update(String table, ContentValues values, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = getWritableDatabase();
		try {
			
			db.update(table, values, whereClause, whereArgs);
			db.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Cursor queryAll(String table) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = null;
		try {
			
			c = db.query(table, null, null, null, null, null, null);
		} catch (Exception e) {
			
		}
		
		return c;
	}
	
	public Cursor queryByPk(BaseTable table, String[] pks) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = null;
		try {
			
			c = db.query(table.mTableName, null, table.mPrimaryKey + "=?", pks, null, null, null);
		} catch (Exception e) {
			
		}
		
		return c;
	}
	
	public void del(BaseTable table, String[] pks) {
		try {
			SQLiteDatabase mDatabase = getWritableDatabase();
			if(mDatabase != null)
				mDatabase.delete(table.mTableName, table.mPrimaryKey+"=?", pks);
		} catch (Exception e) {
			
		}
		
	}
	
	public void otherdel(BaseTable table, String[] pks) {
		try {
			SQLiteDatabase mDatabase = getWritableDatabase();
			if(mDatabase != null)
				mDatabase.delete(table.mTableName, table.mName+"=?", pks);
		} catch (Exception e) {
			
		}
		
	}
	
	public void close() {
		if(mSqLiteDatabase != null) {
			mSqLiteDatabase.close();
		}
	}
	
}
