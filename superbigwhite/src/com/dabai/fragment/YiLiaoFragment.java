package com.dabai.fragment;


import com.dabai.database.R;
import com.dabai.healthCheck.FigureCheck;
import com.dabai.yiliao.neikeActivity;
import com.dabai.yiliao.shenjingActivity;
import com.dabai.yiliao.waikeActivity;
import com.dabai.yiliao.yiliaoActivity;
import com.dabaichat.database.ChatUIActivity;
import com.dabaichat.database.MainActivity;
import com.dabaichat.helper.MyTableHelper;
import com.dabaichat.helper.MydatabaseHelper;
import com.dabaichat.util.SearchLikeUtil;

import android.support.v4.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class YiLiaoFragment extends BaseFragment {

	private MyTableHelper tablehelper = null;
	private static MydatabaseHelper databasehelper = null;
	private MainActivity mMainActivity;
	private ImageView text, shenjing,  neike, waike;
	private int re;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contactsLayout = inflater.inflate(R.layout.yiliao_layout,
				container, false);
		mMainActivity = (MainActivity) getActivity();
		mFragmentManager = getActivity().getSupportFragmentManager();
		text = (ImageView) contactsLayout.findViewById(R.id.text_chat);
		shenjing = (ImageView) contactsLayout.findViewById(R.id.text_shenj);
		neike = (ImageView) contactsLayout.findViewById(R.id.text_neike);
		waike = (ImageView) contactsLayout.findViewById(R.id.text_waike);
		text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				GoOtherActivity(mMainActivity,ChatUIActivity.class);
			}
		});
		
		shenjing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				GoOtherActivity(mMainActivity,shenjingActivity.class);
			}
		});
		
		
		
		neike.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				GoOtherActivity(mMainActivity,neikeActivity.class);
			}
		});
		
		waike.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				GoOtherActivity(mMainActivity,waikeActivity.class);
			}
		});
		
		
		
		return contactsLayout;
	}

	public String find(String str) {
		databasehelper = new MydatabaseHelper(mMainActivity);
		// 从本地数据库中调用需要的东西
		SearchLikeUtil searchUtil = new SearchLikeUtil(
				databasehelper.getReadableDatabase());
		String data = searchUtil.searchStudent(str);
		return data;
	}

	public void GoOtherActivity(Activity a,Class<?> b){
		Intent it = new Intent(a, b);
		startActivity(it);
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.curFragmentTag = getString(R.string.contact_fg);
	}

}
