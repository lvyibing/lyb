package com.dabai.healthCheck;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.dabai.database.R;
import com.dabaichat.database.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Report extends Activity {
	
	private View textView;
	private FigureCheck figureCheck;
	private static DataOutputStream dos = null;
	private static DataInputStream dis = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        
     
        textView=(View)findViewById(R.id.textView);
        String str=getChat();
        if(str.equals("������Ĵ����������䣬���������!"))
        {    textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.a1));}
        else if(str.equals("�������������Щ���أ��ʵ�������ʳ����")){
        	
        	textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.a2));
        	
        }
        else if(str.equals("������Ĵ�����ȷ���״̬��ע���ǿ�������")){
        	
        	textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.a3));
       	
       }
        else if(str.equals("������Ĵ����ضȷ���״̬�������ⶨһ�ݼ��ʼƻ�������ʵʩ��")){
        	
        	textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.a4));
          	
          }
        else if(str.equals("������Ĵ���ƫ��״̬�������ǿӪ�����ʶȶ������壡")){
        	
        	textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.a5));
         	
         }
        else if(str.equals("")){
        	textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.aa));
        	
        	
        }
       
    }
    
    
    public String getChat() {
		// ��ȡ��������
		String chat = "";
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			File f = new File(Environment.getExternalStorageDirectory()
					+ "/baymax", "test.txt");
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
    
  
}