package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.androidapp.R;

import java.util.ArrayList;

import bean.BusRounte;

import static android.view.View.inflate;

/**
 * Created by lenovo on 2018/6/3.
 */

public class BusAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<BusRounte> list;

    public BusAdapter(Context context, ArrayList<BusRounte> list) {
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
            view = inflate(context, R.layout.activity_whichroutechoose, null);
        }
        BusRounte busRounteInfo = list.get(position);
        TextView busName = (TextView) view.findViewById(R.id.item_tv_title);
        busName.setText(busRounteInfo.busName);
        busName.setTag(busRounteInfo.lineID);
        TextView busDirector = (TextView) view.findViewById(R.id.item_tv_des);
        busDirector.setText((busRounteInfo.busDirector).replace("\r\n", "").trim());
        return view;
    }
}