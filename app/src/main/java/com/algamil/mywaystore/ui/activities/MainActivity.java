package com.algamil.mywaystore.ui.activities;

import android.os.Bundle;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.helper.HelperMethod;
import com.algamil.mywaystore.ui.fragments.SplashScreenFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
//        showmadmobe();

        HelperMethod.replaceFragment ( getSupportFragmentManager () , R.id.frame_main_activity,new SplashScreenFragment () );
    }

}
