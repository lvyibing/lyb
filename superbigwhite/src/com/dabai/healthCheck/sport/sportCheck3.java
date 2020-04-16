package com.dabai.healthCheck.sport;


import com.dabai.database.R;
import com.dabai.healthCheck.FoodCheck;
import com.dabai.healthCheck.sportcheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class sportCheck3 extends Activity {
	
	private ImageView button1=null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello3);
        
 button1=(ImageView)findViewById(R.id.backsport3);
        
        button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
			    intent.setClass(sportCheck3.this,sportcheck.class);
			    startActivity(intent);
			    sportCheck3.this.finish();
			}
		});
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
	
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent=new Intent();
		    intent.setClass(sportCheck3.this,sportcheck.class);
		    startActivity(intent);
		    sportCheck3.this.finish();
		}
		
		return super.onKeyDown(keyCode, event);
	}
}