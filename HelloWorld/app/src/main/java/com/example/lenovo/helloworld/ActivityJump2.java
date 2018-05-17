package com.example.lenovo.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityJump2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump2);

        Intent intent = this.getIntent();
        String name = intent.getStringExtra("name");
        TextView textView = (TextView) findViewById(R.id.textView22);
        textView.setText(name);
    }
    public  void  returnValue(View view){
        //获取文本输入内容
        EditText editText = (EditText) findViewById(R.id.editText5);
        String contet = editText.getText().toString();

        Intent intent = getIntent();
        intent.putExtra("msg",contet);
        //3是状态码
        this.setResult(3,intent);
        this.finish();

    }
}
