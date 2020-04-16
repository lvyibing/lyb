package com.dabai.welcome;


import java.util.Calendar;

import com.dabai.database.R;
import com.dabai.find.aboutActivity;
import com.dabaichat.database.MainActivity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;



public class ChangeTimeAcitivity extends Activity{
	

  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		requestWindowFeature(Window.FEATURE_NO_TITLE);//���ر�����
		this.setContentView(R.layout.changtime);//���ò�����Դ
		View iv=(View)this.findViewById(R.id.photo);
		
		//��ȡwelcome.xml��idΪwpic��ImageView���
     	Calendar s=Calendar.getInstance();
     	String a=String.valueOf(s.get(Calendar.DAY_OF_WEEK));
     	//--------------------------------------------------------------------
     	if("1".equals(a))
     	{
     		a="7";
     		
     		iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.q7));
     	}else if("2".equals(a))
     	{
     		a="1";
     		
     		iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.q1));
     	}
     	else if("3".equals(a))
     	{
     		a="2";
     	
     		iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.q2));
     	}
     	else if("4".equals(a))
     	{
    		a="3";
    		
     		iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.q3));
     	}
     
     	else if("5".equals(a))
     	{
     		a="4";
     		
     		iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.q4));
     	}
     	else if("6".equals(a))
     	{
     		a="5";
     		
     		iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.q5));
     	}
    	else if("7".equals(a))
     	{
     		a="6";
     	
     		iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.q6));
     	}
     	//-----------------------------------------------------------------------
		welcome();//��ӭ����
		
		
		
	}
 
	
	
	/**
	 * ��ӭ����,2���Ӻ��л�
	 * @param 
	 * @return
	 */
	public void welcome() {
		new Thread(new Runnable() {//�����߳�
			public void run() {//ʵ��Runnable��run���������߳���
				try {
					Thread.sleep(3000);//��ӭ������ͣ2����
					Message m = new Message();//����Message����
			    	logHandler.sendMessage(m);//����Ϣ�ŵ���Ϣ������
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();//�����߳�
	}
	Handler logHandler = new Handler() {
		public void handleMessage(Message msg) {
			welcome1();//��ʾlogo����
		}
	};
	public void welcome1() {		
		Intent it=new Intent();//ʵ����Intent
		it.setClass(ChangeTimeAcitivity.this, MainActivity.class);//����Class
    	startActivity(it);//����Activity
    	ChangeTimeAcitivity.this.finish();//����Welcome Activity
	}
	

	
}