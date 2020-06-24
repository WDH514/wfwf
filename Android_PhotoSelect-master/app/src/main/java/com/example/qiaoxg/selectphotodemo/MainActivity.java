package com.example.qiaoxg.selectphotodemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiaoxg.selectphotodemo.model.CameraSdkParameterInfo;

public class MainActivity extends Activity {

    private RadioGroup mChoiceMode, mShowCamera, mCrop, mFilter;
    private EditText mRequestNum;
    private CameraSdkParameterInfo mCameraSdkParameterInfo = new CameraSdkParameterInfo();

    private ImageView backimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //((TextView) findViewById(R.id.camerasdk_actionbar_title)).setText("绣儿美图");

        //mChoiceMode = (RadioGroup) findViewById(R.id.choice_mode);
        //mShowCamera = (RadioGroup) findViewById(R.id.show_camera);
        //mRequestNum = (EditText) findViewById(R.id.request_num);
        //mCrop = (RadioGroup) findViewById(R.id.rg_crop);
        //mFilter = (RadioGroup) findViewById(R.id.rg_filter);

        //mRequestNum.setEnabled(false);
       // mRequestNum.setText("");
        /*mChoiceMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.multi) {
                    mRequestNum.setEnabled(true);
                } else {
                    mRequestNum.setEnabled(false);
                    mRequestNum.setText("");
                }
            }
        });*/

        backimage=(ImageView) findViewById(R.id.backbtn);
        backimage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        findViewById(R.id.button).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //boolean mode_flag = mChoiceMode.getCheckedRadioButtonId() == R.id.single;
                mCameraSdkParameterInfo.setSingle_mode(true);

                //boolean camera_flag = mShowCamera.getCheckedRadioButtonId() == R.id.show;
                mCameraSdkParameterInfo.setShow_camera(true);


                //if (!TextUtils.isEmpty(mRequestNum.getText())) {
                    //int maxNum = Integer.valueOf(mRequestNum.getText().toString());
                    mCameraSdkParameterInfo.setMax_image(9);
                //}

                //boolean crop_flag = mCrop.getCheckedRadioButtonId() == R.id.crop_yes;
                mCameraSdkParameterInfo.setCroper_image(false);

                /*if (crop_flag) {
                    //暂时只支持单张即Single_mode模式必须为true
                    if (!mCameraSdkParameterInfo.isSingle_mode()) {
                        Toast.makeText(MainActivity.this, "选择模式必须为单选", Toast.LENGTH_LONG).show();
                        ;
                        return;
                    }
                }*/

                //boolean filter_flag = mFilter.getCheckedRadioButtonId() == R.id.filter_yes;
                mCameraSdkParameterInfo.setFilter_image(true);

                /*if(filter_flag){
                	//暂时只支持单张即Single_mode模式必须为true
                	if(!mCameraSdkParameterInfo.isSingle_mode()){
                		Toast.makeText(MainActivity.this, "选择模式必须为单选", Toast.LENGTH_LONG).show();;
                		return;
                	}
                }*/

                Intent intent = new Intent();
                intent.setClass(getApplication(), PhotoPickActivity.class);
                //intent.setClassName(getApplication(), "com.muzhi.camerasdk.CameraActivity");

                Bundle b = new Bundle();
                b.putSerializable(CameraSdkParameterInfo.EXTRA_PARAMETER, mCameraSdkParameterInfo);
                intent.putExtras(b);
                startActivityForResult(intent, CameraSdkParameterInfo.TAKE_PICTURE_FROM_GALLERY);

            }
        });

        initPermission();

    }

    private void initPermission() {
        //请求Camera权限 与 文件读写 权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}, 1);
        }
    }

    public void openFilterActivity() {

    }

    //跳转到返回结果
    public void openResultActivity() {

    }


    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == CameraSdkParameterInfo.TAKE_PICTURE_FROM_GALLERY) {
            if (data != null) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtras(data.getExtras());
                startActivity(intent);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/
}
