package com.example.sportapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initView();
        initData();
    }

    abstract protected int getContentView();

    abstract protected void initView();

    abstract protected void initData();
}
