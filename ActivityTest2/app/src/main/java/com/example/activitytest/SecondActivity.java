package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondActivity","Task id is"+getTaskId());
        //Log.d("SecondActivity",this.toString());
        setContentView(R.layout.second_layout);
        Button button2=(Button)findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent intent=new Intent(SecondActivity.this,FirstActivity.class);
                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
        //Intent intent=getIntent();
        //String data=intent.getStringExtra("extra_data");
        //Log.d("SecondActivity",data);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("SecondActivity","onDestroy");
    }
}
