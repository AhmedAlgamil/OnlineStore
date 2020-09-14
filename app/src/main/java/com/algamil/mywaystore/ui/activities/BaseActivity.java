package com.algamil.mywaystore.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.algamil.mywaystore.ui.fragments.BaseFragment;

public class BaseActivity extends AppCompatActivity {

    public BaseFragment baseFragment;

    public void superBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();

    }


}
