package com.example.dabai;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;

public class Enemy  {
	//���˵������ʾ
	public int type;
	//����
	public static final int TYPE_FISHY=1;
	//�з
	public static final int TYPE_ZYU=2;
	//����
	public static final int TYPE_HMA=3;
	//��
	public static final int TYPE_SHE=4;
	//����
	public static final int TYPE_FISHZ=5;
	//����
	public static final int TYPE_FISHL=6;
	//����ͼƬ��Դ
	public Bitmap bmpEnemy;
	//��������
	public int x, y;
	//����ÿ֡�Ŀ��
	public  int frameW,frameH;
	//���˵�ǰ��֡�±�
	private int frameIndex;
	//�л����ƶ��ٶ�
	private int speed;
	//�жϵ����Ƿ��Ѿ�����
	public boolean isDead;

	
	//���˵Ĺ��캯��
	public Enemy(Bitmap bmpEnemy,int enemyType,int x,int y){   
		this.bmpEnemy=bmpEnemy;
		frameW=bmpEnemy.getWidth()/10; 
		frameH=bmpEnemy.getHeight();
		this.type=enemyType;
		this.x=x;
		this.y=y;

		//���˵�����
		switch(type){
		//����
			case TYPE_FISHY:
				speed=5;
				break;
		//����
			case TYPE_ZYU:
				speed=18;
				break;
		//����
			case TYPE_HMA:
				speed=6;
				break;
		//��
			case TYPE_SHE:
				speed=15;
				break;
	  //����
			case TYPE_FISHZ:
				speed=6;
				break;
	 //����
			case TYPE_FISHL:
			speed=10;
			break;
		}
	}
	
		//���˵Ļ��ƺ���
		public void draw(Canvas canvas,Paint paint){
			canvas.save();
			canvas.clipRect(x,y,x+frameW,y+frameH);
			canvas.drawBitmap(bmpEnemy, x-frameIndex*frameW, y,paint);
			canvas.restore();
		}
		//���˵��߼�AI
		public void  logic(){
			frameIndex++;
			if(frameIndex>=10){
				frameIndex=0;
			}

			//��ͬ���˵�AI�߼�
			switch (type){
			case TYPE_FISHY:
				if(isDead==false){
					x-=speed;
					if(frameIndex<=5){
						y+=1;
					}else{
						y-=1;
					}
					if(x<-100){
						isDead=true;
					}			
				}
				break;
			case TYPE_ZYU:
				if(isDead==false){
				speed-=1;
				x-=speed;
				if(x<-100){
					isDead=true;
				   }
				}
			case TYPE_HMA:
				if(isDead==false){
					x-=speed;
					if(frameIndex<=5){
						y+=6;
					}else{
						y-=6;
					}
				}
			case TYPE_SHE:
				if(isDead==false){
					if(x>Player.x){
						x-=speed;
					}else{
						x+=speed;
					}
					if(y>Player.y){
						y-=speed;
					}else{
						y+=speed;
					}
				}
			case TYPE_FISHZ:
				if(isDead==false){
					x-=speed;
					if(frameIndex<=5){
						y-=2;
					}else{
						y+=2;
					}
			}
			case TYPE_FISHL:
				if(isDead==false){
					x+=speed;
					if(frameIndex<=5){
						y-=2;
						x-=2;
					}else{
						y+=2;
						x+=2;
					}
				}
	
				
			
				if(x<-100||x>MySurfaceView.screenW+100){
					isDead=true;
				}
				
			}
			
		}
		
		//�ж���ײ�����˺����ǵ��ӵ���
		public boolean isCollsionWith(Bullet bullet){
			int x2=bullet.bulletX;
			int y2=bullet.bulletY;
			int w2=bullet.bmpBullet.getWidth();
			int h2=bullet.bmpBullet.getHeight();
			if (x >= x2 && x >= x2 + w2) {
				return false;
			} else if (x <= x2 && x + frameW <= x2) {
				return false;
			} else if (y >= y2 && y >= y2 + h2/2) {
				return false;
			} else if (y <= y2 && y + frameH <= y2) {
				return false;
			}
			//������ײ����������
			isDead = true;
			
			return true;
		}
        
	
	
	

}
