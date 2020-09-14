package com.algamil.mywaystore.ui.activities;

import android.os.Bundle;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.helper.HelperMethod;
import com.algamil.mywaystore.ui.fragments.ProductFragment;

import butterknife.ButterKnife;

import static com.algamil.mywaystore.data.local.SharedPreferencesManger.saveData;

public class ProductActivity extends BaseActivity {


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_product );
        ButterKnife.bind ( this );
       HelperMethod.replaceFragment ( this.getSupportFragmentManager () , R.id.frame_product , new ProductFragment () );
    }


}
