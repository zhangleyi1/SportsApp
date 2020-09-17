package com.example.sportapp.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class MainTabAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragment;

    @SuppressLint("WrongConstant")
    public MainTabAdapter(List<Fragment> fragmentList, FragmentManager fm) {
        super(fm, 0);
        mFragment = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }
}
