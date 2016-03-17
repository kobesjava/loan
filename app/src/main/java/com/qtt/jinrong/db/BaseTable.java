package com.qtt.jinrong.db;

import java.io.Serializable;

public class BaseTable implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String mTableName = "test";
	public String mPrimaryKey = "_ID";
	public String mName = "search_content";
	
	public static final int SAVE_STATE_UPDATE = 0;
	public static final int SAVE_STATE_INSERT = 1;
	public static final int SAVE_STATE_EXCEPT = 2;
	
	public BaseTable(String table) {
		this.mTableName = table;
	}	

}
