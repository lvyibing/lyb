package com.dabai.yiliao;

import com.dabai.database.*;
import com.dabai.fragment.YiLiaoFragment;
import com.dabaichat.database.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Bundle;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

public class waikeActivity extends ExpandableListActivity {
	/**
	 * 创建一级条目容器
	 */
	List<Map<String, String>> gruops = new ArrayList<Map<String, String>>();
	/**
	 * 存放内容, 以便显示在列表中
	 */
	List<List<Map<String, String>>> childs = new ArrayList<List<Map<String, String>>>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yiliao_waike);
		// 创建二个一级条目标题
		Map<String, String> title_1 = new HashMap<String, String>();
		Map<String, String> title_2 = new HashMap<String, String>();
		Map<String, String> title_3 = new HashMap<String, String>();
		Map<String, String> title_4 = new HashMap<String, String>();
		Map<String, String> title_5 = new HashMap<String, String>();
		Map<String, String> title_6 = new HashMap<String, String>();
		Map<String, String> title_7 = new HashMap<String, String>();
		title_1.put("group", "颈椎病");
		title_2.put("group", "腰椎疾病");
		title_3.put("group", "手部骨关节损伤");
		title_4.put("group", "胸骨骨折");

		title_5.put("group", "手部烧伤");
		title_6.put("group", "冻伤");
		title_7.put("group", "急性化脓性胆管炎");

		gruops.add(title_1);
		gruops.add(title_2);
		gruops.add(title_3);
		gruops.add(title_4);
		gruops.add(title_5);
		gruops.add(title_6);
		gruops.add(title_7);
		// 创建二级条目内容
		// 内容一
		Map<String, String> title_1_content_1 = new HashMap<String, String>();
		Map<String, String> title_1_content_2 = new HashMap<String, String>();
		title_1_content_1.put("child", "临床表现");
		title_1_content_2.put("child", "治疗方法");

		List<Map<String, String>> childs_1 = new ArrayList<Map<String, String>>();
		childs_1.add(title_1_content_1);
		childs_1.add(title_1_content_2);

		// 内容二
		Map<String, String> title_2_content_1 = new HashMap<String, String>();
		Map<String, String> title_2_content_2 = new HashMap<String, String>();
		title_2_content_1.put("child", "临床表现");
		title_2_content_2.put("child", "治疗方法");
		List<Map<String, String>> childs_2 = new ArrayList<Map<String, String>>();
		childs_2.add(title_2_content_1);
		childs_2.add(title_2_content_2);

		// 内容3
		Map<String, String> title_3_content_1 = new HashMap<String, String>();
		Map<String, String> title_3_content_2 = new HashMap<String, String>();
		title_3_content_1.put("child", "临床表现");
		title_3_content_2.put("child", "治疗方法");
		List<Map<String, String>> childs_3 = new ArrayList<Map<String, String>>();
		childs_3.add(title_2_content_1);
		childs_3.add(title_2_content_2);

		// 内容4
		Map<String, String> title_4_content_1 = new HashMap<String, String>();
		Map<String, String> title_4_content_2 = new HashMap<String, String>();
		title_4_content_1.put("child", "临床表现");
		title_4_content_2.put("child", "治疗方法");
		List<Map<String, String>> childs_4 = new ArrayList<Map<String, String>>();
		childs_4.add(title_2_content_1);
		childs_4.add(title_2_content_2);

		// 内容5
		Map<String, String> title_5_content_1 = new HashMap<String, String>();
		Map<String, String> title_5_content_2 = new HashMap<String, String>();
		title_5_content_1.put("child", "临床表现");
		title_5_content_2.put("child", "治疗方法");
		List<Map<String, String>> childs_5 = new ArrayList<Map<String, String>>();
		childs_5.add(title_2_content_1);
		childs_5.add(title_2_content_2);

		// 内容6
		Map<String, String> title_6_content_1 = new HashMap<String, String>();
		Map<String, String> title_6_content_2 = new HashMap<String, String>();
		title_6_content_1.put("child", "临床表现");
		title_6_content_2.put("child", "治疗方法");
		List<Map<String, String>> childs_6 = new ArrayList<Map<String, String>>();
		childs_6.add(title_2_content_1);
		childs_6.add(title_2_content_2);
		// 内容7
		Map<String, String> title_7_content_1 = new HashMap<String, String>();
		Map<String, String> title_7_content_2 = new HashMap<String, String>();
		title_7_content_1.put("child", "临床表现");
		title_7_content_2.put("child", "治疗方法");
		List<Map<String, String>> childs_7 = new ArrayList<Map<String, String>>();
		childs_7.add(title_2_content_1);
		childs_7.add(title_2_content_2);

		childs.add(childs_1);
		childs.add(childs_2);
		childs.add(childs_3);
		childs.add(childs_4);
		childs.add(childs_5);
		childs.add(childs_6);
		childs.add(childs_7);

		/**
		 * 创建ExpandableList的Adapter容器 参数: 1.上下文 2.一级集合 3.一级样式文件 4. 一级条目键值
		 * 5.一级显示控件名 6. 二级集合 7. 二级样式 8.二级条目键值 9.二级显示控件名
		 * 
		 */
		SimpleExpandableListAdapter sela = new SimpleExpandableListAdapter(
				this, gruops, R.drawable.groups, new String[] { "group" },
				new int[] { R.id.textGroup }, childs, R.drawable.childs,
				new String[] { "child" }, new int[] { R.id.textChild });
		// 加入列表
		setListAdapter(sela);
	}

	/**
	 * 列表内容按下
	 */
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		/*
		 * Toast.makeText( shenjingActivity.this, "您选择了" +
		 * gruops.get(groupPosition).toString() + "子编号" +
		 * childs.get(groupPosition).get(childPosition) .toString(),
		 * Toast.LENGTH_SHORT).show();
		 */
		if (childs.get(groupPosition).get(childPosition).toString()
				.equals("{child=临床表现}")) {
			if (gruops.get(groupPosition).toString().equals("{group=颈椎病}")) {

				findlcAndzl(R.string.lc_jizhuib);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=腰椎疾病}")) {

				findlcAndzl(R.string.lc_yaozhuijib);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=手部骨关节损伤}")) {

				findlcAndzl(R.string.lc_shoubugjss);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=胸骨骨折}")) {

				findlcAndzl(R.string.lc_xiongggz);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=手部烧伤}")) {

				findlcAndzl(R.string.lc_shoubuss);
			}else if (gruops.get(groupPosition).toString()
					.equals("{group=冻伤}")) {

				findlcAndzl(R.string.lc_dongs);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=急性化脓性胆管炎}")) {

				findlcAndzl(R.string.lc_jixinghndgy);
			}
		} else if (childs.get(groupPosition).get(childPosition).toString()
				.equals("{child=治疗方法}")) {
			if (gruops.get(groupPosition).toString().equals("{group=颈椎病}")) {

				findlcAndzl(R.string.zl_jizhuib);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=腰椎疾病}")) {

				findlcAndzl(R.string.zl_yaozhuijib);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=手部骨关节损伤}")) {

				findlcAndzl(R.string.zl_shoubugjss);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=胸骨骨折}")) {

				findlcAndzl(R.string.zl_xiongggz);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=手部烧伤}")) {

				findlcAndzl(R.string.zl_shoubuss);
			}else if (gruops.get(groupPosition).toString()
					.equals("{group=冻伤}")) {

				findlcAndzl(R.string.zl_dongs);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=急性化脓性胆管炎}")) {

				findlcAndzl(R.string.zl_jixinghndgy);
			}
		}
		return super.onChildClick(parent, v, groupPosition, childPosition, id);
	}

	public void findlcAndzl(int i) {
		yiliaoActivity.neirong = i;
		Intent it = new Intent(waikeActivity.this, yiliaoActivity.class);
		startActivity(it);
		//waikeActivity.this.finish();
	}

	/**
	 * 二级标题按下
	 */
	@Override
	public boolean setSelectedChild(int groupPosition, int childPosition,
			boolean shouldExpandGroup) {

		return super.setSelectedChild(groupPosition, childPosition,
				shouldExpandGroup);
	}

	/**
	 * 一级标题按下
	 */
	@Override
	public void setSelectedGroup(int groupPosition) {
		super.setSelectedGroup(groupPosition);
	}

	/*public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.setClass(waikeActivity.this, MainActivity.class);
			startActivity(intent);
			waikeActivity.this.finish();
		}

		return super.onKeyDown(keyCode, event);
	}*/
}
