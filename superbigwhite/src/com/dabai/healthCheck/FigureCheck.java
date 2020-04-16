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
					 s="请输入数据！！";
					 result.setText(s);
					 return;
				}
				else{ i1=(float)Float.parseFloat(temp1);
				      i2=(float)Float.parseFloat(temp2);
				      if(i1>320 || i2>300){
							 result.setText("超人，请飞回地球");
							 return;
				      }
				}
			    
				i1=i1/100;
				if((i2/(i1*i1)) <= 25.0 && (i2/(i1*i1)) > 18.0){
					s="您的身高是："+temp1+" cm\n"+"您的体重是："+temp2+" kg\n"+"\n"+"     您的身材处于完美区间，请继续保持!";
					saveChat("您的身材处于完美区间，请继续保持!");
				}else if((i2/(i1*i1)) <= 30.0 && (i2/(i1*i1)) > 25.0){
					s="您的身高是："+temp1+" cm\n"+"您的体重是："+temp2+" kg\n"+"\n"+"     您的身材稍稍有些超重，适当控制饮食即可";
					saveChat("您的身材稍稍有些超重，适当控制饮食即可");
				}else if((i2/(i1*i1)) <= 35.0 && (i2/(i1*i1)) > 30.0){
					s="您的身高是："+temp1+" cm\n"+"您的体重是："+temp2+" kg\n"+"\n"+"     您的身材处于轻度肥胖状态，注意加强身体锻炼";
					saveChat("您的身材处于轻度肥胖状态，注意加强身体锻炼");
				}else if((i2/(i1*i1)) <= 40.0 && (i2/(i1*i1)) > 35.0){
					s="您的身高是："+temp1+" cm\n"+"您的体重是："+temp2+" kg\n"+"\n"+"     您的身材处于中度肥胖状态，请控制饮食和加强锻炼";
				}else if((i2/(i1*i1)) >40.0){
					s="您的身高是："+temp1+" cm\n"+"您的体重是："+temp2+" kg\n"+"\n"+"     您的身材处于重度肥胖状态，立刻拟定一份减肥计划，马上实施！";
					saveChat("您的身材处于重度肥胖状态，立刻拟定一份减肥计划，马上实施！");
				}else if((i2/(i1*i1)) < 18.0){
					s="您的身高是："+temp1+" cm\n"+"您的体重是："+temp2+" kg\n"+"\n"+"     您的身材处于偏瘦状态，建议加强营养，适度锻炼身体！";
					saveChat("您的身材处于偏瘦状态，建议加强营养，适度锻炼身体！");

				}else if(temp1==null || temp2==null || temp1=="" || temp2==""){
					s="请输入数据！";
				}else {
					s="请重新输入！";
				}
				
				result.setText(s);
			}
		});
        
       
    }

    
    
	public void createFile() {
		// 建立baymax文件夹
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
		// 创建文件保存聊天内容
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {

			File f = new File(Environment.getExternalStorageDirectory()
					+ "/baymax", "test.txt");
			try {
				// 保存消息记录
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