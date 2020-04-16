package com.dabaichat.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MydatabaseHelper extends SQLiteOpenHelper{
	
	//实现创建和修改数据库
	
	private static final String DATABASENAME="dabai.db"; 
	private static final String TABLENAME="yixueziliao"; 
	private static final int DATABASVERSION=2; 
	
	
	
	public MydatabaseHelper(Context context) {
		super(context, DATABASENAME, null, DATABASVERSION);
	}



	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql="CREATE TABLE " + TABLENAME + " (" + 
				"id			INTEGER 		PRIMARY KEY ," + 
				"name		VARCHAR(50)		NOT NULL ," + 
				"zl      	VARCHAR(50)		NOT NULL)";
		db.execSQL(sql);
	}



	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		String sql = "DROP TABLE IF EXISTS " + TABLENAME ;
		db.execSQL(sql);
		this.onCreate(db);
		
	}

}
