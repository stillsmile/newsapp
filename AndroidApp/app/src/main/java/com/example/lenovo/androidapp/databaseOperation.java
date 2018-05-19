package com.example.lenovo.androidapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.dataAdapter;
import bean.DataTestBean;
import utils.LogUtils;

public class databaseOperation extends AppCompatActivity implements AdapterView.OnItemClickListener ,View.OnClickListener{

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_operation);
        mContext = this;
        ListView listView = (ListView) findViewById(R.id.lv_dataInfo);
        ArrayList<DataTestBean> list = new ArrayList<DataTestBean>();
        for(int i=0;i < 20 ; i ++){
            DataTestBean testBean = new DataTestBean();
            testBean.id = i;
            testBean.name = "asd  " + i;
            testBean.age = 23;
            testBean.sex = "asd";
            testBean.phone = "asd";
            testBean.salary = 26318;
            list.add(testBean);

        }


        if (list != null && list.size() > 0) {
            LogUtils.w("sss","mContext " + mContext);
            dataAdapter adapter = new dataAdapter(mContext, list);
            listView.setAdapter(adapter);
        }

        //设置onclick事件
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //需要获取条目上bean对象中url做跳转
//        NewsBean bean = (NewsBean) parent.getItemAtPosition(position);
        DataTestBean dataTestBean = (DataTestBean) parent.getItemAtPosition(position);
        String dataTestBeanId =  String.valueOf(dataTestBean.id);
        String dataTestBeanName =  String.valueOf(dataTestBean.name);
        String dataTestBeanSex =  String.valueOf(dataTestBean.sex);
        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_id);
        String tag = (String) checkBox.getTag();
        String tag_test = (String) checkBox.getTag(R.id.tag_test);
        String cb_id = (String) checkBox.getTag(R.id.cb_id);
//        String layoutTag = String.valueOf();

        TextView tv_dataInfo = (TextView) view.findViewById(R.id.tv_dataInfo);
        tv_dataInfo.setText("dataTestBeanId:" + dataTestBeanId + "/n" +
                "dataTestBeanName:" + dataTestBeanName + "/n" +
                "dataTestBeanSex:" + dataTestBeanSex + "/n" +
                "tag:" + tag + "/n" +
                "tag_test:" + tag_test + "/n" +
                "cb_id:" + cb_id);

        Toast.makeText(mContext,"xxxxxxx"+tag ,Toast.LENGTH_SHORT).show();


//        //跳转浏览器
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
////        intent.setData(Uri.parse(url));
//        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}
