package com.example.lenovo.appformyself;

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

import java.util.ArrayList;

import Adapter.NewsAdapter;
import bean.NewsBean;
import utils.NewsUtils;

public class newsapp extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Context mContext;


    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {

            ArrayList<NewsBean> allNews = (ArrayList<NewsBean>) msg.obj;

            if(allNews != null && allNews .size()>0)
            {
                //3.创建一个adapter设置给listview
                NewsAdapter newsAdapter = new NewsAdapter(mContext, allNews);
                lv_news.setAdapter(newsAdapter);
            }

        };
    };

    private ListView lv_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsapp);
        mContext = this;
        //找到控件
        lv_news = (ListView) findViewById(R.id.lv_news);
//        //获取新闻数据，用list封装
//        ArrayList allNews = NewsUtils.getAllNews(mContext);
//
//        //3.创建一个adapter设置给listview
//        NewsAdapter newsAdapter = new NewsAdapter(mContext, allNews);
//        lv_news.setAdapter(newsAdapter);
//
//        //4.设置listview条目的点击事件
//        lv_news.setOnItemClickListener(this);

        //1.先去数据库中获取缓存的新闻数据展示到listview
        ArrayList<NewsBean> allnews_database = NewsUtils.getAllNewsForDatabase(mContext);

        if(allnews_database !=null && allnews_database.size()>0){
            //创建一个adapter设置给listview
            NewsAdapter newsAdapter = new NewsAdapter(mContext, allnews_database);
            lv_news.setAdapter(newsAdapter);
        }

        //2.通过网络获取服务器上的新闻数据用list封装 ,获取网络数据需要在子线程中做
        new Thread(new Runnable() {

            @Override
            public void run() {


                //请求网络数据
                ArrayList<NewsBean> allNews = NewsUtils.getAllNewsForNetWork(mContext);
                //通过handler将msg发送到主线程去更新Ui
                Message msg = Message.obtain();
                msg.obj = allNews;
                handler.sendMessage(msg);


            }
        }).start();


        //3.设置listview条目的点击事件
        lv_news.setOnItemClickListener(this);


    }

    //listview的条目点击时会调用该方法 parent:代表listviw  view:点击的条目上的那个view对象   position:条目的位置  id： 条目的id
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //需要获取条目上bean对象中url做跳转
        NewsBean bean = (NewsBean) parent.getItemAtPosition(position);

        String url = bean.news_url;

        //跳转浏览器
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

    }
}
