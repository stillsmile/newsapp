package com.example.lenovo.androidapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.NewsAdapter;
import bean.NewsBean;
import utils.NewsUtils;

public class newsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Context mContext;
    private ListView lv_news;
    private int count = 0;
    private ArrayList<NewsBean> allNews;
    private NewsAdapter newsAdapter;
//    private ArrayList<NewsBean> allnews_database;
    private ArrayList<NewsBean> allNewsTest;
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {

            allNews = (ArrayList<NewsBean>) msg.obj;

            if(allNews != null && allNews .size()>0)
            {
                //3.创建一个adapter设置给listview
                NewsAdapter newsAdapter = new NewsAdapter(mContext, allNews);
                lv_news.setAdapter(newsAdapter);
            }

        };
    };
    private Runnable runnable = new Runnable() {
        public void run () {
            handler.postDelayed(this,10000);
            count++;
            if(count % 2 == 1){
                getAllCities();
                Toast.makeText(mContext,count +"",Toast.LENGTH_SHORT).show();
            } else {
                getAllCities();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mContext = this;
        lv_news = (ListView) findViewById(R.id.lv_news);

        //1.先去数据库中获取缓存的新闻数据展示到listview
        allNews = NewsUtils.getAllNewsForDatabase(mContext);

        if(allNews !=null || allNews.size()>0){
            //创建一个adapter设置给listview
            newsAdapter = new NewsAdapter(mContext, allNews);
            lv_news.setAdapter(newsAdapter);
        }

        //2.通过网络获取服务器上的新闻数据用list封装 ,获取网络数据需要在子线程中做
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//
//                //请求网络数据
//                allNews = NewsUtils.getAllNewsForNetWork(mContext);
//                //通过handler将msg发送到主线程去更新Ui
//                Message msg = Message.obtain();
//                msg.obj = allNews;
//                handler.sendMessage(msg);
//
//            }
//        }).start();


        //3.设置listview条目的点击事件
        lv_news.setOnItemClickListener(this);

//        list.setAdapter(allNews);
        handler.post(runnable);
    }

    //listview的条目点击时会调用该方法 parent:代表listviw  view:点击的条目上的那个view对象   position:条目的位置  id： 条目的id
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        //需要获取条目上bean对象中url做跳转
        NewsBean bean = (NewsBean) parent.getItemAtPosition(position);

        String url = bean.news_url;

        //跳转浏览器
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
//                    allNews.clear();
//                    allNews.addAll(allNewsTest);
                    if(allNewsTest != null){
                        allNews.clear();
                        allNews.addAll(allNewsTest);
                        newsAdapter.list = allNews;
                        newsAdapter.notifyDataSetChanged();
                        Toast.makeText(mContext,"allNewsTest  = " +allNews.size() ,Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    private void getAllCities() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(count%2==1){
//                    allNews.addAll(NewsUtils.getAllNewsForNetWork(mContext));
                    allNewsTest = NewsUtils.getAllNewsForNetWork(mContext);
                }else{
                    allNewsTest = NewsUtils.getAllNewsForNetWorkTest(mContext);
                }
                mHandler.sendEmptyMessage(1);
            }
        }).start();
    }

}
