package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.androidapp.R;

import java.util.ArrayList;

import bean.NewsBean;
import view.MyImageView;

/**
 * Created by lenovo on 2018/5/17.
 */

public class NewsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NewsBean> list;

    public NewsAdapter(Context context, ArrayList<NewsBean> list) {
        this.context = context;
        this.list = list;
    }

    //用户获取list的长度
    @Override
    public int getCount() {
        return list.size();
    }

    //用于获取listview中的的条目
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //用于对listv的item进行设计
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        if(convertView != null){
            view =convertView;
        }else{
            view = View.inflate(context, R.layout.item_news_layout ,null);
        }

    //2.获取view上的子控件对象
//        ImageView item_img_icon = (ImageView) view.findViewById(R.id.item_img_icon);
//        TextView item_tv_des = (TextView) view.findViewById(R.id.item_tv_des);
//        TextView item_tv_title = (TextView) view.findViewById(R.id.item_tv_title);
            MyImageView item_img_icon = (MyImageView) view.findViewById(R.id.item_img_icon);
            TextView item_tv_des = (TextView) view.findViewById(R.id.item_tv_des);
            TextView item_tv_title = (TextView) view.findViewById(R.id.item_tv_title);
            TextView item_tv_comment = (TextView) view.findViewById(R.id.item_tv_comment);
            TextView item_tv_type = (TextView) view.findViewById(R.id.item_tv_type);
            //3.获取postion位置条目对应的list集合中的新闻数据，Bean对象
            NewsBean newsBean = list.get(position);
            //4.将数据设置给这些子控件做显示
        //        item_img_icon.setImageDrawable(newsBean.icon);//设置imageView的图片
        //        item_tv_title.setText(newsBean.title);
        //        item_tv_des.setText(newsBean.des);
            //4.将数据设置给这些子控件做显示
            item_img_icon.setImageUrl(newsBean.icon_url);
        item_tv_title.setText(newsBean.title);
        item_tv_des.setText(newsBean.des);
        item_tv_comment.setText("评论："+newsBean.comment);

    //0 ：头条 1 ：娱乐 2.体育
        switch (newsBean.type) {
            case 0:
                item_tv_type.setText("头条");
                break;
            case 1:
                item_tv_type.setText("娱乐 ");
                break;
            case 2:
                item_tv_type.setText("体育");
                break;
            default:
                break;
    }




        return view;
    }
}
