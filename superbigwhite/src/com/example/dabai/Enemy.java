package com.example.dabai;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;

public class Enemy  {
	//敌人的种类标示
	public int type;
	//黄鱼
	public static final int TYPE_FISHY=1;
	//螃蟹
	public static final int TYPE_ZYU=2;
	//海马
	public static final int TYPE_HMA=3;
	//蛇
	public static final int TYPE_SHE=4;
	//紫鱼
	public static final int TYPE_FISHZ=5;
	//绿鱼
	public static final int TYPE_FISHL=6;
	//敌人图片资源
	public Bitmap bmpEnemy;
	//敌人坐标
	public int x, y;
	//敌人每帧的宽高
	public  int frameW,frameH;
	//敌人当前的帧下标
	private int frameIndex;
	//敌机的移动速度
	private int speed;
	//判断敌人是否已经出屏
	public boolean isDead;

	
	//敌人的构造函数
	public Enemy(Bitmap bmpEnemy,int enemyType,int x,int y){   
		this.bmpEnemy=bmpEnemy;
		frameW=bmpEnemy.getWidth()/10; 
		frameH=bmpEnemy.getHeight();
		this.type=enemyType;
		this.x=x;
		this.y=y;

		//敌人的种类
		switch(type){
		//黄鱼
			case TYPE_FISHY:
				speed=5;
				break;
		//章鱼
			case TYPE_ZYU:
				speed=18;
				break;
		//海马
			case TYPE_HMA:
				speed=6;
				break;
		//蛇
			case TYPE_SHE:
				speed=15;
				break;
	  //紫鱼
			case TYPE_FISHZ:
				speed=6;
				break;
	 //绿鱼
			case TYPE_FISHL:
			speed=10;
			break;
		}
	}
	
		//敌人的绘制函数
		public void draw(Canvas canvas,Paint paint){
			canvas.save();
			canvas.clipRect(x,y,x+frameW,y+frameH);
			canvas.drawBitmap(bmpEnemy, x-frameIndex*frameW, y,paint);
			canvas.restore();
		}
		//敌人的逻辑AI
		public void  logic(){
			frameIndex++;
			if(frameIndex>=10){
				frameIndex=0;
			}

			//不同敌人的AI逻辑
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
		
		//判断碰撞（敌人和主角的子弹）
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
			//发生碰撞，让其死亡
			isDead = true;
			
			return true;
		}
        
	
	
	

}
