package com.example.lenovo.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

import Adapter.BusDetailAdapter;
import bean.BusDetailInfoBean;
import dao.CollectionDataHelper;
import utils.LogUtils;
import utils.StreamUtils;

public class busdetailinfo extends AppCompatActivity {

    private Context mContext;
    private Intent intent;
    private String buslineID;
    private String busNum;
    private String busDirector;
    private ActionBar supportActionBar;
    private BusDetailAdapter busDetailData;
    private ArrayList<BusDetailInfoBean> busDetailInfos;
    private ArrayList<BusDetailInfoBean> busDetailInfosWeb;
    private int count = 0;

    private Handler handler = new Handler();
    Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(busDetailInfosWeb != null){
                busDetailInfos.clear();
                busDetailInfos.addAll(busDetailInfosWeb);
                busDetailData.list = busDetailInfos;
                busDetailData.notifyDataSetChanged();
//                Toast.makeText(mContext,"allNewsTest  = " +busDetailInfos.size() ,Toast.LENGTH_SHORT).show();
            }
        }
    };

    private Runnable runnable = new Runnable() {
        public void run() {

//            busDetailInfos.clear();
            getbusData(buslineID);
//            busDetailData.notifyDataSetChanged();

            handler.postDelayed(this, 5000);
            supportActionBar.setTitle(busNum + "路公交正在行驶中---" + count);//设置ActionBar的标题
//            Toast.makeText(mContext,count+"",Toast.LENGTH_SHORT).show();

            count++;
            if (count == 10) {
//                handler.removeCallbacks(runnable);
            }
        }
    };

//    Handler handler = new Handler(){
//        public void handleMessage(Message msg) {
//            String list[] = (String[]) msg.obj;
//            int which = msg.what;
//
//        };
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busdetailinfo);
        mContext = this;
        intent = getIntent();
        buslineID = intent.getStringExtra("buslineID");
        busNum = intent.getStringExtra("busNum");
        busDirector = intent.getStringExtra("busDirector");

//        intent.putExtra("busDirector", busDirector);

        supportActionBar = getSupportActionBar();
        supportActionBar.setIcon(R.drawable.bus);//设置ActionBar的icon图标
        supportActionBar.setTitle(busNum + "路公交正在行驶中");//设置ActionBar的标题
        supportActionBar.setHomeButtonEnabled(true);//主键按钮能否可点击
        supportActionBar.setDisplayHomeAsUpEnabled(true);//显示返回图标
        busDetailInfos = new ArrayList<BusDetailInfoBean>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                getbusData(buslineID);
            }
        }).start();

        ListView listView = (ListView) findViewById(R.id.lv_busDataInfo);
        if(busDetailInfos != null &&busDetailInfos.size()>0){
            busDetailData = new BusDetailAdapter(mContext, busDetailInfos);
            listView.setAdapter(busDetailData);
        }else{
            ArrayList<BusDetailInfoBean>  NullbusDetailInfos =  new ArrayList<BusDetailInfoBean>();
            BusDetailInfoBean busDetailInfoBean = new BusDetailInfoBean("","","","","","");
            NullbusDetailInfos.add(busDetailInfoBean);
            busDetailData = new BusDetailAdapter(mContext, NullbusDetailInfos);
            listView.setAdapter(busDetailData);
        }


        Button bt_collection = (Button) findViewById(R.id.bt_collection);
        bt_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionDataHelper collectionDataHelper = new CollectionDataHelper(mContext);
                SQLiteDatabase db = collectionDataHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("buslineiD",buslineID);
                values.put("busnum", busNum + "公交车");
                values.put("busdirector", busDirector);
                values.put("isShow",1);
                Cursor querycursor = db.query("businfodb", null, "buslineiD=?", new String[]{buslineID}, null, null, null);
                if(querycursor != null && querycursor.getCount() >0){//判断cursor中是否存在数据
                    Toast.makeText(mContext,"已收藏" ,Toast.LENGTH_SHORT).show();
                }else{
                    long insert = db.insert("businfodb",null,values);
                    Toast.makeText(mContext,"收藏成功" ,Toast.LENGTH_SHORT).show();
                }
            }
        });


//        开始计时
//        handler.removeCallbacks(runnable);
//        handler.postDelayed(runnable,1000);
        handler.post(runnable);
//        停止计时
//        handler.removeCallbacks(runnable);
//        第三种代码看起来也非常的简洁，推荐使用。

    }

    public void getbusData(final String buslineNum) {
/// 使用UrlConncetion请求服务器将用户密码发送服务器验证
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String path = "http://bus.2500.tv/api_line_status.php";
                    // 1.创建一个httpclient对象
                    HttpClient httpclient = new DefaultHttpClient();
                    // 2.创建一个请求方式
                    HttpPost httppost = new HttpPost(path);
                    // 创建集合封装数据
                    ArrayList<BasicNameValuePair> arrayList = new ArrayList<BasicNameValuePair>();
                    BasicNameValuePair nameValuePair = new BasicNameValuePair("lineID", buslineNum);
                    arrayList.add(nameValuePair);
                    // 创建一个Entity
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(arrayList, "utf-8");
                    // 设置请求时的内容
                    httppost.setEntity(entity);
                    // 3.执行一个请求,返回一个response对象
                    HttpResponse response = httpclient.execute(httppost);
                    // 4.获取状态码
                    int code = response.getStatusLine().getStatusCode();
                    // 5.判断并获取内容
                    if (code == 200) {
                        System.out.println(code);
                        HttpEntity entity1 = response.getEntity();// 获取实体内容，中封装的有http请求返回的流信息
                        InputStream inputStream = entity1.getContent();
                        // 将流信息转换成字符串
                        String result = StreamUtils.streamToString(inputStream);
                        System.out.println("result = " + '{' + result);

                        //2.解析获取的新闻数据到List集合中。

                        JSONObject root_json = new JSONObject('{' + result.substring(13));//将一个字符串封装成一个json对象。
                        JSONArray jsonArray = root_json.getJSONArray("data");//获取root_json中的newss作为jsonArray对象

                        busDetailInfosWeb = new ArrayList<BusDetailInfoBean>();
                        //循环遍历jsonArray
                        for (int i = 0; i < jsonArray.length(); i++) {
                            //获取一条bus的json
                            JSONObject bus_json = jsonArray.getJSONObject(i);

                            BusDetailInfoBean busDetailInfo = new BusDetailInfoBean();
                            busDetailInfo.ID = bus_json.getString("ID");
                            busDetailInfo.BusInfo = bus_json.getString("BusInfo");
                            busDetailInfo.Code = bus_json.getString("Code");
                            busDetailInfo.InTime = bus_json.getString("InTime");
                            busDetailInfo.OutTime = bus_json.getString("OutTime");
                            busDetailInfo.StationCName = bus_json.getString("StationCName");
                            busDetailInfosWeb.add(busDetailInfo);
                        }
                        mHandler.sendEmptyMessage(1);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


    //添加返回的事件  同下一个activity的返回方法
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("点击退回触发");
        LogUtils.w("sss","");
        handler.removeCallbacks(runnable);
    }
}
