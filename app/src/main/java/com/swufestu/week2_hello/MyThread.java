package com.swufestu.week2_hello;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.util.logging.Handler;

public class MyThread  implements Runnable{

    private static final String TAG="MyThread";
    private Handler handler;

    public void setHandler(Handler handler){
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
        Bundle bundle=new Bundle();
        //获取网络数据

    }
}
