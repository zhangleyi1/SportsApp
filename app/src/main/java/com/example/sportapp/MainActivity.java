package com.example.sportapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.example.sportapp.adapter.MainTabAdapter;
import com.example.sportapp.fragment.BbsFragment;
import com.example.sportapp.fragment.NewsFragment;
import com.example.sportapp.fragment.SelfFragment;
import com.example.sportapp.fragment.TournamentFragment;
import com.example.sportapp.view.BottomBarItem;
import com.example.sportapp.view.BottomBarLayout;
import com.example.sportapp.viewpager.NoScrollViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<Fragment> mFragment;
    private MainTabAdapter mMainTabAdapter;
    private NoScrollViewPager mViewPager;
    private BottomBarLayout mBottomBarLayout;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mViewPager = findViewById(R.id.vp_content);
        mBottomBarLayout = findViewById(R.id.bottom_bar);

    }

    @Override
    protected void initData() {
        mFragment = new ArrayList<Fragment>();
        mFragment.add(new TournamentFragment());
        mFragment.add(new BbsFragment());
        mFragment.add(new NewsFragment());
        mFragment.add(new SelfFragment());
        mMainTabAdapter = new MainTabAdapter(mFragment, getSupportFragmentManager());
        mViewPager.setAdapter(mMainTabAdapter);
        mBottomBarLayout.setViewPager(mViewPager);
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem var1, int var2) {

            }
        });
    }
}