package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;

import bean.DataTestBean;
import utils.LogUtils;

/**
 * Created by lenovo on 2018/5/19.
 */

public class TodayDataBase {
    private TodayDataBaseOperHelper todayDataBaseOperHelper;

    public TodayDataBase(Context context) {
        //创建一个帮助类对象
        todayDataBaseOperHelper = new TodayDataBaseOperHelper(context);
    }

    public int delete(ArrayList<DataTestBean> list) {
        //通过帮助类对象获得一个数据库操作对象
        SQLiteDatabase db = null;
        int result = 0;
        if(list.size() > 0){
            db = todayDataBaseOperHelper.getReadableDatabase();
            for (DataTestBean dataTestBean : list){
                result += db.delete("testDataBase","_id=?",new String[]{String.valueOf(dataTestBean.id)});
                LogUtils.w("sss","result" + String.valueOf(result));
                LogUtils.w("sss","id" + String.valueOf(dataTestBean.id));
                LogUtils.w("sss","list.size" + String.valueOf(list.size()));
            }
        }
        db.close();
        return result;
    }

    public int update(ArrayList<DataTestBean> list) {
        //通过帮助类对象获得一个数据库操作对象
        SQLiteDatabase db = todayDataBaseOperHelper.getReadableDatabase();
        ContentValues values;
        int result = 0;
        if(list.size() < 0){
            return result;
        }
        for(DataTestBean dataTestBean :list){
            values = new ContentValues();
            if(TextUtils.isEmpty(String.valueOf(dataTestBean.id))){
                return result;
            }
            values.put("_id",dataTestBean.id);
            values.put("name",dataTestBean.name);
            values.put("sex",dataTestBean.sex);
            values.put("age",dataTestBean.age);
            values.put("phone",dataTestBean.phone);
            values.put("salary",dataTestBean.salary);
             result += db.update("testDataBase",values,"_id=?",new String[]{String.valueOf(dataTestBean.id)});
        }
        db.close();
        return result;
    }

    public long insert(ArrayList<DataTestBean> list) {
        //通过帮助类对象获得一个数据库操作对象
        SQLiteDatabase db = todayDataBaseOperHelper.getReadableDatabase();
        long result = 0;
        long RowID = 0;
        //通过数据库操作对象执行sql操作
        if(list.size() <= 0){
            return result;
        }

        for (int i = 0; i < list.size(); i++) {
            ContentValues values = new ContentValues();
            DataTestBean dataTestBean = list.get(i);
//            values.put("id", dataTestBean.id);
            values.put("name", dataTestBean.name);
            values.put("sex", dataTestBean.sex);
            values.put("age", dataTestBean.age);
            values.put("phone", dataTestBean.phone);
            values.put("salary", dataTestBean.salary);
            //返回新添记录的行号，与主键id无关
            result += i;
            RowID =  db.insert("testDataBase", null, values);
            LogUtils.w("sss","result" + String.valueOf(result));
            LogUtils.w("sss","id" + String.valueOf(dataTestBean.id));
            LogUtils.w("sss","list.size" + String.valueOf(list.size()));
        }
        db.close();

        return result+1;

    }
    public ArrayList<DataTestBean> query(){
        SQLiteDatabase db = todayDataBaseOperHelper.getReadableDatabase();
        ArrayList<DataTestBean> list = new ArrayList<DataTestBean>();
        DataTestBean dataTestBean;
        Cursor cursor = db.rawQuery("select * from testDataBase",null);
        if(cursor!=null&&cursor.getCount()>0){
            while(cursor.moveToNext()){
                dataTestBean = new DataTestBean();
                dataTestBean.setId(cursor.getInt(0));
                dataTestBean.setName(cursor.getString(1));
                dataTestBean.setSex(cursor.getString(2));
                dataTestBean.setAge(cursor.getInt(3));
                dataTestBean.setPhone(cursor.getString(4));
                dataTestBean.setSalary(cursor.getDouble(5));
                list.add(dataTestBean);
            }
        }
        cursor.close();
        return list;
    }

//    public boolean isNull(){
//        boolean isFlag = false;
//        if(){
//
//        }
//
//        return isFlag;
//    }
}
