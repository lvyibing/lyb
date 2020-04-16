 package com.dabai.fragment;
import com.dabai.database.R;
import com.dabai.find.aboutActivity;
import com.dabai.healthCheck.FigureCheck;


import com.dabaichat.database.MainActivity;
import com.example.dabai.GameMainActivity;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FindFragment extends BaseFragment {

	private MainActivity mMainActivity;


	private ImageView   text,about,exit,find;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View newsLayout = inflater.inflate(R.layout.find_layout, container,
				false);
		
		mMainActivity = (MainActivity) getActivity();
		mFragmentManager = getActivity().getSupportFragmentManager();
		text=(ImageView) newsLayout.findViewById(R.id.text_game);
		about= (ImageView) newsLayout.findViewById(R.id.about);
		find=(ImageView) newsLayout.findViewById(R.id.findmore);
		exit= (ImageView) newsLayout.findViewById(R.id.exit);
		text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent it = new Intent(mMainActivity, GameMainActivity.class);
				startActivity(it);
			//	mMainActivity.finish();
			}
		});
		
		about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent it = new Intent(mMainActivity, aboutActivity.class);
				startActivity(it);
				//mMainActivity.finish();
			}
		});
		
		find.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent= new Intent(); 
				intent.setAction("android.intent.action.VIEW"); 
				Uri content_url = Uri.parse("http://www.baidu.com");
				//Uri content_url = Uri.parse("http://www.baidu.com"); 
				intent.setData(content_url); 
				startActivity(intent);
			}
		});
		
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				System.exit(0);
			}
		});
		return newsLayout;
	}
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		MainActivity.curFragmentTag = getString(R.string.news_fg);
	}
	

}
