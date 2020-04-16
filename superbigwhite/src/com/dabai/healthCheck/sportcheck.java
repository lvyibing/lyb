package com.dabai.healthCheck;

import com.dabai.database.R;
import com.dabai.healthCheck.food.FoodCheck6;
import com.dabai.healthCheck.sport.sportCheck1;
import com.dabai.healthCheck.sport.sportCheck2;
import com.dabai.healthCheck.sport.sportCheck3;
import com.dabai.healthCheck.sport.sportCheck4;
import com.dabai.healthCheck.sport.sportCheck5;
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

public class sportcheck extends Activity {
	
	private ImageButton button01;
	private RadioGroup radioGroup;
	private RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5,radioButton6;
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_helloyou);
        
        button01=(ImageButton)findViewById(R.id.button01sport);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroupsport);
        radioButton1=(RadioButton)findViewById(R.id.radioButton1sport);
        radioButton2=(RadioButton)findViewById(R.id.radioButton2sport);
        radioButton3=(RadioButton)findViewById(R.id.radioButton3sport);
        radioButton4=(RadioButton)findViewById(R.id.radioButton4sport);
        radioButton5=(RadioButton)findViewById(R.id.radioButton5sport);
   
        
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    		
    		@Override
    		public void onCheckedChanged(RadioGroup group, int checkedId) {
    			// TODO Auto-generated method stub
    			if(checkedId==radioButton1.getId()){   				
    				
    				
    				 button01.setOnClickListener(new View.OnClickListener() {
    						
    						@Override
    						public void onClick(View v) {
    							// TODO Auto-generated method stub
    				
    			                    Intent intent=new Intent();
    							    intent.setClass(sportcheck.this,sportCheck1.class);
    							    startActivity(intent);
    							    sportcheck.this.finish();
    							
    						}
    					});
    				
    				
    			}else if(checkedId==radioButton2.getId()){
    				
    			
    				
   				 button01.setOnClickListener(new View.OnClickListener() {
   						
   						@Override
   						public void onClick(View v) {
   							// TODO Auto-generated method stub
   				
   			                    Intent intent=new Intent();
   							    intent.setClass(sportcheck.this,sportCheck2.class);
   							    startActivity(intent);
   							    sportcheck.this.finish();
   							
   						}
   					});
   				
    				
    			}else if(checkedId==radioButton3.getId()){
    				
    			
    				
   				 button01.setOnClickListener(new View.OnClickListener() {
   						
   						@Override
   						public void onClick(View v) {
   							// TODO Auto-generated method stub
   				
   			                    Intent intent=new Intent();
   							    intent.setClass(sportcheck.this,sportCheck3.class);
   							    startActivity(intent);
   							    sportcheck.this.finish();
   							
   						}
   					});
   				
    				
    			}else if(checkedId==radioButton4.getId()){
    				
    				
    				
   				 button01.setOnClickListener(new View.OnClickListener() {
   						
   						@Override
   						public void onClick(View v) {
   							// TODO Auto-generated method stub
   				
   			                    Intent intent=new Intent();
   							    intent.setClass(sportcheck.this,sportCheck4.class);
   							    startActivity(intent);
   							    sportcheck.this.finish();
   							
   						}
   					});
   				
    				
    			}else if(checkedId==radioButton5.getId()){
    				
    			
    				
   				 button01.setOnClickListener(new View.OnClickListener() {
   						
   						@Override
   						public void onClick(View v) {
   							// TODO Auto-generated method stub
   				
   			                    Intent intent=new Intent();
   							    intent.setClass(sportcheck.this,sportCheck5.class);
   							    startActivity(intent);
   							    sportcheck.this.finish();
   							
   						}
   					});
   				
    				
    			}else if(checkedId==radioButton6.getId()){
    				
    			
    				
   				 button01.setOnClickListener(new View.OnClickListener() {
   						
   						@Override
   						public void onClick(View v) {
   							// TODO Auto-generated method stub
   				
   			                    Intent intent=new Intent();
   							    intent.setClass(sportcheck.this,FoodCheck6.class);
   							    startActivity(intent);
   							    sportcheck.this.finish();
   							
   						}
   					});
   				
    				
    			}else {
    				DisplayToast("«Î—°‘Ò£°");
    			}
    		}
    	});
        
          
       
    }
    
    public void DisplayToast(String str){
    	Toast toast=Toast.makeText(this, str, Toast.LENGTH_LONG);
    	toast.show();
    }

}