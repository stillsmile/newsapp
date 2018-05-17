package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.appformyself.R;

import java.util.ArrayList;

import bean.NewsBean;
import view.MyImageView;

/**
 * Created by lenovo on 2018/5/16.
 */

public class NewsAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<NewsBean> list;
    public NewsAdapter(Context mContext , ArrayList<NewsBean> list) {
        this.list = list;
        this.context = mContext;
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
        View view =null;
        if(convertView != null){
            view = convertView;
        } else {
            //context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)包一层作为codify的返回值,一般传null
//			view = View.inflate(context, R.layout.item_news_layout, null);//将一个布局文件转换成一个view对象
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_news_layout, null);
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
