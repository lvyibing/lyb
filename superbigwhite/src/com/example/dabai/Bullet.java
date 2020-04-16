package com.example.dabai;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Bullet {
	//声明子弹位图
	public Bitmap  bmpBullet;
	//子弹的坐标
	public int bulletX,bulletY;
//	//子弹的速度
//	private int speed;
	//主角的种类和常量
	public  int bulletType;
	//主角的子弹
	public static final int BULLET_PLAYER=-1;
	//敌人的子弹
	public static final int BULLET_ENEMY1=1;
	//判断子弹是否超出屏幕 优化处理
	public boolean isDead;

	
	//子弹的构造函数
	public Bullet(Bitmap bmpBullet,int bulletX,int bulletY,int bulletType){
		this.bmpBullet=bmpBullet;
		this.bulletX=bulletX;
		this.bulletY=bulletY;
		this.bulletType=bulletType;
/*		//不同子弹速度不一
		switch (bulletType){
		case BULLET_PLAYER:
			speed=10;
			break;
		case BULLET_ENEMY1:
			speed=8;
			break;
		}*/
	}
    //子弹的绘制
	public void draw(Canvas canvas ,Paint paint ){
		canvas.drawBitmap(bmpBullet,bulletX,bulletY,paint);
	}
	//子弹的逻辑
	public void logic(){
		//不同子弹类型逻辑不一
		//主角的子弹
		switch (bulletType){
		case BULLET_PLAYER:
			if(Player.bulletF==0){
			    bulletX+=25;
			    }else if(Player.bulletF==1){
			    	bulletX-=25;
			    }
			
			if(bulletX>MySurfaceView.screenW+50){
				isDead=true;
			}
		case BULLET_ENEMY1:
			bulletX-=15;
			if(bulletX<-50){
				isDead=true;
			}
		}
	}
	
}
