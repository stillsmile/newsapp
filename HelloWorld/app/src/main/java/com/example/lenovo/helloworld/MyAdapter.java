package com.example.lenovo.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.testClass.po.User;

import java.util.List;

/**
 * Created by lenovo on 2018/4/28.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
//    private String [] ss;

    private List<User> list;
    public MyAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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

        User user = list.get(position);
        //构建LayoutInflater对象引用布局
        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.baselayout,null);
        TextView textView18  = (TextView) viewGroup.findViewById(R.id.textView18);
        textView18.setText(user.getName());
        TextView textView19  = (TextView) viewGroup.findViewById(R.id.textView19);
        textView19.setText(user.getPhone());
        TextView textView20  = (TextView) viewGroup.findViewById(R.id.textView20);
        textView20.setText(String.valueOf(user.getAge()));

        return viewGroup;
    }
}
