package com.algamil.mywaystore.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.data.models.sendEmail.SendingEmail;
import com.algamil.mywaystore.helper.HelperMethod;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.algamil.mywaystore.data.api.ApiClient.getApiClient;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.CODE;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.USER_EMAIL_SENDED;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.saveData;
import static com.algamil.mywaystore.ui.fragments.AuthFragment.DIRECTION;


public class EmailFragment extends Fragment {

    @BindView ( R.id.txl_email_sending )
    TextInputEditText txlEmailSending;
    @BindView ( R.id.txl_email_send )
    TextInputLayout txlEmailSend;
    @BindView ( R.id.btn_send )
    AppCompatButton btnSignUpFragmentSignUp;
    @BindView ( R.id.container_send )
    RelativeLayout containerSend;
    int n;

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_email , container , false );
        ButterKnife.bind ( this,view );
        ProductFragment.OPR_PRODUCT = "login";
        return view;
    }

    @OnClick ( { R.id.txl_email_sending , R.id.txl_email_send , R.id.btn_send } )
    public void onClick ( View view ) {
        switch ( view.getId ( ) ) {
            case R.id.txl_email_sending:
                break;
            case R.id.txl_email_send:
                break;
            case R.id.btn_send:
                n = 10000 + new Random ().nextInt(90000);
                sendingEmail(txlEmailSending.getText ().toString () , n + "" );
                break;
        }
    }

    public void sendingEmail ( String toEmail , String code ) {
        getApiClient ( ).sendEmail ( toEmail , code ).enqueue ( new Callback < SendingEmail > ( ) {
            @Override
            public void onResponse ( Call < SendingEmail > call , Response < SendingEmail > response ) {
                try {
                    if ( toEmail.isEmpty () )
                    {
                        HelperMethod.showError ( txlEmailSend , response.body ().getMessage () );
                    }
                    else
                    {
                        saveData ( getActivity () , CODE , n + "" );
                        saveData ( getActivity () , USER_EMAIL_SENDED , txlEmailSending.getText ().toString () );
                        if(DIRECTION == "login")
                        {
                            HelperMethod.replaceFragment ( getActivity ().getSupportFragmentManager () , R.id.cusomer_frame , new PasswordFragment () );
                        }
                        else {
                            HelperMethod.replaceFragment ( getActivity ().getSupportFragmentManager () , R.id.frame_product , new PasswordFragment () );
                        }


                    }

                }
                catch ( Exception e )
                {

                }
            }

            @Override
            public void onFailure ( Call < SendingEmail > call , Throwable t ) {
            }
        } );
    }

}
