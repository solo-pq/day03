package com.jy.day03;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.day03.bean.Girl;
import com.jy.day03.presenter.ImpMainPresenter;
import com.jy.day03.view.MainViewCallback;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BanActivity extends AppCompatActivity implements MainViewCallback {

    private Banner mBanner;
    private ImpMainPresenter presenter;
    private TextView mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban);
        presenter = new ImpMainPresenter(this);
        initView();
        initData();
    }

    private void initData() {
        presenter.getData();
    }

    private void initView() {
        mBanner = findViewById(R.id.banner);
        mPosition = findViewById(R.id.position);
    }

    @Override
    public void onSuccess(List<Girl.ResultsBean> list) {
        List<String> imgList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            imgList.add(list.get(i).getUrl());
        }
        mBanner.setImages(imgList)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load((String) path).into(imageView);
                    }
                })
                .start();
        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int i, float v, int i1) {

                    }

                    @Override
                    public void onPageSelected(int i) {
                        mPosition.setText("当前图片位置："+(i+1));
                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                });
    }

    @Override
    public void onFail(String error) {

    }
}
