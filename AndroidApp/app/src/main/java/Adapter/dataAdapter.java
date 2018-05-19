package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.lenovo.androidapp.R;

import java.util.ArrayList;

import bean.DataTestBean;
import utils.LogUtils;

import static android.view.View.inflate;

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
            view = inflate(context, R.layout.activity_operationdata, null);
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

//        changeItemValuse(context ,et_salary , dataTestBean ,salary);

        String firstTag = (String) cb_id.getTag();
//        String secondTag = (String) cb_id.getTag(R.id.tag_second);
//        LogUtils.w("sss", firstTag);
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


    public static void changeItemValuse(final Context mcontext, final View view , DataTestBean testBean){

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v1) {
                LogUtils.w("sss", "进入onClick方法，我被点击了");
//                private AlertDialog alertDialog;
                final AlertDialog.Builder builder =  new AlertDialog.Builder(mcontext);
                builder.setTitle("你正在修改信息");
                View self_definition_dialog = View.inflate(mcontext,R.layout.self_definition_dialog,null);
                builder.setView(self_definition_dialog);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Button bt_cancel = (Button) self_definition_dialog.findViewById(R.id.bt_cancel);
                Button bt_confirm = (Button) self_definition_dialog.findViewById(R.id.bt_confirm);

                final EditText et_values = (EditText) self_definition_dialog.findViewById(view.getId());
                bt_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtils.w("sss", "取消操作");
                        alertDialog.dismiss();
                    }
                });
                bt_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v2) {
                        LogUtils.w("sss", "确认操作");
                        if(TextUtils.isEmpty(et_values.getText())){
//                        SpannableString s = new SpannableString("你好呀 小美人");//这里输入自己想要的提示文字
//                        et_values.setHint(s);
                            et_values.setHintTextColor(Color.RED);
                            return;
                        }
//                        et_salary.setText(String.valueOf(et_values.getText()));

                        alertDialog.dismiss();
                    }
                });
            }
        });

    }




}
