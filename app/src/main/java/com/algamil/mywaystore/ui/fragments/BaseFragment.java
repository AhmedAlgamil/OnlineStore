package com.algamil.mywaystore.ui.fragments;

import androidx.fragment.app.Fragment;

import com.algamil.mywaystore.ui.activities.BaseActivity;
public class BaseFragment extends Fragment {

    public BaseActivity baseActivity;

    public void setUpActivity() {
        baseActivity = (BaseActivity) getActivity();
        baseActivity.baseFragment = this;
    }

    public void onBack() {
        baseActivity.superBackPressed();
    }

}
