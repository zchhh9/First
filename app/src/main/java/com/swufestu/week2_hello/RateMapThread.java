package com.swufestu.week2_hello;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RateMapThread implements Runnable{
    private static final String TAG="RateThread";
    private Handler handler;
    public RateMapThread(Handler handler) {
        this.handler=handler;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "run: AAAA....");
        List<HashMap<String, String>> retlist=new ArrayList<>();
        Bundle bundle=new Bundle();
        try
        {
            Document doc= null;
            doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/").get();
            Log.i(TAG, "run: title="+doc.title());

            Elements tables=doc.getElementsByTag("table");
            tables.remove(0);
            Element table1=tables.first();
            Elements trs=table1.getElementsByTag("tr");
            trs.remove(0);
            for(Element tr :trs){
                Elements tds=tr.getElementsByTag("td");
                String cname=tds.get(0).text();
                String cval=tds.get(5).text();
                Log.i(TAG, "run: cname"+cname+"---->"+cval);
                HashMap<String,String> map=new HashMap<String,String>();
                map.put("ItemTitle",cname);
                map.put("ItemDetail",cval);
                retlist.add(map);

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(9,retlist);
        handler.sendMessage(msg);
        Log.i(TAG, "run: 消息已发送");
    }
}
