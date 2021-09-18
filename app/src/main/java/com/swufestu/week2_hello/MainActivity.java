package com.swufestu.week2_hello;

import android.nfc.Tag;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity  {
    private  static final String TAG="WWW";
    TextView output;
    EditText editw;
    EditText edith;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt);
        output=findViewById(R.id.out);
        Log.i("111","onCreate:开始运行了");
        Log.i(TAG,"inCreate:");
        editw=findViewById(R.id.inputw);
        edith=findViewById(R.id.inputh);
        //Button btn=findViewById(R.id.btn);
        //btn.setOnClickListener(this);//this表示窗口对象
    }
    public void myClick(View v){
        Log.i(TAG,"myClick:AAAA");
        //获取用户输入
        String height=edith.getText().toString();
        String weight=editw.getText().toString();
        String wtype=null;
        //显示输出控件
        if((height!=null)&&(weight!=null)){
            double ht = Double.parseDouble(height);
            double wt=Double.parseDouble(weight);
            double BMI=wt/(ht*ht);
            if(BMI<=18.4){
                wtype="偏瘦";
            }else if(BMI<=23.9){
                wtype="正常";
            }else if(BMI<=27.9){
                wtype="过重";
            }else{
                wtype="肥胖";
            }
            DecimalFormat decimalFormat =new DecimalFormat("0.00");
            String rst = decimalFormat.format(BMI);
            output.setText("你的BMI指数为："+rst+"\n你的体型分类为："+wtype);
        }

    }
}