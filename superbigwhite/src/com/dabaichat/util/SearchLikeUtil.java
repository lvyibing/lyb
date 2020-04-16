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
	/*ģ����ѯ*/
		//��䲻ͬ��like
		String result="";
		String[] columns=new String[]{"id","name","zl"};
		String selection="name LIKE ? OR zl LIKE ?";
		//��ѯ���Ƿ��������ġ�����
		String [] selectionArgs=new String[]{"%"+str+"%","%"+str+"%"};
		Cursor cursor=db.query(TABLENAME, columns, selection, selectionArgs, null, null, null);
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			result=cursor.getString(2);
		}
		if(result.equals("")){
			return "sorry,������";
		}
		db.close();
		return result;
	}
	
	
	
	
	/*public List<String> searchStudent(){
		List<String> list=new ArrayList<String>();
		ģ����ѯ
		//��䲻ͬ��like
		String[] columns=new String[]{"id","name","zl"};
		String selection="name LIKE ? OR zl LIKE ?";
		//��ѯ���Ƿ��������ġ�����
		String [] selectionArgs=new String[]{"%"+"��ð"+"%","%"+"��ҩ"+"%"};
		Cursor cursor=db.query(TABLENAME, columns, selection, selectionArgs, null, null, null);
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			list.add("��"+cursor.getString(0)+"��"+"�ؼ��֣�"
		+cursor.getString(1)+",���ݣ�"+cursor.getString(2));
		}
		db.close();
		return list;
	}*/

}
