package com.example.dabai;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class GameLost {
    //ʧ�ܵı���
	private Bitmap bmpGameLost;
	//��ťͼƬ��Դ(���º�δ����ͼ)
	private Bitmap LbtnS, LbtnP;
	//��ť������
	private int LbtnX, LbtnY;
	//��ť�Ƿ��±�ʶλ
	private Boolean isPress;
	//�˵���ʼ��
	public GameLost(Bitmap bmpGameLost, Bitmap LbtnS, Bitmap LbtnP) {
		this.bmpGameLost = bmpGameLost;
		this.LbtnS=LbtnS;
		this.LbtnP=LbtnP;
		//X���У�Y������Ļ�ײ�
		LbtnX = MySurfaceView.screenW / 2 - LbtnS.getWidth() / 2;
		LbtnY = MySurfaceView.screenH -LbtnS.getHeight();
		isPress = false;
	}
	//�˵���ͼ����
	public void draw(Canvas canvas, Paint paint) {
		//���Ʋ˵�����ͼ
		canvas.drawBitmap(bmpGameLost, (MySurfaceView.screenW-bmpGameLost.getWidth())/2, 0, paint);
		//����δ���°�ťͼ
		if (isPress) {//�����Ƿ��»��Ʋ�ͬ״̬�İ�ťͼ
			canvas.drawBitmap(LbtnP, LbtnX, LbtnY, paint);
		} else {
			canvas.drawBitmap(LbtnS, LbtnX, LbtnY, paint);
		}
	}
	//�˵������¼���������Ҫ���ڴ���ť�¼�
	public void onTouchEvent(MotionEvent event) {
		//��ȡ�û���ǰ����λ��
		int pointX = (int) event.getX();
		int pointY = (int) event.getY();
		//���û��ǰ��¶������ƶ�����
		if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
			//�ж��û��Ƿ����˰�ť
			if (pointX >LbtnX && pointX < LbtnX + LbtnS.getWidth()) {
				if (pointY > LbtnY && pointY < LbtnY + LbtnS.getHeight()) {
					isPress = true;
				} else {
					isPress = false;
				}
			} else {
				isPress = false;
			}
			//���û���̧����
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			//̧���ж��Ƿ�����ť����ֹ�û��ƶ�����
			if (pointX > LbtnX && pointX < LbtnX + LbtnS.getWidth()) {
				if (pointY > LbtnY && pointY < LbtnY +LbtnS.getHeight()) {
					//��ԭButton״̬Ϊδ����״̬
					isPress = false;
					//�ı䵱ǰ��Ϸ״̬Ϊ��ʼ��Ϸ
			       MySurfaceView.gameState = MySurfaceView.GAME_MENU;
				}
			}
		}
	}
}