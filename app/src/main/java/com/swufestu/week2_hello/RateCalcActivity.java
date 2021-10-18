package com.swufestu.week2_hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class RateCalcActivity extends AppCompatActivity {

    String TAG="RateCalc";
    float rate=0f;
    EditText inp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_calc);
        String title=getIntent().getStringExtra("title");
        rate=getIntent().getFloatExtra("rate",0f);

        Log.i(TAG, "onCreate: title ="+title);
        Log.i(TAG, "onCreate: rate="+rate);
        ((TextView)findViewById(R.id.title2)).setText(title);
        inp2=(EditText)findViewById(R.id.inp2);
        inp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                TextView show=(TextView) RateCalcActivity.this.findViewById(R.id.show2);
                if(editable.length()>0){
                    float val=Float.parseFloat(editable.toString());
                    show.setText(val+"人民币=======>"+(100/rate*val)+title);
                }else {
                    show.setText("");
                }
            }
        });

    }
}