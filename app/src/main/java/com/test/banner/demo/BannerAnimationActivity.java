package com.test.banner.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.test.banner.App;
import com.test.banner.R;
import com.test.banner.SampleAdapter;
import com.test.banner.loader.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.AccordionTransformer;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.youth.banner.transformer.CubeInTransformer;
import com.youth.banner.transformer.CubeOutTransformer;
import com.youth.banner.transformer.DefaultTransformer;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.FlipHorizontalTransformer;
import com.youth.banner.transformer.FlipVerticalTransformer;
import com.youth.banner.transformer.ForegroundToBackgroundTransformer;
import com.youth.banner.transformer.RotateDownTransformer;
import com.youth.banner.transformer.RotateUpTransformer;
import com.youth.banner.transformer.ScaleInOutTransformer;
import com.youth.banner.transformer.ScaleSmallTransformer;
import com.youth.banner.transformer.StackTransformer;
import com.youth.banner.transformer.TabletTransformer;
import com.youth.banner.transformer.ZoomInTransformer;
import com.youth.banner.transformer.ZoomOutSlideTransformer;
import com.youth.banner.transformer.ZoomOutTranformer;

import java.util.ArrayList;
import java.util.List;

public class BannerAnimationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OnBannerListener {
    Banner banner;
    List<Class<? extends ViewPager.PageTransformer>> transformers = new ArrayList<>();

    public void initData() {
        transformers.add(DefaultTransformer.class);
        transformers.add(ScaleSmallTransformer.class);
        transformers.add(AccordionTransformer.class);
        transformers.add(BackgroundToForegroundTransformer.class);
        transformers.add(ForegroundToBackgroundTransformer.class);
        transformers.add(CubeInTransformer.class);//兼容问题，慎用
        transformers.add(CubeOutTransformer.class);
        transformers.add(DepthPageTransformer.class);
        transformers.add(FlipHorizontalTransformer.class);
        transformers.add(FlipVerticalTransformer.class);
        transformers.add(RotateDownTransformer.class);
        transformers.add(RotateUpTransformer.class);
        transformers.add(ScaleInOutTransformer.class);
        transformers.add(StackTransformer.class);
        transformers.add(TabletTransformer.class);
        transformers.add(ZoomInTransformer.class);
        transformers.add(ZoomOutTranformer.class);
        transformers.add(ZoomOutSlideTransformer.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_animation);
        initData();
        banner = (Banner) findViewById(R.id.banner);
        ListView listView = (ListView) findViewById(R.id.list);
        String[] data = getResources().getStringArray(R.array.anim);
        listView.setAdapter(new SampleAdapter(this, data));
        listView.setOnItemClickListener(this);
        banner.setOffscreenPageLimit(3);
        banner.setPageMargin(20);
        banner.setImages(App.images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();


        findViewById(R.id.btn_update)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        banner.update(getImages());
                        Toast.makeText(v.getContext(), "提示", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        banner.setBannerAnimation(transformers.get(position));
    }

    @Override
    public void onBannerClick(int position) {
        Toast.makeText(getApplicationContext(), "你点击了：" + position, Toast.LENGTH_SHORT).show();
    }

    private List<String> getImages() {
        List<String> im
                = new ArrayList<>();
        im.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1838281048,3807966177&fm=26&gp=0.jpg");
        im.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1374563669,1118995928&fm=26&gp=0.jpg");
        im.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=251340773,3971422111&fm=26&gp=0.jpg");
        im.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=138136525,1863773672&fm=26&gp=0.jpg");
        im.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3850508610,3930590534&fm=26&gp=0.jpg");
        return im;
    }
}
