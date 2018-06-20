package com.example.lenovo.androidapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import utils.LogUtils;

public class MesgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesg);
        final Intent intent = getIntent();
        Uri data = intent.getData();
        String scheme = data.getScheme();
        System.out.println("data :" + data);
        System.out.println("scheme :" + scheme);
        LogUtils.w("sss",data.toString());

        final String objects[] = {"今天的风儿轻柔无比，今天的花儿香飘万里；今天的鸟儿十分欢喜，今天的云儿满载笑意；今天的事儿万分顺利，今天的人儿如此甜蜜。所有美...",
                "丫头，生活是你自己的，你哭它就对你哭，你笑它就对你笑。转眼，又是一年，你的生日即将来到。今年，还是少不了我对你的祝福，我忍不住...",
                "世界上最动听的声音，是妈妈声声的呼唤；世界上最温暖的笑容，是妈妈温暖的笑脸。妈妈，原谅生日时我不能陪在您身边，在这个日子里，我...",
                "今天是你的生日，祝你：发财势头如快马加鞭，一日千里；发展速度如滔滔江水，势不可挡；好事发生如雨后春笋，络绎不绝；祝福发送如比赛..."};
        //1.获取控件
        ListView lv_mesg = (ListView) findViewById(R.id.lv_mesg);
       //2.lv展示数据需要数据适配器 adapter
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,R.layout.item1,objects);
        //3.设置适配器数据
        lv_mesg.setAdapter(arrayAdapter);

        lv_mesg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String object = objects[position];
                Intent intent1 = new Intent();
                intent1.setAction("android.intent.action.SEND");
                intent1.addCategory("android.intent.category.DEFAULT");
                intent1.setType("text/plain");
                intent1.putExtra("sms_body",object);
                startActivity(intent1);
            }
        });



    }
}
