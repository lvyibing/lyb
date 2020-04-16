package com.example.dabai;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Coin {
	//�������
	public static int coins;
	//��ҵ�λͼ
	private Bitmap bmpCoin;
	//��ҵ�����
	private int cx,cy;
	//��ҵļ�����
	public static int countCoin;
	//�����
	public static int countL;
	//����ʱ
	public static int timeJ,timeD;
	//ʤ���ı�־λ
	public boolean Win;


	
	//���ϵͳ�Ĺ�����
	public Coin(Bitmap bmpCoins){
		this.bmpCoin=bmpCoins;
		cx=MySurfaceView.screenW-3*bmpCoin.getWidth();
		cy=0;
	}
	//��ҵĻ�ͼ����
	public void draw(Canvas canvas,Paint paint){
		canvas.drawBitmap(bmpCoin, cx, cy, paint);
		//ʹ��drawText���������С����
		Paint cpaint=new Paint();
		cpaint.setTextSize(35);
		cpaint.setColor(Color.GRAY);
		cpaint.setAntiAlias(true);
		cpaint.setAlpha(0x77);
		canvas.drawText(" "+coins, cx+bmpCoin.getWidth(), cy+bmpCoin.getHeight(), cpaint);

		canvas.drawText("����ʱ"+timeD, MySurfaceView.screenW/2-bmpCoin.getWidth(),bmpCoin.getHeight(), cpaint);
	}
	
	//����ϵͳ���߼�
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
