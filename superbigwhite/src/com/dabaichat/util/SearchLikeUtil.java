package com.dabaichat.util;



import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SearchLikeUtil {
	private SQLiteDatabase db=null;
	private static final String TABLENAME="yixueziliao";
	public SearchLikeUtil(SQLiteDatabase db) {
		this.db = db;
	} 
	public String searchStudent(String str){	
	/*模糊查询*/
		//语句不同于like
		String result="";
		String[] columns=new String[]{"id","name","zl"};
		String selection="name LIKE ? OR zl LIKE ?";
		//查询的是符合条件的。。。
		String [] selectionArgs=new String[]{"%"+str+"%","%"+str+"%"};
		Cursor cursor=db.query(TABLENAME, columns, selection, selectionArgs, null, null, null);
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			result=cursor.getString(2);
		}
		if(result.equals("")){
			return "sorry,请联网";
		}
		db.close();
		return result;
	}
	
	
	
	
	/*public List<String> searchStudent(){
		List<String> list=new ArrayList<String>();
		模糊查询
		//语句不同于like
		String[] columns=new String[]{"id","name","zl"};
		String selection="name LIKE ? OR zl LIKE ?";
		//查询的是符合条件的。。。
		String [] selectionArgs=new String[]{"%"+"感冒"+"%","%"+"吃药"+"%"};
		Cursor cursor=db.query(TABLENAME, columns, selection, selectionArgs, null, null, null);
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			list.add("【"+cursor.getString(0)+"】"+"关键字："
		+cursor.getString(1)+",内容："+cursor.getString(2));
		}
		db.close();
		return list;
	}*/

}
