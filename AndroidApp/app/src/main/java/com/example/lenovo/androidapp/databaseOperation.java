package com.example.lenovo.androidapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.dataAdapter;
import bean.DataTestBean;
import dao.TodayDataBase;
import utils.DataTestUtils;
import utils.LogUtils;

public class databaseOperation extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private Context mContext;
    private ListView listView;
    private dataAdapter adapter;
    private ArrayList<DataTestBean> list;
    private ArrayList<DataTestBean> isSelectNum = new ArrayList<DataTestBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_operation);
        mContext = this;
        listView = (ListView) findViewById(R.id.lv_dataInfo);
        list = new ArrayList<DataTestBean>();
        for (int i = 0; i < 20; i++) {
            DataTestBean testBean = new DataTestBean();
            testBean.id = i;
            testBean.name = "asd  " + i;
            testBean.age = 23;
            testBean.sex = "asd";
            testBean.phone = "asd";
            testBean.salary = 26318;
            list.add(testBean);
        }
        DataTestBean testBean = new DataTestBean();
        testBean.id = 1;
        testBean.name = "asd " + 1;
        testBean.age = 23;
        testBean.sex = "asd";
        testBean.phone = "asd";
        testBean.salary = 26318;
        list.add(testBean);
//        list = new TodayDataBase(mContext).query();
        if (list != null && list.size() > 0) {
            LogUtils.w("sss", "mContext " + mContext);
            adapter = new dataAdapter(mContext, list);
            listView.setAdapter(adapter);
        }
        //设置onclick事件
        listView.setOnItemClickListener(this);

        Button bt_add = (Button) findViewById(R.id.bt_add);
        Button bt_delete = (Button) findViewById(R.id.bt_delete);
        Button bt_update = (Button) findViewById(R.id.bt_update);
        Button bt_query = (Button) findViewById(R.id.bt_query);
        bt_add.setOnClickListener(this);
        bt_update.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_query.setOnClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //需要获取条目上bean对象中url做跳转
//        NewsBean bean = (NewsBean) parent.getItemAtPosition(position);
        final DataTestBean dataTestBean = (DataTestBean) parent.getItemAtPosition(position);
        final CheckBox cb_id = (CheckBox) view.findViewById(R.id.cb_id);
//        isSelectNum = new ArrayList<DataTestBean>();
        if (cb_id.isChecked()){
            cb_id.setChecked(false);
            LogUtils.w("sss","移除的id : " + dataTestBean.getId());
            isSelectNum.remove(dataTestBean);
        }else {
            cb_id.setChecked(true);
            LogUtils.w("sss","添加的id : " + dataTestBean.getId());
            isSelectNum.add(dataTestBean);
        }
        String dataTestBeanId = String.valueOf(dataTestBean.id);
        String dataTestBeanName = String.valueOf(dataTestBean.name);
        String dataTestBeanSex = String.valueOf(dataTestBean.sex);
        String tag = (String) cb_id.getTag();
        String tag_test = (String) cb_id.getTag(R.id.tag_test);
//        Object Tag_cb_id = cb_id.getTag(R.id.cb_id);
//        String layoutTag = String.valueOf();
//        self_definitionFordata(mContext ,dataTestBean ,position);

        //创建自定义提示框
        AlertDialog.Builder builder =  new AlertDialog.Builder(mContext);
        builder.setTitle("你正在修改信息");
        View self_definition_dialog = View.inflate(mContext,R.layout.self_definition_dialog,null);
        builder.setView(self_definition_dialog);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //控件赋值
        final TextView et_id = (TextView) self_definition_dialog.findViewById(R.id.et_id);
        et_id.setText(String.valueOf(dataTestBean.id));
        final EditText et_name = (EditText) self_definition_dialog.findViewById(R.id.et_name);
        et_name.setText(String.valueOf(dataTestBean.name));
        final EditText et_sex = (EditText) self_definition_dialog.findViewById(R.id.et_sex);
        et_sex.setText(String.valueOf(dataTestBean.sex));
        final EditText et_age = (EditText) self_definition_dialog.findViewById(R.id.et_age);
        et_age.setText(String.valueOf(dataTestBean.age));
        final EditText et_phone = (EditText) self_definition_dialog.findViewById(R.id.et_phone);
        et_phone.setText(String.valueOf(dataTestBean.phone));
        final EditText et_salary = (EditText) self_definition_dialog.findViewById(R.id.et_salary);
        et_salary.setText(String.valueOf(dataTestBean.salary));

        //监听事件按钮
        Button bt_cancel = (Button) self_definition_dialog.findViewById(R.id.bt_cancel);
        Button bt_confirm = (Button) self_definition_dialog.findViewById(R.id.bt_confirm);

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
                //设置背景框提示文字和背景颜色
//                if(TextUtils.isEmpty(et_values.getText())){
////                        SpannableString s = new SpannableString("你好呀 小美人");//这里输入自己想要的提示文字
////                        et_values.setHint(s);
//                    et_values.setHintTextColor(Color.RED);
//                    return;
//                }
//                        et_salary.setText(String.valueOf(et_values.getText()));
                //修改listbean的值
                dataTestBean.setId(Integer.valueOf(String.valueOf(et_id.getText())));
                dataTestBean.setName(String.valueOf(et_name.getText()));
                dataTestBean.setSex(String.valueOf(et_sex.getText()));
                dataTestBean.setAge(Integer.valueOf(String.valueOf(et_age.getText())));
                dataTestBean.setPhone(String.valueOf(et_phone.getText()));
                dataTestBean.setSalary(Double.valueOf(String.valueOf(et_salary.getText())));
//                listView.notifyDataSetChanged();
                //刷新展示框中的值
                ArrayList<DataTestBean> NowUpdate = new ArrayList<DataTestBean>();
                NowUpdate.add(dataTestBean);
                int updateNum = new TodayDataBase(mContext).update(NowUpdate);
                LogUtils.w("sss","更新了" + updateNum + "条！");
                Toast.makeText(mContext,"更新了" + updateNum + "条！",Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();

                //关闭自定义框
                alertDialog.dismiss();
                cb_id.setChecked(false);

            }
        });

        TextView tv_dataInfo = (TextView) databaseOperation.this.findViewById(R.id.tv_dataInfo);
        tv_dataInfo.setText("dataTestBeanId:" + dataTestBeanId + "\n" +
                "dataTestBeanName:" + dataTestBeanName + "\n" +
                "dataTestBeanSex:" + dataTestBeanSex + "\n" +
                "tag:" + tag + "\n" +
                "tag_test:" + tag_test + "\n" +
                "cb_id:" + cb_id);

//        Toast.makeText(mContext, "xxxxxxx" + tag, Toast.LENGTH_SHORT).show();


//        //跳转浏览器
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
////        intent.setData(Uri.parse(url));
//        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_add :
//                AlertDialog.Builder builder =  new AlertDialog.Builder(mContext);
//                builder.setTitle("你正在修改信息");
//                View self_definition_dialog = View.inflate(mContext,R.layout.self_definition_dialog,null);
//                builder.setView(self_definition_dialog);
//                final AlertDialog alertDialog = builder.create();
//                alertDialog.show();

               long insertresult =  new TodayDataBase(mContext).insert(list);
                adapter.notifyDataSetChanged();
                LogUtils.w("sss","更新了" + insertresult + "条！");
                Toast.makeText(mContext,"添加了" + insertresult + "条！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_update :
                int updateresult =  new TodayDataBase(mContext).update(list);
                adapter.notifyDataSetChanged();
                LogUtils.w("sss","更新了" + updateresult + "条！");
                Toast.makeText(mContext,"更新了" + updateresult + "条！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_query :
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new DataTestUtils(mContext,list).getDataTestInfo();
                    }
                }).start();

                break;
            case R.id.bt_delete :
                int deleteresult =  new TodayDataBase(mContext).delete(isSelectNum);
                adapter.notifyDataSetChanged();
                LogUtils.w("sss","删除了" + deleteresult + "条！");
                Toast.makeText(mContext,"删除了" + deleteresult + "条！",Toast.LENGTH_SHORT).show();
                break;
        }

    }
    public static  void self_definitionFordata(final Context mContext , final DataTestBean dataTestBean , int position){
        //创建自定义提示框
        AlertDialog.Builder builder =  new AlertDialog.Builder(mContext);
        builder.setTitle("你正在修改信息");
        View self_definition_dialog = View.inflate(mContext,R.layout.self_definition_dialog,null);
        builder.setView(self_definition_dialog);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //控件赋值
        final TextView et_id = (TextView) self_definition_dialog.findViewById(R.id.et_id);
        et_id.setText(String.valueOf(dataTestBean.id));
        final EditText et_name = (EditText) self_definition_dialog.findViewById(R.id.et_name);
        et_name.setText(String.valueOf(dataTestBean.name));
        final EditText et_sex = (EditText) self_definition_dialog.findViewById(R.id.et_sex);
        et_sex.setText(String.valueOf(dataTestBean.sex));
        final EditText et_age = (EditText) self_definition_dialog.findViewById(R.id.et_age);
        et_age.setText(String.valueOf(dataTestBean.age));
        final EditText et_phone = (EditText) self_definition_dialog.findViewById(R.id.et_phone);
        et_phone.setText(String.valueOf(dataTestBean.phone));
        final EditText et_salary = (EditText) self_definition_dialog.findViewById(R.id.et_salary);
        et_salary.setText(String.valueOf(dataTestBean.salary));

        //监听事件按钮
        Button bt_cancel = (Button) self_definition_dialog.findViewById(R.id.bt_cancel);
        Button bt_confirm = (Button) self_definition_dialog.findViewById(R.id.bt_confirm);

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
                //设置背景框提示文字和背景颜色
//                if(TextUtils.isEmpty(et_values.getText())){
////                        SpannableString s = new SpannableString("你好呀 小美人");//这里输入自己想要的提示文字
////                        et_values.setHint(s);
//                    et_values.setHintTextColor(Color.RED);
//                    return;
//                }
//                        et_salary.setText(String.valueOf(et_values.getText()));
                //修改listbean的值
                dataTestBean.setId(Integer.valueOf(String.valueOf(et_id.getText())));
                dataTestBean.setName(String.valueOf(et_name.getText()));
                dataTestBean.setSex(String.valueOf(et_sex.getText()));
                dataTestBean.setAge(Integer.valueOf(String.valueOf(et_age.getText())));
                dataTestBean.setPhone(String.valueOf(et_phone.getText()));
                dataTestBean.setSalary(Double.valueOf(String.valueOf(et_salary.getText())));
//                listView.notifyDataSetChanged();
                //刷新展示框中的值
                ArrayList<DataTestBean> NowUpdate = new ArrayList<DataTestBean>();
                NowUpdate.add(dataTestBean);
                int updateNum = new TodayDataBase(mContext).update(NowUpdate);
                LogUtils.w("sss","更新了" + updateNum + "条！");
                Toast.makeText(mContext,"更新了" + updateNum + "条！",Toast.LENGTH_LONG).show();
//                adapter.notifyDataSetChanged();
//
//                //关闭自定义框
//                alertDialog.dismiss();
//                cb_id.setChecked(false);

            }
        });

    }
}
