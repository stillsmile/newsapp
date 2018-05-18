package dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

/**
 * Created by user on 2018/5/18.
 */

public class databaseOpenHelper extends SQLiteOpenHelper {
    public databaseOpenHelper(Context context) {
        //context :上下文 name：数据库文件的名称  factory 用来创建cursor对象(结果集)，默认为null  version ：版本
        super(context, "testDataBase.db", null, 1);
        //此处操作执行，创建了数据库，为接下来的表的创建提供方便


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //oncreate方法是数据库第一次创建的时候会被调用,特别适合做表结构的初始化，需要执行sql语句
        System.out.println("oncreate方法是数据库第一次创建的时候会被调用");
//        SQLiteDatabase db  可以用来执行sql语句
        String sql ="create table testDataBase (_id integer primary key autoincrement,name vachar(20))";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //onUpgrade方法是数据库每次数据库版本升级的时候会被调用，

    }
}
