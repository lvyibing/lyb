package com.example.dabai;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Coin {
	//声明金币
	public static int coins;
	//金币的位图
	private Bitmap bmpCoin;
	//金币的坐标
	private int cx,cy;
	//金币的计数器
	public static int countCoin;
	//里程数
	public static int countL;
	//倒计时
	public static int timeJ,timeD;
	//胜利的标志位
	public boolean Win;


	
	//金币系统的构造器
	public Coin(Bitmap bmpCoins){
		this.bmpCoin=bmpCoins;
		cx=MySurfaceView.screenW-3*bmpCoin.getWidth();
		cy=0;
	}
	//金币的绘图函数
	public void draw(Canvas canvas,Paint paint){
		canvas.drawBitmap(bmpCoin, cx, cy, paint);
		//使用drawText绘制字体的小技巧
		Paint cpaint=new Paint();
		cpaint.setTextSize(35);
		cpaint.setColor(Color.GRAY);
		cpaint.setAntiAlias(true);
		cpaint.setAlpha(0x77);
		canvas.drawText(" "+coins, cx+bmpCoin.getWidth(), cy+bmpCoin.getHeight(), cpaint);

		canvas.drawText("倒计时"+timeD, MySurfaceView.screenW/2-bmpCoin.getWidth(),bmpCoin.getHeight(), cpaint);
	}
	
	//分数系统的逻辑
	public void logic(){
		countCoin++;
		coins=countCoin/100;
		countL++;
		if(countL%20==0){
			timeJ++;
		}
		timeD=120-timeJ;
		if(timeD==0){
		    Win=true;
		}
		
		
	}

}
