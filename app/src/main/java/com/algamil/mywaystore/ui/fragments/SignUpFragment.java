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

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.data.models.signup.SignUp;
import com.algamil.mywaystore.helper.HelperMethod;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
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
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.saveData;


public class SignUpFragment extends Fragment {

    private static final int SELECT_IMAGE = 1;
    @BindView ( R.id.select_photo )
    CircleImageView addPhoto;
    @BindView ( R.id.tx_name_sign_up )
    TextInputEditText txNameSignUp;
    @BindView ( R.id.txl_name_sign_up )
    TextInputLayout txlNameSignUp;
    @BindView ( R.id.tx_email_sign_up )
    TextInputEditText txEmailSignUp;
    @BindView ( R.id.txl_email_sign_up )
    TextInputLayout txlEmailSignUp;
    @BindView ( R.id.tx_phone_sign_up )
    TextInputEditText txPhoneSignUp;
    @BindView ( R.id.txl_phone_sign_up )
    TextInputLayout txlPhoneSignUp;
    @BindView ( R.id.tx_location_sign_up )
    TextInputEditText txLocationSignUp;
    @BindView ( R.id.txl_location_sign_up )
    TextInputLayout txlLocationSignUp;
    @BindView ( R.id.tx_edit_password )
    TextInputEditText txEditPassword;
    @BindView ( R.id.tx_password )
    TextInputLayout txPassword;
    @BindView ( R.id.btn_sign_up_fragment_sign_up )
    AppCompatButton btnSignUpFragmentSignUp;
    private Bitmap selectedPhoto;

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_sign_up , container , false );
        ProductFragment.OPR_PRODUCT = "login";
        ButterKnife.bind(this,view);
        return view;
    }
    @OnClick ( { R.id.select_photo , R.id.btn_sign_up_fragment_sign_up } )
    public void onClick ( View view ) {
        switch ( view.getId ( ) ) {
            case R.id.select_photo:
                goToGallery(SELECT_IMAGE);
                break;
            case R.id.btn_sign_up_fragment_sign_up:
                if(btnSignUpFragmentSignUp.getText () == HelperMethod.getStringFromXML ( getActivity () , R.string.sign_up ))
                {
                    if(selectedPhoto == null)
                    {
                        makeSignUp(txNameSignUp.getText ().toString ()
                                ,txEmailSignUp.getText ().toString ()
                                ,txPhoneSignUp.getText ().toString ()
                                ,txLocationSignUp.getText ().toString ()
                                ,txEditPassword.getText ().toString ()
                                ,"");
                    }
                    else {
                        makeSignUp(txNameSignUp.getText ().toString ()
                                ,txEmailSignUp.getText ().toString ()
                                ,txPhoneSignUp.getText ().toString ()
                                ,txLocationSignUp.getText ().toString ()
                                ,txEditPassword.getText ().toString ()
                                ,"https://mywaystore123.000webhostapp.com/images/" + uploadImage( selectedPhoto ) + ".JPG");
                    }
                }
                break;
        }
    }

    public void goToGallery ( int operation ) {
        Intent intent = new Intent ( );
        intent.setType ( "image/*" );
        intent.setAction ( Intent.ACTION_GET_CONTENT );
        startActivityForResult ( Intent.createChooser ( intent , "Select Picture" ) , SELECT_IMAGE );
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
                        addPhoto.setImageBitmap ( selectedPhoto );
                    } catch ( IOException e ) {
                        e.printStackTrace ( );
                    }
                }
            } else if ( resultCode == Activity.RESULT_CANCELED ) {
            }
        }
    }

    public void makeSignUp(String name,String email,String phone ,String location,String password ,String imageURL)
    {
        getApiClient ().signUp ( name,email,phone,password,location,imageURL ).enqueue ( new Callback < SignUp > ( ) {
            @Override
            public void onResponse ( Call < SignUp > call , Response < SignUp > response ) {
                try {
                    if(response.body ().getMessage ().equals ( "Parameters are missing" ))
                    {
                        showingError(txlNameSignUp , HelperMethod.getStringFromXML ( getActivity (),R.string.required));
                        showingError(txlEmailSignUp , HelperMethod.getStringFromXML ( getActivity (),R.string.required));
                        showingError(txlPhoneSignUp , HelperMethod.getStringFromXML ( getActivity (),R.string.required));
                        showingError(txlLocationSignUp , HelperMethod.getStringFromXML ( getActivity (),R.string.required));
                        showingError(txPassword , HelperMethod.getStringFromXML ( getActivity (),R.string.required));
                    } else {
                        if(response.body ().getMessage ().equals ( "email already exist!" ) )
                        {
                            showingError(txlNameSignUp , null);
                            showingError(txlEmailSignUp , HelperMethod.getStringFromXML ( getActivity (),R.string.email_exist));
                            showingError(txlPhoneSignUp , null);
                            showingError(txlLocationSignUp , null);
                            showingError(txPassword , null);
                        }
                        else if( response.body ().getMessage ().equals ( "phone already exist!" ))
                        {
                            showingError(txlNameSignUp , null);
                            showingError(txlEmailSignUp , null);
                            showingError(txlPhoneSignUp , HelperMethod.getStringFromXML ( getActivity (),R.string.phone_exist));
                            showingError(txlLocationSignUp , null);
                            showingError(txPassword , null);
                        }
                        else {
                            showingError ( txlNameSignUp , null );
                            showingError ( txlEmailSignUp , null );
                            showingError ( txlPhoneSignUp , null );
                            showingError ( txlLocationSignUp , null );
                            showingError ( txPassword , null );
                            saveData ( getActivity () , CUSTOMER_ID ,response.body ().getCustomerId () );
                            saveData ( getActivity () , USER_IMAGE ,response.body ().getData ().get ( 0 ).getImageUrl () );
                            saveData ( getActivity () , USER_EMAIL ,response.body ().getData ().get ( 0 ).getEmail () );
                            saveData ( getActivity () , USER_NAME ,response.body ().getData ().get ( 0 ).getName () );
                            saveData ( getActivity () , USER_PHONE ,response.body ().getData ().get ( 0 ).getPhone () );
                            saveData ( getActivity () , USER_LOCATION ,response.body ().getData ().get ( 0 ).getLocation () );
                            saveData ( getActivity () , REMEMBER_ME ,true);
                            if(AuthFragment.DIRECTION == "login")
                            {
                                HelperMethod.replaceFragment ( getActivity (). getSupportFragmentManager () , R.id.cusomer_frame , new AuthFragment () );
                            }
                            else
                            {
                                HelperMethod.replaceFragment ( getActivity (). getSupportFragmentManager () , R.id.frame_product , new ProductFragment () );
                            }

                        }
                    }
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

    public void showingError(TextInputLayout view , String error)
    {
        view.setError ( error );
        YoYo.with ( Techniques.Shake ).playOn ( view );
    }

    public String uploadImage ( @NotNull Bitmap bitmap) {
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
                try {
                }
                catch ( Exception e )
                {

                }
            }
        } );
        return imgname;
    }

}
