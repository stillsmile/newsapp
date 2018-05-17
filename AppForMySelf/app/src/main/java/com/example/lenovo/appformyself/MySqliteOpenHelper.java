package com.example.lenovo.appformyself;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2018/5/13.
 */

public class MySqliteOpenHelper extends SQLiteOpenHelper {

    public MySqliteOpenHelper(Context context) {
        //context 上下文，name 数据库文件的名称  factory 用来创建cursor对象，默认为null
        // version数据库的版本号从1开始如果发生改变onupgrade方法将被调用
        super(context, "info1.db", null , 2);
    }

    //onCreate方法是数据库第一次创建的时候会被调用
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20)),phone varchar(11)");
        LogUtils.w("sss","数据库创建成功！");
    }


    //onUpgrade数据库版本号发生改变时才会执行，特别适合做表结构的修改
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("alter table info add phone varchar(11)");

        LogUtils.w("sss","上一个版本号 : " +oldVersion + " , 当前版本号："  + newVersion );

    }
}
