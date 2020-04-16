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
	//������Ϸ״̬����
	public  static final int GAME_MENU=0;
    public  static final int GAMEING=1;
	public  static final int GAME_WIN=2;
	public  static final int GAME_LOST=3;
	public  static final int GAME_PAUSE=-1;
	//��ǰ����Ϸ״̬
	public static int gameState=GAME_MENU;
	private Resources res =this.getResources();
	private Bitmap menubg;//��Ϸ�˵�����
	private Bitmap bmpGameWin;//ʤ������
	private Bitmap bmpGameLost;//��Ϸʧ�ܱ���
	private Bitmap bmpCoin;//��ҵ�ͼƬ
	private Bitmap btnS;//��Ϸ��ʼ
	private Bitmap btnP;//��Ϸ��ʼ������
	private Bitmap LbtnS;
	private Bitmap LbtnP;
	private Bitmap fish1;//����������ͼ
	private Bitmap paopao;//��������
	private Bitmap bmpHp;//��������Ѫ��
	private Bitmap bmpEnemyfish;//����
	private Bitmap bmpEnemyzyu;//���~
	private Bitmap bmpEnemyhma;//���R
	private Bitmap bmpEnemyshe;//��
	private Bitmap bmpEnemyfishz;//����
	private Bitmap bmpEnemyfishl;//����

	//����
	public SoundPool sp;
	public int music;
	
	//������Ϸ����
	private Bitmap bmpBG;
	private Bitmap bmpBG1;
	//�������Ƕ���
	private Player player;
	//����һ���˵�����
	private GameMenu gameMenu;
	//������Ϸ����״̬�ı���
	private GameBg backGround;
	//������Ϸʧ�ܽ���
	private GameLost gameLost;
	//������Ϸ���ϵͳ
	private Coin coin;
	//����һ�����˵�����
	private Vector<Enemy>vcEnemy;
	//�������ɵ��˵�ʱ��
	private int createEnemyTime=50;
	private int count;
	//���˵�����
	private int enemyArray[][]={
			{5,5},{2,5,1,3},{3,2,5,1},{1,6,1,4},{4},
			{1,4,2,3},{1,6,3,1},{3,3},{6,4},{5},
			{3,2,3,1},{2,1},{3,6,3},{1},{2,1,1},{3},{2,3},{1},{2},
			{1,6,1,1},{1},{2,1,1},{3},{2,3},{1},{2},
			{1,},{1,1},{3,3},
			{1,1,2,3},{1,1},{2,2}
	};
	//��ǰȡ��һά���±�
	private int enemyArrayIndex;
	//����⣬Ϊ�������˸����������
	private Random random;
	//���˵��ӵ�����
	private Vector<Bullet> vcBullet=new Vector<Bullet>();
	//����ӵ��ļ�����
	private int countEnemyBullet;
	//���ǵ��ӵ�����
	private Vector<Bullet> vcBulletPlayer =new Vector<Bullet>(); 
	//�ӵ��ļ�����
	private int countPlayerBullet;
	//���ǵ��ӵ�
	private Bitmap playerBullet;
	//���˵��ӵ�
	private Bitmap enemyBullet;
	//��ըЧ��������
	private Vector<Boom> vcBoom=new Vector<Boom>();
	//������ըЧ����λͼ
	private Bitmap bmpBoom;
	
	
	
		//��������x,yֵ
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
		//���ñ�������
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
	 * �Զ�����Ϸ��ʼ������
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
			//�˵���ʵ�� 
			gameMenu=new GameMenu(menubg,btnS,btnP);//ʵ���˵�����
			backGround=new GameBg(bmpBG,bmpBG1);//ʵ����Ϸ����
			coin=new Coin(bmpCoin);//ʵ�����
			player=new Player(fish1,bmpHp,paopao);//ʵ����Ϸ����
			gameLost=new GameLost(bmpGameLost,LbtnS,LbtnP);
			//ʵ�����˵�����
			vcEnemy=new Vector<Enemy>();
			//�����ӵ�������
			vcBulletPlayer=new Vector<Bullet>();
			//ʵ�������
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
					        //�˵��Ļ�ͼ����
					gameMenu.draw(canvas, paint);
				            break;
				case  GAMEING:
					backGround.draw(canvas, paint);
					player.draw(canvas, paint);
					coin.draw(canvas, paint);
					//���Ƶ���
					for(int i=0;i<vcEnemy.size();i++){
						vcEnemy.elementAt(i).draw(canvas, paint);
					}  
					//�����ӵ��Ļ���
					for(int i=0;i<vcBullet.size();i++){
						vcBullet.elementAt(i).draw(canvas, paint);
					}
					//���������ӵ��Ļ���
					for(int i=0;i<vcBulletPlayer.size();i++){
						vcBulletPlayer.elementAt(i).draw(canvas,paint);		
					}
					//��ըЧ���Ļ���
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
	 * �����¼�����
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event){
		switch (gameState){
		case GAME_MENU:
			     //�˵��Ĵ����¼�����
			     gameMenu.onTouchEvent(event);
		          break;
		case GAMEING:
			     player.onTouchEvent(event);
			      break;
		case GAME_PAUSE:
			      break;		      
		case  GAME_LOST:
	              //ʧ�ܽ���Ĵ����¼�
			      gameLost.onTouchEvent(event);
			      break;
		case  GAME_WIN:
			    
			      break;
		}
		return true;
		
	}
	/*
	 * ʵ������µļ�������
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
	 * ʵ���̧��ļ�������
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
			//�����߼�
			for(int i=0;i<vcEnemy.size();i++){
				Enemy en =vcEnemy.elementAt(i);
				if(en.isDead){
					vcEnemy.removeElementAt(i);
					sp.play(music, 1, 1, 0, 0, 1);
				}else{
					en.logic();
				}	
			}
			
			//���ɵ���
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
			//������������ǵ���ײ
			for(int i=0;i<vcEnemy.size();i++){
				if(player.isCollsionWith(vcEnemy.elementAt(i))) {
					//������ײ ����Ѫ����һ
					player.setFishHp(player.getFishHp()-1);
				}
			}
 			//�������ǵ��ӵ���л���ײ
			for (int i = 0; i < vcBulletPlayer.size(); i++) {
				//ȡ�������ӵ�������ÿ��Ԫ��
				Bullet blPlayer = vcBulletPlayer.elementAt(i);
				for (int j = 0; j < vcEnemy.size(); j++) {
					//��ӱ�ըЧ��
					//ȡ���л�������ÿ��Ԫ�������ӵ������ж�
					if (vcEnemy.elementAt(j).isCollsionWith(blPlayer)) {
			          
//						vcBoom.add(new Boom(bmpBoom,vcEnemy.elementAt(i).x,vcEnemy.elementAt(i).y,7));
					}
				}		
			} 
			//���ûѪ���������¼�
			if (player.getFishHp() <= 0) {
				gameState = GAME_LOST;
			}
			//����ʤ��
			if(coin.Win==true){
				gameState=GAME_WIN;
			}
			
			//ÿ����������ӵ�
			countPlayerBullet++;
			if(countPlayerBullet%10==0){
				int x=player.x+fish1.getWidth()/10;
				int y=player.y+fish1.getHeight()/2;
				vcBulletPlayer.add(new Bullet(playerBullet,x,y,Bullet.BULLET_PLAYER));		
			}
			//���������ӵ����߼�
			for(int i=0;i<vcBulletPlayer.size();i++){
				Bullet b=vcBulletPlayer.elementAt(i);
				if(b.isDead){
					vcBulletPlayer.removeElement(b);					
				}else{
						b.logic();
						}
			}

			//��ըЧ�����߼�
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
			//������Ϸ
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
