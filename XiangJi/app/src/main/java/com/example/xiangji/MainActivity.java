package com.example.xiangji;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_TAKE_PHOTO_CODE=1;
    public static final int REQUST_TAKE_PHOTTO_CODE2=2;
    private ImageView imageView;
    Button startbtn;
    // /storage/emulated/0/pic
    public final static String SAVED_IMAGE_PATH1=Environment.getExternalStorageDirectory().getAbsolutePath()+"/pic";//+"/pic";
    // /storage/emulated/0/Pictures
    public final static String SAVED_IMAGE_PATH=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();//.getAbsolutePath()+"/pic";//+"/pic";
    String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d("SAVED_IMAGE_PATH1",SAVED_IMAGE_PATH1);
        Log.d("SAVED_IMAGE_PATH",SAVED_IMAGE_PATH);

        //布局初始化绑定
        imageView = (ImageView) findViewById(R.id.pic);
        startbtn = (Button) findViewById(R.id.startCamera);

        //调用按钮监听
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取SD卡安装状态
                String state= Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)){

                    //设置图片保存路径
                    photoPath=SAVED_IMAGE_PATH+"/"+System.currentTimeMillis()+".png";

                    File imageDir=new File(photoPath);
                    if(!imageDir.exists()){
                        try {
                            //根据一个 文件地址生成一个新的文件用来存照片
                            imageDir.createNewFile();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    takePhotoByMethod1();
                    //takePhotoByMethod2();
                }else {
                    Toast.makeText(MainActivity.this,"SD卡未插入",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    /*
     * 接收调用相机后返回的数据
     * */
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode==REQUEST_TAKE_PHOTO_CODE&&resultCode==Activity.RESULT_OK){
            File photoFile=new File(photoPath);
            if (photoFile.exists()){
                //通过图片地址将图片加载到bitmap里面
                Bitmap bm= BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                //将拍摄的照片显示到界面上
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bm);
            }else {
                Toast.makeText(MainActivity.this,"图片文件不存在",Toast.LENGTH_LONG).show();
            }
        }else if (requestCode==REQUST_TAKE_PHOTTO_CODE2&&resultCode==Activity.RESULT_OK){
            Bundle bundle=data.getExtras();
            if (bundle!=null){
                Bitmap bm= (Bitmap) bundle.get("data");
                if (bm!=null){
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageBitmap(bm);
                }
            }else {
                Toast.makeText(MainActivity.this,"没有压缩的图片数据",Toast.LENGTH_LONG).show();
            }
        }
    }
    /*
     * 第一种方式调用系统摄像头拍照
     * */
    private void takePhotoByMethod1(){
        //实例化intent,指向摄像头
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //根据路径实例化图片文件
        File photoFile=new File(photoPath);
        //设置拍照后图片保存到文件中
        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(photoFile));
        //启动拍照activity并获取返回数据
        startActivityForResult(intent,REQUEST_TAKE_PHOTO_CODE);
    }
    /*
     * 第二种方式调用系统摄像头拍照
     * */
    /*private void takePhotoByMethod2(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUST_TAKE_PHOTTO_CODE2);
    }*/

}