package com.example.lenovo.helloworld;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class progressAndHandler extends AppCompatActivity {


    private Handler handler = new Handler();
    private Handler handlerMessage = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            ProgressDialog dialog = (ProgressDialog) msg.obj;
            if(msg.arg1==1){
                System.out.print("下载成功");
            }else if(msg.arg1==2){
                System.out.print("下载失败");
            }
            dialog.cancel();
//            ViewGroup view = (ViewGroup) findViewById(R.id.progressBar6).getParent();
//            TextView textView = new TextView(progressAndHandler.this);
//            textView.setText("handlerMessage下载成功！");
//            view.addView(textView);
            TextView textView = (TextView) findViewById(R.id.textView14);
            textView.setText("handlerMessage下载成功！");

            return false;
        }
    });
    private  boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_and_handler);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ViewGroup view = (ViewGroup) findViewById(R.id.progressBar6).getParent();
                        view.removeView(findViewById(R.id.progressBar6));
                        TextView textview = new TextView(progressAndHandler.this);
                        textview.setText("下载成功");
                        view.addView(textview);
                    }
                });

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar4);
                            if (progressBar == null){
                                Toast.makeText(progressAndHandler.this,"进度条不存在",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            progressBar.incrementProgressBy(10);
//                            progressBar.getMax()==100
                            if (progressBar.getProgress()==100) {
                                ViewGroup view = (ViewGroup) findViewById(R.id.progressBar4).getParent();
                                view.removeView(findViewById(R.id.progressBar4));
                                TextView textView = new TextView(progressAndHandler.this);
                                textView.setText("下载成功");
                                view.addView(textView);
                                flag =false;
                            }
                        }
                    });
                }
            }
        }).start();





    }

    public void add(View view){
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar4);
//        progressBar.setProgress(progressBar.getProgress()+10);



        progressBar.incrementProgressBy(10);

    }
    public void reduce(View view){
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar4);
//        progressBar.setProgress(progressBar.getProgress()-10);
        progressBar.incrementProgressBy(-10);

    }

    public void UIThread(final View view){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.print("线程已经执行1");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("线程已经执行2");
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.print("线程已经执行3");
                        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar6);
                        ViewGroup progress = (ViewGroup) progressBar.getParent();
                        progress.removeView(progressBar);
                    }
                });

            }
        }).start();

    }
    public  void testdialog1(View view){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("标题信息");
        dialog.setMessage("正在下载！！！");
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message msg = new Message();
                msg.obj =dialog;
                msg.arg1 =1;
                handlerMessage.sendMessage(msg);
                Bundle bundle  = msg.getData();//bundle类似于一个map。key只能为string
                bundle.putBoolean("String", true);
            }
        }).start();


    }
    public  void testdialog2(View view){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("标题信息");
        dialog.setMessage("正在下载！！！");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(100);
        dialog.incrementProgressBy(15);
        dialog.show();

    }
}
