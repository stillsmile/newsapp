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

    //创建一个Handler
    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            String list[] = (String[]) msg.obj;
            int which = msg.what;
            String lineNum = list[0];
            String linename = list[1];
            String lineroute = list[2];
            if (which == 1){
                TextView tv_busDirectionOppsite = (TextView) findViewById(R.id.tv_busDirectionOppsite);
                tv_busDirectionOppsite.setText(linename);
                TextView tv_direction = (TextView) findViewById(R.id.tv_direction);
                tv_direction.setText(lineroute);
            } else {
                TextView tv_busNameOppsite = (TextView) findViewById(R.id.tv_busNameOppsite);
                tv_busNameOppsite.setText(linename);
                TextView tv_directionOppsite = (TextView) findViewById(R.id.tv_directionOppsite);
                tv_directionOppsite.setText(lineroute);
                whichShow = 0;
            }

        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busquerypage);
        mContext =this;
        intent = new Intent();

        Button bt_search = (Button) findViewById(R.id.bt_search);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_busNum = (EditText) findViewById(R.id.et_busNum);
                busNum = et_busNum.getText().toString();
                setContentView(R.layout.whichroutechoose);
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
        });

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
                System.out.println("buslineID =" +buslineID + ",busName =" +busName + ",busDirector" + busDirector);
                LogUtils.w("sss",buslineID);
                LogUtils.w("sss",busName);
                LogUtils.w("sss",busDirector);
                String list[] = {buslineID,busName,busDirector};
                Message msg = Message.obtain();
                msg.what = ++ whichShow;//指定msg的code ，在接受的时候可以根据what判断是哪个msg
                msg.obj = list;
                handler.sendMessage(msg);

            }
        }

    }

}
