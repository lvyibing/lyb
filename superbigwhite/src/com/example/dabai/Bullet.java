package com.example.dabai;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Bullet {
	//�����ӵ�λͼ
	public Bitmap  bmpBullet;
	//�ӵ�������
	public int bulletX,bulletY;
//	//�ӵ����ٶ�
//	private int speed;
	//���ǵ�����ͳ���
	public  int bulletType;
	//���ǵ��ӵ�
	public static final int BULLET_PLAYER=-1;
	//���˵��ӵ�
	public static final int BULLET_ENEMY1=1;
	//�ж��ӵ��Ƿ񳬳���Ļ �Ż�����
	public boolean isDead;

	
	//�ӵ��Ĺ��캯��
	public Bullet(Bitmap bmpBullet,int bulletX,int bulletY,int bulletType){
		this.bmpBullet=bmpBullet;
		this.bulletX=bulletX;
		this.bulletY=bulletY;
		this.bulletType=bulletType;
/*		//��ͬ�ӵ��ٶȲ�һ
		switch (bulletType){
		case BULLET_PLAYER:
			speed=10;
			break;
		case BULLET_ENEMY1:
			speed=8;
			break;
		}*/
	}
    //�ӵ��Ļ���
	public void draw(Canvas canvas ,Paint paint ){
		canvas.drawBitmap(bmpBullet,bulletX,bulletY,paint);
	}
	//�ӵ����߼�
	public void logic(){
		//��ͬ�ӵ������߼���һ
		//���ǵ��ӵ�
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
