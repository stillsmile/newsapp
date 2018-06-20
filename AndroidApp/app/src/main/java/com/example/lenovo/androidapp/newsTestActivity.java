package com.example.lenovo.androidapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class newsTestActivity extends Activity {

	private ListView lview;
	private List<Map<String, Object>> data_list;
	private SimpleAdapter sim_adapter;
	// ICON
	private int[] icon = { R.drawable.icon_01, R.drawable.icon_02,
			R.drawable.icon_03, R.drawable.icon_04, R.drawable.icon_05,
			R.drawable.icon_06, R.drawable.icon_07, R.drawable.icon_08,
			R.drawable.icon_09, R.drawable.icon_10, R.drawable.icon_11,
			R.drawable.icon_12, R.drawable.icon_13, R.drawable.icon_14 };
	private String[] iconName = { "ͨ茶叶", "汉堡", "肉", "香肠", "披萨", "虾", "水果", "鱼",
			"面包", "蟹", "鸡腿", "根茎蔬菜", "蛋糕", "酒" };

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newtest);

		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(android.R.id.content, new fragment1());
		transaction.commit();

	}
}
