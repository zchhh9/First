package com.swufestu.week2_hello;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ConfigActivity2 extends AppCompatActivity {
    private  static final String TAG="ConfigActivity2";
    EditText dollarText;
    EditText euroText;
    EditText wonText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config2);
        Intent intent =getIntent();
        float dollar2=intent.getFloatExtra("dollar_rate_key",0.0f);
        float euro2=intent.getFloatExtra("euro_rate_key",0.0f);
        float won2=intent.getFloatExtra("won_rate_key",0.0f);

        Log.i(TAG,"onCreate:dollar2="+dollar2);
        Log.i(TAG,"onCreate:euro2="+euro2);
        Log.i(TAG,"onCreate:won2="+won2);

        dollarText=findViewById(R.id.text_dollar);
        euroText=findViewById(R.id.text_euro);
        wonText=findViewById(R.id.text_won);
        //将汇率填入控件中
        dollarText.setText(String.valueOf(dollar2));
        euroText.setText(String.valueOf(euro2));
        wonText.setText(String.valueOf(won2));
    }
    public void save(View btn){
       Log.i(TAG,"save");
       //获取新的值
       float newdollar= Float.parseFloat(dollarText.getText().toString());
       float neweuro= Float.parseFloat(euroText.getText().toString());
       float newwon= Float.parseFloat(wonText.getText().toString());

       Log.i(TAG,"获取到新的值");
       Log.i(TAG,"onCreate:newdollar="+newdollar);
       Log.i(TAG,"onCreate:neweuro="+neweuro);
       Log.i(TAG,"onCreate:newwon="+newwon);
       Intent intent=getIntent();
       //保存到Bundle或放入到Extra
       intent.putExtra("dollar_key",newdollar);
       intent.putExtra("euro_key",neweuro);
       intent.putExtra("won_key",newwon);

      /* Bundle bdl=new Bundle();
       bdl.putFloat("dollor_key",newdollar);
       bdl.putFloat("euro_key",neweuro);
       bdl.putFloat("won_key",newwon);
       intent.putExtras(bdl);*/
       setResult(3,intent);
       //返回到调用页面
       finish();
    }

}