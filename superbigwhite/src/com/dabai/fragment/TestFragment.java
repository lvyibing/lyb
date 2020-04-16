package com.dabai.fragment;



import com.dabai.database.R;

import com.dabai.healthCheck.FigureCheck;
import com.dabai.healthCheck.FoodCheck;
import com.dabai.healthCheck.Report;
import com.dabai.healthCheck.sportcheck;
import com.dabaichat.database.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class TestFragment extends BaseFragment {

	private static final String TAG = "MessageFragment";
	private ImageView btn1, btn2, btn3, btn4;
	private MainActivity mMainActivity;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.test_layout,
				container, false);

		btn1 = (ImageView) messageLayout.findViewById(R.id.btn1);
		btn2 = (ImageView) messageLayout.findViewById(R.id.btn2);
		btn3 = (ImageView) messageLayout.findViewById(R.id.btn3);
		btn4 = (ImageView) messageLayout.findViewById(R.id.btn4);
		mMainActivity = (MainActivity) getActivity();
		mFragmentManager = getActivity().getSupportFragmentManager();
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(mMainActivity, FigureCheck.class);
				startActivity(it);
				//mMainActivity.finish();
			}
		});
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(mMainActivity, FoodCheck.class);
				startActivity(it);
				//mMainActivity.finish();
			}
		});
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(mMainActivity, sportcheck.class);
				startActivity(it);
				//mMainActivity.finish();
			}
		});
		btn4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(mMainActivity, Report.class);
				startActivity(it);
				//mMainActivity.finish();
			}
		});
		

		return messageLayout;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MainActivity.curFragmentTag = getString(R.string.message_fg);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();

	}

}
