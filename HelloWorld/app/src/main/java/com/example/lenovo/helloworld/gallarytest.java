package com.example.lenovo.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class gallarytest extends AppCompatActivity {

    private int images[] = new int[]{
            R.mipmap.a1,
            R.mipmap.a2,
            R.mipmap.a3,
            R.mipmap.a4,
            R.mipmap.a5,
            R.mipmap.a6,
            R.mipmap.a7
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallarytest);
        Gallery gallery = (Gallery) findViewById(R.id.gallary);
        final ImageView imageView = (ImageView) findViewById(R.id.imageview11);

        myAdapter adapter =new myAdapter(this,images);
        gallery.setAdapter(adapter);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imageView.setImageResource(images[position]);
            }
        });

    }

    class myAdapter extends BaseAdapter{
        private Context context;
        private  int images[];

        public myAdapter(Context context, int[] images) {
            this.context = context;
            this.images = images;
        }


        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new Gallery.LayoutParams(360,480));
            imageView.setImageResource(images[position]);

            return imageView;
        }
    }
}
