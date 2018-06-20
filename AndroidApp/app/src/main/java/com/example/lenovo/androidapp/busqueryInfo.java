package com.example.lenovo.androidapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import Adapter.collectionDataBAdapter;
import bean.CollectionDataBean;
import dao.CollectionDataHelper;

public class busqueryInfo extends AppCompatActivity{


    private Context mContext;
    private Intent intent;
    private String busNum;
    private int whichShow =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busquerypage);
        mContext =this;
//        intent = new Intent();
//        intent.setClass(this,busrountingshow.class);
//        startActivity(intent);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setIcon(R.drawable.bus);//设置ActionBar的icon图标
        supportActionBar.setTitle("线路查询");//设置ActionBar的标题
        supportActionBar.setHomeButtonEnabled(false);//主键按钮能否可点击
        supportActionBar.setDisplayHomeAsUpEnabled(true);//显示返回图标



        Button bt_search = (Button) findViewById(R.id.bt_search);

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_busNum = (EditText) findViewById(R.id.et_busNum);
                busNum = et_busNum.getText().toString();

                intent=new Intent(mContext,busrountingshow.class);
                intent.putExtra("busNum",busNum);
                startActivity(intent);

            }
        });

        //通过Editor对象操作数据库
//        SharedPreferences sharedPreferences = getSharedPreferences("collectionData",0);
//        SharedPreferences.Editor edit = sharedPreferences.edit();
        CollectionDataHelper collectionDataHelper = new CollectionDataHelper(mContext);
        SQLiteDatabase db = collectionDataHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from CollectionData", null);//查询获取数据
        ArrayList<CollectionDataBean> CollectionDatalist = new ArrayList<CollectionDataBean>();
        if(cursor != null && cursor.getCount() > 0){
//            while(cursor.moveToNext()){
//                CollectionDataBean collection = new CollectionDataBean();
//                collection.id = cursor.getInt(0);
//                collection.buslineiD = cursor.getString(1);
//                collection.busnum = cursor.getString(2);
//                collection.isShow = cursor.getInt(3);
//                CollectionDatalist.add(collection);
//            }
        }
        db.close();
        cursor.close();
        ListView lv_colectionData = (ListView) findViewById(R.id.lv_colectionData);
        if(CollectionDatalist!=null &&CollectionDatalist.size()>0){
            collectionDataBAdapter arrayAdapter = new collectionDataBAdapter(mContext,CollectionDatalist);
            lv_colectionData.setAdapter(arrayAdapter);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:////主键id 必须这样写
                onBackPressed();//按返回图标直接回退上个界面
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
