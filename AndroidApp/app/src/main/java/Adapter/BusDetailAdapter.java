package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.androidapp.R;

import java.util.ArrayList;

import bean.BusDetailInfoBean;

import static android.view.View.inflate;

/**
 * Created by lenovo on 2018/5/28.
 */

public class BusDetailAdapter extends BaseAdapter {

    private Context context;
    ArrayList<BusDetailInfoBean> list;
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
            view = inflate(context, R.layout.whichroutechoose, null);
        }
        BusDetailInfoBean busDetailData = list.get(position);
        TextView busName = (TextView) view.findViewById(R.id.item_tv_title);
        busName.setText(busDetailData.StationCName);
        TextView busDirector = (TextView) view.findViewById(R.id.item_tv_des);
        busDirector.setText(busDetailData.InTime);
        if(busDetailData.InTime =="" || busDetailData.InTime == null){
        }else{
//            view.setBackground(android.R.color.darker_gray);
        }


        return view;
    }
}
