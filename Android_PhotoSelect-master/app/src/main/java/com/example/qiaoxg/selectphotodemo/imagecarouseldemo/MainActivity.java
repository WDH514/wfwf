package com.example.qiaoxg.selectphotodemo.imagecarouseldemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiaoxg.selectphotodemo.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import com.example.qiaoxg.selectphotodemo.imagecarouseldemo.list.Fruit;
import com.example.qiaoxg.selectphotodemo.imagecarouseldemo.list.FruitAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 图片轮播控件
    private ViewPager mViewPager;
    private TextView mTvPagerTitle;
    private LinearLayout mLineLayoutDot;
    private ImageCarousel imageCarousel;
    private List<View> dots;//小点

    // 图片数据，包括图片标题、图片链接、数据、点击要打开的网站（点击打开的网页或一些提示指令）
    private List<ImageInfo> imageInfoList;
    private List<Fruit> fruitList = new ArrayList<>();

    private Button backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maina);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

        backbutton=(Button) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        initView();
        initEvent();
        imageStart();
        initFruits(); //初始化水果数据
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);  //ArrayAdaptero它可以通过泛型来指定 要适配的数据类型，然后在构造函数中把要适配的数据传入。ArrayAdapter有多个构造函数的重载，你应该根据实际情况选择最合适的一种。这里由于我们提供的数据都是字符串，因此将 ArrayAdapter的泛型指定为String,然后在ArrayAdaptei■的构造函数中依次传入当前上下文、 ListView子项布局的id,以及要适配的数据。注意，我们使用了 android.R. layout. simple_ list item l作为ListView子项布局的id,这是一个Android内置的布局文件，里面只有一个 TextView,可用于简单地显示一段文本。这样适配器对象就构建好了。
        ListView listview= (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter); //还需要调用ListView的setAdapter()方法，将构建好的适配器对象传递进去，这样 ListView和数据之间的关联就建立完成了。
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Fruit fruit = fruitList.get(position);
//                Toast.makeText(
//                        MainActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
//            }
//        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                switch(fruit.getName()){
                    case "people":
                        doStartApplicationWithPackageName("com.Company.ardonghua");
                        break;
                }
            }
        });
    }
    //打开外部app，新窗口打开
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

    private void initFruits()
    {
            Fruit apple = new Fruit("",R.drawable.strawberry );
            fruitList.add(apple);
            Fruit people = new Fruit("people",R.drawable.feidie);
            fruitList.add(people);
            Fruit orange = new Fruit("",R.drawable.wuguisharen);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("",R.drawable.dogcat);
            fruitList.add(watermelon);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        imageInfoList = new ArrayList<>();
        imageInfoList.add(new ImageInfo(1, "", "", "https://wx4.sinaimg.cn/mw690/86f68d37ly1gd6fhrxozzj20wt0i2e81.jpg", ""));
        imageInfoList.add(new ImageInfo(1, "", "", "https://wx3.sinaimg.cn/mw690/86f68d37ly1gd6fhrotvkj20wt0i2qsv.jpg", ""));
        imageInfoList.add(new ImageInfo(1, "", "", "https://wx1.sinaimg.cn/mw690/86f68d37ly1gd6fhqqob8j20wt0i2e5o.jpg", ""));
        imageInfoList.add(new ImageInfo(1, "", "仅展示", "https://wx3.sinaimg.cn/mw690/86f68d37ly1gd6fhq6e65j20wt0i24qp.jpg", ""));
        imageInfoList.add(new ImageInfo(1, "", "仅展示", "https://wx1.sinaimg.cn/mw690/86f68d37ly1gd6fhpbckzj20wt0i2gue.jpg", ""));

    }

    /**
     * 初始化控件
     */
    private void initView() {

        mViewPager = findViewById(R.id.viewPager);
        mTvPagerTitle = findViewById(R.id.tv_pager_title);
        mLineLayoutDot = findViewById(R.id.lineLayout_dot);

    }

    private void imageStart() {
        //设置图片轮播
        int[] imgaeIds = new int[]{R.id.pager_image1, R.id.pager_image2, R.id.pager_image3, R.id.pager_image4,
                R.id.pager_image5, R.id.pager_image6, R.id.pager_image7, R.id.pager_image8};
        String[] titles = new String[imageInfoList.size()];
        List<SimpleDraweeView> simpleDraweeViewList = new ArrayList<>();

        for (int i = 0; i < imageInfoList.size(); i++) {
            titles[i] = imageInfoList.get(i).getTitle();
            Fresco.initialize(this);
            SimpleDraweeView simpleDraweeView;
            simpleDraweeView = new SimpleDraweeView(this);
            simpleDraweeView.setAspectRatio(1.78f);
            // 设置一张默认的图片
            GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(this.getResources())
                    .setPlaceholderImage(ContextCompat.getDrawable(this, R.drawable.defult), ScalingUtils.ScaleType.CENTER_CROP).build();
            simpleDraweeView.setHierarchy(hierarchy);
            simpleDraweeView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));

            //加载高分辨率图片;
            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageInfoList.get(i).getImage()))
                    .setResizeOptions(new ResizeOptions(1280, 720))
                    .build();
            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                    //.setLowResImageRequest(ImageRequest.fromUri(Uri.parse(listItemBean.test_pic_low))) //在加载高分辨率图片之前加载低分辨率图片
                    .setImageRequest(imageRequest)
                    .setOldController(simpleDraweeView.getController())
                    .build();
            simpleDraweeView.setController(controller);

            simpleDraweeView.setId(imgaeIds[i]);//给view设置id
            simpleDraweeView.setTag(imageInfoList.get(i));
            simpleDraweeView.setOnClickListener(this);
            titles[i] = imageInfoList.get(i).getTitle();
            simpleDraweeViewList.add(simpleDraweeView);

        }

        dots = addDots(mLineLayoutDot, fromResToDrawable(this, R.drawable.ic_dot_focused), simpleDraweeViewList.size());
        imageCarousel = new ImageCarousel(this, mViewPager, mTvPagerTitle, dots, 5000);
        imageCarousel.init(simpleDraweeViewList, titles)
                .startAutoPlay();
        imageCarousel.start();

    }


    /**
     * 动态添加一个点
     *
     * @param linearLayout 添加到LinearLayout布局
     * @param backgount    设置
     * @return 小点的Id
     */
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private int addDot(final LinearLayout linearLayout, Drawable backgount) {
        final View dot = new View(this);
        LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dotParams.width = 16;
        dotParams.height = 16;
        dotParams.setMargins(4, 0, 4, 0);
        dot.setLayoutParams(dotParams);
        dot.setBackground(backgount);
        dot.setId(View.generateViewId());
        ((Activity) this).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                linearLayout.addView(dot);
            }
        });

        return dot.getId();
    }


    /**
     * 资源图片转Drawable
     *
     * @param context 上下文
     * @param resId   资源ID
     * @return 返回Drawable图像
     */
    public static Drawable fromResToDrawable(Context context, int resId) {
        return ContextCompat.getDrawable(context, resId);
        //return context.getResources().getDrawable(resId);
    }

    /**
     * 添加多个轮播小点到横向线性布局
     *
     * @param linearLayout 线性横向布局
     * @param backgount    小点资源图标
     * @param number       数量
     * @return 返回小点View集合
     */
    private List<View> addDots(final LinearLayout linearLayout, Drawable backgount, int number) {
        List<View> dots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int dotId = addDot(linearLayout, backgount);
            dots.add(findViewById(dotId));

        }
        return dots;
    }


}
