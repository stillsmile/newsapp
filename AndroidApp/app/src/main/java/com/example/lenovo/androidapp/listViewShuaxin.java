package com.example.lenovo.androidapp;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.MyAdapter;


public class listViewShuaxin extends Activity {

    private int[] images;
    private ListView listview;
    private MyAdapter adapter;
    List<Map<String, Integer>> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_shuaxin);

        images = new int[]{android.R.drawable.ic_btn_speak_now,
                android.R.drawable.alert_light_frame,
                android.R.drawable.arrow_down_float,
                android.R.drawable.arrow_up_float,
                android.R.drawable.btn_star_big_off,
                android.R.drawable.btn_star_big_on,
                android.R.drawable.button_onoff_indicator_off,
                android.R.drawable.button_onoff_indicator_on,
                android.R.drawable.checkbox_off_background,
                android.R.drawable.checkbox_on_background,
                android.R.drawable.ic_btn_speak_now,
                android.R.drawable.ic_delete};

        listview = (ListView) findViewById(R.id.listview);
        al = new ArrayList<Map<String, Integer>>();

        for (int i = 0; i < 12; i++) {
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            map.put("" + i, images[i]);
            al.add(map);
        }

        adapter = new MyAdapter(this, al, R.layout.list_item, new String[]{"imageview", "tv"},
                new int[]{R.id.imageview, R.id.tv});
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int count,
                                    long arg3) {
                //  if(adapter.getCount()==count+1) //点击最后一行，listview增加数据
                //   {
                al.clear();
                HashMap<String, Integer> map = new HashMap<String, Integer>();
                map.put("" + (adapter.mItemList.size()), android.R.drawable.ic_dialog_email);
                al.add(map);
                adapter.mItemList = al;
                adapter.notifyDataSetChanged();
                Toast.makeText(listViewShuaxin.this, "正在刷新", Toast.LENGTH_SHORT).show();
                //  }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
