package com.swufestu.week2_hello;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

public class RateListActivity extends AppCompatActivity {
    private  static final String TAG="WWW";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);
        ListView listView=findViewById(R.id.mylist1);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        // String[] list_data={"one","two","three","four"};
       // ListAdapter adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list_data);
        //listView.setAdapter(adapter);

        Handler handler=new Handler(){
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.i(TAG, "handleMessage: 接收消息");
                if(msg.what==9){
                    List<String> rlist=(List<String>) msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(RateListActivity.this, android.R.layout.simple_list_item_1,rlist);
                    listView.setAdapter(adapter);
                    //调整控件显示状态
                    progressBar.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);

                }

            }
        };
        RateThread rt=new RateThread(handler);
        Thread t=new Thread(rt);
        t.start();
    }
}