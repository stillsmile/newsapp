package com.example.lenovo.appformyself;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import crud.dao.crudInfoDao;

public class crud extends AppCompatActivity implements View.OnClickListener {

    private Context context;

    //增删改查
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        context = this;
        findViewById(R.id.bt_add).setOnClickListener(this);
        findViewById(R.id.bt_delete).setOnClickListener(this);
        findViewById(R.id.bt_update).setOnClickListener(this);
        findViewById(R.id.bt_select).setOnClickListener(this);
        findViewById(R.id.bt_bus).setOnClickListener(this);
        findViewById(R.id.bt_news).setOnClickListener(this);

        //1找到listview
        ListView listView = (ListView) findViewById(R.id.lv_showUserInfo);
        //2创建一个Adaptec对象
        MyListAdapter myListAdapter = new MyListAdapter();
        //3.讲Adaptec设置给listview
        listView.setAdapter(myListAdapter);
    }

    public void newsShowInClient(){
        //跳转下一页面
        Intent intent  = new Intent();
        intent.setClass(context, newsapp.class);
        startActivity(intent);
    }

    public void busInfo() {
        TextView textView = (TextView) findViewById(R.id.tv_bus);
//        textView.setText("www.baidu.com");
        try {
//            URL url = new URL("https://bus.2500.tv/lineInfo.php?lineID=10000520");
            URL url = new URL("https://www.baidu.com/");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000 * 10);

            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream inputStream = connection.getInputStream();
                String result = StreamUtils.streamToString(inputStream);
                textView.setText(result);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {

        crudInfoDao crudInfoDao = new crudInfoDao(context);
        switch (v.getId()) {

            case R.id.bt_add:
                crudInfoDao.add("腾讯", "d2312312");
                crudInfoDao.add("阿里云", "asdx");
                crudInfoDao.add("京东", "zx");
                crudInfoDao.add("淘宝", "zx");
                break;
            case R.id.bt_delete:
                crudInfoDao.delete("腾讯");
                break;
            case R.id.bt_update:
                crudInfoDao.update("腾讯", "scccc");
                break;
            case R.id.bt_select:
                crudInfoDao.select("腾讯");
                break;
            case R.id.bt_bus:
                busInfo();
                break;
            case R.id.bt_news:
                newsShowInClient();
                break;
            default:
                break;

        }


    }

    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //构建LayoutInflater对象引用布局
            LayoutInflater layoutInflater =
                    (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.baselayout, null);

            EditText editText1 = (EditText) viewGroup.findViewById(R.id.EditText1);
            editText1.setText("1");
            EditText editText2 = (EditText) viewGroup.findViewById(R.id.EditText2);
            editText2.setText("QQ");
            EditText editText3 = (EditText) viewGroup.findViewById(R.id.EditText3);
            editText3.setText("1262288");

//            TextView textView18 = (TextView) viewGroup.findViewById(R.id.textView18);
//            textView18.setText("1");
//            TextView textView19 = (TextView) viewGroup.findViewById(R.id.textView19);
//            textView19.setText("QQ");
//            TextView textView20 = (TextView) viewGroup.findViewById(R.id.textView20);
//            textView20.setText("126387163188");

            return viewGroup;
        }
    }


}
