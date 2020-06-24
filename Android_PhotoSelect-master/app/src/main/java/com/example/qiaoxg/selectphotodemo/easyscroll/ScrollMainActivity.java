package com.example.qiaoxg.selectphotodemo.easyscroll;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qiaoxg.selectphotodemo.MainActivity;
import com.example.qiaoxg.selectphotodemo.R;


public class ScrollMainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageView mainbtn1;
    private ImageView mainbtn2;
    private ImageView mainbtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.scrollactivity_main);

        mainbtn1=(ImageView) findViewById(R.id.imageVie1);
        mainbtn2=(ImageView) findViewById(R.id.imageVie2);
        mainbtn3=(ImageView) findViewById(R.id.imageVie3);
        mainbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScrollMainActivity.this, com.example.qiaoxg.selectphotodemo.MainActivity.class);
                //intent.putExtra("imagpath", imagUrl);
                startActivity(intent);
            }
        });

        mainbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScrollMainActivity.this, com.example.qiaoxg.selectphotodemo.imagecarouseldemo.MainActivity.class);
                //intent.putExtra("imagpath", imagUrl);
                startActivity(intent);
            }
        });
        mainbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doStartApplicationWithPackageName("com.example.zhanting");
            }
        });

        mRecyclerView = findViewById(R.id.mRecyclerVie);
        mRecyclerView.setAdapter(new SplashAdapter(ScrollMainActivity.this));
        mRecyclerView.setLayoutManager(new ScollLinearLayoutManager(ScrollMainActivity.this));

        //smoothScrollToPosition滚动到某个位置（有滚动效果）
        mRecyclerView.smoothScrollToPosition(Integer.MAX_VALUE / 2);
    }
    private void doStartApplicationWithPackageName(String packagename) {
        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            //BDToast.showToast(getText(R.string.app_not_found).toString());
            Toast.makeText(getApplicationContext(), "app_not_found", Toast.LENGTH_SHORT).show();
            return;
        }
        else
            Toast.makeText(getApplicationContext(), "open successfully", Toast.LENGTH_SHORT).show();

        Intent resolveIntent = getPackageManager().getLaunchIntentForPackage(packagename);// 这里的packname就是从上面得到的目标apk的包名
        startActivity(resolveIntent);// 启动目标应用
    }


}

