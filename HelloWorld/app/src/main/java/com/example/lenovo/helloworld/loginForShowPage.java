package com.example.lenovo.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class loginForShowPage extends AppCompatActivity {

    private  String [] appName = new String[]{
            "登录页面",
            "Activity跳转",
            "时间轴和日期对话框",
            "进度条与消息队列处理器",
            "scrollViewAnd动态添加数据",
            "画廊空间gallary",
            "HelloWorld",
            "button按钮事件",
            "线程与优化",
            "消息提示抽屉",
            "图片展示器",
            "特殊按钮",
            "spiner与适配器",
            "线性布局",
            "相对布局相当于div+css",
            "对话框"

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_for_show_page);
        ListView listView = (ListView) findViewById(R.id.appItemName);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,appName);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(loginForShowPage.this,"ssss"+id+position,Toast.LENGTH_SHORT).show();
                Intent Intent = new Intent();
                Intent.putExtra("username","username");
                Intent.putExtra("password","password");

                if(appName[(int) id] == "登录页面"){
                    Intent.setClass(loginForShowPage.this,login.class);
                    startActivity(Intent);
                } else if (appName[(int) id] == "HelloWorld"){
                    Intent.setClass(loginForShowPage.this,MainActivity.class);
                    startActivity(Intent);

                } else if (appName[(int) id] == "button按钮事件"){
                    Intent.setClass(loginForShowPage.this,buttonAction.class);
                    startActivity(Intent);
                } else if (appName[(int) id] == "线程与优化"){
                    Intent.setClass(loginForShowPage.this,threadTest.class);
                    startActivity(Intent);
                } else if(appName[(int) id] == "消息提示抽屉"){
                    Intent.setClass(loginForShowPage.this,messageShow.class);
                    startActivity(Intent);
                }  else if(appName[(int) id] == "图片展示器"){
                    Intent.setClass(loginForShowPage.this,imageShow.class);
                    startActivity(Intent);
                } else if(appName[(int) id] == "特殊按钮"){
                    Intent.setClass(loginForShowPage.this,specialButton.class);
                    startActivity(Intent);
                }  else if(appName[(int) id] == "spiner与适配器"){
                    Intent.setClass(loginForShowPage.this,spinerAndAdapter.class);
                    startActivity(Intent);
                } else if(appName[(int) id] == "线性布局"){
                    Intent.setClass(loginForShowPage.this,lineayout.class);
                    startActivity(Intent);
                } else if (appName[(int) id] == "相对布局相当于div+css"){
                    Intent.setClass(loginForShowPage.this,relativelayout.class);
                    startActivity(Intent);
                } else if (appName[(int) id] == "对话框"){
                    Intent.setClass(loginForShowPage.this,dialogTest.class);
                    startActivity(Intent);
                } else if (appName[(int) id] == "scrollViewAnd动态添加数据"){
                    Intent.setClass(loginForShowPage.this,scrollViewAndAddAata.class);
                    startActivity(Intent);
                }else if (appName[(int) id] == "进度条与消息队列处理器"){
                    Intent.setClass(loginForShowPage.this,progressAndHandler.class);
                    startActivity(Intent);
                } else if (appName[(int) id] == "Activity跳转"){
                    Intent.setClass(loginForShowPage.this,ActivityJump.class);
                    startActivity(Intent);
                } else if (appName[(int) id] == "画廊空间gallary"){
                    Intent.setClass(loginForShowPage.this,gallarytest.class);
                    startActivity(Intent);
                } else if (appName[(int) id] == "时间轴和日期对话框"){
                    Intent.setClass(loginForShowPage.this,DataAndTime.class);
                    startActivity(Intent);
                } else if (appName[(int) id] == ""){
                    Intent.setClass(loginForShowPage.this,scrollViewAndAddAata.class);
                    startActivity(Intent);
                }
            }
        });

        //把view强转成groupview 他就具有了一个findviewbyid方法
        ViewGroup view  = (ViewGroup) getLayoutInflater().inflate(R.layout.header_show,null);
//        listView.addHeaderView(view);//隐藏导入的textview
        //导入的的TextView不具有页面第一行的属性，所以替换一行的textview 为固定显示


//        MyAdapter MyAdapter = new MyAdapter(this,appName);
//        MyAdapter.getView()

        TextView textview = (TextView) view.findViewById(R.id.titleShow);
//        textview.setText("随便变成什么都行");




    }
}
