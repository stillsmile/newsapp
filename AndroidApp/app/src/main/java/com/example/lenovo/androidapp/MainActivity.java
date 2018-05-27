package com.example.lenovo.androidapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext =this;
        intent = new Intent();

        Button button = (Button) findViewById(R.id.bt_news);
        button.setOnClickListener(this);
        Button bt_database = (Button) findViewById(R.id.bt_database);
        bt_database.setOnClickListener(this);
        Button bt_busHelper = (Button) findViewById(R.id.bt_busHelper);
        bt_busHelper.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bt_news :
                intent.setClass(this,newsActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_database:
                intent.setClass(this,databaseOperation.class);
                startActivity(intent);
                break;
            case R.id.bt_busHelper:
                intent.setClass(this,busqueryInfo.class);
                startActivity(intent);
                break;

            default: break;
        }

    }
}
