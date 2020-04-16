package com.dabaichat.database;

import com.dabai.fragment.TestFragment;


import com.dabai.database.R;
import com.dabai.fragment.BaseFragment;
import com.dabai.fragment.FindFragment;
import com.dabai.fragment.YiLiaoFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.graphics.Color;

/**
 * 项目的主Activity，所有的Fragment都嵌入在这里�?
 * 
 * @author 
 */
public class MainActivity extends FragmentActivity implements OnClickListener {

	private static final String TAG = "MainActivity";

	
	public  TestFragment messageFragment;

	private YiLiaoFragment contactsFragment;
	
	private FindFragment newsFragment;
	

	private View messageLayout ,contactsLayout, newsLayout ,settingLayout;

	private ImageView messageImage,contactsImage,newsImage,settingImage;
	
	private TextView messageText,contactsText,settingText,newsText;

	/**
	 * 用于对Fragment进行管理
	 */
	private FragmentManager fragmentManager;
	private FragmentTransaction mFragmentTransaction;

	public static  String curFragmentTag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initViews();
		fragmentManager = getSupportFragmentManager();
		setCurrentFragment();
	}

	/**
	 * 在这里获取到每个�?��用到的控件的实例，并给它们设置好必要的点击事件�?
	 */
	private void initViews() {
		messageLayout = findViewById(R.id.message_layout);
		contactsLayout = findViewById(R.id.contacts_layout);
		newsLayout = findViewById(R.id.news_layout);
		messageImage = (ImageView) findViewById(R.id.message_image);
		contactsImage = (ImageView) findViewById(R.id.contacts_image);
		newsImage = (ImageView) findViewById(R.id.news_image);
		messageText = (TextView) findViewById(R.id.message_text);
		contactsText = (TextView) findViewById(R.id.contacts_text);
		newsText = (TextView) findViewById(R.id.news_text);
		messageLayout.setOnClickListener(this);
		contactsLayout.setOnClickListener(this);
		newsLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.message_layout:
			setTabSelection(getString(R.string.message_fg));
			break;
		case R.id.contacts_layout:
			setTabSelection(getString(R.string.contact_fg));
			break;
		case R.id.news_layout:
			setTabSelection(getString(R.string.news_fg));
			break;
		
		default:
			break;
		}
	}

	private void setCurrentFragment(){
		clearSelection();
		mFragmentTransaction = fragmentManager.beginTransaction();
		messageImage.setImageResource(R.drawable.message_selected);
		messageText.setTextColor(Color.WHITE);
		if (messageFragment == null) {
			messageFragment = new TestFragment();
			mFragmentTransaction.add(R.id.content, messageFragment,getString(R.string.message_fg));
			commitTransactions();
		}
		curFragmentTag = getString(R.string.message_fg);
	}
	
	
	/**
	 * 根据传入的tag参数来设置�?中的tab页�?
	 * 
	 * @param tag
	 *          
	 */
	public  void setTabSelection(String tag) {
		clearSelection();
		mFragmentTransaction = fragmentManager.beginTransaction();
		 if(TextUtils.equals(tag, getString(R.string.message_fg))){
			messageImage.setImageResource(R.drawable.message_selected);
			messageText.setTextColor(Color.WHITE);
			
			if (messageFragment == null) {
				messageFragment = new TestFragment();
			} 
			
		}else if(TextUtils.equals(tag, getString(R.string.contact_fg))){
			contactsImage.setImageResource(R.drawable.contacts_selected);
			contactsText.setTextColor(Color.WHITE);
			if (contactsFragment == null) {
				contactsFragment = new YiLiaoFragment();
			} 
			
		}else if(TextUtils.equals(tag, getString(R.string.news_fg))){
			newsImage.setImageResource(R.drawable.news_selected);
			newsText.setTextColor(Color.WHITE);
			if (newsFragment == null) {
				newsFragment = new FindFragment();
			}
			
		}
		 switchFragment(tag);
		 
	}

	public  void switchFragment(String tag){
		if(TextUtils.equals(tag, curFragmentTag)){
			return;
		}
		
		if(curFragmentTag != null){
			detachFragment(getFragment(curFragmentTag));
			
		}
		attachFragment(R.id.content,getFragment(tag),tag);
		curFragmentTag = tag;
		commitTransactions();
	} 
	
	private void detachFragment(Fragment f){
		
		if(f != null && !f.isDetached()){
			ensureTransaction();
			mFragmentTransaction.detach(f);
		}
	}
	
	private FragmentTransaction ensureTransaction( ){
		if(mFragmentTransaction == null){
			mFragmentTransaction = fragmentManager.beginTransaction();
			mFragmentTransaction
			.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			
		}
		return mFragmentTransaction;
		
	}
	
	private void attachFragment(int layout, Fragment f,String tag){
		if(f != null){
			if(f.isDetached()){
				ensureTransaction();
				mFragmentTransaction.attach(f);
				
			}else if(!f.isAdded()){
				ensureTransaction();
				mFragmentTransaction.add(layout,f, tag);
			}
		}
	}
	
	private void commitTransactions(){
		if (mFragmentTransaction != null && !mFragmentTransaction.isEmpty()) {
			mFragmentTransaction.commit();
			mFragmentTransaction = null;
		}
	}
	private Fragment getFragment(String tag){
		
		Fragment f = fragmentManager.findFragmentByTag(tag);
		
		if(f == null){
			
			f = BaseFragment.newInstance(getApplicationContext(), tag);
		}
		return f;
		
	}
	
	
	private void clearSelection() {
		messageImage.setImageResource(R.drawable.message_unselected);
		messageText.setTextColor(Color.parseColor("#82858b"));
		contactsImage.setImageResource(R.drawable.contacts_unselected);
		contactsText.setTextColor(Color.parseColor("#82858b"));
		newsImage.setImageResource(R.drawable.news_unselected);
		newsText.setTextColor(Color.parseColor("#82858b"));
	}

}
