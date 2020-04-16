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
	
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
		this.setContentView(R.layout.changtime);//设置布局资源
		View iv=(View)this.findViewById(R.id.photo);
		
		//获取welcome.xml中id为wpic的ImageView组件
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
		welcome();//欢迎界面
		
		
		
	}
 
	
	
	/**
	 * 欢迎界面,2秒钟后切换
	 * @param 
	 * @return
	 */
	public void welcome() {
		new Thread(new Runnable() {//创建线程
			public void run() {//实现Runnable的run方法，即线程体
				try {
					Thread.sleep(3000);//欢迎界面暂停2秒钟
					Message m = new Message();//创建Message对象
			    	logHandler.sendMessage(m);//将消息放到消息队列中
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();//启动线程
	}
	Handler logHandler = new Handler() {
		public void handleMessage(Message msg) {
			welcome1();//显示logo界面
		}
	};
	public void welcome1() {		
		Intent it=new Intent();//实例化Intent
		it.setClass(ChangeTimeAcitivity.this, MainActivity.class);//设置Class
    	startActivity(it);//启动Activity
    	ChangeTimeAcitivity.this.finish();//结束Welcome Activity
	}
	

	
}