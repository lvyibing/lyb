package com.example.dabai;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class GameLost {
    //失败的背景
	private Bitmap bmpGameLost;
	//按钮图片资源(按下和未按下图)
	private Bitmap LbtnS, LbtnP;
	//按钮的坐标
	private int LbtnX, LbtnY;
	//按钮是否按下标识位
	private Boolean isPress;
	//菜单初始化
	public GameLost(Bitmap bmpGameLost, Bitmap LbtnS, Bitmap LbtnP) {
		this.bmpGameLost = bmpGameLost;
		this.LbtnS=LbtnS;
		this.LbtnP=LbtnP;
		//X居中，Y紧接屏幕底部
		LbtnX = MySurfaceView.screenW / 2 - LbtnS.getWidth() / 2;
		LbtnY = MySurfaceView.screenH -LbtnS.getHeight();
		isPress = false;
	}
	//菜单绘图函数
	public void draw(Canvas canvas, Paint paint) {
		//绘制菜单背景图
		canvas.drawBitmap(bmpGameLost, (MySurfaceView.screenW-bmpGameLost.getWidth())/2, 0, paint);
		//绘制未按下按钮图
		if (isPress) {//根据是否按下绘制不同状态的按钮图
			canvas.drawBitmap(LbtnP, LbtnX, LbtnY, paint);
		} else {
			canvas.drawBitmap(LbtnS, LbtnX, LbtnY, paint);
		}
	}
	//菜单触屏事件函数，主要用于处理按钮事件
	public void onTouchEvent(MotionEvent event) {
		//获取用户当前触屏位置
		int pointX = (int) event.getX();
		int pointY = (int) event.getY();
		//当用户是按下动作或移动动作
		if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
			//判定用户是否点击了按钮
			if (pointX >LbtnX && pointX < LbtnX + LbtnS.getWidth()) {
				if (pointY > LbtnY && pointY < LbtnY + LbtnS.getHeight()) {
					isPress = true;
				} else {
					isPress = false;
				}
			} else {
				isPress = false;
			}
			//当用户是抬起动作
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			//抬起判断是否点击按钮，防止用户移动到别处
			if (pointX > LbtnX && pointX < LbtnX + LbtnS.getWidth()) {
				if (pointY > LbtnY && pointY < LbtnY +LbtnS.getHeight()) {
					//还原Button状态为未按下状态
					isPress = false;
					//改变当前游戏状态为开始游戏
			       MySurfaceView.gameState = MySurfaceView.GAME_MENU;
				}
			}
		}
	}
}