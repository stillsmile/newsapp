package com.example.lenovo.helloworld;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.lenovo.helloworld.R.id.textView8;

public class threadTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);

        Button button10 = (Button) findViewById(R.id.button10);
        TranslateAnimation animation = new TranslateAnimation(0,150,0,0);
        animation.setRepeatCount(30);//设置移动次数
        animation.setDuration(2000);//设置移动事件2000毫秒
        button10.setAnimation(animation);

        Button button11 = (Button) findViewById(R.id.button11);
        final TextView textView = (TextView) findViewById(textView8);

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //可读性差，必然维护性差
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);//睡眠5秒，耗时
                            System.out.println("线程睡眠五秒之后结束");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        v.post(new Runnable() {
                            @Override
                            public void run() {
                                TextView textView1 = (TextView) v;
                                textView1.setText("点之后我的值改变了");
                                textView.setText("我也改变了");
                            }
                        });
                    }
                }).start();

            }
        });

        final Button button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button12.setText("正在进行耗时操作，请等待五秒钟");
                new threadTest12().execute();
            }
        });
    }

    public class threadTest12 extends AsyncTask<Object, Object, Integer> {
        @Override
        protected Integer doInBackground(Object... params) {

            try {
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int countData = 5;
            return countData;
        }
        @Override
        protected void onPostExecute(Integer countData) {
            Toast toast =  Toast.makeText(threadTest.this,"事件开始执行",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0, 0);
            toast.show();
            TextView textView9 = (TextView) findViewById(R.id.textView9);
            textView9.setText("文本信息已经改变了。。");
        }
    }
}
