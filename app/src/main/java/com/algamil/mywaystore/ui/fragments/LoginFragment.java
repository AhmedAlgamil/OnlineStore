package com.algamil.mywaystore.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.data.models.login.Login;
import com.algamil.mywaystore.helper.HelperMethod;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.algamil.mywaystore.data.api.ApiClient.getApiClient;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.CUSTOMER_ID;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.REMEMBER_ME;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.USER_EMAIL;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.USER_IMAGE;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.USER_LOCATION;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.USER_NAME;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.USER_PHONE;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.saveData;
import static com.algamil.mywaystore.helper.Constant.LOGIN;

public class LoginFragment extends Fragment {
    @BindView ( R.id.tx_phone_login )
    TextInputEditText txPhoneLogin;
    @BindView ( R.id.txl_phone_login )
    TextInputLayout txlPhoneLogin;
    @BindView ( R.id.tx_edit_password )
    TextInputEditText txEditPassword;
    @BindView ( R.id.tx_password )
    TextInputLayout txPassword;
    @BindView ( R.id.foget )
    TextView foget;
    @BindView ( R.id.remember_check )
    CheckBox rememberCheck;
    @BindView ( R.id.linear_foget )
    LinearLayout linearFoget;
    @BindView ( R.id.btn_login )
    AppCompatButton btnLogin;
    @BindView ( R.id.btn_sign_up )
    AppCompatButton btnSignUp;
    @BindView ( R.id.login_container )
    RelativeLayout loginContainer;
    InterstitialAd mPublisherInterstitialAd;

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_login , container , false );
        ProductFragment.OPR_PRODUCT = "no";
        ButterKnife.bind ( this , view );
        mPublisherInterstitialAd = new InterstitialAd(getContext());
        mPublisherInterstitialAd.setAdUnitId("ca-app-pub-2008695252233572/9351175675");
        mPublisherInterstitialAd.loadAd(new AdRequest.Builder().build());
        if (mPublisherInterstitialAd.isLoaded()) {

        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        Log.d("TAG", "The interstitial wasn't loaded yet.");
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.

                mPublisherInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.

            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });
        return view;
    }

    @OnClick ( { R.id.foget , R.id.remember_check , R.id.btn_login , R.id.btn_sign_up } )
    public void onClick ( View view ) {
        switch ( view.getId ( ) ) {
            case R.id.foget:
                if( AuthFragment.DIRECTION == "login" )
                {
                    HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) ,
                            R.id.cusomer_frame , new EmailFragment ( ) );
                }
                else {
                    HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) ,
                            R.id.frame_product , new EmailFragment ( ) );
                }
                break;
            case R.id.remember_check:
                break;
            case R.id.btn_login:
                makeLogin ( );
                break;
            case R.id.btn_sign_up:
                if( AuthFragment.DIRECTION == "login" )
                {
                    HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) ,
                            R.id.cusomer_frame , new SignUpFragment ( ) );
                }
                else {
                    HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) ,
                            R.id.frame_product , new SignUpFragment ( ) );
                }
                break;
        }
    }

    public void makeLogin ( ) {
        getApiClient ( ).login ( txPhoneLogin.getText ( ).toString ( ) ,
                txEditPassword.getText ( ).toString ( ) ).enqueue ( new Callback < Login > ( ) {
            @Override
            public void onResponse ( Call < Login > call , Response < Login > response ) {
                try {
                    if ( response.body ( ).getStatus ( ) ) {
                        saveData ( getActivity ( ) , LOGIN , LOGIN );
                        saveData ( getActivity ( ) , REMEMBER_ME , rememberCheck.isChecked ( ) );
                        saveData ( getActivity ( ) , CUSTOMER_ID ,
                                response.body ( ).getData ( ).get ( 0 ).getId ( ) );
                        saveData ( getActivity ( ) , USER_IMAGE ,
                                response.body ( ).getData ( ).get ( 0 ).getImage_url ( ) );
                        saveData ( getActivity ( ) , USER_EMAIL ,
                                response.body ( ).getData ( ).get ( 0 ).getEmail ( ) );
                        saveData ( getActivity ( ) , USER_NAME ,
                                response.body ( ).getData ( ).get ( 0 ).getName ( ) );
                        saveData ( getActivity ( ) , USER_PHONE ,
                                response.body ( ).getData ( ).get ( 0 ).getPhone ( ) );
                        saveData ( getActivity ( ) , USER_LOCATION ,
                                response.body ( ).getData ( ).get ( 0 ).getLocation ( ) );
                        ProductFragment.OPR_PRODUCT = "no";
                        if( AuthFragment.DIRECTION == "login" )
                        {
                            HelperMethod.replaceFragment ( getActivity ().getSupportFragmentManager () , R.id.cusomer_frame , new AuthFragment () );
                        }
                        else
                        {
                            HelperMethod.replaceFragment ( getActivity ().getSupportFragmentManager () , R.id.frame_product , new ProductFragment() );
                        }


                    } else {
                        HelperMethod.showSnackBar ( loginContainer , "تاكد من رقم الهاتف وكلمة المرور " );
                    }

                } catch ( Exception e ) {

                }

            }

            @Override
            public void onFailure ( Call < Login > call , Throwable t ) {

            }
        } );
    }

}
