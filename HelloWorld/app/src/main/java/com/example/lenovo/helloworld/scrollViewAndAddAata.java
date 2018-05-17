package com.example.lenovo.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.testClass.po.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class scrollViewAndAddAata extends AppCompatActivity {
    String ss[] = new String[]{
            "静态的数据一",
            "静态的数据二",
            "静态的数据三",
            "静态的数据四",
            "静态的数据五",
    };


    private int count = 0;

    private List<User> list = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_and_add_aata);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.testAddData);
        for (; count < 30; count++) {
            TextView textView = new TextView(this);
            textView.setText("静态的准备数据一" + count);
            textView.setTextSize(25);
            linearLayout.addView(textView);
        }
        //如何知道scrollview滚动到底部了

        final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollViewtest);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    int scrollY = v.getScrollY();//scrollY返回scrollview 对象  以及向上滚动的长度
                    int height = v.getHeight(); //scrollview 显示的高度
                    ScrollView scrollView1 = (ScrollView) v;
                    int linearlayoutHeight = scrollView1.getChildAt(0).getMeasuredHeight();

                    System.out.println("向上滚动的长度" + scrollY + ",显示的高度" + height +
                    "屏幕的实际高度" + linearlayoutHeight);
                    if (scrollY + height == linearlayoutHeight) {

                        Toast.makeText(scrollViewAndAddAata.this,"到达底部",Toast.LENGTH_SHORT).show();
                        int max  = count + 30;
                        for (; count < max; count++) {
                            TextView textView = new TextView(scrollViewAndAddAata.this);
                            textView.setText("静态的准备数据一" + count);
                            textView.setTextSize(25);
                            linearLayout.addView(textView);
                        }
                    }
                }

                return false;
            }
        });




    }

    public void objectDataShow(View view){
        setContentView(R.layout.listviewshow);

        System.out.println("一进入该方法一进入该方法一进入该方法一进入该方法");
//        适配器部分
        ListView listView = (ListView) findViewById(R.id.listviewshowTest);
        for (int i = 0 ; i < 10 ; i ++){
            User user = new User("张三"+i,"18861855124",i+10);
            list.add(user);
        }

//        listView.setAdapter(new MyAdapter(this,list));

        List<Map<String,String>> data = new ArrayList<Map<String,String>>();
        for (int i=0 ;i<10;i++){
            Map<String,String> map =new HashMap<String,String>();
            map.put("name","wei"+i);
            map.put("phone","18861855124"+i);
            map.put("age","12322"+i);
            data.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                data,
                R.layout.baselayout,
                new String[]{"age","phone","name"},
                new int[]{R.id.textView18,R.id.textView19,R.id.textView20});
        listView.setAdapter(simpleAdapter);


    }

    public void gridviewDataShow(View view){
        setContentView(R.layout.gridviewdatashow);
        System.out.println("一进入该方法一进入该方法一进入该方法一进入该方法");

        GridView gridView = (GridView) findViewById(R.id.GridViewTest);

        int images [] = new int[]{
                R.mipmap.a1,
                R.mipmap.a2,
                R.mipmap.a3,
                R.mipmap.a4,
                R.mipmap.a5,
                R.mipmap.a6,
                R.mipmap.a7,
                R.mipmap.a1,
                R.mipmap.a2,
                R.mipmap.a3,
                R.mipmap.a4,
                R.mipmap.a5,
                R.mipmap.a6,
                R.mipmap.a7
        };
        gridView.setAdapter(new MyAdapter1(this,images));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(scrollViewAndAddAata.this,""+position+id,Toast.LENGTH_SHORT).show();
            }
        });


    }
    private  class  MyAdapter1 extends BaseAdapter{

        private Context context;
        private int[] images;

        public MyAdapter1(Context context, int[] images) {
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

            int resId = images[position];
            ImageView imageView =new ImageView(context);
            imageView.setImageResource(resId);
            imageView.setMinimumWidth(300);
            imageView.setMinimumWidth(300);
            return imageView;
        }
    }




}
