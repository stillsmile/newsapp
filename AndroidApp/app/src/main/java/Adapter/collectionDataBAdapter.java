package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.androidapp.R;

import java.util.ArrayList;

import bean.CollectionDataBean;


/**
 * Created by lenovo on 2018/6/20.
 */

public class collectionDataBAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<CollectionDataBean> list;

    public collectionDataBAdapter(Context context, ArrayList<CollectionDataBean> list) {
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
        if(convertView != null){
            view =convertView;
        }else{
            view = View.inflate(context, R.layout.buscollectiondatashow ,null);
        }
        CollectionDataBean collectionDataBean = list.get(position);
        TextView tv_id = (TextView) view.findViewById(R.id.tv_id);
        TextView tv_buslineiD = (TextView) view.findViewById(R.id.tv_buslineiD);
        TextView tv_busnum = (TextView) view.findViewById(R.id.tv_busnum);
        TextView tv_collectionShow = (TextView) view.findViewById(R.id.tv_collectionShow);
        tv_id.setText(collectionDataBean.id);
        tv_buslineiD.setText(collectionDataBean.buslineiD);
        tv_busnum.setText(collectionDataBean.busnum);
        tv_collectionShow.setText(collectionDataBean.isShow);

        return view;
    }
}
