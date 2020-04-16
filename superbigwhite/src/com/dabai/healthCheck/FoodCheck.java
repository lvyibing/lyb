package com.dabai.healthCheck;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.dabai.database.R;
import com.dabai.healthCheck.food.FoodCheck1;
import com.dabai.healthCheck.food.FoodCheck2;
import com.dabai.healthCheck.food.FoodCheck3;
import com.dabai.healthCheck.food.FoodCheck4;
import com.dabai.healthCheck.food.FoodCheck5;
import com.dabai.healthCheck.food.FoodCheck6;
import com.dabaichat.database.MainActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FoodCheck extends Activity {
	
	private ImageButton button01;
	private RadioGroup radioGroup;
	private RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5,radioButton6;
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
        
        button01=(ImageButton)findViewById(R.id.button01);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        radioButton1=(RadioButton)findViewById(R.id.radioButton1);
        radioButton2=(RadioButton)findViewById(R.id.radioButton2);
        radioButton3=(RadioButton)findViewById(R.id.radioButton3);
        radioButton4=(RadioButton)findViewById(R.id.radioButton4);
        radioButton5=(RadioButton)findViewById(R.id.radioButton5);
        radioButton6=(RadioButton)findViewById(R.id.radioButton6);
        
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    		
    		@Override
    		public void onCheckedChanged(RadioGroup group, int checkedId) {
    			// TODO Auto-generated method stub
    			if(checkedId==radioButton1.getId()){   				
    				DisplayToast("多吃一些蛋白质类食物。详见“医师”鉴定");
    				
    				 button01.setOnClickListener(new View.OnClickListener() {
    						
    						@Override
    						public void onClick(View v) {
    							// TODO Auto-generated method stub
    			
    			                    Intent intent=new Intent();
    							    intent.setClass(FoodCheck.this,FoodCheck1.class);
    							    startActivity(intent);
    							    FoodCheck.this.finish();
    							
    						}
    					});
    				
    				
    			}else if(checkedId==radioButton2.getId()){
    				
    				DisplayToast("荤素搭配，比较均衡。详见“医师”鉴定");
    				
   				 button01.setOnClickListener(new View.OnClickListener() {
   						
   						@Override
   						public void onClick(View v) {
   							// TODO Auto-generated method stub
   				
   			                    Intent intent=new Intent();
   							    intent.setClass(FoodCheck.this,FoodCheck2.class);
   							    startActivity(intent);
   							    FoodCheck.this.finish();
   							
   						}
   					});
   				
    				
    			}else if(checkedId==radioButton3.getId()){
    				
    				DisplayToast("多吃富含维生素的蔬菜。详见“医师”鉴定");
    				
   				 button01.setOnClickListener(new View.OnClickListener() {
   						
   						@Override
   						public void onClick(View v) {
   							// TODO Auto-generated method stub
   				
   			                    Intent intent=new Intent();
   							    intent.setClass(FoodCheck.this,FoodCheck3.class);
   							    startActivity(intent);
   							    FoodCheck.this.finish();
   							
   						}
   					});
   				
    				
    			}else if(checkedId==radioButton4.getId()){
    				
    				DisplayToast("管好自己的胃。详见“医师”鉴定");
    				
   				 button01.setOnClickListener(new View.OnClickListener() {
   						
   						@Override
   						public void onClick(View v) {
   							// TODO Auto-generated method stub
   				
   			                    Intent intent=new Intent();
   							    intent.setClass(FoodCheck.this,FoodCheck4.class);
   							    startActivity(intent);
   							    FoodCheck.this.finish();
   							
   						}
   					});
   				
    				
    			}else if(checkedId==radioButton5.getId()){
    				
    				DisplayToast("快成仙了！详见“医师”鉴定");
    				
   				 button01.setOnClickListener(new View.OnClickListener() {
   						
   						@Override
   						public void onClick(View v) {
   							// TODO Auto-generated method stub
   				
   			                    Intent intent=new Intent();
   							    intent.setClass(FoodCheck.this,FoodCheck5.class);
   							    startActivity(intent);
   							    FoodCheck.this.finish();
   							
   						}
   					});
   				
    				
    			}else if(checkedId==radioButton6.getId()){
    				
    				DisplayToast("可考虑申请吉尼斯纪录。详见“医师”鉴定");
    				
   				 button01.setOnClickListener(new View.OnClickListener() {
   						
   						@Override
   						public void onClick(View v) {
   							// TODO Auto-generated method stub
   				
   			                    Intent intent=new Intent();
   							    intent.setClass(FoodCheck.this,FoodCheck6.class);
   							    startActivity(intent);
   							    FoodCheck.this.finish();
   							
   						}
   					});
   				
    				
    			}else {
    				DisplayToast("请选择！");
    			}
    		}
    	});
        
          
       
    }
    
    public void DisplayToast(String str){
    	Toast toast=Toast.makeText(this, str, Toast.LENGTH_LONG);
    	toast.show();
    }
    
    

}