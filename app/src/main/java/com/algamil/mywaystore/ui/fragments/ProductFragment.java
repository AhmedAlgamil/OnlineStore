package com.algamil.mywaystore.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.data.models.bags.Bags;
import com.algamil.mywaystore.data.models.favourite.Favourite;
import com.algamil.mywaystore.data.models.notification.Notification2;
import com.algamil.mywaystore.helper.HelperMethod;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.algamil.mywaystore.data.api.ApiClient.getApiClient;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.CATEGORY;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.CUSTOMER_ID;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.MY_PHONE;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_COST;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_DETAILS;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_IMAGE;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_NAME;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_QUANTITY;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.REMEMBER_ME;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.loadBoolean;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.loadData;


public class ProductFragment extends BaseFragment {
    public static int quantityNumber = 1;
    public static String OPR_FAVURITE = "not added";
    String my_id = "";
    @BindView ( R.id.tv_title )
    TextView tvTitle;
    @BindView ( R.id.image_view_product )
    ImageView imageViewProduct;
    @BindView ( R.id.tv_product_cost )
    TextView tvProductCost;
    @BindView ( R.id.product_name )
    TextView productName;
    @BindView ( R.id.product_quntity )
    TextView productQuntity;
    @BindView ( R.id.product_details )
    TextView productDetails;
    @BindView ( R.id.fab_plus )
    FloatingActionButton fabPlus;
    @BindView ( R.id.quantity )
    EditText quantity;
    @BindView ( R.id.fab_minus )
    FloatingActionButton fabMinus;
    @BindView ( R.id.quantity_linear )
    LinearLayout quantityLinear;
    @BindView ( R.id.scroll_view_product )
    ScrollView scrollViewProduct;
    @BindView ( R.id.add_to_favourite )
    Button addToFavourite;
    @BindView ( R.id.add_to_bags )
    Button addToBags;
    @BindView ( R.id.container )
    ConstraintLayout container;
    public static String OPR_PRODUCT = "close";
    public static boolean IS_HERE = false;

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public void onBack ( ) {
        super.onBack ( );
        if(OPR_PRODUCT == "close")
        {
            baseActivity.finish ();
        }
        else if(OPR_PRODUCT == "no")
        {
            OPR_PRODUCT = "close";
        }
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_product2 , container , false );
        ButterKnife.bind ( this , view );
        setUpActivity ();
        checking();
        if(IS_HERE)
        {
            addToFavourite.setCompoundDrawablesRelativeWithIntrinsicBounds ( null ,
                    null ,
                    HelperMethod.getDrawableFromXML ( getActivity () ,
                            R.drawable.added_at_favourit ) , null );
        }
        else {
            addToFavourite.setCompoundDrawablesRelativeWithIntrinsicBounds ( null ,
                    null ,
                    HelperMethod.getDrawableFromXML ( getActivity () ,
                            R.drawable.add_to_favourite ) , null );
        }
        Glide.with ( getActivity () )
                .load ( loadData ( getActivity () , PRODUCT_IMAGE ) )
                .centerCrop ( )
                .into ( imageViewProduct );
        tvTitle.setText ( loadData ( getActivity () , CATEGORY ) );
        productName.setText ( loadData ( getActivity () , PRODUCT_NAME ) );
        productDetails.setText ( loadData ( getActivity () , PRODUCT_DETAILS ) );
        tvProductCost.setText ( loadData ( getActivity () , PRODUCT_COST ) );
        productQuntity.setText ( loadData ( getActivity () , PRODUCT_QUANTITY ) );
        quantity.setText ( quantityNumber + "" );
        addToBags.setOnLongClickListener ( new View.OnLongClickListener ( ) {
            @Override
            public boolean onLongClick ( View v ) {
                editOrder ( );
                return true;
            }
        } );

        return view;
    }

    public void checking ( ) {
//        loadData ( getActivity () , CUSTOMER_ID )
        getApiClient ( ).getMyFavouite ( "26" ).enqueue ( new Callback < Favourite > ( ) {
            @Override
            public void onResponse (Call <Favourite> call , Response < Favourite > response ) {
                if(response.body().getStatus() == true)
                {
                    Toast.makeText(baseActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    for ( int i = 0 ; i < response.body ( ).getData ( ).size ( ) ; i++ ) {
                        if ( response.body ( ).getData ( ).get ( i ).getProduct_name ( ) == loadData ( getActivity () , PRODUCT_NAME ) ) {
                            my_id = response.body ( ).getData ( ).get ( i ).getId ( );
//                            Toast.makeText(baseActivity, "yessss is here" + response.body().getData().get(i).getProduct_name(), Toast.LENGTH_SHORT).show();

                            IS_HERE = true;
                        }
                        else {
                            Toast.makeText(baseActivity, "noooooo is not here" + response.body().getData().get(i).getProduct_name(), Toast.LENGTH_SHORT).show();
                            IS_HERE = false;
                        }
                    }
                } else {

                }

            }

            @Override
            public void onFailure ( Call < Favourite > call , Throwable t ) {
                Toast.makeText(baseActivity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        } );
    }

    @OnClick ( { R.id.fab_plus , R.id.fab_minus , R.id.add_to_favourite , R.id.add_to_bags } )
    public void onClick ( View view ) {
        switch ( view.getId ( ) ) {
            case R.id.fab_plus:
                plusNumber ( );
                break;
            case R.id.fab_minus:
                minusNumber ( );
                break;
            case R.id.add_to_favourite:
                if(loadBoolean ( getActivity () , REMEMBER_ME ))
                {
                    if(IS_HERE)
                    {
                        makeDelete2 ( my_id );
                        addToFavourite.setCompoundDrawablesRelativeWithIntrinsicBounds ( null ,
                                null ,
                                HelperMethod.getDrawableFromXML ( getActivity () ,
                                        R.drawable.added_at_favourit ) , null );
                    }
                    else {
                        makeAddToFavourite( getActivity () );
                        addToFavourite.setCompoundDrawablesRelativeWithIntrinsicBounds ( null ,
                                null ,
                                HelperMethod.getDrawableFromXML ( getActivity () ,
                                        R.drawable.add_to_favourite ) , null );
                    }

                }
                else
                {
                    HelperMethod.replaceFragment ( getActivity ().getSupportFragmentManager () , R.id.frame_product , new LoginFragment () );
                }
                break;
            case R.id.add_to_bags:
                if(loadBoolean ( getActivity () , REMEMBER_ME ))
                {
                    makeAddToBags( getActivity () ,container );
                }
                else {
                    HelperMethod.replaceFragment ( getActivity ().getSupportFragmentManager () , R.id.frame_product , new LoginFragment () );
                }
                break;
        }
    }

    public void makeDelete2 ( String id ) {

        getApiClient ( ).deletefromeFavourite ( id ).enqueue ( new Callback < Favourite > ( ) {
            @Override
            public void onResponse ( Call < Favourite > call , Response < Favourite > response ) {

            }

            @Override
            public void onFailure ( Call < Favourite > call , Throwable t ) {
                Toast.makeText(baseActivity, t.getLocalizedMessage() + " delete", Toast.LENGTH_SHORT).show();
            }
        } );
    }

    public boolean getAllPeople ( ) {
        final boolean[] status = { false };
        getApiClient ( ).getNotification ( loadData ( getActivity () , CUSTOMER_ID ) ).enqueue ( new Callback < Notification2 > ( ) {
            @Override
            public void onResponse ( Call < Notification2 > call ,
                                     Response < Notification2 > response ) {
                try {
                    if ( response.body ( ).getData ( ).isEmpty ( ) ) {
                        status[ 0 ] = false;
                        Toast.makeText ( baseActivity , status[0] + " this is get all people " , Toast.LENGTH_SHORT ).show ( );
                    } else {
                        status[ 0 ] = true;
                        Toast.makeText ( baseActivity , status[0] + " " , Toast.LENGTH_SHORT ).show ( );
                    }

                } catch ( Exception e ) {

                }
            }

            @Override
            public void onFailure ( Call < Notification2 > call , Throwable t ) {
                try {
                    Toast.makeText(baseActivity, t.getLocalizedMessage() + "   get all people", Toast.LENGTH_SHORT).show();
                } catch ( Exception e ) {

                }
            }
        } );
        return status[ 0 ];
    }

    public void editOrder ( ) {
        getApiClient ( ).editOrder ( loadData ( getActivity () , PRODUCT_NAME ) , quantityNumber + "" ,
                loadData ( getActivity () , MY_PHONE ) )
                .enqueue ( new Callback < Bags > ( ) {
                    @Override
                    public void onResponse ( Call < Bags > call , Response < Bags > response ) {
                        try {
                            HelperMethod.showSnackBar ( container , "تم تعديل الكمية المطلوبة " );
                        } catch ( Exception e ) {

                        }

                    }

                    @Override
                    public void onFailure ( Call < Bags > call , Throwable t ) {
                        try {
                            Toast.makeText(baseActivity, t.getLocalizedMessage() + " edit order ", Toast.LENGTH_SHORT).show();
                        } catch ( Exception e ) {

                        }
                    }
                } );
    }

    public void plusNumber ( ) {
        quantityNumber++;
        quantity.setText ( quantityNumber + "" );
    }

    public void minusNumber ( ) {
        if ( quantityNumber == 0 ) {

        } else {
            quantityNumber--;
            quantity.setText ( quantityNumber + "" );
        }

    }

    public void makeAddToFavourite(Activity activity) {
        getApiClient ( ).addToFavourite ( loadData ( activity , PRODUCT_NAME ) , loadData ( activity ,
                PRODUCT_COST ) , loadData ( activity , CATEGORY ) ,
                loadData ( activity , PRODUCT_DETAILS ) , loadData ( activity , PRODUCT_QUANTITY ) ,
                loadData ( activity , CUSTOMER_ID ) , loadData ( activity , PRODUCT_IMAGE ) )
                .enqueue ( new Callback < Favourite > ( ) {
                    @Override
                    public void onResponse ( Call < Favourite > call ,
                                             Response < Favourite > response ) {
                        try {

                        } catch ( Exception e ) {

                        }
                    }

                    @Override
                    public void onFailure ( Call < Favourite > call , Throwable t ) {
                        try {
                            Toast.makeText(baseActivity, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                        } catch ( Exception e ) {

                        }
                    }
                } );
    }

    public void makeAddToBags ( Activity activity , View container ) {
        getApiClient ( ).addToBags ( loadData ( activity , PRODUCT_NAME ) , loadData ( activity ,
                PRODUCT_COST ) , loadData ( activity , CATEGORY ) ,
                loadData ( activity , PRODUCT_DETAILS ) , quantityNumber + "" , loadData ( activity ,
                        CUSTOMER_ID ) , loadData ( activity , PRODUCT_IMAGE ) )
                .enqueue ( new Callback < Bags > ( ) {
                    @Override
                    public void onResponse ( Call < Bags > call , Response < Bags > response ) {
                        try {
                            HelperMethod.showSnackBar ( container ,
                                    response.body ( ).getMessage ( ) );
                        } catch ( Exception e ) {

                        }
                    }

                    @Override
                    public void onFailure ( Call < Bags > call , Throwable t ) {
                        try {
                            Toast.makeText(baseActivity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        } catch ( Exception e ) {

                        }
                    }
                } );
    }

}
