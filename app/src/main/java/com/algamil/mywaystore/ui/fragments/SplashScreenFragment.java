package com.algamil.mywaystore.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.helper.HelperMethod;
import com.algamil.mywaystore.ui.activities.CustomerActivity;

import butterknife.ButterKnife;

import static com.algamil.mywaystore.data.local.SharedPreferencesManger.OPERATION_WALK;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.loadData;


public class SplashScreenFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate( R.layout.splash_screen, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpActivity ();
        HelperMethod.onPermission ( getActivity ( ) );
        ButterKnife.bind(this, view);

        new CountDownTimer (4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(loadData(getActivity () , OPERATION_WALK) == null)
                {
                    HelperMethod.replaceFragment ( getActivity ().getSupportFragmentManager () , R.id.frame_main_activity , new WalkthroghLayoutFragment () );
                }
                else if(loadData(getActivity () , OPERATION_WALK) .equals ( "finish" )  )
                {
                    Intent intent = new Intent ( getActivity () , CustomerActivity.class );
                    getActivity ().startActivity ( intent );
                    getActivity ().finish ();
                }
                else
                {

                }

            }

        }.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
