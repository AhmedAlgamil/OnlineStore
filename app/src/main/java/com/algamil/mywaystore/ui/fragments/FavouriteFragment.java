package com.algamil.mywaystore.ui.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.adapters.FavouriteAdapter;
import com.algamil.mywaystore.data.models.favourite.Favourite;
import com.algamil.mywaystore.data.models.favourite.FavouriteData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.algamil.mywaystore.data.api.ApiClient.getApiClient;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.CUSTOMER_ID;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.REMEMBER_ME;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.loadBoolean;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.loadData;

public class FavouriteFragment extends Fragment {
    @BindView ( R.id.recycler_favourite )
    RecyclerView recyclerFavourite;
    @BindView ( R.id.swip_refresh_favourite )
    SwipeRefreshLayout swipRefreshFavourite;
    @BindView ( R.id.container_favourite )
    RelativeLayout containerFavourite;
    FavouriteAdapter favouriteAdapter;

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {

        View view = inflater.inflate ( R.layout.fragment_favourite , container , false );
        ButterKnife.bind ( this , view );
        if(loadBoolean ( getActivity () , REMEMBER_ME ))
        {
            getMyFavourite(loadData ( getActivity ( ) , CUSTOMER_ID ));
            swipRefreshFavourite.setOnRefreshListener ( new SwipeRefreshLayout.OnRefreshListener ( ) {
                @Override
                public void onRefresh ( ) {
                    getMyFavourite ( CUSTOMER_ID );
                }
            } );
        }
        else {

        }

        return view;
    }


    public void getMyFavourite ( String phone ) {
        getApiClient ().getMyFavouite ( "26" ).enqueue ( new Callback < Favourite > ( ) {
            @Override
            public void onResponse ( Call < Favourite > call , Response < Favourite > response ) {
                try {
                        makeRecyclerView ( response.body ( ).getData ( ) );
                        swipRefreshFavourite.setRefreshing ( true );
                        new CountDownTimer ( 3000 , 1000 ) {
                            @Override
                            public void onTick ( long millisUntilFinished ) {

                            }

                            @Override
                            public void onFinish ( ) {
                                swipRefreshFavourite.setRefreshing ( false );
                            }
                        }.start ( );

                } catch ( Exception e ) {

                }
            }

            @Override
            public void onFailure (Call <Favourite> call , Throwable t ) {
                try {

                }
                catch ( Exception e )
                {

                }
            }
        } );
    }

    public void makeRecyclerView ( List <FavouriteData> products ) {
        recyclerFavourite.setHasFixedSize ( true );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager ( getActivity ( ) );
        recyclerFavourite.setLayoutManager ( layoutManager );
        favouriteAdapter = new FavouriteAdapter ( products , getActivity ( ) );
        recyclerFavourite.setAdapter ( favouriteAdapter );
    }


}
