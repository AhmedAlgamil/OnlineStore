package com.algamil.mywaystore.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.data.models.signup.SignUp;
import com.algamil.mywaystore.helper.HelperMethod;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
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
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.loadData;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.saveData;

public class ProfileFragment extends Fragment {

    private static final int SELECT_IMAGE = 1;
    @BindView ( R.id.select_photo_profile )
    CircleImageView selectPhotoProfile;
    @BindView ( R.id.tx_name_profile )
    TextInputEditText txNameProfile;
    @BindView ( R.id.txl_name_profile )
    TextInputLayout txlNameProfile;
    @BindView ( R.id.tx_email_profile )
    TextInputEditText txEmailProfile;
    @BindView ( R.id.txl_email_profile )
    TextInputLayout txlEmailProfile;
    @BindView ( R.id.tx_phone_profile )
    TextInputEditText txPhoneProfile;
    @BindView ( R.id.txl_phone_profile )
    TextInputLayout txlPhoneProfile;
    @BindView ( R.id.tx_location_profile )
    TextInputEditText txLocationProfile;
    @BindView ( R.id.txl_location_profile )
    TextInputLayout txlLocationProfile;
    @BindView ( R.id.tx_edit_password_profile )
    TextInputEditText txEditPasswordProfile;
    @BindView ( R.id.tx_password_profile )
    TextInputLayout txPasswordProfile;
    @BindView ( R.id.btn_change_fragment_profile )
    AppCompatButton btnChangeFragmentProfile;
    @BindView ( R.id.scrolling )
    ScrollView scrolling;
    private Bitmap selectedPhoto;

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_profile , null );
        ButterKnife.bind ( this , view );
        makeInit();
        return view;
    }

    @Override
    public void onViewCreated ( View view , Bundle savedInstanceState ) {
        super.onViewCreated ( view , savedInstanceState );

    }

    @OnClick ( { R.id.select_photo_profile , R.id.btn_change_fragment_profile } )
    public void onClick ( View view ) {
        switch ( view.getId ( ) ) {
            case R.id.select_photo_profile:
                goToGallery(SELECT_IMAGE);
                break;
            case R.id.btn_change_fragment_profile:
                if(txEditPasswordProfile.getText ().toString ().isEmpty ())
                {
                    HelperMethod.showError ( txPasswordProfile , "هذا الحقل مطلوب " );
                }
                else{
                    HelperMethod.showError ( txPasswordProfile , "" );
                    makeEdit2();
                }

                break;
        }
    }

    public void makeInit()
    {
        txEmailProfile.setText ( loadData ( getActivity () , USER_EMAIL ));
        Glide.with ( getActivity ( ).getApplicationContext ( ) )
                .load ( loadData ( getActivity ( ) , USER_IMAGE ) )
                .into ( selectPhotoProfile );
        txNameProfile.setText ( loadData ( getActivity () , USER_NAME ));
        txPhoneProfile.setText ( loadData ( getActivity () , USER_PHONE ));
        txLocationProfile.setText ( loadData ( getActivity () , USER_LOCATION ));
    }

public void makeEdit2()
{
    if(selectedPhoto == null) {
        makeEdit(txNameProfile.getText ().toString ()
                ,txEmailProfile.getText ().toString ()
                ,txPhoneProfile.getText ().toString ()
                ,txLocationProfile.getText ().toString ()
                ,txEditPasswordProfile.getText ().toString ()
                ,loadData ( getActivity ( ) , USER_IMAGE ));
    }
    else {
        makeEdit(txNameProfile.getText ().toString ()
                ,txEmailProfile.getText ().toString ()
                ,txPhoneProfile.getText ().toString ()
                ,txLocationProfile.getText ().toString ()
                ,txEditPasswordProfile.getText ().toString ()
                ,"https://mywaystore123.000webhostapp.com/images/" + uploadImage( selectedPhoto ) + ".JPG");
    }
}

    public void goToGallery ( int operation ) {
        Intent intent = new Intent ( );
        intent.setType ( "image/*" );
        intent.setAction ( Intent.ACTION_GET_CONTENT );
        startActivityForResult ( Intent.createChooser ( intent , "Select Picture" ) , SELECT_IMAGE );
    }

    public void makeEdit(String name,String email,String phone ,String location,String password ,String imageURL)
    {
        getApiClient ().editProfile ( loadData ( getActivity () , CUSTOMER_ID ) , name,email,phone,password,location,imageURL ).enqueue ( new Callback < SignUp > ( ) {
            @Override
            public void onResponse ( Call < SignUp > call , Response < SignUp > response ) {
                try {
                    saveData ( getActivity () , USER_IMAGE ,response.body ().getData ().get ( 0 ).getImageUrl () );
                    saveData ( getActivity () , USER_EMAIL ,response.body ().getData ().get ( 0 ).getEmail () );
                    saveData ( getActivity () , USER_NAME ,response.body ().getData ().get ( 0 ).getName () );
                    saveData ( getActivity () , USER_PHONE ,response.body ().getData ().get ( 0 ).getPhone () );
                    saveData ( getActivity () , USER_LOCATION ,response.body ().getData ().get ( 0 ).getLocation () );
                    saveData ( getActivity () , REMEMBER_ME ,true);
                    HelperMethod.replaceFragment ( getActivity ().getSupportFragmentManager () , R.id.cusomer_frame , new AuthFragment () );
                }
                catch ( Exception e )
                {

                }
            }

            @Override
            public void onFailure ( Call < SignUp > call , Throwable t ) {
                try {

                }
                catch ( Exception e )
                {

                }
            }
        } );
    }

    @RequiresApi ( api = Build.VERSION_CODES.O )
    @Override
    public void onActivityResult ( int requestCode , int resultCode , @Nullable Intent data ) {

        super.onActivityResult ( requestCode , resultCode , data );
        if ( requestCode == SELECT_IMAGE ) {
            if ( resultCode == Activity.RESULT_OK ) {
                if ( data != null ) {
                    try {
                        selectedPhoto = MediaStore.Images.Media.getBitmap ( getActivity ( ).getContentResolver ( ) , data.getData ( ) );
                        selectPhotoProfile.setImageBitmap ( selectedPhoto );
                    } catch ( IOException e ) {
                        e.printStackTrace ( );
                    }
                }
            } else if ( resultCode == Activity.RESULT_CANCELED ) {
            }
        }
    }

    public static String uploadImage ( @NotNull Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream ( );
        bitmap.compress ( Bitmap.CompressFormat.JPEG , 100 , byteArrayOutputStream );
        String encodedImage = Base64.encodeToString ( byteArrayOutputStream.toByteArray ( ) , Base64.DEFAULT );
        String imgname = String.valueOf ( Calendar.getInstance ( ).getTimeInMillis ( ) );
        getApiClient ( ).Uploading ( imgname , encodedImage ).enqueue ( new Callback < String > ( ) {
            @Override
            public void onResponse ( Call < String > call , Response < String > response ) {
                try {

                }
                catch ( Exception ex )
                {

                }
            }

            @Override
            public void onFailure ( Call < String > call , Throwable t ) {

            }
        } );
        return imgname;
    }


}
