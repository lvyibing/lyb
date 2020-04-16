package com.dabaichat.adapter;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.util.List;
import com.dabai.database.R;
import com.dabaichat.enity.Message;





import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MessageAdapter extends ArrayAdapter<Message> {
	private int resourceld;

	public MessageAdapter(Context context, int textViewResourceld,
			List<Message> objects) {
		super(context, textViewResourceld, objects);
		resourceld = textViewResourceld;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub

		Message msg = getItem(position);
	//	View view;
		ViewHolder viewHolder;
		if (view == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceld, null);
			viewHolder = new ViewHolder();
			viewHolder.leftLayout = (LinearLayout) view
					.findViewById(R.id.left_layout);
			viewHolder.rightLayout = (LinearLayout) view
					.findViewById(R.id.right_layout);
			viewHolder.phoright = (ImageView) view.findViewById(R.id.headright);
			viewHolder.pholeft = (ImageView) view.findViewById(R.id.headleft);
			viewHolder.leftMsg =  (TextView) view.findViewById(R.id.left_msg);
			viewHolder.rightMsg = (TextView) view.findViewById(R.id.right_msg);
			view.setTag(viewHolder);

		} else {
		//	view = convertView;
			viewHolder = (ViewHolder) view.getTag();

		}

		if (msg.getType() == Message.TYPE_RECEIVED) {
			viewHolder.leftLayout.setVisibility(View.VISIBLE);
			viewHolder.rightLayout.setVisibility(View.GONE);
			viewHolder.leftMsg.setText(msg.getContent());
			viewHolder.leftMsg.setBackground(new BitmapDrawable(msg.getQipao()));
			viewHolder.pholeft.setImageBitmap((Bitmap) msg.getTupian());
		} else if (msg.getType() == Message.TYPE_SENT) {
			viewHolder.rightLayout.setVisibility(View.VISIBLE);
			viewHolder.leftLayout.setVisibility(View.GONE);
			viewHolder.rightMsg.setText(msg.getContent());
			viewHolder.phoright.setImageBitmap((Bitmap) msg.getTupian());
			viewHolder.rightMsg.setBackground(new BitmapDrawable(msg.getQipao()));
		}
		return view;

	}

	 class ViewHolder {
		LinearLayout leftLayout;
		LinearLayout rightLayout;
		TextView leftMsg;
		TextView rightMsg;
		ImageView pholeft;
		ImageView phoright;
	}

}
