package com.example.lenovo.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ActivityJump extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
    }
    public  void testJump(View view){
        EditText editText = (EditText) findViewById(R.id.editText3);
        String name =  editText.getText().toString();
        Intent intent = new Intent();
        intent.setClass(this,ActivityJump2.class);
        intent.putExtra("name",name);
//        this.startActivity(intent);
        this.startActivityForResult(intent,1);
    }


    //回调函数当jump 按下返回的（页面返回的不按钮，结束当前activity2，返回到activity1）
    //回调函数开始执行（） //ActivityJump2.finish()方法之后
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode != 3){
            return;
        }
        String msg = data.getStringExtra("msg");
        EditText editText = (EditText) findViewById(R.id.editText4);
        editText.setText(msg);
    }
}
