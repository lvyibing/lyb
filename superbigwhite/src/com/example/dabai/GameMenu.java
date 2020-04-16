package com.example.dabai;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;


public class GameMenu {
	//�˵�����ͼ
	private Bitmap bmpMenu;
	//��ťͼƬ��Դ(���º�δ����ͼ)
	private Bitmap btnS, btnP;
	//��ť������
	private int btnX, btnY;
	//��ť�Ƿ��±�ʶλ
	private Boolean isPress;
	//�˵���ʼ��
	public GameMenu(Bitmap bmpMenu, Bitmap btnS, Bitmap btnP) {
		this.bmpMenu = bmpMenu;
		this.btnS=btnS;
		this.btnP=btnP;
		//X���У�Y������Ļ�ײ�
		btnX = MySurfaceView.screenW / 2 - btnS.getWidth() / 2;
		btnY = MySurfaceView.screenH - btnS.getHeight();
		isPress = false;
	}
	//�˵���ͼ����
	public void draw(Canvas canvas, Paint paint) {
		//���Ʋ˵�����ͼ
		canvas.drawBitmap(bmpMenu, (MySurfaceView.screenW-bmpMenu.getWidth())/2, 0, paint);
		//����δ���°�ťͼ
		if (isPress) {//�����Ƿ��»��Ʋ�ͬ״̬�İ�ťͼ
			canvas.drawBitmap(btnP, btnX, btnY, paint);
		} else {
			canvas.drawBitmap(btnS, btnX, btnY, paint);
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
			if (pointX > btnX && pointX < btnX + btnS.getWidth()) {
				if (pointY > btnY && pointY < btnY + btnS.getHeight()) {
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
			if (pointX > btnX && pointX < btnX + btnS.getWidth()) {
				if (pointY > btnY && pointY < btnY +btnS.getHeight()) {
					//��ԭButton״̬Ϊδ����״̬
					isPress = false;
					//�ı䵱ǰ��Ϸ״̬Ϊ��ʼ��Ϸ
			       MySurfaceView.gameState = MySurfaceView.GAMEING;
				}
			}
		}
	}
}
