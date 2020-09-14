package com.algamil.mywaystore.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.data.models.login.Login;
import com.algamil.mywaystore.helper.HelperMethod;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.algamil.mywaystore.data.api.ApiClient.getApiClient;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.CODE;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.USER_EMAIL_SENDED;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.loadData;
import static com.algamil.mywaystore.ui.fragments.AuthFragment.DIRECTION;

public class PasswordFragment extends Fragment {

    @BindView ( R.id.tx_code )
    TextInputEditText txCode;
    @BindView ( R.id.txl_phone_login )
    TextInputLayout txlPhoneLogin;
    @BindView ( R.id.tx_edit_password )
    TextInputEditText txEditPassword;
    @BindView ( R.id.tx_password )
    TextInputLayout txPassword;
    @BindView ( R.id.tx_edit_confirm_password )
    TextInputEditText txEditConfirmPassword;
    @BindView ( R.id.tx_confirm_password )
    TextInputLayout txConfirmPassword;
    @BindView ( R.id.send_again )
    TextView sendAgain;
    @BindView ( R.id.btn_change )
    AppCompatButton btnChange;
    @BindView ( R.id.container_password )
    RelativeLayout containerPassword;

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_password , container , false );
        ProductFragment.OPR_PRODUCT = "email";
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick ( { R.id.send_again , R.id.btn_change } )
    public void onClick ( View view ) {
        switch ( view.getId ( ) ) {
            case R.id.send_again:
                if(DIRECTION == "login")
                {
                    HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) , R.id.cusomer_frame , new EmailFragment ( ) );
                }
                else {
                    HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) , R.id.frame_product , new EmailFragment ( ) );
                }

                break;
            case R.id.btn_change:
                change();
                if(DIRECTION == "login")
                {
                    HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) , R.id.cusomer_frame , new LoginFragment ( ) );
                }
                else {
                    HelperMethod.replaceFragment ( getActivity ( ).getSupportFragmentManager ( ) , R.id.frame_product , new LoginFragment ( ) );
                }

                break;
        }
    }

    public void change (  ) {
        if ( txCode.getText ( ).toString ( ).equals (   loadData ( getActivity ( ) , CODE ) )) {
            if(txEditPassword.getText ().toString () .equals (  txEditConfirmPassword.getText ().toString ()))
            {
                getApiClient().changePassword ( loadData ( getActivity () , USER_EMAIL_SENDED ) , txEditPassword.getText ().toString () )
                        .enqueue ( new Callback < Login > ( ) {
                            @Override
                            public void onResponse ( Call < Login > call , Response < Login > response ) {
                                try {
                                }
                                catch ( Exception e )
                                {

                                }

                            }

                            @Override
                            public void onFailure ( Call < Login > call , Throwable t ) {
                                try {

                                }
                                catch ( Exception e )
                                {

                                }

                            }
                        } );
            }
            else {
                HelperMethod.showSnackBar ( containerPassword , "كلمة المرور غير متطابقة" );
            }
        } else {
            HelperMethod.showSnackBar ( containerPassword , "الكود غير صحيح" );
        }
    }

}
