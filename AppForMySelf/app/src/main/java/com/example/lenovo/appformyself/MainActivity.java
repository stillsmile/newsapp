package com.example.lenovo.appformyself;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText et_username;
    private EditText et_userpassword;
    private CheckBox ck_isSavePassWord;
    private Button bt_login;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        et_username = (EditText) findViewById(R.id.et_usernmae);
        et_userpassword = (EditText) findViewById(R.id.et_userpassword);
        ck_isSavePassWord = (CheckBox) findViewById(R.id.ck_isSavePassWord);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        bt_login.setOnClickListener(this);

        callShowBack();
        //createSQLiteOpenHelper  创建数据库方法
        createSQLiteOpenHelper();
    }

    public void createSQLiteOpenHelper(){

        MySqliteOpenHelper mySqliteOpenHelper = new MySqliteOpenHelper(context);
        SQLiteDatabase sqLiteDatabase =  mySqliteOpenHelper.getReadableDatabase();


    }

    public void callShowBack() {

        Map<String, String> userInfo = UserInfoUtil.getUserInfo_android(context);
        if (userInfo != null) {
            String username = userInfo.get("username");
            String password = userInfo.get("password");
            et_username.setText(username);
            et_userpassword.setText(password);
            Toast.makeText(context, "初始化密码成功！", Toast.LENGTH_SHORT).show();
            ck_isSavePassWord.setChecked(true);
        }
    }

    public void login() {
        String username = et_username.getText().toString().trim();
        String userpassword = et_userpassword.getText().toString().trim();
        boolean ischecked = ck_isSavePassWord.isChecked();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userpassword)) {
            Toast.makeText(context, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        //是否记住密码，如果记住将被保存在本地
        if (ischecked) {
            //判断sdcard是否正常
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                Toast.makeText(context, "sdcard不存在或未挂载！", Toast.LENGTH_SHORT).show();
                LogUtils .w("sss", "sdcard状态" + Environment.MEDIA_MOUNTED);
                LogUtils.w("sss", "sdcard状态" + Environment.getExternalStorageState());
                return;
            }
            File sdcard_filedir =Environment.getExternalStorageDirectory();
//            long usablespace = sdcard_filedir.getUsableSpace();
            String usablespace =  Formatter.formatFileSize(context,sdcard_filedir.getUsableSpace());
            LogUtils.w("sss","" + usablespace);


            boolean result = UserInfoUtil.saveUserInfo_android(context, username, userpassword);
            if (result) {
                Toast.makeText(context, "用户名密码保存成功！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "用户名密码保存失败！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "不需要保存！", Toast.LENGTH_SHORT).show();
        }

        //跳转下一页面
        Intent intent  = new Intent();
        intent.setClass(context, crud.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                login();
                break;
        }

    }
}
