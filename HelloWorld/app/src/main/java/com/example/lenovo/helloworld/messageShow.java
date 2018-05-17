package com.example.lenovo.helloworld;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class messageShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_show);



    }

    public void ceshiButton(View view) throws XmlPullParserException, IOException {
        String text ="";
        XmlResourceParser xrp =  this.getResources().getXml(R.xml.users);
        while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT){
            if(xrp.getEventType() == XmlResourceParser.START_TAG){
                String tagName =  xrp.getName();
                if (tagName.equals("user")) {
                    String uname =  xrp.getAttributeValue(0);
                    String phone =  xrp.getAttributeValue(1);
                    text +="姓名： " + uname + "姓名： "+ phone + ";\n";
                }
            }
            xrp.next();
        }
        TextView textView = (TextView) findViewById(R.id.ceshi);
        textView.setText(text);
    }

    public  void  testNotifiaction(View view){
        NotificationManager notificationManager
                = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mbuilder
                = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle("叙利亚战争")
                .setContentText("美国强权")
                .setTicker("叙利亚战争，美国强权？");

        Notification notification = mbuilder.build();
        notificationManager.notify(1,notification);

        TextView textView = (TextView) findViewById(R.id.ceshi);
        textView.setTextColor(Integer.valueOf(R.color.bcColor));
        this.getResources().getColor(R.color.bcColor);


//        notificationManager.notify(id, Notification);//发送一个通知
//        notificationManager.cancel();//取消通知


    }
}
