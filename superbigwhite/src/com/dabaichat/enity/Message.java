package com.dabaichat.enity;

import android.graphics.Bitmap;

public class Message {
	public static final int TYPE_RECEIVED = 0;// ��ʾ���ܵ�һ����Ϣ
	public static final int TYPE_SENT = 1;// ��ʾ������һ����Ϣ
	private String content;// ��Ϣ������
	private int type;// ��Ϣ������
	private Bitmap tupian;
	private Bitmap qipao;
	public Message(String content, int type,Bitmap tupian) {
		// TODO Auto-generated constructor stub
		this.content = content;
		this.type = type;
		this.tupian=tupian;
	}
	
	public Message(String content, int type, Bitmap tupian, Bitmap qipao) {
		super();
		this.content = content;
		this.type = type;
		this.tupian = tupian;
		this.qipao = qipao;
	}

	public String getContent() {

		return content;
	}

	public int getType() {
		return type;
	}

	public Object getTupian() {
		return tupian;
	}

	public void setTupian(Bitmap tupian) {
		this.tupian = tupian;
	}

	public Bitmap getQipao() {
		return qipao;
	}

	public void setQipao(Bitmap qipao) {
		this.qipao = qipao;
	}
	
	
	
}
