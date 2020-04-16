package com.example.dabai;


import java.util.Random;

import java.util.Vector;

import com.dabai.database.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements Callback,Runnable{
	private SurfaceHolder sfh;
	private Paint paint;
	private Thread th;
	private boolean flag;
	private Canvas canvas;
	public static int screenW,screenH;
	//定义游戏状态常量
	public  static final int GAME_MENU=0;
    public  static final int GAMEING=1;
	public  static final int GAME_WIN=2;
	public  static final int GAME_LOST=3;
	public  static final int GAME_PAUSE=-1;
	//当前的游戏状态
	public static int gameState=GAME_MENU;
	private Resources res =this.getResources();
	private Bitmap menubg;//游戏菜单背景
	private Bitmap bmpGameWin;//胜利背景
	private Bitmap bmpGameLost;//游戏失败背景
	private Bitmap bmpCoin;//金币的图片
	private Bitmap btnS;//游戏开始
	private Bitmap btnP;//游戏开始被按下
	private Bitmap LbtnS;
	private Bitmap LbtnP;
	private Bitmap fish1;//声明主角鱼图
	private Bitmap paopao;//声明泡泡
	private Bitmap bmpHp;//声明主角血量
	private Bitmap bmpEnemyfish;//黄鱼
	private Bitmap bmpEnemyzyu;//章魚
	private Bitmap bmpEnemyhma;//海馬
	private Bitmap bmpEnemyshe;//蛇
	private Bitmap bmpEnemyfishz;//紫鱼
	private Bitmap bmpEnemyfishl;//绿鱼

	//音乐
	public SoundPool sp;
	public int music;
	
	//声明游戏背景
	private Bitmap bmpBG;
	private Bitmap bmpBG1;
	//声明主角对象
	private Player player;
	//声明一个菜单对象
	private GameMenu gameMenu;
	//声明游戏进行状态的背景
	private GameBg backGround;
	//声明游戏失败界面
	private GameLost gameLost;
	//声明游戏金币系统
	private Coin coin;
	//声明一个敌人的容器
	private Vector<Enemy>vcEnemy;
	//声明生成敌人的时间
	private int createEnemyTime=50;
	private int count;
	//敌人的数组
	private int enemyArray[][]={
			{5,5},{2,5,1,3},{3,2,5,1},{1,6,1,4},{4},
			{1,4,2,3},{1,6,3,1},{3,3},{6,4},{5},
			{3,2,3,1},{2,1},{3,6,3},{1},{2,1,1},{3},{2,3},{1},{2},
			{1,6,1,1},{1},{2,1,1},{3},{2,3},{1},{2},
			{1,},{1,1},{3,3},
			{1,1,2,3},{1,1},{2,2}
	};
	//当前取出一维的下标
	private int enemyArrayIndex;
	//随机库，为创建敌人赋予随机坐标
	private Random random;
	//敌人的子弹容器
	private Vector<Bullet> vcBullet=new Vector<Bullet>();
	//添加子弹的计数器
	private int countEnemyBullet;
	//主角的子弹容器
	private Vector<Bullet> vcBulletPlayer =new Vector<Bullet>(); 
	//子弹的计数器
	private int countPlayerBullet;
	//主角的子弹
	private Bitmap playerBullet;
	//敌人的子弹
	private Bitmap enemyBullet;
	//爆炸效果的容器
	private Vector<Boom> vcBoom=new Vector<Boom>();
	//声明爆炸效果的位图
	private Bitmap bmpBoom;
	
	
	
		//传感器的x,y值
		public static float ax=0,ay=0,az=0;
	
	
	
	public MySurfaceView(Context context){
		super(context);
		sfh=this.getHolder();
		sfh.addCallback(this);
		paint=new Paint(); 
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		setFocusable(true);
		setFocusableInTouchMode(true);
		//设置背景常亮
		this.setKeepScreenOn(true);
		sp=new SoundPool(10,AudioManager.STREAM_SYSTEM,5);
		music=sp.load(context, R.raw.shot, 1);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		screenW=this.getWidth();
		screenH=this.getHeight();
		initGame();
		flag=true;
		th=new Thread(this);
		th.start();
		
	}
	/*
	 * 自定义游戏初始化函数
	 */
	private void initGame(){
		if(gameState==GAME_MENU){
			menubg=BitmapFactory.decodeResource(res, R.drawable.bg1);
			btnP=BitmapFactory.decodeResource(res, R.drawable.button_press);
			btnS=BitmapFactory.decodeResource(res, R.drawable.button);
			LbtnP=BitmapFactory.decodeResource(res, R.drawable.lbutton_press);
			LbtnS=BitmapFactory.decodeResource(res, R.drawable.lbutton);
			bmpBG=BitmapFactory.decodeResource(res, R.drawable.water);
			bmpBG1=BitmapFactory.decodeResource(res, R.drawable.zhi);
			fish1=BitmapFactory.decodeResource(res, R.drawable.fish1);
			bmpEnemyfish=BitmapFactory.decodeResource(res, R.drawable.fishy);
			bmpEnemyzyu=BitmapFactory.decodeResource(res, R.drawable.zyu);
			bmpEnemyhma=BitmapFactory.decodeResource(res, R.drawable.hma);
			bmpHp=BitmapFactory.decodeResource(res,R.drawable.hp);
			bmpGameLost=BitmapFactory.decodeResource(res,R.drawable.lost);
			bmpCoin=BitmapFactory.decodeResource(res,R.drawable.coin);
			paopao=BitmapFactory.decodeResource(res,R.drawable.pop);
			playerBullet=BitmapFactory.decodeResource(res,R.drawable.bullet);
			enemyBullet=BitmapFactory.decodeResource(res,R.drawable.bullet1);
			bmpEnemyshe=BitmapFactory.decodeResource(res,R.drawable.she);
			bmpEnemyfishz=BitmapFactory.decodeResource(res,R.drawable.fishz);
			bmpEnemyfishl=BitmapFactory.decodeResource(res,R.drawable.fishl);
			bmpBoom=BitmapFactory.decodeResource(res,R.drawable.boom);
			bmpGameWin=BitmapFactory.decodeResource(res,R.drawable.bmpwin);
			//菜单类实例 
			gameMenu=new GameMenu(menubg,btnS,btnP);//实例菜单界面
			backGround=new GameBg(bmpBG,bmpBG1);//实例游戏背景
			coin=new Coin(bmpCoin);//实例金币
			player=new Player(fish1,bmpHp,paopao);//实例游戏主角
			gameLost=new GameLost(bmpGameLost,LbtnS,LbtnP);
			//实例敌人的容器
			vcEnemy=new Vector<Enemy>();
			//主角子弹的容器
			vcBulletPlayer=new Vector<Bullet>();
			//实例随机库
			random=new Random();
			


		}
	}
	public void myDraw(){
		try{
			canvas=sfh.lockCanvas();
			if(canvas!=null){
				canvas.drawColor(Color.WHITE);		
				switch (gameState){
				case GAME_MENU:
					        //菜单的绘图函数
					gameMenu.draw(canvas, paint);
				            break;
				case  GAMEING:
					backGround.draw(canvas, paint);
					player.draw(canvas, paint);
					coin.draw(canvas, paint);
					//绘制敌人
					for(int i=0;i<vcEnemy.size();i++){
						vcEnemy.elementAt(i).draw(canvas, paint);
					}  
					//敌人子弹的绘制
					for(int i=0;i<vcBullet.size();i++){
						vcBullet.elementAt(i).draw(canvas, paint);
					}
					//处理主角子弹的绘制
					for(int i=0;i<vcBulletPlayer.size();i++){
						vcBulletPlayer.elementAt(i).draw(canvas,paint);		
					}
					//爆炸效果的绘制
					for(int i=0;i<vcBoom.size();i++){
						vcBoom.elementAt(i).draw(canvas, paint);
					}
					        break;
				case GAME_WIN: 
					canvas.drawBitmap(bmpGameWin,  (MySurfaceView.screenW-bmpGameLost.getWidth())/2, 0, paint);
					        break;
			    case GAME_LOST:
			    	gameLost.draw(canvas, paint);
					        break;
				}
				
			}
		}catch(Exception e){
			
		}finally{
			if(canvas!=null)
				sfh.unlockCanvasAndPost(canvas);
		}
	}
	
	/*
	 * 触屏事件监听
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event){
		switch (gameState){
		case GAME_MENU:
			     //菜单的触屏事件处理
			     gameMenu.onTouchEvent(event);
		          break;
		case GAMEING:
			     player.onTouchEvent(event);
			      break;
		case GAME_PAUSE:
			      break;		      
		case  GAME_LOST:
	              //失败界面的触屏事件
			      gameLost.onTouchEvent(event);
			      break;
		case  GAME_WIN:
			    
			      break;
		}
		return true;
		
	}
	/*
	 * 实体键按下的监听函数
	 */
	public boolean onKeyDown(int keyCode,KeyEvent event){
		if(keyCode==KeyEvent.KEYCODE_BACK){
			System.exit(0);
		}
		switch (gameState){
		case GAME_MENU:
			      break;
		case  GAMEING:
			player.onKeyDown(keyCode, event);
			      break;
		case  GAME_WIN:
			      break;
		case GAME_LOST:
			      break;
		case  GAME_PAUSE:
			      break;
		}
		return true;
	}
	/*
	 * 实体键抬起的监听函数
	 */
	public boolean onKeyUp(int keyCode,KeyEvent event){
		switch (gameState){
		case GAME_MENU:
			      break;
		case  GAMEING:
			player.onKeyUp(keyCode, event);
			      break;
		case  GAME_WIN:
			      break;
		case GAME_LOST:
			      break;
		case  GAME_PAUSE:
			      break;
		}
		return true;
		}
	
	private void logic(){
		switch (gameState){
		case GAME_MENU:
			break;
		case GAMEING:
			backGround.logic();
			player.logic();
			coin.logic();
			//敌人逻辑
			for(int i=0;i<vcEnemy.size();i++){
				Enemy en =vcEnemy.elementAt(i);
				if(en.isDead){
					vcEnemy.removeElementAt(i);
					sp.play(music, 1, 1, 0, 0, 1);
				}else{
					en.logic();
				}	
			}
			
			//生成敌人
			count++;
			if(count%createEnemyTime==0){
				for(int i =0;i<enemyArray[enemyArrayIndex].length;i++){
					if(enemyArray[enemyArrayIndex][i]==1){
						int x=MySurfaceView.screenW+random.nextInt(100);
						int y=random.nextInt(MySurfaceView.screenH-GameBg.bg1y-50)+GameBg.bg1y;					
						vcEnemy.addElement(new Enemy(bmpEnemyfish,1,x,y));
					}else if(enemyArray[enemyArrayIndex][i]==2){
						int y=random.nextInt(MySurfaceView.screenH-GameBg.bg1y-50)+GameBg.bg1y;	
						vcEnemy.addElement(new Enemy(bmpEnemyzyu,2,MySurfaceView.screenW+100,y));
					}else if(enemyArray[enemyArrayIndex][i]==3){
						int y=random.nextInt(MySurfaceView.screenH-GameBg.bg1y-50)+GameBg.bg1y;	
        				vcEnemy.addElement(new Enemy(bmpEnemyhma,3,MySurfaceView.screenW+100,y));
					}else if(enemyArray[enemyArrayIndex][i]==4){
						int y=random.nextInt(MySurfaceView.screenH-GameBg.bg1y-50)+GameBg.bg1y;	
        				vcEnemy.addElement(new Enemy(bmpEnemyshe,4,MySurfaceView.screenW+100,y));
					}else if(enemyArray[enemyArrayIndex][i]==5){
						int y=random.nextInt(MySurfaceView.screenH-GameBg.bg1y-50)+GameBg.bg1y;	
        				vcEnemy.addElement(new Enemy(bmpEnemyfishz,5,MySurfaceView.screenW+100,y));
					}else if(enemyArray[enemyArrayIndex][i]==6){
						int y=random.nextInt(MySurfaceView.screenH-GameBg.bg1y-50)+GameBg.bg1y;	
        				vcEnemy.addElement(new Enemy(bmpEnemyfishl,6,-100,y));
        				}
				}                               
				
				if(enemyArrayIndex>5){                     
					enemyArrayIndex=0;
				}else{
					enemyArrayIndex++;  
				}
			}
			//处理敌人与主角的碰撞
			for(int i=0;i<vcEnemy.size();i++){
				if(player.isCollsionWith(vcEnemy.elementAt(i))) {
					//发生碰撞 主角血量减一
					player.setFishHp(player.getFishHp()-1);
				}
			}
 			//处理主角的子弹与敌机碰撞
			for (int i = 0; i < vcBulletPlayer.size(); i++) {
				//取出主角子弹容器的每个元素
				Bullet blPlayer = vcBulletPlayer.elementAt(i);
				for (int j = 0; j < vcEnemy.size(); j++) {
					//添加爆炸效果
					//取出敌机容器的每个元与主角子弹遍历判断
					if (vcEnemy.elementAt(j).isCollsionWith(blPlayer)) {
			          
//						vcBoom.add(new Boom(bmpBoom,vcEnemy.elementAt(i).x,vcEnemy.elementAt(i).y,7));
					}
				}		
			} 
			//如果没血处理死亡事件
			if (player.getFishHp() <= 0) {
				gameState = GAME_LOST;
			}
			//处理胜利
			if(coin.Win==true){
				gameState=GAME_WIN;
			}
			
			//每秒添加主角子弹
			countPlayerBullet++;
			if(countPlayerBullet%10==0){
				int x=player.x+fish1.getWidth()/10;
				int y=player.y+fish1.getHeight()/2;
				vcBulletPlayer.add(new Bullet(playerBullet,x,y,Bullet.BULLET_PLAYER));		
			}
			//处理主角子弹的逻辑
			for(int i=0;i<vcBulletPlayer.size();i++){
				Bullet b=vcBulletPlayer.elementAt(i);
				if(b.isDead){
					vcBulletPlayer.removeElement(b);					
				}else{
						b.logic();
						}
			}

			//爆炸效果的逻辑
			for(int i=0;i<vcBoom.size();i++){
				Boom boom=vcBoom.elementAt(i);
				if(boom.playEnd){
					vcBoom.removeElementAt(i);
				}else{
					vcBoom.elementAt(i).logic();
				}
			}
			break;
		case GAME_PAUSE:
			break;
		case GAME_WIN:
			break;
		case  GAME_LOST:
			//重置游戏
			player.fishHp=3;
			initGame();
			for(int i=0;i<vcEnemy.size();i++){
			    vcEnemy.removeElementAt(i);
			    }
			enemyArrayIndex = 0;
			player.isCollsion=false;
			Coin.countCoin=0;
			Coin.coins=0;
			Coin.countL=0;
			Coin.timeJ=0;
			Coin.timeD=0;
			break;
			
		}
	}

	@Override
	public void run() {
		while(flag){
			long start=System.currentTimeMillis();
			myDraw();
			logic();
			long end=System.currentTimeMillis();
			try{
				if(end-start<50){
					Thread.sleep(50-(end-start));
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		flag=false;
	}
	

}
