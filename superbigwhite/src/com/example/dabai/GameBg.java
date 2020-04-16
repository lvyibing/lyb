package com.example.dabai;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GameBg {
	//游戏背景的图片资源
	//为了循环播放，这里定义两个位图对象，
	//其资源引用的是同一张图片
	private Bitmap bmpBackGround1;
	private Bitmap bmpBackGround2; 
	//山水的图片
	private Bitmap bmpBackGround3;
	private Bitmap bmpBackGround4; 
	//游戏背景坐标
	public static int bg1x, bg1y, bg2x, bg2y;
	public static int bg3x,bg3y,bg4x,bg4y;
	//背景滚动速度
	private int speed = 6;
	private int speed1=1;

	public GameBg(Bitmap bmpBackGround,Bitmap bmpBackGround2) {
		this.bmpBackGround1 = bmpBackGround;
		this.bmpBackGround2 = bmpBackGround;
		this.bmpBackGround3=bmpBackGround2;
		this.bmpBackGround4=bmpBackGround2;
		
		//首先让第一张背景底部正好填满整个屏幕
		bg1x = 0;
		bg1y=MySurfaceView.screenH-bmpBackGround1.getHeight();
		bg2x=bg1x+bmpBackGround1.getWidth();
		bg2y = bg1y ;
		
		bg3x = 0;
		bg3y=0;
		bg4x=bg3x+bmpBackGround3.getWidth();
		bg4y = bg3y ;
		
	}
	//游戏背景的绘图函数
	public void draw(Canvas canvas, Paint paint) {
		//绘制两张背景
		canvas.drawBitmap(bmpBackGround3, bg3x, bg3y, paint);
		canvas.drawBitmap(bmpBackGround4, bg4x, bg4y, paint);
		canvas.drawBitmap(bmpBackGround1, bg1x, bg1y, paint);
		canvas.drawBitmap(bmpBackGround2, bg2x, bg2y, paint);

		
	}

	//游戏背景的逻辑函数
	public void logic() {
		bg1x-= speed;
		bg2x-= speed;
		
		bg3x-= speed1;
		bg4x-= speed1;
		//当第一张图片的Y坐标超出屏幕，
		//立即将其坐标设置到第二张图的上方
		if(bg1x<-bmpBackGround1.getWidth()){
			bg1x=bg2x+bmpBackGround1.getWidth();
    	}
    	if(bg2x<-bmpBackGround1.getWidth()){
    		bg2x=bg1x+bmpBackGround1.getWidth();
		}
    	
    	if(bg3x<-bmpBackGround3.getWidth()){
			bg3x=bg4x+bmpBackGround3.getWidth();
    	}
    	if(bg4x<-bmpBackGround3.getWidth()){
    		bg4x=bg3x+bmpBackGround3.getWidth();
		}
	}
}
