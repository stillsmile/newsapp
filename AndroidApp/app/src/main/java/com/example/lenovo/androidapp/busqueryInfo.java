package com.example.lenovo.androidapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import utils.LogUtils;

public class busqueryInfo extends AppCompatActivity{


    private Context mContext;
    private Intent intent;
    private String busNum;
    private int whichShow =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busquerypage);
        mContext =this;
//        intent = new Intent();
//        intent.setClass(this,busrountingshow.class);
//        startActivity(intent);
        Button bt_search = (Button) findViewById(R.id.bt_search);

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_busNum = (EditText) findViewById(R.id.et_busNum);
                busNum = et_busNum.getText().toString();

                intent=new Intent(mContext,busrountingshow.class);
                intent.putExtra("busNum",busNum);
                startActivity(intent);

            }
        });


    }

}
