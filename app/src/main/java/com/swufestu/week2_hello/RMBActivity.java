package com.swufestu.week2_hello;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RMBActivity extends AppCompatActivity implements  Runnable{
    private  static final String TAG="WWW";
    TextView output;
    EditText RMB;
    private float dollarRate=0.28f;
    private float euroRate=0.21f;
    private float wonRate=501f;

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmbactivity);
        Log.i("111","onCreate:开始运行了");
        Log.i(TAG,"inCreate:");
        RMB=findViewById(R.id.input);
        output=findViewById(R.id.output);

        //获取SharedPreferences对象
        SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        dollarRate=sharedPreferences.getFloat("dollar_rate",0.1f);
        euroRate=sharedPreferences.getFloat("euro_rate",0.2f);
        wonRate=sharedPreferences.getFloat("won_rate",0.3f);
        Log.i(TAG, "onCreate: get from sp dollar="+dollarRate);
        Log.i(TAG, "onCreate: get from sp euro="+euroRate);
        Log.i(TAG, "onCreate: get from sp won="+wonRate);

        handler=new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage:收到消息 ");
                if(msg.what==6){
                    String str=(String)msg.obj;
                    Log.i(TAG, "handleMessage: str="+str);
                }
                super.handleMessage(msg);
            }
        };
        //开启线程
        Thread t=new Thread(this);
        t.start();//自动调用this.run()        
    }
    public void myClick(View btn){
        Log.i(TAG,"click");
        String num=RMB.getText().toString();
        if(num.length()>0){
            float result=0;
            float rm =Float.parseFloat(num);
            if(btn.getId()==R.id.dollar){
                result=rm*dollarRate;
            }else if(btn.getId()==R.id.euro){
                result=rm*euroRate;
            }else{
                result=rm*wonRate;
            }
            output.setText(String.format("%.2f",result));
        }else{
            //重置输出
            //output.setText("Hello");
            Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
        }
    }
    public void openOne(View btn){
        openconfig();
    }

    private void openconfig() {
        Intent config = new Intent(this, ConfigActivity2.class);
        config.putExtra("dollar_rate_key", dollarRate);
        config.putExtra("euro_rate_key", euroRate);
        config.putExtra("won_rate_key", wonRate);

        Log.i(TAG, "openOne:dollarRate=" + dollarRate);
        Log.i(TAG, "openOne:euroRate=" + dollarRate);
        Log.i(TAG, "openOne:wonRate=" + dollarRate);
        startActivityForResult(config, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1 && resultCode==3){
            dollarRate=data.getFloatExtra("dollar_key",0.1f);
            euroRate=data.getFloatExtra("euro_key",0.1f);
            wonRate=data.getFloatExtra("won_key",0.1f);

            /*Bundle bundle=data.getExtras();
            dollarRate=bundle.getFloat("dollar_key",0.1f);
            euroRate=bundle.getFloat("euro_key",0.1f);
            wonRate=bundle.getFloat("won_key",0.1f);*/

            Log.i(TAG, "onActivityResult: dollarrate=" + dollarRate);
            Log.i(TAG, "onActivityResult: eurorate=" + euroRate);
            Log.i(TAG, "onActivityResult: wonrate=" + wonRate);

            //保存数据到sp
            SharedPreferences sp=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putFloat("dollar_rate",dollarRate);
            editor.putFloat("euro_rate",euroRate);
            editor.putFloat("won_rate",wonRate);
            editor.apply();
            Log.i(TAG, "onActivityResult: save to sp dollar="+dollarRate);
            Log.i(TAG, "onActivityResult: save to sp euro="+euroRate);
            Log.i(TAG, "onActivityResult: save to sp won="+wonRate);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_set){
            openconfig();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "run: ....");
        //获取网络数据
        URL url=null;
        try {
            url=new URL("https://www.usd-cny.com/bankofchina.htm");
            //url=new URL("https://www.swufe.edu.cn/");
            HttpURLConnection http=(HttpURLConnection) url.openConnection();
            InputStream in=http.getInputStream();
            String html=inputStream2String(in);
            Log.i(TAG, "run: html="+html);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //发送消息给主线程
        Message msg=handler.obtainMessage();
        msg.what=6;
        msg.obj="Hello from run";
        handler.sendMessage(msg);
        Log.i(TAG, "run: 消息已发送");
    }

    private String inputStream2String(InputStream inputStream) throws IOException{
        final int bufferSize=1024;
        final char[] buffer=new char[bufferSize];
        final StringBuilder out= new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"gb2312");
        //Reader in = new InputStreamReader(inputStream,"utf-8");
        while(true){
            int rsz=in.read(buffer,0,buffer.length);
            if(rsz<0)
                break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }


}
