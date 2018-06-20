package com.example.lenovo.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.telephony.SmsManager;

import java.util.ArrayList;

public class DefineMessgActivity extends AppCompatActivity {

    private EditText et_number;
    private EditText et_sms_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_messg);

        et_number = (EditText) findViewById(R.id.et_number);
        et_sms_content = (EditText) findViewById(R.id.et_sms_content);
    }

    //点击按钮 跳转到短信模板页面
    public void insert(View v){
        Intent intent = new Intent(this,SmsTemplateActivity.class);
        //☆☆☆开启Activity有2种方式
        //(1)如果想要开启的Activity的界面的数据 用 startActivityForResult();
        //(2)如果就是简简单单页面的跳转  就用startActivity()
        startActivityForResult(intent, 2);
    }
    //点击+ 按钮 跳转到 联系人页面
    public void add(View v) {
        //[1]创建意图对象
        Intent intent = new Intent(this,ContactActivity.class);
        //[2]开启Activity
//		startActivity(intent);
        //[3]小细节 ☆☆☆☆  如果一个页面开启另外一个页面  并且当开启的这个页面关闭的时候 还要另外一个页面的数据  使用下面这个方法开启Activity
        startActivityForResult(intent, 1);

    }

    //当我们开启的Activity的页面关闭的时候这个方法会调用
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            //代表我要请求 ContactActivity的数据
            String phone = data.getStringExtra("name");
            et_number.setText(phone);
        }else if (requestCode == 2) {
            //代表我要请求SmsTemplateActivity 的数据
            String smscontent = data.getStringExtra("smscontent");
            et_sms_content.setText(smscontent);

        }

		/*if (resultCode == 10) {
			//说明数据是由 ContactActivity返回

			String phone = data.getStringExtra("name");
			et_number.setText(phone);

		}else if (resultCode == 20) {
			//说明数据是由SmsTemplateActivity返回
			String smscontent = data.getStringExtra("smscontent");
			et_sms_content.setText(smscontent);

		}
		*/


        super.onActivityResult(requestCode, resultCode, data);
    }

    //点击按钮实现发送短信的逻辑
    public void click(View v) {
        String number = et_number.getText().toString().trim();
        String content = et_sms_content.getText().toString().trim();

        //[1]获取smsManager的实例
        SmsManager smsManager = SmsManager.getDefault();
        //[1.1]如果短信内容过过多 发不出去  分条发送
        ArrayList<String> divideMessages = smsManager.divideMessage(content);
        for (String div : divideMessages) {

            //[2]发送短信数据
            smsManager.sendTextMessage(number, null, div, null, null);
        }

    }

}
