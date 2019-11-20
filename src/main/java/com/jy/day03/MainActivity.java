package com.jy.day03;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.jy.day03.adapter.MyPagerAdapter;
import com.jy.day03.adapter.RecAdapter;
import com.jy.day03.bean.Girl;
import com.jy.day03.presenter.ImpMainPresenter;
import com.jy.day03.view.MainViewCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MainViewCallback {

    private DrawerLayout mDrawer;
    private LinearLayout mMain;
    private ViewPager mVp;
    private RecyclerView mRec;
    private NavigationView mNavigation;
    private Toolbar mTool;
    private TextView mTv;
    private ImpMainPresenter presenter;
    private List<Fragment> frags;
    private RecAdapter recAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new ImpMainPresenter(this);
        initView();
        initDrawer();
        initData();
    }

    private void initDrawer() {
        mTool.setTitle("");
        setSupportActionBar(mTool);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mTool, R.string.open, R.string.close);
        toggle.syncState();
        mDrawer.addDrawerListener(toggle);
        ImageView head = mNavigation.getHeaderView(0).findViewById(R.id.head);
        File file = new File("/sdcard/Pictures/hhh.png");
        if (file.exists()){
            Glide.with(MainActivity.this)
                    .load(file)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(head);
        }
        mDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                mMain.setX(mNavigation.getRight());
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }

    private void initData() {
        presenter.getData();
    }

    private void initView() {
        mDrawer = findViewById(R.id.drawer);
        mMain = findViewById(R.id.main);
        mVp = findViewById(R.id.vp);
        mRec = findViewById(R.id.rec);
        mNavigation = findViewById(R.id.navigation);
        mTool = findViewById(R.id.tool);
        mTv = findViewById(R.id.tv);

        List<Girl.ResultsBean> list = new ArrayList<>();
        recAdapter = new RecAdapter(this, list);
        mRec.setAdapter(recAdapter);

        mRec.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRec.setLayoutManager(manager);
        manager.setOrientation(OrientationHelper.HORIZONTAL);
        recAdapter.setOnClickListener(new RecAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position) {
                mVp.setCurrentItem(position);
            }

            @Override
            public void onItemLongClick(int position) {
                Intent intent = new Intent(MainActivity.this, BanActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mRec.smoothScrollToPosition(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onSuccess(List<Girl.ResultsBean> list) {
        frags = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Fragment fragment = PageFragment.newInstance(list.get(i).getUrl());
            frags.add(fragment);
        }
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), frags);
        Log.i("TAG", "onSuccess: " + frags.size());
        mVp.setAdapter(adapter);

        recAdapter.initData(list);
    }

    @Override
    public void onFail(String error) {
        Log.i("TAG", "onFail: " + error);
    }

}
