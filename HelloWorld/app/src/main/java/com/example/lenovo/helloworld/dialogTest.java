package com.example.lenovo.helloworld;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class dialogTest extends AppCompatActivity {


    private String []ss = new String[]{
            "java",
            "c#",
            "pho",
            "python",
            "c++"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);
    }

    public void  ceshidialog(View view){

        /**
         * 如何创建一个对话框AlertDialog
         * 1。AlertDialog。Builder
         * 在create方法执行之前，应该对对话框的一些参数进行纤细的设置
         * 2。builder。create（）可以返回一个AlertDialog对象
         * 3.dialog.show（）显示对话框
         *
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示信息");
        builder.setMessage("my first dialog");
        builder.setPositiveButton("确定",null);
        builder.setNegativeButton("否定",null);
//        builder.setNeutralButton("一般",null);//一般不用
        builder.setItems(ss, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(dialogTest.this,ss[which],Toast.LENGTH_SHORT).show();
            }
        });



        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void  ceshidialog2(View view){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示信息");
            builder.setItems(ss, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(dialogTest.this,ss[which],Toast.LENGTH_SHORT).show();
                }
            });



        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void  ceshidialog3(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //  1   默认选中第二个元素
        builder.setSingleChoiceItems(ss, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(dialogTest.this,ss[which],Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void  ceshidialog4(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //  1   默认选中第二个元素           new boolean[true,false,true]，下面这个
        builder.setMultiChoiceItems(ss, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(dialogTest.this,ss[which],Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void  ceshidialog5(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //  1   默认选中第二个元素           new boolean[true,false,true]，下面这个
        builder.setTitle("自定义的对话框");
        View  suibian = this.getLayoutInflater().inflate(R.layout.header_show,null);
        builder.setView(suibian);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
