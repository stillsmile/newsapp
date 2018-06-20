package com.example.lenovo.androidapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        intent = new Intent();

        Button button = (Button) findViewById(R.id.bt_news);
        button.setOnClickListener(this);
        Button bt_database = (Button) findViewById(R.id.bt_database);
        bt_database.setOnClickListener(this);
        Button bt_busHelper = (Button) findViewById(R.id.bt_busHelper);
        bt_busHelper.setOnClickListener(this);
        //fragment测试案例
        Button bt_fragment = (Button) findViewById(R.id.bt_fragment);
        bt_fragment.setOnClickListener(this);
        Button bt_listviewshuxin = (Button) findViewById(R.id.bt_listviewshuxin);
        bt_listviewshuxin.setOnClickListener(this);
        Button bt_hideIntent = (Button) findViewById(R.id.bt_hideIntent);
        bt_hideIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建隐示意图对象
                Intent intent1 = new Intent();
                //设置意图的action动作
                intent1.setAction(Intent.ACTION_CALL);
//                intent1.setAction("android.intent.action.busquery");
                //设置数据data
                String telNum = "18861855124";
                intent1.setData(Uri.parse("tel:" + telNum));//打电话给自己
//                Uri data = intent1.getData();//获取数据
//                System.out.println("data-:" + data);   data-:tel:18861855124
//                String scheme = data.getScheme();     scheme = tel;

//                intent1.addCategory("android.intent.category.DEFAULT");
                //开启activity  记得加上权限  call_phone的权限
                startActivity(intent1);
            }
        });
        Button bt_mesgShow = (Button) findViewById(R.id.bt_mesgShow);
        bt_mesgShow.setOnClickListener(this);
        Button bt_DefineMessg = (Button) findViewById(R.id.bt_DefineMessg);
        bt_DefineMessg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_news:
                intent.setClass(this, newsActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_database:
                intent.setClass(this, databaseOperation.class);
                startActivity(intent);
                break;
            case R.id.bt_busHelper:
                intent.setClass(this, busqueryInfo.class);
                startActivity(intent);
                break;
            case R.id.bt_listviewshuxin:
                intent.setClass(this, listViewShuaxin.class);
                startActivity(intent);
                break;
            case R.id.bt_fragment:
                intent.setClass(this, newsTestActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_mesgShow:
                intent.setClass(this,MesgActivity.class);
                intent.setAction("android.intent.action.mesgDataTest");
                intent.setData(Uri.parse("testData" +  "测试数据123"));
                startActivity(intent);
                break;
            case R.id.bt_DefineMessg:
                intent.setClass(this,DefineMessgActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }

    }
}
