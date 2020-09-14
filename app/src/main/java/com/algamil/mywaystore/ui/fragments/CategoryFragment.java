package com.algamil.mywaystore.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.helper.HelperMethod;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.algamil.mywaystore.data.local.SharedPreferencesManger.CATEGORY;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.OPERATION;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.saveData;
import static com.algamil.mywaystore.ui.fragments.AuthFragment.OPR_BACK;

public class CategoryFragment extends Fragment {

    @BindView ( R.id.im_food_category )
    ImageView imFoodCategory;
    @BindView ( R.id.tv_food_category )
    TextView tvFoodCategory;
    @BindView ( R.id.im_perfums_category )
    ImageView imPerfumsCategory;
    @BindView ( R.id.tv_perfums_category )
    TextView tvPerfumsCategory;
    @BindView ( R.id.im_cleans_category )
    ImageView imCleansCategory;
    @BindView ( R.id.tv_cleans_category )
    TextView tvCleansCategory;
    @BindView ( R.id.im_toys_category )
    ImageView imToysCategory;
    @BindView ( R.id.tv_toys_category )
    TextView tvToysCategory;
    @BindView ( R.id.im_school_category )
    ImageView imSchoolCategory;
    @BindView ( R.id.tv_school_category )
    TextView tvSchoolCategory;

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_category , container , false );
        ButterKnife.bind ( this , view );
        return view;
    }

    public void showRecyclerFood ( ) {
        saveData ( getActivity ( ) , CATEGORY , "طعام" );
        OPR_BACK = 1;
        HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) ,
                R.id.cusomer_frame , new FoodsFragment ( ) );
        saveData ( getActivity ( ) , OPERATION , "food" );
    }

    public void showRecyclerPerfum ( ) {
        saveData ( getActivity ( ) , CATEGORY , "عطور" );
        OPR_BACK = 1;
        HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) ,
                R.id.cusomer_frame , new FoodsFragment ( ) );
        saveData ( getActivity ( ) , OPERATION , "perfum" );
    }

    public void showRecyclerClean ( ) {
        saveData ( getActivity ( ) , CATEGORY , "تنظيف" );
        OPR_BACK = 1;
        HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) ,
                R.id.cusomer_frame , new FoodsFragment ( ) );
    }

    public void showRecyclerToys ( ) {
        saveData ( getActivity ( ) , CATEGORY , "العاب" );
        OPR_BACK = 1;
        HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) ,
                R.id.cusomer_frame , new FoodsFragment ( ) );
        saveData ( getActivity ( ) , OPERATION , "toy" );
    }

    public void showRecyclerSchool ( ) {
        OPR_BACK = 1;
        saveData ( getActivity ( ) , CATEGORY , "ادوات مدرسية" );
        HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) ,
                R.id.cusomer_frame , new FoodsFragment ( ) );
        saveData ( getActivity ( ) , OPERATION , "school" );
    }

    @OnClick ( { R.id.im_food_category , R.id.tv_food_category , R.id.im_perfums_category ,
            R.id.tv_perfums_category , R.id.im_cleans_category , R.id.tv_cleans_category ,
            R.id.im_toys_category , R.id.tv_toys_category , R.id.im_school_category ,
            R.id.tv_school_category } )
    public void onClick ( View view ) {
        switch ( view.getId ( ) ) {
            case R.id.im_food_category:
                showRecyclerFood ( );
                break;
            case R.id.tv_food_category:
                showRecyclerFood ( );
                break;
            case R.id.im_perfums_category:
                showRecyclerPerfum();
                break;
            case R.id.tv_perfums_category:
                showRecyclerPerfum();
                break;
            case R.id.im_cleans_category:
                showRecyclerClean();
                break;
            case R.id.tv_cleans_category:
                showRecyclerClean();
                break;
            case R.id.im_toys_category:
                showRecyclerToys();
                break;
            case R.id.tv_toys_category:
                showRecyclerToys();
                break;
            case R.id.im_school_category:
                showRecyclerSchool();
                break;
            case R.id.tv_school_category:
                showRecyclerSchool();
                break;
        }
    }
}