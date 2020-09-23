package com.example.sportapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getContentView(),container,false);
        initView(rootView);
        initData();
        return rootView;
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    abstract protected int getContentView();
}
