package demo.example.com.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView lvAppList;
    private Handler mHandler = new Handler();
    private MyAppAdapter myAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册事件
        EventBus.getDefault().register(this);

        lvAppList = findViewById(R.id.lv_app_list);
        myAppAdapter = new MyAppAdapter(MainActivity.this);
        lvAppList.setAdapter(myAppAdapter);

        initAppList();

    }

    /**
     * 接收到消息重新获取应用列表
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        Toast.makeText(MainActivity.this,messageEvent.toString(),Toast.LENGTH_LONG).show();
        //更新
        initAppList();
    }

    /**
     * 加载应用列表
     */
    private void initAppList(){

        /*
          开启子线程扫描手机应用
         */

        new Thread(){
            @Override
            public void run() {
                super.run();

                //扫描得到APP列表
                final List<AppBean> appInfos = AppTool.scanLocalInstallAppList(MainActivity.this.getPackageManager());
                final List<AppBean> sort = sort(appInfos);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        myAppAdapter.setData(sort);
                    }
                });

            }
        }.start();

    }

    /**
     * 根据最后更新时间对应用列表进行排序
     */
    public List<AppBean> sort(List<AppBean> list){

        List<AppBean> arrayList = new ArrayList<>();

        HashMap<AppBean,Long> map = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), (long) Integer.parseInt(list.get(i).getLastUpdateTime()));
        }

        //将map.entrySet()转换成list
        ArrayList<Map.Entry<AppBean, Long>> mapList = new ArrayList<>(map.entrySet());
        //降序排列
        Collections.sort(mapList, new Comparator<Map.Entry<AppBean, Long>>() {
            @Override
            public int compare(Map.Entry<AppBean, Long> o1, Map.Entry<AppBean, Long> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (Map.Entry<AppBean, Long> mapping : mapList) {
            Log.e("xxx","==============="+mapping.getKey().getLastUpdateTime());
            arrayList.add(mapping.getKey());
        }

        return arrayList;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //解注册事件
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}
