package com.swufestu.week2_hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class twoteam extends AppCompatActivity {
    int scoreA=0;
    int scoreB=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twoteam);

    }
    public void click(View btn){
        if(btn.getId()==R.id.point3a){
            scoreA+=3;
        }else if(btn.getId()==R.id.point2a){
            scoreA+=2;
        }else if(btn.getId()==R.id.point1a){
            scoreA+=1;
        }else{
            scoreA=0;
            scoreB=0;
        }
        show();
    }
    public void myclickb(View btn){
        if(btn.getId()==R.id.point3b){
            scoreB+=3;
        }else if(btn.getId()==R.id.point2b){
            scoreB+=2;
        }else if(btn.getId()==R.id.point1b){
            scoreB+=1;
        }
        show();
    }
    private void show(){
        TextView output1=findViewById(R.id.output1);
        TextView output2=findViewById(R.id.output2);
        output1.setText(String.valueOf(scoreA));
        output2.setText(String.valueOf(scoreB));
    }

}