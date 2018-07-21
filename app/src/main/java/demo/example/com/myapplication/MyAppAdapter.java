package demo.example.com.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的作用：APP列表展示适配器
 * lenovo 刘珂珂
 * 2018/7/21
 * 14:30
 */

public class MyAppAdapter extends BaseAdapter {

    private List<AppBean> myAppBean = new ArrayList<>();
    private Context context;

    public MyAppAdapter(Context context) {
        this.context = context;
    }

    public MyAppAdapter() {
    }

    public void setData(List<AppBean> myAppInfos) {
        this.myAppBean = myAppInfos;
        notifyDataSetChanged();
    }

    public List<AppBean> getData() {
        return myAppBean;
    }

    @Override
    public int getCount() {
        if (myAppBean != null && myAppBean.size() > 0) {
            return myAppBean.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (myAppBean != null && myAppBean.size() > 0) {
            return myAppBean.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (myAppBean != null && myAppBean.size() > 0) {
            return position;
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        AppBean myAppInfo = myAppBean.get(position);
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_app_info, null);
            mViewHolder.iv_app_icon = convertView.findViewById(R.id.iv_app_icon);
            mViewHolder.tv_app_name = convertView.findViewById(R.id.tv_app_name);
            mViewHolder.tv_app_version = convertView.findViewById(R.id.tv_app_version);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.iv_app_icon.setImageDrawable(myAppInfo.getAppIcon());
        mViewHolder.tv_app_name.setText(myAppInfo.getAppName());
        mViewHolder.tv_app_version.setText(myAppInfo.getAppVersion());
        return convertView;
    }

    class ViewHolder {

        ImageView iv_app_icon;
        TextView tv_app_name;
        TextView tv_app_version;
    }
}
