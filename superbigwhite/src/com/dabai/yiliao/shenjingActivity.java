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

public class shenjingActivity extends ExpandableListActivity {
	/**
	 * ����һ����Ŀ����
	 */
	List<Map<String, String>> gruops = new ArrayList<Map<String, String>>();
	/**
	 * �������, �Ա���ʾ���б���
	 */
	List<List<Map<String, String>>> childs = new ArrayList<List<Map<String, String>>>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yiliao_shenjingke);
		// ��������һ����Ŀ����
		Map<String, String> title_1 = new HashMap<String, String>();
		Map<String, String> title_2 = new HashMap<String, String>();
		Map<String, String> title_3 = new HashMap<String, String>();
		Map<String, String> title_4 = new HashMap<String, String>();
		Map<String, String> title_5 = new HashMap<String, String>();
		title_1.put("group", "����֢");
		title_2.put("group", "��˥��");
		title_3.put("group", "ǿ��֢");
		title_4.put("group", "�ر����˸�");
		title_5.put("group", "����֢");
		gruops.add(title_1);
		gruops.add(title_2);
		gruops.add(title_3);
		gruops.add(title_4);
		gruops.add(title_5);

		// ����������Ŀ����
		// ����һ
		Map<String, String> title_1_content_1 = new HashMap<String, String>();
		Map<String, String> title_1_content_2 = new HashMap<String, String>();
		title_1_content_1.put("child", "�ٴ�����");
		title_1_content_2.put("child", "���Ʒ���");

		List<Map<String, String>> childs_1 = new ArrayList<Map<String, String>>();
		childs_1.add(title_1_content_1);
		childs_1.add(title_1_content_2);

		// ���ݶ�
		Map<String, String> title_2_content_1 = new HashMap<String, String>();
		Map<String, String> title_2_content_2 = new HashMap<String, String>();
		title_2_content_1.put("child", "�ٴ�����");
		title_2_content_2.put("child", "���Ʒ���");
		List<Map<String, String>> childs_2 = new ArrayList<Map<String, String>>();
		childs_2.add(title_2_content_1);
		childs_2.add(title_2_content_2);

		// ����3
		Map<String, String> title_3_content_1 = new HashMap<String, String>();
		Map<String, String> title_3_content_2 = new HashMap<String, String>();
		title_3_content_1.put("child", "�ٴ�����");
		title_3_content_2.put("child", "���Ʒ���");
		List<Map<String, String>> childs_3 = new ArrayList<Map<String, String>>();
		childs_3.add(title_2_content_1);
		childs_3.add(title_2_content_2);

		// ����4
		Map<String, String> title_4_content_1 = new HashMap<String, String>();
		Map<String, String> title_4_content_2 = new HashMap<String, String>();
		title_4_content_1.put("child", "�ٴ�����");
		title_4_content_2.put("child", "���Ʒ���");
		List<Map<String, String>> childs_4 = new ArrayList<Map<String, String>>();
		childs_4.add(title_2_content_1);
		childs_4.add(title_2_content_2);

		// ����5
		Map<String, String> title_5_content_1 = new HashMap<String, String>();
		Map<String, String> title_5_content_2 = new HashMap<String, String>();
		title_5_content_1.put("child", "�ٴ�����");
		title_5_content_2.put("child", "���Ʒ���");
		List<Map<String, String>> childs_5 = new ArrayList<Map<String, String>>();
		childs_5.add(title_2_content_1);
		childs_5.add(title_2_content_2);

		childs.add(childs_1);
		childs.add(childs_2);
		childs.add(childs_3);
		childs.add(childs_4);
		childs.add(childs_5);

		/**
		 * ����ExpandableList��Adapter���� ����: 1.������ 2.һ������ 3.һ����ʽ�ļ� 4. һ����Ŀ��ֵ
		 * 5.һ����ʾ�ؼ��� 6. �������� 7. ������ʽ 8.������Ŀ��ֵ 9.������ʾ�ؼ���
		 * 
		 */
		SimpleExpandableListAdapter sela = new SimpleExpandableListAdapter(
				this, gruops, R.drawable.groups, new String[] { "group" },
				new int[] { R.id.textGroup }, childs, R.drawable.childs,
				new String[] { "child" }, new int[] { R.id.textChild });
		// �����б�
		setListAdapter(sela);
	}

	/**
	 * �б����ݰ���
	 */
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		/*
		 * Toast.makeText( shenjingActivity.this, "��ѡ����" +
		 * gruops.get(groupPosition).toString() + "�ӱ��" +
		 * childs.get(groupPosition).get(childPosition) .toString(),
		 * Toast.LENGTH_SHORT).show();
		 */
		if (childs.get(groupPosition).get(childPosition).toString()
				.equals("{child=�ٴ�����}")) {
			if (gruops.get(groupPosition).toString().equals("{group=����֢}")) {

				findlcAndzl(R.string.lc_yiyu);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=��˥��}")) {

				findlcAndzl(R.string.lc_shenjingshuairuo);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=ǿ��֢}")) {

				findlcAndzl(R.string.lc_qiangpo);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=�ر����˸�}")) {

				findlcAndzl(R.string.lc_huibi);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=����֢}")) {

				findlcAndzl(R.string.lc_jiaolv);
			}
		} else if (childs.get(groupPosition).get(childPosition).toString()
				.equals("{child=���Ʒ���}")) {
			if (gruops.get(groupPosition).toString().equals("{group=����֢}")) {

				findlcAndzl(R.string.zl_yiyu);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=��˥��}")) {

				findlcAndzl(R.string.zl_shenjingshuairuo);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=ǿ��֢}")) {

				findlcAndzl(R.string.zl_qiangpo);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=�ر����˸�}")) {

				findlcAndzl(R.string.zl_huibi);
			} else if (gruops.get(groupPosition).toString()
					.equals("{group=����֢}")) {

				findlcAndzl(R.string.zl_jiaolv);
			}
		}
		return super.onChildClick(parent, v, groupPosition, childPosition, id);
	}

	public void findlcAndzl(int i) {
		yiliaoActivity.neirong = i;
		Intent it = new Intent(shenjingActivity.this, yiliaoActivity.class);
		startActivity(it);
		//shenjingActivity.this.finish();
	}

	/**
	 * �������ⰴ��
	 */
	@Override
	public boolean setSelectedChild(int groupPosition, int childPosition,
			boolean shouldExpandGroup) {

		return super.setSelectedChild(groupPosition, childPosition,
				shouldExpandGroup);
	}

	/**
	 * һ�����ⰴ��
	 */
	@Override
	public void setSelectedGroup(int groupPosition) {
		super.setSelectedGroup(groupPosition);
	}
	/*public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
	
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent=new Intent();
		    intent.setClass(shenjingActivity.this,MainActivity.class);
		    startActivity(intent);
		    shenjingActivity.this.finish();
		}
		
		return super.onKeyDown(keyCode, event);
	}*/
}
