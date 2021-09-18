package com.swufestu.week2_hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Score_count extends AppCompatActivity {
    int score=0;
    private  static final String TAG="WWW";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_count);
        Log.i("111","onCreate:开始运行了");
        Log.i(TAG,"inCreate:");

    }
    public void myClick(View btn){
        Log.i(TAG,"myClick:AAAA");
        if(btn.getId()==R.id.score3){
            score+=3;
        }else if(btn.getId()==R.id.score2){
            score+=2;
        }else if(btn.getId()==R.id.score1){
            score+=1;
        }else{
            score=0;
        }
        TextView output=findViewById(R.id.output);
        output.setText(String.valueOf(score));
    }

}