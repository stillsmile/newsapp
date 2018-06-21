package dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import utils.LogUtils;

/**
 * Created by lenovo on 2018/6/20.
 */

public class CollectionDataHelper extends SQLiteOpenHelper {


    public CollectionDataHelper(Context context) {
        super(context, "businfodb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="create table businfodb " +
                            "(_id integer  ," +
                            "buslineid varchar(200)," +
                            "busnum varchar(300)," +
                            "busdirector varchar(300)," +
                            "isshow integer)";
        db.execSQL(sql);
        LogUtils.w("ceshi","数据库创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
