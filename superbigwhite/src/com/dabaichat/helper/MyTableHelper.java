package com.dabaichat.helper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class MyTableHelper {
	private SQLiteDatabase db=null;
	private static final String TABLENAME="yixueziliao"; 

	public MyTableHelper(SQLiteDatabase db) {
		this.db = db;
	}
	public void insert(String name,String zl){
		ContentValues values=new ContentValues();
		values.put("name", name);
		values.put("zl", zl);
		db.insert(TABLENAME, null, values);
		db.close();
	}
	public void delete(int id){
		String whereClause="id=?";
		String[] whereArgs=new String[]{String.valueOf(id)};
		db.delete(TABLENAME, whereClause, whereArgs);
		db.close();
	}
}
