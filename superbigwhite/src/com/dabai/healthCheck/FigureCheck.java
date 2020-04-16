package com.dabai.healthCheck;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dabai.database.R;

public class FigureCheck extends Activity {
	
	private ImageButton button01;
	private TextView height,weight,result;
	private EditText heightEdit,weightEdit;
	private static DataOutputStream dos = null;
	private static DataInputStream dis = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.figure);
        createFile();
        button01=(ImageButton)findViewById(R.id.button01);
        height=(TextView)findViewById(R.id.height);
        weight=(TextView)findViewById(R.id.weight);
        result=(TextView)findViewById(R.id.result);
        
        heightEdit=(EditText)findViewById(R.id.heightEdit);
        weightEdit=(EditText)findViewById(R.id.weightEdit);
        
        button01.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				float i1 ,i2;
				String s = null;
				String temp1=heightEdit.getText().toString();
				String temp2=weightEdit.getText().toString();
				 heightEdit.setText(null);
				 weightEdit.setText(null);
				if(temp1==null  || temp2==null || temp1.equals("")  || temp2.equals("")){
					 s="���������ݣ���";
					 result.setText(s);
					 return;
				}
				else{ i1=(float)Float.parseFloat(temp1);
				      i2=(float)Float.parseFloat(temp2);
				      if(i1>320 || i2>300){
							 result.setText("���ˣ���ɻص���");
							 return;
				      }
				}
			    
				i1=i1/100;
				if((i2/(i1*i1)) <= 25.0 && (i2/(i1*i1)) > 18.0){
					s="��������ǣ�"+temp1+" cm\n"+"���������ǣ�"+temp2+" kg\n"+"\n"+"     ������Ĵ����������䣬���������!";
					saveChat("������Ĵ����������䣬���������!");
				}else if((i2/(i1*i1)) <= 30.0 && (i2/(i1*i1)) > 25.0){
					s="��������ǣ�"+temp1+" cm\n"+"���������ǣ�"+temp2+" kg\n"+"\n"+"     �������������Щ���أ��ʵ�������ʳ����";
					saveChat("�������������Щ���أ��ʵ�������ʳ����");
				}else if((i2/(i1*i1)) <= 35.0 && (i2/(i1*i1)) > 30.0){
					s="��������ǣ�"+temp1+" cm\n"+"���������ǣ�"+temp2+" kg\n"+"\n"+"     ������Ĵ�����ȷ���״̬��ע���ǿ�������";
					saveChat("������Ĵ�����ȷ���״̬��ע���ǿ�������");
				}else if((i2/(i1*i1)) <= 40.0 && (i2/(i1*i1)) > 35.0){
					s="��������ǣ�"+temp1+" cm\n"+"���������ǣ�"+temp2+" kg\n"+"\n"+"     ������Ĵ����жȷ���״̬���������ʳ�ͼ�ǿ����";
				}else if((i2/(i1*i1)) >40.0){
					s="��������ǣ�"+temp1+" cm\n"+"���������ǣ�"+temp2+" kg\n"+"\n"+"     ������Ĵ����ضȷ���״̬�������ⶨһ�ݼ��ʼƻ�������ʵʩ��";
					saveChat("������Ĵ����ضȷ���״̬�������ⶨһ�ݼ��ʼƻ�������ʵʩ��");
				}else if((i2/(i1*i1)) < 18.0){
					s="��������ǣ�"+temp1+" cm\n"+"���������ǣ�"+temp2+" kg\n"+"\n"+"     ������Ĵ���ƫ��״̬�������ǿӪ�����ʶȶ������壡";
					saveChat("������Ĵ���ƫ��״̬�������ǿӪ�����ʶȶ������壡");

				}else if(temp1==null || temp2==null || temp1=="" || temp2==""){
					s="���������ݣ�";
				}else {
					s="���������룡";
				}
				
				result.setText(s);
			}
		});
        
       
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
					+ "/baymax", "test.txt");
			try {
				// ������Ϣ��¼
				dos = new DataOutputStream(new FileOutputStream(f));
				// buf.append(str);
				dos.writeChars(str);
				dos.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
    
	/*@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
	
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent=new Intent();
		    intent.setClass(FigureCheck.this,MainActivity.class);
		    startActivity(intent);
		    FigureCheck.this.finish();
		}
		
		return super.onKeyDown(keyCode, event);
	}*/
}