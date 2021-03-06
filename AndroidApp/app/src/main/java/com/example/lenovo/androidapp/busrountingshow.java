package com.example.lenovo.androidapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import Adapter.BusAdapter;
import bean.BusRounte;
import utils.LogUtils;

public class busrountingshow extends AppCompatActivity {

    private Context mContext;
    private Intent intent;
    private String busNum;
    private int whichShow = 0;
    ArrayList<BusRounte> busData = new ArrayList<BusRounte>();

    //创建一个Handler
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
//            String list[] = (String[]) msg.obj;
            int which = msg.what;
            if (which == 1) {
                ListView busListView = (ListView) findViewById(R.id.lv_busWhichChoose);
                BusAdapter busAdapter = new BusAdapter(mContext, busData);
                busListView.setAdapter(busAdapter);
                busListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        BusRounte busRounte = (BusRounte) parent.getItemAtPosition(position);
                        String buslineID = busRounte.lineID;
                        String busDirector = busRounte.busDirector;
                        intent = new Intent(mContext, busdetailinfo.class);
                        intent.putExtra("buslineID", buslineID);
                        intent.putExtra("busNum", busNum);
                        intent.putExtra("busDirector", busDirector);
                        startActivity(intent);
                    }
                });
            } else {
                whichShow = 0;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busrountingshow);
        mContext = this;
        intent = getIntent();
        busNum = intent.getStringExtra("busNum");

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setIcon(R.drawable.bus);//设置ActionBar的icon图标
        supportActionBar.setTitle("当前行驶的路线是 :" + busNum);//设置ActionBar的标题
        supportActionBar.setHomeButtonEnabled(true);//主键按钮能否可点击
        supportActionBar.setDisplayHomeAsUpEnabled(true);//显示返回图标

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getRounteInfo(busNum);
                } catch (ParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getRounteInfo(String busname) throws ParserException, IOException {

        URL url = new URL("http://bus.2500.tv/line.php?line="
                + URLEncoder.encode(busname, "utf-8"));
        Parser parser = new Parser((HttpURLConnection) url.openConnection());
        NodeList nodes = parser.parse(new TagNameFilter("dd"));
        if (nodes != null) {
            for (int i = 0; i < nodes.size(); i++) {
                Node textnode = (Node) nodes.elementAt(i);
                // System.out.println("toHtml   :"+textnode.toHtml());
                int lineIDbegin = textnode.toHtml().indexOf("lineID=") + 7;
                int lineIDend = textnode.toHtml().indexOf("&roLine");
                String lineID = textnode.toHtml().substring(lineIDbegin,
                        lineIDend);
                System.out.println("lineID =  " + lineID);
                System.out.println("測試2 =  "
                        + textnode.getFirstChild().getNextSibling()
                        .getNextSibling().toPlainTextString());
                System.out.println("測試3 =  "
                        + textnode.getLastChild().getPreviousSibling()
                        .toPlainTextString());
                System.out.println("=================================================");
                String buslineID = lineID;
                String busName = textnode.getFirstChild().getNextSibling().getNextSibling().toPlainTextString();
                String busDirector = textnode.getLastChild().getPreviousSibling().toPlainTextString();
                System.out.println("buslineID =" + buslineID + ",busName =" + busName + ",busDirector" + busDirector);
                LogUtils.w("sss", buslineID);
                LogUtils.w("sss", busName);
                LogUtils.w("sss", busDirector);
                String list[] = {buslineID, busName, busDirector};
//                Message msg = Message.obtain();
//                msg.what = ++ whichShow;//指定msg的code ，在接受的时候可以根据what判断是哪个msg
//                msg.obj = list;
//                handler.sendMessage(msg);
                BusRounte busRounte = new BusRounte();
                busRounte.busName = busName;
                busRounte.lineID = buslineID;
                busRounte.busDirector = busDirector;
                busData.add(busRounte);
            }
            handler.sendEmptyMessage(1);
        }

    }

//    class BusAdapter extends BaseAdapter {
//
//        private Context context;
//        private ArrayList<BusRounte> list;
//
//        BusAdapter(Context mContent, ArrayList<BusRounte> list) {
//            this.context = mContext;
//            this.list = list;
//        }
//
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return list.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View view = null;
//            if (convertView != null) {
//                view = convertView;
//            } else {
//                view = inflate(context, R.layout.activity_whichroutechoose, null);
//            }
//            BusRounte busRounteInfo = list.get(position);
//            TextView busName = (TextView) view.findViewById(R.id.item_tv_title);
//            busName.setText(busRounteInfo.busName);
//            busName.setTag(busRounteInfo.lineID);
//            TextView busDirector = (TextView) view.findViewById(R.id.item_tv_des);
//            busDirector.setText((busRounteInfo.busDirector).replace("\r\n", "").trim());
//            return view;
//        }
//    }

    //添加返回的事件  同上一个activity的返回方法
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


}
