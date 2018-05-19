package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import bean.NewsBean;

/**
 * Created by user on 2018/5/18.
 */

public class dataBaseMathod {


    private Context mContex;

    public dataBaseMathod(Context context) {
        mContex = context;
    }

    public int delete() {

        //创建一个帮助类对象
        databaseOpenHelper databaseOpenHelper = new databaseOpenHelper(mContex);
        //调用getReadableDatabase方法，来初始化数据库的创建
        SQLiteDatabase database = databaseOpenHelper.getReadableDatabase();
        String sql = "delete from testDataBase where id = 'xx'  ";
//        database.delete(String table, String whereClause, String[] whereArgs);
        //table 表名 whereClause 删除条件 whereArgs 条件的占位符的参数，  返回值 成功删除的多少行
       int result =  database.delete("testDataBase", "id=?", new String[]{"name"});

        return  result;

    }

    public boolean insert() {
        databaseOpenHelper databaseOpenHelper = new databaseOpenHelper(mContex);
        SQLiteDatabase database = databaseOpenHelper.getReadableDatabase();
        String sql = "insert into testDataBase(_id,name) values('1','name1')";
        ContentValues values = new ContentValues();
        values.put("name", "zs");
        values.put("_id", "1");
        //table 表名  nullColumnHack可以为空，表示添加一个空行，values：数据一行的值，返回值：代表添加这个新行的ID ，-1代表添加失败
        long result = database.insert("testDataBase", null, values);

        database.close();
        if (result != -1) {
            return true;
        }else{
            return false;
        }
    }

    public boolean update() {
        databaseOpenHelper databaseOpenHelper = new databaseOpenHelper(mContex);
        SQLiteDatabase database = databaseOpenHelper.getReadableDatabase();
        String sql = "update testDataBase set naem='dd' where id ='1'";
        ContentValues values = new ContentValues();
        values.put("name", "zs");
        values.put("_id", "1");
        //table 表名  nullColumnHack可以为空，表示添加一个空行，values：数据一行的值，返回值：成功更新多少行，-1代表添加失败
        long result = database.update("testDataBase",values,"name=?",new String[]{"ccc"});
        database.close();
        if (result != -1) {
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<NewsBean> query() {
        databaseOpenHelper databaseOpenHelper = new databaseOpenHelper(mContex);
        SQLiteDatabase database = databaseOpenHelper.getReadableDatabase();
        String sql = "select * from testDataBase where id = '1'";

        //(String table  表名, String[] columns  查询的列名（如何null则查询所有列）, String selection 查询条件,
        //   String[] selectionArgs 条件占位符的参数值,
        // String groupBy按什么字段分组, String having分组的条件,
        // String orderBy 按什么字段排序
        ArrayList<NewsBean> newsBeenlist = new ArrayList<NewsBean>();

          Cursor cursor = database.query("testDataBase",new String[]{"_id","name"},"name=?",new String[]{"xxx"},null,null,"_id desc");
            if(cursor != null && cursor.getCount() >0){
                while (cursor.moveToNext()) {//条件，游标能否定位到下一行
                    NewsBean newsBean = new NewsBean();
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String des = cursor.getString(2);

                    newsBean.id  =id;
                    newsBean.title  =title;
                    newsBean.des  =des;

                    newsBeenlist.add(newsBean);
                    System.out.println("id" +id + "title"  + title + "des" + des );
                }
            }

        database.close();
        return newsBeenlist;
    }

}
