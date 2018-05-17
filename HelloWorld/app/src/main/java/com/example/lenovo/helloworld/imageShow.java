package com.example.lenovo.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class imageShow extends AppCompatActivity {

    private int imageName[] = new int[]{
        R.mipmap.a1,
        R.mipmap.a2,
        R.mipmap.a3,
        R.mipmap.a4,
        R.mipmap.a5,
        R.mipmap.a6,
        R.mipmap.a7
    };

    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int showWhitch =  ++count ;
//                ImageView view = (ImageView) v;
//                view.setImageResource(imageName[showWhitch]);
//                if (showWhitch == 2){
//                    count = 0;
//                }
//            }
//        });
        final int width = this.getWindowManager().getDefaultDisplay().getWidth();
        final TextView textView = (TextView) findViewById(R.id.currentImage);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            int screenX = (int) event.getX();
            ImageView view = (ImageView) v;
                /***
                 *  0    1
                 *  1    2
                 *  2    3
                 */
                Log.w("screenX", String.valueOf(screenX));
                Log.w("width", String.valueOf(width));
                if(screenX >= (width/2)){
                    //在右边
                    if (count == 6){
                        count = -1;
                    }
                    Log.i("showWhitch", String.valueOf(count));
                    textView.setText("当前是"+  (count+2)+"张图片");
                    view.setImageResource(imageName[++count]);
                } else{
                    //在左边
                    if (count == 0){
                        count=7;
                    }

                    Log.i("showWhitch", String.valueOf(count));
                    textView.setText("当前是"+  (count)+"张图片");
                    view.setImageResource(imageName[--count]);
                }

                return false;
            }
        });
    }
}
