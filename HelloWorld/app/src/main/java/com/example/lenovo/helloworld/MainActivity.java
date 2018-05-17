package com.example.lenovo.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void test1(View view) {
        Log.i("name22","12323dsds");
        Toast.makeText(this,"提示出来了，再见",Toast.LENGTH_SHORT).show();
//        Toast.makeText(login.this,"我就弹出个提示",Toast.LENGTH_LONG).show();
    }
    public void test2(View view){
        Log.i("name","12323");
        Log.w("sad","dsad");
        Log.e("show","sdasd");

        Toast.makeText(this,"我就是一个按钮，别点我，转向一个页面",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
//        intent.setClass();
//        Intent.setClass(loginForShowPage.this,MainActivity.class);
//        startActivity(intent);
    }
}
