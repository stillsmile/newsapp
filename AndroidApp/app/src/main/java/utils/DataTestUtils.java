package utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import bean.DataTestBean;
import bean.NewsBean;

/**
 * Created by lenovo on 2018/5/19.
 */

public class DataTestUtils {

    private Context mContext;
    private ArrayList<DataTestBean> list;
    private String urlConnection = "http://47.98.145.175/itheima74/servlet/GetNewsServlet";

    //http://47.98.145.175/itheima74/servlet/GetNewsServlet
    public DataTestUtils(Context mContext, ArrayList<DataTestBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public ArrayList<NewsBean> getDataTestInfo(){
        ArrayList<NewsBean> listForShow = null;
        try {
            URL url = new URL(urlConnection);
            HttpURLConnection httpsURLConnection  = (HttpURLConnection) url.openConnection();
            httpsURLConnection.setConnectTimeout(1000*10);
            httpsURLConnection.setRequestMethod("POST");
            if(httpsURLConnection.getResponseCode() == 200){
                InputStream inputStream = httpsURLConnection.getInputStream();
                String result = StreamUtils.streamToString(inputStream);
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("newss");
                for (int i=0;i < jsonArray.length(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i);
                    NewsBean newsBean = new NewsBean();
                    int id = json.getInt("id");
                    int type = json.getInt("type");
                    int comment = json.getInt("comment");
                    String time = json.getString("time");
                    String des = json.getString("des");
                    String title = json.getString("title");
                    String icon_url = json.getString("icon_url");
                    String news_url = json.getString("news_url");
//                    public String title;
//                    public String news_url;
//                    public String time;
                    newsBean.id = id;
                    newsBean.type = type;
                    newsBean.comment =comment;
                    newsBean.time =time;
                    newsBean.des =des;
                    newsBean.time = title;
                    newsBean.icon_url = icon_url;
                    newsBean.news_url = news_url;

                    LogUtils.w("netTest","id : " + id);
                    LogUtils.w("netTest","type : " + type);
                    LogUtils.w("netTest","time : " + time);
                    LogUtils.w("netTest","comment : " + comment);
                    LogUtils.w("netTest","des : " + des);
                    LogUtils.w("netTest","time : " + time);
                    LogUtils.w("netTest","icon_url : " + icon_url);
                    LogUtils.w("netTest","icon_url : " + icon_url);
                    listForShow.add(newsBean);
                }
                return listForShow;
            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


}
