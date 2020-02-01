package com.example.cixiut;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btA;
    private Button btB;
    private Button btC;
    private Button btD;
    private boolean isVisible = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btA = (Button) findViewById(R.id.btA);
        btB = (Button) findViewById(R.id.btB);
        btC = (Button) findViewById(R.id.btC);
        btD = (Button) findViewById(R.id.btD);
        btB.setVisibility(View.INVISIBLE);
        btC.setVisibility(View.INVISIBLE);
        btD.setVisibility(View.INVISIBLE);
        btA.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    btB.setVisibility(View.VISIBLE);
                    btC.setVisibility(View.VISIBLE);
                    btD.setVisibility(View.VISIBLE);
                    isVisible = false;
                } else {
                    btB.setVisibility(View.INVISIBLE);
                    btC.setVisibility(View.INVISIBLE);
                    btD.setVisibility(View.INVISIBLE);
                    isVisible = true;
                }
            }
        });
    }
}
