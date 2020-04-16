package com.dabai.yiliao;
import com.dabai.database.*;
import com.dabaichat.database.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class yiliaoActivity extends Activity {
	private TextView yiliao_shenj_text;
	private Button shenjing_return;
	public static int neirong;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yiliao_shenjing);
		init();
	}

	private void init() {
		yiliao_shenj_text=(TextView) findViewById(R.id.yiliao_shenjing_text);
		yiliao_shenj_text.setText((String) this.getResources().getString(neirong));
	
	}
	
	
}
