package com.dabaichat.database;

import java.io.File;




import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;


import com.dabai.database.R;
import com.dabai.healthCheck.FigureCheck;
import com.dabaichat.adapter.MessageAdapter;
import com.dabaichat.enity.Message;


import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;





public class SetMainActivity extends Activity implements OnClickListener {
	private Context context = this;
	private GridView gridView;
	private static final int[] btns = new int[] { R.id.btn1, R.id.btn2,
			R.id.btn3, R.id.btn4};
	private int[] chat_icon = new int[] {R.drawable.bai1, R.drawable.bai2, R.drawable.bai3,
			R.drawable.bai4};
	
	private int[] head_icon = new int[] { R.drawable.touxiang1, R.drawable.touxiang2,
			R.drawable.touxiang3, R.drawable.touxiang4, R.drawable.touxiang5, R.drawable.touxiang6,
			R.drawable.touxiang7, R.drawable.touxiang8,R.drawable.touxiang9, R.drawable.touxiang10,
			R.drawable.touxiang11, R.drawable.touxiang12,};
	
	private int[] back_icon = new int[] { R.drawable.beij1, R.drawable.beij2,
			R.drawable.beij3, R.drawable.beij4, R.drawable.beij5, R.drawable.beij6,
			R.drawable.beij7, R.drawable.beij8,R.drawable.beij9, R.drawable.beij10,
			R.drawable.beij11, R.drawable.beij12,};
	private int[] pao_icon = new int[] { R.drawable.p1, R.drawable.p2,
			R.drawable.p3, R.drawable.p4,R.drawable.p5,R.drawable.p6,R.drawable.p7, R.drawable.p8,R.drawable.p9};
	//private String[] titles = new String[] { "����", "����", "����", "����" };
	private String[] head_title = new String[] { "1","2","3","4","5","6","7","8","9","10","11","12" };
	private String[] back_title = new String[] { "1","2","3","4","5","6","7","8","9","10","11","12"};
	private String[] chat_title = new String[] { "1","2","3","4"};
	private String[] pao_title = new String[] { "1","2","3","4","5","6","7","8","9"};
	private View view, view2;
	private ImageView img,img_back,img_head,img_pao,img_chat;
	private ListView lv;
	private MessageAdapter a;
	private List<Message> msgList = new ArrayList<Message>();
	private AlertDialog head_dialog,back_dialog,pao_dialog,bai_dialog,dialog;
	private static int tupian;
	private final String IMAGE_TYPE = "image/*";
	private final int IMAGE_CODE = 0; // �����IMAGE_CODE���Լ����ⶨ���

	
	public void saveBitmap(Bitmap bm, String picname) {
		// �����ñ��浽�ļ�
		
		     if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
		            File f = new File(Environment.getExternalStorageDirectory()+"/baymax", picname);
		    		if (f.exists()) {
		    			f.delete();
		    		}
		    		try {
		    			FileOutputStream out = new FileOutputStream(f);
		    			bm.compress(Bitmap.CompressFormat.PNG, 90, out);
		    			out.flush();
		    			out.close();
		    		} catch (FileNotFoundException e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		} catch (IOException e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
		    }
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_main);
		init();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void init() {
		img_back=(ImageView) findViewById(R.id.img_back);
		img_pao=(ImageView) findViewById(R.id.img_pao);
		img_head=(ImageView) findViewById(R.id.img_head);
		img_chat=(ImageView) findViewById(R.id.img_chat);
		for (int btn : btns) {
			findViewById(btn).setOnClickListener(this);
		}
		systeminit();
	}
	
	private void setheadicon() {
		// �Զ��嵯���Ի�������ͷ��
		Builder builder = new AlertDialog.Builder(SetMainActivity.this);
		dialog= builder.create();
		dialog.setTitle("��ѡ��");
		LayoutInflater flater = LayoutInflater.from(this);
		View view = flater.inflate(R.layout.headimg, null);
		dialog.setView(view);
		dialog.show();
	}

	private void setChatBai() {
		// �����������
		Builder builder = new AlertDialog.Builder(SetMainActivity.this);
		bai_dialog = builder.create();
		bai_dialog.setTitle("��ѡ��");
		LayoutInflater flater = LayoutInflater.from(this);
		View view = flater.inflate(R.layout.system_img, null);
		GridView grid = (GridView) view.findViewById(R.id.gridview);
		// ����������
		ArrayList<HashMap<String, Object>> imagelist_list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < chat_icon.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("cimages", chat_icon[i]);
			map.put("ctitles", chat_title[i]);
			imagelist_list.add(map);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, imagelist_list,
				R.layout.chat_set, new String[] { "cimages", "ctitles" },
				new int[] { R.id.cimage, R.id.ctxt });
		grid.setAdapter(simpleAdapter);
		bai_dialog.setView(view);
		bai_dialog.show();

		// ���ѡ������ͷ��,��grid���ͼƬ��Ӽ���
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (chat_icon[arg2]) {
				case R.drawable.bai1:
					chat_icon(R.drawable.bai1);
					break;
				case R.drawable.bai2:
					chat_icon(R.drawable.bai2);
					break;
				case R.drawable.bai3:
					chat_icon(R.drawable.bai3);
					break;
				case R.drawable.bai4:
					chat_icon(R.drawable.bai4);
					break;

				default:

					break;
				}
			}
		});
		// ��������󱣴浽�ļ�

	}

	private void setBackground() {
		// ���ñ���
		Builder b = new AlertDialog.Builder(SetMainActivity.this);
		back_dialog = b.create();
		back_dialog.setTitle("��ѡ�񱳾�");
		LayoutInflater flater = LayoutInflater.from(this);
		View view = flater.inflate(R.layout.system_img, null);
		GridView gg = (GridView) view.findViewById(R.id.gridview);
		// ����������
		ArrayList<HashMap<String, Object>> imagelist_list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < back_icon.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("cimages", back_icon[i]);
			map.put("ctitles", back_title[i]);
			imagelist_list.add(map);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, imagelist_list,
				R.layout.chat_set, new String[] { "cimages", "ctitles" },
				new int[] { R.id.cimage, R.id.ctxt });
		gg.setAdapter(simpleAdapter);
		back_dialog.setView(view);
		back_dialog.show();
		// ���ѡ������ͷ��,��grid���ͼƬ��Ӽ���
		gg.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (back_icon[arg2]) {
				case R.drawable.beij1:
					back_icon(R.drawable.beijing1);
					break;
				case R.drawable.beij2:
					back_icon(R.drawable.beijing2);
					break;
				case R.drawable.beij3:
					back_icon(R.drawable.beijing3);
					break;
				case R.drawable.beij4:
					back_icon(R.drawable.beijing4);
					break;
				case R.drawable.beij5:
					back_icon(R.drawable.beijing5);
					break;
				case R.drawable.beij6:
					back_icon(R.drawable.beijing6);
					break;
				case R.drawable.beij7:
					back_icon(R.drawable.beijing7);
					break;
				case R.drawable.beij8:
					back_icon(R.drawable.beijing8);
					break;
				case R.drawable.beij9:
					back_icon(R.drawable.beijing9);
					break;
				case R.drawable.beij10:
					back_icon(R.drawable.beijing10);
					break;
				case R.drawable.beij11:
					back_icon(R.drawable.beijing11);
					break;
				case R.drawable.beij12:
					back_icon(R.drawable.beijing12);
					break;
				default:

					break;
				}
			}
		});

	}
	
	private void setqipao() {
		// ��������

		Builder b = new AlertDialog.Builder(SetMainActivity.this);
		pao_dialog = b.create();
		pao_dialog.setTitle("��ѡ��");
		LayoutInflater flater = LayoutInflater.from(this);
		View view = flater.inflate(R.layout.system_img, null);
		GridView gg = (GridView) view.findViewById(R.id.gridview);
		// ����������
		ArrayList<HashMap<String, Object>> imagelist_list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < pao_icon.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("cimages", pao_icon[i]);
			map.put("ctitles", pao_title[i]);
			imagelist_list.add(map);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, imagelist_list,
				R.layout.chat_set, new String[] { "cimages", "ctitles" },
				new int[] { R.id.cimage, R.id.ctxt });
		gg.setAdapter(simpleAdapter);
		pao_dialog.setView(view);
		pao_dialog.show();
		// ���ѡ������ͷ��,��grid���ͼƬ��Ӽ���
		gg.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (pao_icon[arg2]) {
				case R.drawable.p1:
					po_icon(R.drawable.p1);
					break;
				case R.drawable.p2:
					po_icon(R.drawable.p2);
					break;
				case R.drawable.p3:
					po_icon(R.drawable.p3);
					break;
				case R.drawable.p4:
					po_icon(R.drawable.p4);
					break;
				case R.drawable.p5:
					po_icon(R.drawable.p5);
					break;
				case R.drawable.p6:
					po_icon(R.drawable.p6);
					break;
				case R.drawable.p7:
					po_icon(R.drawable.p7);
					break;
				case R.drawable.p8:
					po_icon(R.drawable.p8);
					break;
				case R.drawable.p9:
					po_icon(R.drawable.p9);
					break;

				default:

					break;
				}
			}
		});

	}
	
	
	public void imgselect(View v) {
		//ѡ�����ͷ��
		switch (v.getId()) {
		case R.id.load_btn:
			
			// TODO����������ͼƬ
			Toast.makeText(this, "sorry", 1000).show();
			break;
		case R.id.localimg_btn:
			// ������Ƭ
			Toast.makeText(this, "sorry", 1000).show();
			//setImage();
			break;
		case R.id.sysimg_btn:
			// ϵͳָ��ͼƬ
			Builder builder = new AlertDialog.Builder(SetMainActivity.this);
			if (head_dialog == null) {
				head_dialog = builder.create();
			}
			head_dialog.setIcon(R.drawable.ic_launcher);
			head_dialog.setTitle("��ѡ��ͷ��");
			head_dialog.setView(view);

			head_dialog.show();

			// ���ѡ������ͷ��,��grid���ͼƬ��Ӽ���
			gridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

					switch (head_icon[arg2]) {
					case R.drawable.touxiang1:
						head_icon(R.drawable.touxiang1);
						break;
					case R.drawable.touxiang2:
						head_icon(R.drawable.touxiang2);
						break;
					case R.drawable.touxiang3:
						head_icon(R.drawable.touxiang3);
						break;
					case R.drawable.touxiang4:
						head_icon(R.drawable.touxiang4);
						break;
					case R.drawable.touxiang5:
						head_icon(R.drawable.touxiang5);
						break;
					case R.drawable.touxiang6:
						head_icon(R.drawable.touxiang6);
						break;
					case R.drawable.touxiang7:
						head_icon(R.drawable.touxiang7);
						break;
					case R.drawable.touxiang8:
						head_icon(R.drawable.touxiang8);
						break;
					case R.drawable.touxiang9:
						head_icon(R.drawable.touxiang9);
						break;
					case R.drawable.touxiang10:
						head_icon(R.drawable.touxiang10);
						break;
					case R.drawable.touxiang11:
						head_icon(R.drawable.touxiang11);
						break;
					case R.drawable.touxiang12:
						head_icon(R.drawable.touxiang12);
						break;

					default:

						break;
					}
				}
			});

		}

	}
/*
	private void setImage() {
		// ʹ��intent����ϵͳ�ṩ����Ṧ�ܣ�ʹ��startActivityForResult��Ϊ�˻�ȡ�û�ѡ���ͼƬ
		Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
		getAlbum.setType(IMAGE_TYPE);
		startActivityForResult(getAlbum, IMAGE_CODE);
	};
*/
	public void back_icon(int b) {
		img_back.setBackgroundResource(b);
		back_dialog.setIcon(b);
		Toast.makeText(this, "�������óɹ�", 1000).show();
		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), b);
		back_dialog.cancel();
		saveBitmap(bm, "back_icon");
	}

	public void chat_icon(int b) {
		img_chat.setBackgroundResource(b);
		bai_dialog.setIcon(b);
		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), b);
		saveBitmap(bm, "chat_icon");
		bai_dialog.cancel();
		Toast.makeText(this, "���ͷ�����óɹ�", 1000).show();
	}

	public void po_icon(int b){
		img_pao.setBackgroundResource(b);
		pao_dialog.setIcon(b);
		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), b);
		saveBitmap(bm, "pao_icon");
		pao_dialog.cancel();
		Toast.makeText(this, "�������óɹ�", 1000).show();
	}

	
	public void head_icon(int a) {
		img_head.setBackgroundResource(a);
		head_dialog.setIcon(a);
		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), a);
		saveBitmap(bm, "head_icon");
		head_dialog.cancel();
		dialog.cancel();
		Toast.makeText(this, "ͷ�����óɹ�", 1000).show();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) { // �˴��� RESULT_OK ��ϵͳ�Զ����һ������
			Log.e("TAG->onresult", "ActivityResult resultCode error");
			return;
		}

		Bitmap bm = null;

		// ���ĳ������ContentProvider���ṩ���� ����ͨ��ContentResolver�ӿ�

		ContentResolver resolver = getContentResolver();

		// �˴��������жϽ��յ�Activity�ǲ�������Ҫ���Ǹ�

		if (requestCode == IMAGE_CODE) {
			try {
				Uri originalUri = data.getData(); // ���ͼƬ��uri
				bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
				// �Եõ�bitmapͼƬ
				// Drawable ee=new BitmapDrawable(bm);
				// ���￪ʼ�ĵڶ����֣���ȡͼƬ��·����
				String[] proj = { MediaStore.Images.Media.DATA };
				// ������android��ý�����ݿ�ķ�װ�ӿڣ�����Ŀ�Android�ĵ�

				Cursor cursor = managedQuery(originalUri, proj, null, null,
						null);
				// ���Ҹ������ ����ǻ���û�ѡ���ͼƬ������ֵ
				int column_index = cursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				// �����������ͷ ���������Ҫ����С�ĺ���������Խ��
				cursor.moveToFirst();
				// ����������ֵ��ȡͼƬ·��
				String path = cursor.getString(column_index);
				// imgPath.setText(path);
				/*ChatUIActivity.a = bm;
				Intent it = new Intent(SetMainActivity.this,
						ChatUIActivity.class);
				startActivity(it);*/
				//Bitmap bm = BitmapFactory.decodeResource(context.getResources(), b);
				//ChatUIActivity.bm = bm;

				saveBitmap(bm, "chat_icon");

				 Intent it = new Intent(SetMainActivity.this, ChatUIActivity.class);
				startActivity(it);
				SetMainActivity.this.finish();
			} catch (IOException e) {
				Log.e("TAG-->Error", e.toString());
			}

		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			setBackground();
			break;
		case R.id.btn2:
			setChatBai();
			break;
		case R.id.btn3:
			setheadicon();
			break;
		case R.id.btn4:
			setqipao();
			break;
	
		default:
			Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
			break;
		}
	}
	public void systeminit(){
		// ʵ���������е�ϵͳͼƬ
				ArrayList<HashMap<String, Object>> imagelist_list = new ArrayList<HashMap<String, Object>>();
				for (int i = 0; i < head_icon.length; i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("images", head_icon[i]);
					map.put("titles", head_title[i]);
					imagelist_list.add(map);
				}
				SimpleAdapter simpleAdapter = new SimpleAdapter(this, imagelist_list,
						R.layout.imgview, new String[] { "images", "titles" },
						new int[] { R.id.image, R.id.txt });
				// ��ȡ�����ļ��е�grid
				LayoutInflater li = LayoutInflater.from(this);
				view = li.inflate(R.layout.system_img, null);
				gridView = (GridView) view.findViewById(R.id.gridview);
				gridView.setAdapter(simpleAdapter);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
	
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent=new Intent();
		    intent.setClass(SetMainActivity.this,ChatUIActivity.class);
		    startActivity(intent);
		    SetMainActivity.this.finish();
		}
		
		return super.onKeyDown(keyCode, event);
	}
}
