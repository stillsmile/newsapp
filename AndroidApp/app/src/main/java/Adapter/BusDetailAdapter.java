package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.androidapp.R;

import java.util.ArrayList;

import bean.BusDetailInfoBean;
import utils.LogUtils;

import static android.view.View.inflate;

/**
 * Created by lenovo on 2018/5/28.
 */

public class BusDetailAdapter extends BaseAdapter {

    private Context context;
    public ArrayList<BusDetailInfoBean> list;
    public BusDetailAdapter(Context context, ArrayList<BusDetailInfoBean> list){
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = inflate(context, R.layout.activity_busrountedata, null);
        }
        BusDetailInfoBean busDetailData = list.get(position);
        TextView busName = (TextView) view.findViewById(R.id.item_tv_title);
        busName.setText(busDetailData.StationCName);
        TextView busDirector = (TextView) view.findViewById(R.id.item_tv_des);
        TextView StationCNameNum = (TextView) view.findViewById(R.id.item_img_icon);
        StationCNameNum.setText(position +1 + "");
        busDirector.setText("");

//        BusInfo

        String BusInfo = busDetailData.BusInfo;
        String busInTime = busDetailData.InTime;
        String StationCName = busDetailData.StationCName;
        String Code = busDetailData.Code;
        String ID = busDetailData.ID;
        view.setBackgroundColor(Color.WHITE);
        if(busInTime.equals("") || busInTime.length() == 0){
        } else{
            busDirector.setText(BusInfo + "             "+busDetailData.InTime );
            busDirector.setTextColor(Color.RED);
            view.setBackgroundColor(Color.GRAY);
        }
        LogUtils.w("sss","busDetailData.InTime=" +busDetailData.InTime +"==");
        LogUtils.w("aa","测试数据的更新" + position);


        return view;
    }
}
