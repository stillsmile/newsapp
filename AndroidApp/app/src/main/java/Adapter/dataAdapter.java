package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.lenovo.androidapp.R;

import java.util.ArrayList;

import bean.DataTestBean;
import utils.LogUtils;

/**
 * Created by lenovo on 2018/5/18.
 */

public class dataAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DataTestBean> list;

    public dataAdapter(Context context, ArrayList<DataTestBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = View.inflate(context, R.layout.operationdata, null);
        }
//        android:tag_first="1"
//        android:tag_second="2"
        CheckBox cb_id = (CheckBox) view.findViewById(R.id.cb_id);
        EditText et_name = (EditText) view.findViewById(R.id.et_name);
        EditText et_sex = (EditText) view.findViewById(R.id.et_sex);
        EditText et_age = (EditText) view.findViewById(R.id.et_age);
        EditText et_phone = (EditText) view.findViewById(R.id.et_phone);
        EditText et_salary = (EditText) view.findViewById(R.id.et_salary);
        DataTestBean dataTestBean = list.get(position);

        String firstTag = (String) cb_id.getTag();
//        String secondTag = (String) cb_id.getTag(R.id.tag_second);
        LogUtils.w("sss", firstTag);
//        LogUtils.w("sss",secondTag);

//        cb_id.setText(String.valueOf(dataTestBean.id));
//        cb_id.setTag(cb_id);
        cb_id.setTag(R.id.cb_id, dataTestBean.id);
        cb_id.setTag(R.id.tag_test, "test,tagName");
        et_name.setText(dataTestBean.name);
        et_sex.setText(dataTestBean.sex);
        et_age.setText(String.valueOf(dataTestBean.age));
        et_phone.setText(dataTestBean.phone);
        et_salary.setText(String.valueOf(dataTestBean.salary));

        return view;
    }
}
