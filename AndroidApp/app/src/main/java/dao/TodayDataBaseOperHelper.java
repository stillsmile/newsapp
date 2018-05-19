package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2018/5/19.
 */

public class TodayDataBaseOperHelper extends SQLiteOpenHelper {
    public TodayDataBaseOperHelper(Context context) {
        super(context, "todayDataBase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table testDataBase (_id integer primary key autoincrement" +
                ",name vachar(20)" +
                ",sex varchar(20)" +
                ",age integer" +
                ",phone integer" +
                ",salary double);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
