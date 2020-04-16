package com.example.dabai;


import android.app.Service;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class Player  {
	
	public int fishHp=3;
	private Bitmap bmpHp;
	public static int x,y;
	private Bitmap fish1;
	private Bitmap paopao;
	private int speed=10;
	public  boolean isUP,isDown,isLeft,isRight;
	private int currentFrame1;
		private SensorManager sm;
		private Sensor sensor;
		private SensorEventListener mySensorListener;
		private float ax=0,ay=0,az=0;
		private boolean jump;
		private int jumpC;
		private int jumpV=30;
		private int noCollsionCount=0;
		private int noCollsionTime =60;
		public boolean isCollsion;
		public boolean isCoin;
		private int costJump=100;
		public boolean isCost=true;
		public static int  bulletF=0;

		
	
	public Player(Bitmap fish1,Bitmap bmpHp,Bitmap paopao){
		this.fish1=fish1;
		this.bmpHp=bmpHp;
		this.paopao=paopao;
		x=MySurfaceView.screenW/2-fish1.getWidth()/10;
		y=MySurfaceView.screenH/5*4-fish1.getHeight();
		sm=(SensorManager)GameMainActivity.instance.getSystemService(Service.SENSOR_SERVICE);
		
		sensor=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		mySensorListener=new SensorEventListener(){
			@Override
			public void onSensorChanged(SensorEvent event){
				ax=event.values[0];
				ay=event.values[1];
				az=event.values[2];
			}
			@Override
			public void onAccuracyChanged(Sensor sensor,int accuracy){}
		};
		sm.registerListener(mySensorListener, sensor,SensorManager.SENSOR_DELAY_GAME);
	}

	public void draw(Canvas canvas,Paint paint){
		canvas.save();
		canvas.clipRect(x, y, x+fish1.getWidth()/10, y+fish1.getHeight());
		if(isRight){
			canvas.scale(-1, 1, x+fish1.getWidth()/20, y+fish1.getHeight()/2);	
		}
		if(isUP){
			canvas.rotate(15,x+fish1.getWidth()/20, y+fish1.getHeight()/2);
		}
		if(isDown){
			canvas.rotate(-15,x+fish1.getWidth()/20, y+fish1.getHeight()/2);
		}
		if(isCollsion){
		 
			if(noCollsionCount%2==0){
			       	canvas.drawBitmap(fish1, x-currentFrame1*fish1.getWidth()/10, y, paint);
		            canvas.drawBitmap(paopao, x-currentFrame1*fish1.getWidth()/10, y, paint);
			}
		}else{    
		    canvas.drawBitmap(fish1, x-currentFrame1*fish1.getWidth()/10, y, paint);
		}	
	    canvas.restore();
	
	
		
		for(int i=0;i<fishHp;i++){
			canvas.drawBitmap(bmpHp, i*bmpHp.getWidth(), 0, paint);
		}
		
	}

	public void onKeyDown(int keyCode,KeyEvent event){
		if(keyCode==KeyEvent.KEYCODE_DPAD_UP){
			isUP=true;
		}
		if(keyCode==KeyEvent.KEYCODE_DPAD_DOWN){
			isDown=true;
		}
		if(keyCode==KeyEvent.KEYCODE_DPAD_LEFT){
			isLeft=true;
		}
		if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
			isRight=true;
		}
	}

	public void onKeyUp(int keyCode,KeyEvent event){
		if(keyCode==KeyEvent.KEYCODE_DPAD_UP){
			isUP=false;
		}
		if(keyCode==KeyEvent.KEYCODE_DPAD_DOWN){
			isDown=false;
		}
		if(keyCode==KeyEvent.KEYCODE_DPAD_LEFT){
			isLeft=false;
		}
		if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
			isRight=false;
		}
	}

	public void onTouchEvent(MotionEvent event){
		  if(y<GameBg.bg1y+40&&event.getAction() == MotionEvent.ACTION_DOWN ){
			  if(isCoin){
				   isCost=false;
				   jump=true;		
			  }	
	    	}
	}
	
	public void logic(){
	
		x-=1;
		
		if(Coin.countCoin>=costJump){
			isCoin=true;
		}else{
			isCoin=false;
		}
		if(isCost==false){
			Coin.countCoin-=costJump;
			isCost=true;
		}
		currentFrame1++;
		if(currentFrame1>=9){
			currentFrame1=0;
		}
		if(currentFrame1>=5){
			y+=1;
		}else{
			y-=1;
		}
        if(ax>0.3){
        	isDown=true;
        	isUP=false;
        }
        if(ax<-0.3){
        	isUP=true;
        	isDown=false;
        }
        if(ay<0.3&ay>-0.3){
        	isRight=false;
        	isLeft=false;
        }
        if(ax<0.3&ax>0.3){
        	isUP=false;
        	isDown=false;
        }
        if(ay>0.3){
        	isRight=true;
        	isLeft=false;
        	bulletF=0;
        }
        if(ay<-0.3){
        	isLeft=true;
        	isRight=false;
        	bulletF=1;
        }
		if(isLeft&&x>0){
			x-=speed+1;
		}
		if(isRight&&x<MySurfaceView.screenW-fish1.getWidth()/10){
			x+=speed-1;
		}
		if(isUP&&y>GameBg.bg1y-15){
			y-=speed;
		}
		if(isUP&&y<GameBg.bg1y){
			y+=speed;
		}
		if(isDown&&y<MySurfaceView.screenH-fish1.getHeight()+40){
			y+=speed;
		}
		jumpC++;
		if(jump&&jumpC<25){
			jumpV-=2;
			y-=jumpV;
		}else{
			jumpV=30;
			jumpC=0;
			jump=false;
		}
		if(x<0){
			x=0;
		}
	
		if(x>MySurfaceView.screenW){
			x=MySurfaceView.screenW-fish1.getWidth()/10;
		}
		if(y>MySurfaceView.screenH-fish1.getHeight()){
			y=MySurfaceView.screenH-fish1.getHeight();
		}
		if(isCollsion){
			noCollsionCount++;
			if(noCollsionCount>=noCollsionTime){
				isCollsion=false;
				noCollsionCount=0;
			}
		}

	}
	public void setFishHp(int hp){
		this.fishHp=hp;
	}
	public int getFishHp(){
		return fishHp;
	}

	public boolean isCollsionWith(Enemy en) {
		if (isCollsion == false) {
			int cx1 = x + fish1.getWidth() / 20;
			int cy1 = y + fish1.getHeight() / 2;
			int cx2 = en.x + en.frameW / 2;
			int cy2 = en.y + en.frameH / 2;
			int r1 = fish1.getWidth() / 40;
			int r2 = en.frameW / 4;
			if (Math.sqrt(Math.pow(cx1 - cx2, 2) + Math.pow(cy1 - cy2, 2)) <= r1
					+ r2) {
				isCollsion = true;
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

}
