package com.algamil.mywaystore.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.adapters.SliderAdapter;
import com.algamil.mywaystore.helper.HelperMethod;
import com.algamil.mywaystore.ui.activities.MainActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.algamil.mywaystore.data.local.SharedPreferencesManger.CUSTOMER_ID;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.REMEMBER_ME;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.USER_EMAIL;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.USER_IMAGE;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.USER_LOCATION;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.USER_NAME;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.USER_PHONE;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.loadBoolean;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.loadData;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.saveData;

public class AuthFragment extends BaseFragment {

    @BindView ( R.id.title )
    TextView title;
    @BindView ( R.id.my_photo )
    CircleImageView myPhoto;
    @BindView ( R.id.tabs )
    TabLayout tabs;
    @BindView ( R.id.view_pager )
    ViewPager viewPager;
    @BindView ( R.id.im_edit_profile )
    ImageView imEditProfile;
    @BindView ( R.id.tv_edit_profile )
    TextView tvEditProfile;
    @BindView ( R.id.im_sign_out )
    ImageView imSignOut;
    @BindView ( R.id.tv_sign_out )
    TextView tvSignOut;
    @BindView ( R.id.bottom_sheet )
    NestedScrollView bottomSheet;
    private String OPR = "collapse";
    BottomSheetBehavior bottomSheetBehavior;
    //  1 = back , 0 = finish
    public static int OPR_BACK = 0;
    public static String DIRECTION ;

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_auth , container , false );
        ButterKnife.bind ( this , view );

        if ( loadBoolean ( getActivity () , REMEMBER_ME ) )
        {
            setUpActivity ( );
            bottomSheetBehavior = BottomSheetBehavior.from ( bottomSheet );
            setInit ( );
            setIcons ( );
            Glide.with ( getActivity ( ).getApplicationContext ( ) )
                    .load ( loadData ( getActivity ( ) , USER_IMAGE ) )
                    .into ( myPhoto );
        }
        else {
            setUpActivity ( );
            bottomSheetBehavior = BottomSheetBehavior.from ( bottomSheet );
            setInit ( );
            setIcons ( );
            myPhoto.setImageResource ( R.drawable.myprofile );
        }
        return view;
    }

    public void setInit ( ) {
        SliderAdapter sliderAdapter = new SliderAdapter ( getChildFragmentManager ( ) );
        sliderAdapter.addFrag ( new CategoryFragment ( ) , "" );
        sliderAdapter.addFrag ( new BagsFragment ( ) , "" );
        sliderAdapter.addFrag ( new FavouriteFragment ( ) , "" );
        sliderAdapter.addFrag ( new AboutFragment ( ) , "" );
        viewPager.setAdapter ( sliderAdapter );
        tabs.setupWithViewPager ( viewPager );
    }

    public void setIcons ( ) {
        tabs.getTabAt ( 0 ).setIcon ( R.drawable.category );
        tabs.getTabAt ( 1 ).setIcon ( R.drawable.bags );
        tabs.getTabAt ( 2 ).setIcon ( R.drawable.add_to_favourite );
        tabs.getTabAt ( 3 ).setIcon ( R.drawable.about );
    }

    @Override
    public void onBack ( ) {
        if ( OPR_BACK == 1 ) {
            if ( OPR == "expanded" ) {
                OPR = "collapsed";
                OPR_BACK = 0;
                bottomSheetBehavior.setState ( BottomSheetBehavior.STATE_COLLAPSED );
            } else {
                OPR_BACK = 0;
            }
            super.onBack ( );
        } else {
            baseActivity.finish ( );
        }
    }

    public void showBottom ( ) {
        if ( OPR == "collapsed" ) {
            OPR = "expanded";
            OPR_BACK = 1;
            bottomSheetBehavior.setState ( BottomSheetBehavior.STATE_EXPANDED );
        }
    }

    @OnClick ( { R.id.my_photo , R.id.im_edit_profile , R.id.tv_edit_profile , R.id.im_sign_out ,
            R.id.tv_sign_out } )
    public void onClick ( View view ) {
        switch ( view.getId ( ) ) {
            case R.id.my_photo:
                if ( loadBoolean ( getActivity () , REMEMBER_ME ) )
                {
                    OPR = "collapsed";
                    showBottom ( );
                }
                else {
                    OPR_BACK = 1;
                    DIRECTION = "login";
                    HelperMethod.replaceFragment ( getActivity ().getSupportFragmentManager () , R.id.cusomer_frame , new LoginFragment () );
                }
                break;
            case R.id.im_edit_profile:
                bottomSheetBehavior.setState ( BottomSheetBehavior.STATE_COLLAPSED );
                HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) , R
                        .id.cusomer_frame , new ProfileFragment ( ) );
                break;
            case R.id.tv_edit_profile:
                bottomSheetBehavior.setState ( BottomSheetBehavior.STATE_COLLAPSED );
                HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) , R
                        .id.cusomer_frame , new ProfileFragment ( ) );
                break;
            case R.id.im_sign_out:
                saveData ( getActivity ( ) , REMEMBER_ME , false );
                saveData ( getActivity () , CUSTOMER_ID ,null );
                saveData ( getActivity () , USER_IMAGE ,"" );
                saveData ( getActivity () , USER_EMAIL ,"");
                saveData ( getActivity () , USER_NAME ,"");
                saveData ( getActivity () , USER_PHONE ,"");
                saveData ( getActivity () , USER_LOCATION ,"");
                bottomSheetBehavior.setState ( BottomSheetBehavior.STATE_COLLAPSED );
                Intent intent = new Intent ( getActivity ( ) , MainActivity.class );
                getActivity ( ).startActivity ( intent );
                getActivity ( ).finish ( );
                break;
            case R.id.tv_sign_out:
                saveData ( getActivity ( ) , REMEMBER_ME , false );
                bottomSheetBehavior.setState ( BottomSheetBehavior.STATE_COLLAPSED );
                Intent intent2 = new Intent ( getActivity ( ) , MainActivity.class );
                getActivity ( ).startActivity ( intent2 );
                getActivity ( ).finish ( );
                break;
        }
    }
}
