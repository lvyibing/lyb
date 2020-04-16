package com.example.dabai;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GameBg {
	//��Ϸ������ͼƬ��Դ
	//Ϊ��ѭ�����ţ����ﶨ������λͼ����
	//����Դ���õ���ͬһ��ͼƬ
	private Bitmap bmpBackGround1;
	private Bitmap bmpBackGround2; 
	//ɽˮ��ͼƬ
	private Bitmap bmpBackGround3;
	private Bitmap bmpBackGround4; 
	//��Ϸ��������
	public static int bg1x, bg1y, bg2x, bg2y;
	public static int bg3x,bg3y,bg4x,bg4y;
	//���������ٶ�
	private int speed = 6;
	private int speed1=1;

	public GameBg(Bitmap bmpBackGround,Bitmap bmpBackGround2) {
		this.bmpBackGround1 = bmpBackGround;
		this.bmpBackGround2 = bmpBackGround;
		this.bmpBackGround3=bmpBackGround2;
		this.bmpBackGround4=bmpBackGround2;
		
		//�����õ�һ�ű����ײ���������������Ļ
		bg1x = 0;
		bg1y=MySurfaceView.screenH-bmpBackGround1.getHeight();
		bg2x=bg1x+bmpBackGround1.getWidth();
		bg2y = bg1y ;
		
		bg3x = 0;
		bg3y=0;
		bg4x=bg3x+bmpBackGround3.getWidth();
		bg4y = bg3y ;
		
	}
	//��Ϸ�����Ļ�ͼ����
	public void draw(Canvas canvas, Paint paint) {
		//�������ű���
		canvas.drawBitmap(bmpBackGround3, bg3x, bg3y, paint);
		canvas.drawBitmap(bmpBackGround4, bg4x, bg4y, paint);
		canvas.drawBitmap(bmpBackGround1, bg1x, bg1y, paint);
		canvas.drawBitmap(bmpBackGround2, bg2x, bg2y, paint);

		
	}

	//��Ϸ�������߼�����
	public void logic() {
		bg1x-= speed;
		bg2x-= speed;
		
		bg3x-= speed1;
		bg4x-= speed1;
		//����һ��ͼƬ��Y���곬����Ļ��
		//���������������õ��ڶ���ͼ���Ϸ�
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
