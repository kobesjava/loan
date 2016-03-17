package com.qtt.jinrong.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SearchDB extends BaseTable {

    /**
     * ��ʷ��¼��
     */
    public static final int RECORD_COUNT = 10;

    private static final long serialVersionUID = -8888174717749630072L;

    public static final String TABLE_NAME = "tb_search";

    public static final String SEARCH_ID = "search_id";
    public static final String SEARCH_CONTENT = "search_content";
    public static final String SEARCH_DATE = "search_date";
    public static final String USER_ID = "user_id";

    private String mSearchId;
    private String mSearchContent;
    //����������¼��ʱ�䣨Ԥ���ֶΣ�����չ���ܣ�
    private String mSearchDate;
    //�û�ID
    private String user_id;

    public SearchDB() {
        super(TABLE_NAME);
    }

    public SearchDB(String table) {
        super(TABLE_NAME);
    }


    public SearchDB(String table, String mSearchContent, String mSearchDate) {
        super(table);
        this.mSearchContent = mSearchContent;
        this.mSearchDate = mSearchDate;
    }

    public int save(Context ctx,int count,String deleteId) {
        int retId = SAVE_STATE_INSERT;
        DatabaseUtil dbHelper = null;
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            dbHelper = DatabaseUtil.get(ctx);
            db = dbHelper.getWritableDatabase();
            if(count>=RECORD_COUNT){
                db.delete(TABLE_NAME,SEARCH_ID + "=? and "+USER_ID + " =?", new String[]{mSearchId,user_id});
            }
            cursor = db.query(TABLE_NAME, new String[] { SEARCH_CONTENT }, SEARCH_CONTENT
                    + "=? and "+ USER_ID + "=?", new String[] { mSearchContent ,user_id}, null, null, null);
            if(cursor!=null&&cursor.moveToNext()){
                // ��ݿ���ھ�ɾ��
                dbHelper.otherdel(this, new String[]{mSearchContent});
            }
            // ����
            ContentValues values = new ContentValues();
            values.put(SEARCH_CONTENT, mSearchContent);
            values.put(SEARCH_DATE, mSearchDate);
            values.put(USER_ID, user_id);
            dbHelper.insert(TABLE_NAME, values);
            retId = SAVE_STATE_INSERT;
            return retId;
        } catch (Exception e) {
            e.printStackTrace();
            retId = SAVE_STATE_EXCEPT;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
            if (dbHelper != null) {
                dbHelper.close();
                dbHelper = null;
            }
        }
        return retId;
    }



    public static SearchDB deleteByPrimaryKey(Context ctx,String user_id, String search_id) {
        DatabaseUtil dbHelper = null;
        Cursor cursor = null;
        SQLiteDatabase db = null;
        SearchDB cd = null;
        try {
            dbHelper = DatabaseUtil.get(ctx);
            db = dbHelper.getWritableDatabase();
            db.delete(TABLE_NAME, SEARCH_ID + "=? and "+USER_ID + " =?",
                    new String[] {search_id,user_id});
        } catch (Exception e) {
            e.printStackTrace();
            cd = null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
            if (dbHelper != null) {
                dbHelper.close();
                dbHelper = null;
            }
        }
        return cd;
    }

    public static boolean clearAll(Context ctx,String user_id) {
        boolean retId = true;
        DatabaseUtil dbHelper = null;
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            dbHelper = DatabaseUtil.get(ctx);
            db = dbHelper.getWritableDatabase();
            db.delete(TABLE_NAME, USER_ID + " =?",
                    new String[] {user_id});
            retId = true;
        } catch (Exception e) {
            e.printStackTrace();
            retId = false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
            if (dbHelper != null) {
                dbHelper.close();
                dbHelper = null;
            }
        }
        return retId;
    }

    public static void getAll(Context ctx,String user_id,ArrayList<SearchDB> list){
        DatabaseUtil dbHelper = null;
        Cursor cursor = null;
        SQLiteDatabase db = null;
        SearchDB cd = null;
        try {
            dbHelper = DatabaseUtil.get(ctx);
            db = dbHelper.getWritableDatabase();

            cursor = db.query(TABLE_NAME, new String[] {
                            SEARCH_CONTENT, SEARCH_DATE ,SEARCH_ID,USER_ID},
                    USER_ID + " =?",
                    new String[] {user_id}, null,
                    null, null);

            if(cursor != null){
                while (cursor.moveToNext()) {
                    cd = new SearchDB();
                    cd.setSearch_content(cursor.getString(0));
                    cd.setSearch_date(cursor.getString(1));
                    cd.setSearch_id(cursor.getString(2));
                    list.add(0,cd);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            cd = null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
            if (dbHelper != null) {
                dbHelper.close();
                dbHelper = null;
            }
        }
    }

    public String getSearch_id() {
        return mSearchId;
    }

    public void setSearch_id(String mSearchId) {
        this.mSearchId = mSearchId;
    }

    public String getSearch_content() {
        return mSearchContent;
    }

    public void setSearch_content(String mSearchContent) {
        this.mSearchContent = mSearchContent;
    }

    public String getSearch_date() {
        return mSearchDate;
    }

    public void setSearch_date(String mSearchDate) {
        this.mSearchDate = mSearchDate;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }



}
