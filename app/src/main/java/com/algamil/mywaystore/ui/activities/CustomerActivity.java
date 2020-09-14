package com.algamil.mywaystore.ui.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.helper.HelperMethod;
import com.algamil.mywaystore.helper.InternetState;
import com.algamil.mywaystore.ui.fragments.AuthFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerActivity extends BaseActivity {

    @BindView ( R.id.cusomer_frame )
    FrameLayout cusomerFrame;
    @BindView ( R.id.im_error )
    ImageView imError;
    @BindView ( R.id.tv_error )
    TextView tvError;
    @BindView ( R.id.swipe_customer )
    SwipeRefreshLayout swipeCustomer;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_customer );
        ButterKnife.bind ( this );
        swipeCustomer.setRefreshing ( true );
        makeInit();
        new CountDownTimer (4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                swipeCustomer.setRefreshing ( false );
            }

        }.start();

        swipeCustomer.setOnRefreshListener ( new SwipeRefreshLayout.OnRefreshListener ( ) {
            @Override
            public void onRefresh ( ) {
                makeInit();
            }
        } );

    }

    public void makeInit()
    {
        if ( InternetState.isConnected ( getApplicationContext ( ) ) ) {
            showmadmobe ( );
            cusomerFrame.setVisibility ( View.VISIBLE );
            imError.setVisibility ( View.GONE );
            tvError.setVisibility ( View.GONE );
            swipeCustomer.setEnabled ( false );
            HelperMethod.replaceFragment ( getSupportFragmentManager ( ) , R.id.cusomer_frame ,
                    new AuthFragment ( ) );

            new CountDownTimer (4000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    swipeCustomer.setRefreshing ( false );
                }

            }.start();
        } else {
            imError.setVisibility ( View.VISIBLE );
            tvError.setVisibility ( View.VISIBLE );
            cusomerFrame.setVisibility ( View.INVISIBLE );
            swipeCustomer.setEnabled ( true );
            new CountDownTimer (4000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    swipeCustomer.setRefreshing ( false );
                }

            }.start();

        }
    }

    public void showmadmobe ( ) {
        MobileAds.initialize ( this , new OnInitializationCompleteListener ( ) {
            @Override
            public void onInitializationComplete ( InitializationStatus initializationStatus ) {
            }
        } );
        AdView mAdView = findViewById ( R.id.adView2 );
        AdRequest adRequest = new AdRequest.Builder ( ).build ( );
        mAdView.loadAd ( adRequest );
        mAdView.setAdListener ( new AdListener ( ) {
            @Override
            public void onAdLoaded ( ) {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad ( int errorCode ) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened ( ) {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked ( ) {
            }

            @Override
            public void onAdLeftApplication ( ) {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed ( ) {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        } );

    }


}