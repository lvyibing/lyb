package com.example.dabai;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Boom {
	//爆炸效果的资源图
	private Bitmap bmpBoom;
	//爆炸效果的位置
	private int boomX,boomY;
	//爆炸动画的当前帧
	private int currentFrameIndex;
	//爆炸的总帧数
	private int totleFrame;
	//每帧的宽高
	private int frameW,frameH;
	//播放的标志
	public boolean playEnd=false;
	
	//爆炸效果的构造函数
	public Boom(Bitmap bmpBoom,int x,int y,int totleFrame){
		this.bmpBoom=bmpBoom;
		this.boomX=x;
		this.boomY=y;
		this.totleFrame=totleFrame;
		frameW=bmpBoom.getWidth()/totleFrame;
		frameH=bmpBoom.getHeight();
	}
	
	//爆炸效果的绘图函数
	public void draw(Canvas canvas ,Paint paint ){
		canvas.save();
		canvas.clipRect(boomX,boomY,boomX+frameW,boomY+frameH);
		canvas.drawBitmap(bmpBoom, boomX-currentFrameIndex*frameW,boomY, paint);
		canvas.restore();
	}
	//爆炸效果的逻辑
	public void logic(){
		if(currentFrameIndex<totleFrame){
			currentFrameIndex++;
		}else{
			playEnd=true;
		}
	}

}
