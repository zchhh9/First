package com.swufestu.week2_hello;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RMBActivity extends AppCompatActivity {
    private  static final String TAG="WWW";
    TextView output=findViewById(R.id.output);
    private float dollarRate=0.28f;
    private float euroRate=0.21f;
    private float wonRate=0.22f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmbactivity);
        Log.i("111","onCreate:开始运行了");
        Log.i(TAG,"inCreate:");
    }
    public void myClick(View btn){
        Log.i(TAG,"click");
        EditText RMB=findViewById(R.id.RMB);
        String num=RMB.getText().toString();
        if(num.length()>0 ){
            float r=0;
            float rm =Float.parseFloat(num);
            if(btn.getId()==R.id.dollar){
                r=rm*dollarRate;
            }else if(btn.getId()==R.id.euro){
                r=rm*euroRate;
            }else{
                r=rm*wonRate;
            }
            output.setText(String.valueOf(r));
        }else{
            //重置输出
            output.setText("Hello");
            Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
        }
    }
       public void openOne(View btn){
            Intent config =new Intent(this,ConfigActivity2.class);
            config.putExtra("dollar_rate_key",dollarRate);
            config.putExtra("euro_rate_key",euroRate);
            config.putExtra("won_rate_key",wonRate);

            Log.i(TAG,"openOne:dollarRate="+dollarRate);
            Log.i(TAG,"openOne:euroRate="+dollarRate);
            Log.i(TAG,"openOne:wonRate="+dollarRate);
            startActivity(config);
        }
    }
