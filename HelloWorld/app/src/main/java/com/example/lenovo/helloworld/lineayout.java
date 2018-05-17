package com.example.lenovo.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class lineayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineayout);
//        handmakelayout();

    }

    public void autolayout(){
        setContentView(R.layout.activity_lineayout);
    }

    public void handlayout(View view){
        handmakelayout();
    }

    public  void handmakelayout(){
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final LinearLayout.LayoutParams params
                = new LinearLayout.LayoutParams(android.app.ActionBar.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);

        //所有的layout类都是viewgroup的子类
        //linearLayout.addView(child).
        //linearLayout.removeView(view);
        final TextView textView = new TextView(this);
        textView.setText("hello my first  hang make layout,点击我生成动态添加新的文字");
        textView.setTextSize(25);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ViewGroup parent = (ViewGroup) v.getParent();
                TextView textView1 = new TextView(lineayout.this);
                textView1.setTextSize(25);
                textView1.setText("动态生成字");
                textView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        parent.removeView(v);
                        Toast.makeText(lineayout.this,"已经被移除了",Toast.LENGTH_SHORT).show();
                    }
                });


                parent.addView(textView1);

            }
        });

        Button button = new Button(this);
        button.setText("切换到自动布局");
//        button.setTextSize(30);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autolayout();
            }
        });

        linearLayout.addView(textView);
        linearLayout.addView(button);
        this.setContentView(linearLayout);

    }
}
