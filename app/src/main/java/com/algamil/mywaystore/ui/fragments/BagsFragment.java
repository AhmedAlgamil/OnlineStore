package com.algamil.mywaystore.ui.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.adapters.BagsAdapter;
import com.algamil.mywaystore.data.models.bags.Bags;
import com.algamil.mywaystore.data.models.bags.BagsData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.algamil.mywaystore.data.api.ApiClient.getApiClient;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.REMEMBER_ME;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.USER_PHONE;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.loadBoolean;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.loadData;

public class BagsFragment extends Fragment {

    @BindView ( R.id.recycler_bags )
    RecyclerView recyclerBags;

    BagsAdapter bagsAdapter;
    @BindView ( R.id.swip_refresh )
    SwipeRefreshLayout swipRefresh;
    @BindView ( R.id.bags_container )
    ConstraintLayout bagsContainer;

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {

        View view = inflater.inflate ( R.layout.fragment_bags , container , false );
        ButterKnife.bind ( this , view );
        if ( loadBoolean ( getActivity ( ) , REMEMBER_ME ) ) {
            getMyBags ( );
            swipRefresh.setOnRefreshListener ( new SwipeRefreshLayout.OnRefreshListener ( ) {
                @Override
                public void onRefresh ( ) {
                    getMyBags ( );
                }
            } );
        } else {

        }

        return view;
    }

    public void getMyBags ( ) {
        getApiClient ( ).getMyBags ( loadData ( getActivity ( ) , USER_PHONE ) ).enqueue ( new Callback < Bags > ( ) {
            @Override
            public void onResponse ( Call < Bags > call , Response < Bags > response ) {
                try {
                    if ( response.body ( ).getStatus ( ).equals ( true ) ) {
                        makeRecyclerView ( response.body ( ).getData ( ) );
                        swipRefresh.setRefreshing ( true );
                        new CountDownTimer ( 3000 , 1000 ) {
                            @Override
                            public void onTick ( long millisUntilFinished ) {

                            }

                            @Override
                            public void onFinish ( ) {
                                swipRefresh.setRefreshing ( false );
                            }
                        }.start ( );
                    } else {

                    }

                } catch ( Exception e ) {

                }

            }

            @Override
            public void onFailure ( Call < Bags > call , Throwable t ) {
                try {

                } catch ( Exception e ) {

                }
            }
        } );
    }

    public void makeRecyclerView ( List < BagsData > products ) {
        recyclerBags.setHasFixedSize ( true );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager ( getActivity ( ) );
        recyclerBags.setLayoutManager ( layoutManager );
        bagsAdapter = new BagsAdapter ( products , getActivity ( ) );
        recyclerBags.setAdapter ( bagsAdapter );
    }

}
