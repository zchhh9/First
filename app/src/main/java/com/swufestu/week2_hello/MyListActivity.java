package com.swufestu.week2_hello;

import android.app.ListActivity;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyListActivity extends ListActivity implements Runnable{
    private  static final String TAG="WWW";
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> list1=new ArrayList<String>();
        for(int i=1;i<100;i++){
            list1.add("item"+i);
        }
        String [] list_data={"one","two","three","four"};
        ListAdapter adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list_data);
        setListAdapter(adapter);

       handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==9){
                    List<String> list2=(List<String>) msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(MyListActivity.this, android.R.layout.simple_list_item_1,list2);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
        Thread t=new Thread(this);
        t.start();//自动调用this.run()
    }


    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "run: AAAA....");
        List<String > retlist=new ArrayList<String>();
        Bundle bundle=new Bundle();
        try
        {
            Document doc= null;
            doc = Jsoup.connect("https://www.usd-cny.com/bankofchina.htm").get();
            Log.i(TAG, "run: title="+doc.title());
            Elements tables=doc.getElementsByTag("table");
            Element table1=tables.first();
            Elements trs=table1.getElementsByTag("tr");
            trs.remove(0);
            for(Element tr :trs){
                Elements tds=tr.getElementsByTag("td");
                String cname=tds.get(0).text();
                String cval=tds.get(5).text();
                retlist.add(cname+"----->"+cval);
        }
        }catch (IOException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(9,retlist);
        handler.sendMessage(msg);
        Log.i(TAG, "run: 消息已发送");
    }
}