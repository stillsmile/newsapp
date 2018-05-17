package crud.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lenovo.appformyself.LogUtils;
import com.example.lenovo.appformyself.MySqliteOpenHelper;

/**
 * Created by lenovo on 2018/5/13.
 */

public class crudInfoDao {

    private MySqliteOpenHelper mySqliteOpenHelper;
    public crudInfoDao (Context context){
        //执行sql语句需要的mySqliteOpenHelper
        //创建一个帮主类对象
         mySqliteOpenHelper = new MySqliteOpenHelper(context);

        //delete  insert   query    update
    }

    public boolean add(String name , String phone) {

        //执行sql语句需要的mySqliteOpenHelper
        //创建一个帮主类对象
//        MySqliteOpenHelper mySqliteOpenHelper = new MySqliteOpenHelper(context);
        //调用getReadableDatabase 方法，来初始化数据库的创建

        SQLiteDatabase sqLiteDatabase =  mySqliteOpenHelper.getReadableDatabase();

        //sql : sql语句，bindArgs : sql语句中的占位符的值
        String sql = "insert into info(name,phone) values(?,?);";
//        sqLiteDatabase.execSQL(sql,new Object[]{"wanghu","188626226272"});


        //table 表名 nullColumnHack可以为空，表示添加一个空行
        ContentValues  contentValues= new ContentValues();//是用map封装的对象，用来存放值

        contentValues.put("name",name);
        contentValues.put("phone",phone);
        //values 添加一行值  返回值：代表添加这个新行的ID -1 代表添加失败
        long result = sqLiteDatabase.insert("info",null, contentValues);
        sqLiteDatabase.close();

        if(result != -1 ){
            LogUtils.e("sss","添加成功");
            return true;
        } else {
            return false;
        }


    }

    public int delete(String name) {

        SQLiteDatabase sqLiteDatabase =  mySqliteOpenHelper.getReadableDatabase();

        //sql : sql语句，bindArgs : sql语句中的占位符的值
        String sql = "DELETE FROM info WHERE name = ? ";
//        sqLiteDatabase.execSQL(sql,new Object[]{"wanghu"});

        //table 表名    whereClause删除条件    whereArgs删除参数
        int result = sqLiteDatabase.delete("info" ,"name = ? " , new String[]{name});
        sqLiteDatabase.close();

        LogUtils.e("sss","添加删除了" + result + "条记录！");
        return result;

    }

    public int update(String name , String phone) {
        SQLiteDatabase sqLiteDatabase =  mySqliteOpenHelper.getReadableDatabase();

        //sql : sql语句，bindArgs : sql语句中的占位符的值
        String sql = "UPDATE info SET  phone = '111120200220' WHERE name = ？";
//        sqLiteDatabase.execSQL(sql,new Object[]{"wanghu"});

        //table 表名    whereClause删除条件    whereArgs删除参数
        ContentValues  contentValues= new ContentValues();//是用map封装的对象，用来存放值
        contentValues.put("phone",phone);
        int result = sqLiteDatabase.update("info", contentValues,"name = ? ", new String[]{ name});

        sqLiteDatabase.close();
        //返回成功修改多少行
        LogUtils.e("sss","添加修改了" + result + "条记录！");
        return result;

    }

    public void select(String name) {
        SQLiteDatabase sqLiteDatabase =  mySqliteOpenHelper.getReadableDatabase();

        //sql : sql语句，bindArgs : sql语句中的占位符的值
        String sql = "select * from info where name = ?";

        //table 表名    columns:查询的列名 如果null 代表查询所有列  new String[]{"_id","name" ,"phone"} 相当于null
        // selection  查询条件   selectionArgs查询的参数
        // groupBy 按什么分组    having分组的条件
        //orderBy 按什么字段排序
//        Cursor cursor =  sqLiteDatabase.query("info",  new String[]{"_id","name" ,"phone"},"name =?",
//               new String[]{name}, null, null,"_id desc") ;
        Cursor cursor =  sqLiteDatabase.query("info",  null,null,
               null, null, null,"_id desc") ;

//        Cursor cursor =  sqLiteDatabase.rawQuery(sql,new String[]{"wanghu"});
        //解析cursor对象中的数据
//        Map<UserInfo> userInfolist = new HashMap<>();

        if(cursor != null && cursor.getCount()>0){
            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件游标能否定位到下一行
                int id = cursor.getInt(0);
                String Tb_name = cursor.getString(1);
                String phone = cursor.getString(2);

                LogUtils.e("sss", "id:" + id + " ,name:" + Tb_name + " ,phone:" +phone );
            }

        }else {
            LogUtils.e("sss", "无数据存在");
        }
        cursor.close();

    }

}
