package com.dabaichat.database;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.dabaichat.adapter.MessageAdapter;
import com.dabaichat.enity.Message;
import com.dabaichat.helper.MyTableHelper;
import com.dabaichat.helper.MydatabaseHelper;
import com.dabaichat.testinternet.testinternet;
import com.dabaichat.util.SearchLikeUtil;

import com.dabai.database.R;
import com.dabai.fragment.YiLiaoFragment;
import com.dabai.yiliao.yiliaoActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.os.Environment;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.ListView;

@SuppressLint("NewApi")
public class ChatUIActivity extends Activity implements HttpGetDataListener {
	private Context context = this;
	private ListView msgListView;
	private EditText inputText;
	private ImageButton send, set, delete;
	private MessageAdapter adapter;
	private List<Message> msgList = new ArrayList<Message>();
	public static Bitmap a = null;
	public static Bitmap bm = null;
	public static Bitmap background = null, pao = null, cspao = null;
	private ListView listview;
	private static DataOutputStream dos = null;
	private static DataInputStream dis = null;
	private String content = "", data = "", accept = "", allChat = "";
	private MyTableHelper tablehelper = null;
	private static MydatabaseHelper databasehelper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chat_avtivity);
				createFile();
				initMsgs();

	}

	// -----------------------
	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.delete:
			deleteLisiner();
			break;
		case R.id.send:
			sendListener();
			break;
		case R.id.set:
			setListener();
			break;
	
		default:
			break;
		}
	}

	

	public void createFile() {
		// ����baymax�ļ���
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			File sdcardDir = Environment.getExternalStorageDirectory();
			String path = sdcardDir.getPath() + "/baymax";
			File path1 = new File(path);
			if (!path1.exists()) {
				path1.mkdirs();
			}
		}
	}

	public void saveChat(String str) {
		// �����ļ�������������
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {

			File f = new File(Environment.getExternalStorageDirectory()
					+ "/baymax", "hello.txt");
			try {
				// ������Ϣ��¼
				dos = new DataOutputStream(new FileOutputStream(f, true));
				// buf.append(str);
				dos.writeChars(str);
				dos.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public Bitmap geticon(String path) {
		// ���ļ���ȡ��ͼƬ����
		File mFile = new File(path);
		if (mFile.exists()) {
			Bitmap bitmap = BitmapFactory.decodeFile(path);
			return bitmap;
		}
		return null;
	}

	public void setadapter() {
		adapter = new MessageAdapter(ChatUIActivity.this, R.layout.msg_item,
				msgList);
		msgListView = (ListView) findViewById(R.id.msg_list_view);
		msgListView.setAdapter(adapter);
	}

	@SuppressLint("NewApi")
	public void setLocalIcon() {
		// ��ȡ��������ͼƬ
		a = geticon(Environment.getExternalStorageDirectory()
				+ "/baymax/head_icon");
		bm = geticon(Environment.getExternalStorageDirectory()
				+ "/baymax/chat_icon");
		background = geticon(Environment.getExternalStorageDirectory()
				+ "/baymax/back_icon");
		pao = geticon(Environment.getExternalStorageDirectory()
				+ "/baymax/pao_icon");
		if (bm == null) {
			bm = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.bai1);
		}
		if (a == null) {
			a = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.touxiang1);
		}
		if (pao == null) {
			pao = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.p6);
		}
		if (cspao == null) {
			cspao = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.p6);
		}
		if (background == null) {
			background = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.beijing1);
		}
			Drawable drawable = new BitmapDrawable(background);
			listview.setBackground(drawable);
		
	}

	public void setListener() {
		Intent it = new Intent(ChatUIActivity.this, SetMainActivity.class);
		startActivity(it);
		ChatUIActivity.this.finish();
	}
	
	

	public void deleteLisiner() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				ChatUIActivity.this);
		builder.setTitle("��ȷ��Ҫɾ��ȫ����");
		builder.setPositiveButton("�ǵ�", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if (Environment.MEDIA_MOUNTED.equals(Environment
						.getExternalStorageState())) {

					File f = new File(Environment.getExternalStorageDirectory()
							+ "/baymax", "hello.txt");
					if (f.exists()) {
						f.delete();
						int size = msgList.size();
						if (size > 0) {
							msgList.removeAll(msgList);
							adapter.notifyDataSetChanged();
							msgListView.setSelection(msgList.size());
						}
					}
				}

			}
		});
		builder.setNegativeButton("��Ҫ", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});
		builder.create().show();
	}

	

	public void showChat() {
		// ����������ʾ����Ļ
		String chat = getChat();
		if (!chat.equals("")) {
			String[] str = chat.split(":");
			for (int j = 0; j < str.length; j++) {
				if (j % 2 == 0) {
					Message msg = new Message(str[j], Message.TYPE_SENT, a, pao);
					msgList.add(msg);
					adapter.notifyDataSetChanged();
					msgListView.setSelection(msgList.size());
				} else if (j % 2 == 1) {
					Message msg = new Message(str[j], Message.TYPE_RECEIVED,
							bm, cspao);
					msgList.add(msg);
					adapter.notifyDataSetChanged();
					msgListView.setSelection(msgList.size());
				}

			}
		}
	}

	public String getChat() {
		// ��ȡ��������
		String chat = "";
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			File f = new File(Environment.getExternalStorageDirectory()
					+ "/baymax", "hello.txt");
			if (f.exists()) {

				try {
					dis = new DataInputStream(new FileInputStream(f));
					while (true) {
						chat += dis.readChar();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						dis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		return chat;
	}

	// -----------------------

	@SuppressLint("NewApi")
	public void initzujian() {
		// ��ʼ�����
		msgListView = (ListView) findViewById(R.id.msg_list_view);
		inputText = (EditText) findViewById(R.id.input_text);
		send = (ImageButton) findViewById(R.id.send);
		listview = (ListView) findViewById(R.id.msg_list_view);
		set = (ImageButton) findViewById(R.id.set);
		delete = (ImageButton) findViewById(R.id.delete);
	}

	public void initMsgs() {
		initzujian();
		setLocalIcon();
		setadapter();
		if(databasehelper==null){
			initDatabase();
		}
		showChat();

	}

	public void sendListener() {
		
		boolean aaa = new testinternet().isNetworkConnected(context);
		boolean bbb = new testinternet().isWifiConnected(context);
		// ���粻����
		if (aaa == true || bbb == true) {
			content = inputText.getText().toString();
			String he = content;
			HttpData httpData = (HttpData) new HttpData(
					"http://www.tuling123.com/openapi/api?key=98783ab5ae2299f62311de317f406843&info="
							+ he, this).execute();
		} else {
					sendLocalLisner();
				

		}
		
		
		
		
	}

	@Override
	public void getDataUrl(String data) {
		// TODO Auto-generated method stub
		parseText(data);
	}

	public void parseText(String str) {
		try {
			JSONObject jb = new JSONObject(str);

			Message msg = new Message(content, Message.TYPE_SENT, a, pao);
			msgList.add(msg);
			adapter.notifyDataSetChanged();
			msgListView.setSelection(msgList.size());
			inputText.setText("");

			Message msga = new Message(jb.getString("text"),
					Message.TYPE_RECEIVED, bm, cspao);
			msgList.add(msga);
			adapter.notifyDataSetChanged();
			msgListView.setSelection(msgList.size());
			accept=jb.getString("text");
			allChat = content + ":" + accept + ":";
			saveChat(allChat);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//-------
	public void sendLocalLisner() {
		// ���׷�������
		content = inputText.getText().toString();

		
			Message msg = new Message(content, Message.TYPE_SENT, a, pao);
			msgList.add(msg);
			adapter.notifyDataSetChanged();
			msgListView.setSelection(msgList.size());
			inputText.setText("");
		
		// �����ݿ����ҵ���Ҫ�Ķ���
		accept = find(content);
		allChat = content + ":" + accept + ":";
		saveChat(allChat);
		Message remsg = new Message(accept, Message.TYPE_RECEIVED, bm, cspao);
		msgList.add(remsg);
		adapter.notifyDataSetChanged();
		msgListView.setSelection(msgList.size());
	}
	public String find(String str) {
		// �ӱ������ݿ��е�����Ҫ�Ķ���
		SearchLikeUtil searchUtil = new SearchLikeUtil(
				databasehelper.getReadableDatabase());
		String data = searchUtil.searchStudent(str);
		return data;
	}
	public void initDatabase(){
		 databasehelper = new MydatabaseHelper(ChatUIActivity.this);
		 tablehelper = new	MyTableHelper(databasehelper.getWritableDatabase());
		 tablehelper.insert("����˭", "��ã����Ǵ�ף����˽�˽�������"); 
		 tablehelper = new	MyTableHelper(databasehelper.getWritableDatabase());
		 tablehelper.insert("����", "��ֻ�ϻ���ֻ�ϻ��ܵĿ�~"); 
		 tablehelper = new	MyTableHelper(databasehelper.getWritableDatabase());
		 tablehelper.insert("Ů����", "�����㰡"); 
		 tablehelper = new	MyTableHelper(databasehelper.getWritableDatabase());
		 tablehelper.insert("Ц��", "�Ҳ���"); 
		 tablehelper = new	MyTableHelper(databasehelper.getWritableDatabase());
		 tablehelper.insert("���", "��ã����Ǵ�ף����˽�˽�������"); 
		 tablehelper = new	MyTableHelper(databasehelper.getWritableDatabase());
		 tablehelper.insert("���", "balalalala~"); 
		 tablehelper = new	MyTableHelper(databasehelper.getWritableDatabase());
		 tablehelper.insert("ͷʹ", "����Ϣ����"); 
		 tablehelper = new	MyTableHelper(databasehelper.getWritableDatabase());
		 tablehelper.insert("�Ǻ�", "�ٺ�"); 
		 tablehelper = new	MyTableHelper(databasehelper.getWritableDatabase());
		 tablehelper.insert("�ٺ�", "�Ǻ�"); 
		 tablehelper = new	MyTableHelper(databasehelper.getWritableDatabase());
		 tablehelper.insert("����", "���"); 
	}

}
