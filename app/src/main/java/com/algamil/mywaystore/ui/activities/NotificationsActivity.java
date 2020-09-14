package com.algamil.mywaystore.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.data.models.notification.Notification2;
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
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.LOCATION;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.MY_PHONE;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.NAME;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.saveData;

public class NotificationsActivity extends Activity {

    @BindView ( R.id.customer_phone )
    TextInputEditText customerPhone;
    @BindView ( R.id.customer_name )
    TextInputEditText customerName;
    @BindView ( R.id.customer_location )
    TextInputEditText customerLocation;
    @BindView ( R.id.btn_submit )
    Button btnSubmit;
    @BindView ( R.id.container )
    ConstraintLayout container;
    @BindView ( R.id.phone_layout )
    TextInputLayout phoneLayout;
    @BindView ( R.id.name_layout )
    TextInputLayout nameLayout;
    @BindView ( R.id.location_layout )
    TextInputLayout locationLayout;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_notifications );
        ButterKnife.bind ( this );

    }

    public void makeNotification ( String name, String phone ,String location ) {
        getApiClient ( ).insertNotification ( name , phone ,location )
                .enqueue ( new Callback < Notification2 > ( ) {
                    @Override
                    public void onResponse ( Call < Notification2 > call ,
                                             Response < Notification2 > response ) {
                        try {
                            saveData ( NotificationsActivity.this , MY_PHONE , phone );
                            saveData ( NotificationsActivity.this , LOCATION , location );
                            saveData ( NotificationsActivity.this , NAME , name );
                            finish ();
                            Toast.makeText ( NotificationsActivity.this , "قم بالضغط على الطلب مرة اخرى" ,
                                    Toast.LENGTH_SHORT ).show ( );
                        }
                        catch ( Exception e )
                        {

                        }

                    }

                    @Override
                    public void onFailure ( Call < Notification2 > call , Throwable t ) {
                        try {

                        }
                        catch ( Exception e )
                        {

                        }
                    }
                } );
    }

    @OnClick ( R.id.btn_submit )
    public void onClick ( ) {
        if( checkPhone() && checkName () && checkLocation () )
        {
            makeNotification ( customerName.getText ().toString () , customerPhone.getText ().toString ()
                    ,customerLocation.getText ().toString () );
        }
    }

    public boolean checkPhone( )
    {
        boolean isPhone = false;
        if(customerPhone.getText ().toString ().isEmpty ())
        {
            HelperMethod.showError ( phoneLayout , "هذا الحقل مطلوب" );
            isPhone = false;
        }
        else
        {
            if(customerPhone.getText ().toString ().length () != 11)
            {
                HelperMethod.showError ( phoneLayout , "الرقم الهاتف غير صحيح" );
                isPhone = false;
            }
            else {
                HelperMethod.showError ( phoneLayout , "" );
                isPhone = true;
            }

        }
        return isPhone;
    }

    public boolean checkName()
    {
        boolean isName = false;
        if(customerName.getText ().toString ().isEmpty ())
        {
            HelperMethod.showError ( nameLayout , "هذا الحقل مطلوب" );
            isName = false;
        }
        else
        {
            HelperMethod.showError ( nameLayout , "" );
            isName = true;
        }
        return isName;
    }

    public boolean checkLocation()
    {
        boolean isLocation = false;
        if(customerLocation.getText ().toString ().isEmpty ())
        {
            HelperMethod.showError ( locationLayout , "هذا الحقل مطلوب" );
            isLocation = false;
        }
        else
        {
            HelperMethod.showError ( locationLayout , "" );
            isLocation = true;
        }
        return isLocation;
    }

}
