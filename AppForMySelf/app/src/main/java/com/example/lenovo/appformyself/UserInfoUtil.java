package com.example.lenovo.appformyself;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2018/5/12.
 */
public class UserInfoUtil {


    public static boolean saveUserInfo_android(Context context , String username, String password) {

       //1 创建一个sharedpreference对象
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
        //2通过sharedPreferences对象获取一个editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //3往editor中添加数据
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
        return true;
    }

    public static Map< String , String > getUserInfo_android(Context context){

        //1 创建一个sharedpreference对象
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
        //2通过sharedPreferences对象获取存放数据
        String username = sharedPreferences.getString("username","默认值");
        String password = sharedPreferences.getString("password","默认值");

        Map<String,String> map = new HashMap<String, String>();
        map.put("username",username);
        map.put("password",password);
        Log.w("sss", "读取成功");
        return map;
    }


    public static boolean saveUserInfo(Context context , String username, String password) {

        //保存用户名密码
//        String path = "/data/data/com.example.lenovo.appformyself/";
        String userinfo = username + "##" + password;
        String path = context.getFilesDir().getPath();
        //路径 /data/user/0/com.example.lenovo.appformyself/files
        String GetSDCardpath  = Environment.getExternalStorageDirectory().getPath();
        //路径获取sdCard内存卡的路径地址


//        通过context对象读取一个私有目录的文件读取流
//        try {
//            FileOutputStream fileOutputStream = context.openFileOutput("userinfosave.txt",Context.MODE_PRIVATE);
//            FileInputStream fileInputStream = context.openFileInput("userinfosave.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        Log.w("sss", "路径 " + path);
        File file = new File(path, "userinfosave.txt");
        if (file.exists()) {
            Log.w("sss", "file exists");
        }
        try {
            FileOutputStream fs = new FileOutputStream(file);
            fs.write(userinfo.getBytes());
            fs.close();
            Log.w("sss", "保存");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.w("sss", "保存失败");
        return false;
    }


    public static Map< String , String > getUserInfo(Context context){

//        String path = "/data/data/com.example.lenovo.appformyself/";
//       String userinfo = username + "##" + password;
        String path = context.getFilesDir().getPath();

        File file = new File(path, "userinfosave.txt");
        if (file.exists()) {
            Log.w("sss", "file exists");
        }
        try {
//            FileOutputStream fs = new FileOutputStream(file);
            FileInputStream fs = new FileInputStream(file);
            BufferedReader br =  new BufferedReader(new InputStreamReader(fs));
            String readline = br.readLine();
            String []split =  readline.split("##");
            Map<String,String> map = new HashMap<String, String>();
            map.put("username",split[0]);
            map.put("password",split[1]);

            Log.w("sss", "读取成功");
            fs.close();
            br.close();
            return map;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
