package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.lenovo.androidapp.R;

import java.util.List;
import java.util.Map;

public class MyAdapter extends SimpleAdapter{
    int count = 0;
    public Context mContext;
    public List<Map<String, Integer>> mItemList;
 
    public MyAdapter(Context context, List<? extends Map<String, Integer>> data,
            int resource, String[] from, int[] to) {
       
        super(context, data, resource, from, to);
        this.mContext = context;
        mItemList = (List<Map<String, Integer>>) data;
        if(data == null){
            count = 0;
        }else{
            count = data.size();
        }
    }
    public int getCount() {
        return mItemList.size();
    }

    public Object getItem(int pos) {
        return pos;
    }

    public long getItemId(int pos) {
        return pos;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
        }
        
        Map<String ,Integer> map = mItemList.get(position);
        int image  =  map.get(""+position);
        View view = super.getView(position, convertView, parent);
        ImageView imageview = (ImageView)view.findViewById(R.id.imageview);
        TextView tv = (TextView)view.findViewById(R.id.tv);
        imageview.setBackgroundResource(image);
        tv.setText("第"+position+"行");
        return view;
    }
}

