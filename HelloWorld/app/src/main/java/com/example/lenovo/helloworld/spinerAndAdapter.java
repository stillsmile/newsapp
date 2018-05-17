package com.example.lenovo.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class spinerAndAdapter extends AppCompatActivity {

    private String []ss = new String[]{
            "java",
            "c#",
            "pho",
            "python",
            "c++"
    };
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiner_and_adapter);
//        List赋值
        list.add("旁边");
        list.add("有一个");
        list.add("小妹妹");
        list.add("高三");
        list.add("准备考试");
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        BaseAdapter adapter = new myAdapter();
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2
                = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,ss);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("选择spinner的是item项");
                Toast.makeText(spinerAndAdapter.this,"position:"+ position +
                        "选择的内容"+ ss[position]+ "，id:" + id ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    private  class  myAdapter2 extends BaseAdapter{
        @Override
        public int getCount() {
            return 0;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

    //list   与    string  数组的赋值显示
    private  class  myAdapter extends BaseAdapter{
        @Override
        public int getCount() {

            return  list.size();
//            return ss.length;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(spinerAndAdapter.this);
//            textView.setText(ss[position]);
            textView.setText(list.get(position));
//            textView.setText("你管我设置的什么");
            return textView;
            //我返回的是一个textview对象就行，其他的不管，不就是一个string吗？
        }
    }
}
