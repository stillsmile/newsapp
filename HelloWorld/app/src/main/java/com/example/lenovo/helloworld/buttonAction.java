package com.example.lenovo.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class buttonAction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_action);

        //单机事件
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("log日志","点击方法被调用了");
                Toast.makeText(buttonAction.this,"button被点击了",Toast.LENGTH_SHORT).show();
            }
        });

        //单击事件
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(myListener);

        //单击事件
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("单击事件。。。");
            }
        });
        //长按事件
        button3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                System.out.println("长按时间。。。");
//                Toast.makeText();

                //返回false不消费当前事件，如果绑定了点击事件
                //会继续执行单机事件
                return false;
            }
        });

        //触摸事件
        //元事件 action_down ，action_up ，action_move
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //event记录了事件发生时的一些信息，如何坐标等等
                int actiontype = event.getAction();
                if (actiontype == MotionEvent.ACTION_DOWN){
                    System.out.println("touch 按下。。。");
                } else if(actiontype == MotionEvent.ACTION_MOVE){
                    System.out.println("touch 移动。。。");
                } else if(actiontype == MotionEvent.ACTION_UP){
                    System.out.println("touch 松开。。。");
                }else{
                    System.out.println("没有事件。。。");
                }
                return false;
            }
        });
        //移动事件demo
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.activity_button_action);
        final Button button7 = (Button) findViewById(R.id.button7);
        viewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int actiontype = event.getAction();
                if (actiontype == MotionEvent.ACTION_DOWN){
                    System.out.println("touch 按下。。。");
                    System.out.println("移动位置X。。。"+event.getX());
                    System.out.println("移动位置Y。。。"+event.getY());
                } else if(actiontype == MotionEvent.ACTION_MOVE){
                    button7.setX(event.getX());
                    button7.setY(event.getY());
                    System.out.println("touch 移动。。。");
                } else if(actiontype == MotionEvent.ACTION_UP){
                    System.out.println("touch 松开。。。");
                }else{
                    System.out.println("没有事件。。。");
                }
                //需要阻断事件，消费他，否则不触发移动事件
                return true;
            }
        });
        //键盘移动(待调节)
//        Button button8 = (Button) findViewById(R.id.button8);
////        EditText editText = (EditText) findViewById(R.id.editText);
//        button8.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                System.out.println(""+keyCode);
//                return true;
//            }
//        });

        //焦点事件
        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                System.out.println("焦点事件。。。");
            }
        });


    }
    public  void testClick(View view){
        view.setX(view.getX()+10);
        Toast.makeText(buttonAction.this,"button空间上绑定的方法",Toast.LENGTH_SHORT);
    }
    private  View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button5){
                Toast.makeText(buttonAction.this,"解耦方法被调用(可复用式方法)",Toast.LENGTH_SHORT).show();
            }

        }
    };


}
