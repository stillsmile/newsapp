package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import bean.NewsBean;

/**
 * Created by lenovo on 2018/5/17.
 */

public class NewsDaoUtils {

    private NewsOpenHelper newsOpenHelper;
    public NewsDaoUtils(Context context){
        //创建一个帮助类对象
        newsOpenHelper = new NewsOpenHelper(context);
    }

    //删除数据库中缓存的旧数据
    public void delete(){

        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
        db.delete("news", null, null);
        db.close();
    }

    //向数据库中添加新闻数据
    public void saveNews(ArrayList<NewsBean> list){

        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
        for (NewsBean newsBean : list) {
            ContentValues values = new ContentValues();
            values.put("_id", newsBean.id);
            values.put("title", newsBean.title);
            values.put("des", newsBean.des);
            values.put("icon_url", newsBean.icon_url);
            values.put("news_url", newsBean.news_url);
            values.put("type", newsBean.type);
            values.put("time", newsBean.time);
            values.put("comment", newsBean.comment);
            db.insert("news", null, values);

        }

        db.close();
    }

    //从数据库中获取缓存的新闻数据
    public ArrayList<NewsBean> getNews(){
        ArrayList<NewsBean> list = new ArrayList<NewsBean>();
        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from news", null);//查询获取数据

        if(cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext()){
                NewsBean newsBean = new NewsBean();
                newsBean. id = cursor.getInt(0);
                newsBean. title = cursor.getString(1);
                newsBean. des =	cursor.getString(2);
                newsBean. icon_url =	cursor.getString(3);
                newsBean. news_url =	cursor.getString(4);
                newsBean. 	type = cursor.getInt(5);
                newsBean. time =	cursor.getString(6);
                newsBean. 	comment = cursor.getInt(7);

                list.add(newsBean);
            }
        }
        db.close();
        cursor.close();
        return list;

    }


}
