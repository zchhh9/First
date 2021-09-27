package com.swufestu.week2_hello;

import android.content.Intent;
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

public class RMBActivity extends AppCompatActivity {
    private  static final String TAG="WWW";
    TextView output;
    EditText RMB;
    private float dollarRate=0.1f;
    private float euroRate=0.2f;
    private float wonRate=0.3f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmbactivity);
        Log.i("111","onCreate:开始运行了");
        Log.i(TAG,"inCreate:");
        RMB=findViewById(R.id.input);
        output=findViewById(R.id.output);
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
            /*dollarRate=data.getFloatExtra("dollar_key",0.1f);
            euroRate=data.getFloatExtra("euro_key",0.1f);
            wonRate=data.getFloatExtra("won_key",0.1f);*/
            Bundle bundle=data.getExtras();
            dollarRate=bundle.getFloat("dollar_key",0.1f);
            euroRate=bundle.getFloat("euro_key",0.1f);
            wonRate=bundle.getFloat("won_key",0.1f);
            Log.i(TAG, "onActivityResult: dollarrate=" + dollarRate);
            Log.i(TAG, "onActivityResult: eurorate=" + euroRate);
            Log.i(TAG, "onActivityResult: wonrate=" + wonRate);
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
}
