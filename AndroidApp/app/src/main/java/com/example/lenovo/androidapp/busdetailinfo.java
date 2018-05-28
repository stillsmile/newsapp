package com.example.lenovo.androidapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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
import utils.StreamUtils;

public class busdetailinfo extends AppCompatActivity {

    private Context mContext;
    private Intent intent;
    private String buslineID;
    private ArrayList<BusDetailInfoBean> busDetailInfos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busdetailinfo);
        mContext =this;
        intent = getIntent();
        buslineID = intent.getStringExtra("buslineID");

        new Thread(new Runnable() {
            @Override
            public void run() {
                getbusData(buslineID);
            }
        }).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ListView listView = (ListView) findViewById(R.id.lv_busDataInfo);
        BusDetailAdapter busDetailData = new BusDetailAdapter(mContext,busDetailInfos);

        listView.setAdapter(busDetailData);


    }

    public void getbusData(String buslineNum){
/// 使用UrlConncetion请求服务器将用户密码发送服务器验证
        try {
            String path = "http://bus.2500.tv/api_line_status.php";
            // 1.创建一个httpclient对象
            HttpClient httpclient = new DefaultHttpClient();
            // 2.创建一个请求方式
            HttpPost httppost = new HttpPost(path);
            // 创建集合封装数据
            ArrayList<BasicNameValuePair> arrayList = new ArrayList<BasicNameValuePair>();
            BasicNameValuePair nameValuePair = new BasicNameValuePair("lineID",buslineNum);
            arrayList.add(nameValuePair);
            // 创建一个Entity
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(arrayList,"utf-8");
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
                System.out.println("result = " + '{'+result);

                //2.解析获取的新闻数据到List集合中。

                JSONObject root_json = new JSONObject('{'+result.substring(13));//将一个字符串封装成一个json对象。
                JSONArray jsonArray = root_json.getJSONArray("data");//获取root_json中的newss作为jsonArray对象

                busDetailInfos = new ArrayList<BusDetailInfoBean>();
                for (int i = 0 ;i < jsonArray.length();i++){//循环遍历jsonArray
                    JSONObject bus_json = jsonArray.getJSONObject(i);//获取一条bus的json

                    BusDetailInfoBean busDetailInfo = new BusDetailInfoBean();
                    busDetailInfo.ID = bus_json.getString("ID");
                    busDetailInfo.BusInfo = bus_json.getString("BusInfo");
                    busDetailInfo.Code = bus_json.getString("Code");
                    busDetailInfo.InTime = bus_json.getString("InTime");
                    busDetailInfo.OutTime = bus_json.getString("OutTime");
                    busDetailInfo.StationCName = bus_json.getString("StationCName");

                    busDetailInfos.add(busDetailInfo);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
