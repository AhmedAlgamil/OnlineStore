package com.algamil.mywaystore.ui.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.adapters.ProductRecyclerItem;
import com.algamil.mywaystore.data.models.product.Product;
import com.algamil.mywaystore.data.models.product.ProductData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.algamil.mywaystore.data.api.ApiClient.getApiClient;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.CATEGORY;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.loadData;

public class FoodsFragment extends Fragment {

    @BindView ( R.id.et_search_foods )
    EditText etSearchFoods;
    @BindView ( R.id.search_foods )
    ImageView searchFoods;
    @BindView ( R.id.linear_Layout_foods )
    LinearLayout linearLayoutFoods;
    @BindView ( R.id.et_search_cost )
    EditText etSearchCost;
    @BindView ( R.id.search_dolar )
    ImageView searchDolar;
    @BindView ( R.id.linear_Layout_foods_cost )
    LinearLayout linearLayoutFoodsCost;
    @BindView ( R.id.recycler_foods )
    RecyclerView recyclerFoods;
    ProductRecyclerItem productRecyclerItem;
    @BindView ( R.id.make_swipe )
    SwipeRefreshLayout makeSwipe;

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
    }

    @Override
    public View onCreateView ( LayoutInflater inflater , ViewGroup container ,
                               Bundle savedInstanceState ) {

        View view = inflater.inflate ( R.layout.fragment_products , container , false );
        ButterKnife.bind ( this , view );
        makeSwipe2();
        makeSwipe.setOnRefreshListener ( new SwipeRefreshLayout.OnRefreshListener ( ) {
            @Override
            public void onRefresh ( ) {
                new CountDownTimer (3000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
//                        makeSwipe2();
                        makeSwipe.setRefreshing ( false );
                    }
                }.start();
            }
        } );
        return view;
    }

    public void makeSwipe2 ( ) {
        if ( loadData ( getActivity ( ) , CATEGORY ) == "تنظيف" ) {
            makeSearch ( loadData ( getActivity ( ) , CATEGORY ) , etSearchFoods.getText ( ).toString ( ) , etSearchCost.getText ( ).toString ( ) );
        } else if ( loadData ( getActivity ( ) , CATEGORY ) == "عطور" ) {
            makeSearch ( loadData ( getActivity ( ) , CATEGORY ) , etSearchFoods.getText ( ).toString ( ) , etSearchCost.getText ( ).toString ( ) );
        } else if ( loadData ( getActivity ( ) , CATEGORY ) == "طعام" ) {
            makeSearch ( loadData ( getActivity ( ) , CATEGORY ) , etSearchFoods.getText ( ).toString ( ) , etSearchCost.getText ( ).toString ( ) );
        } else if ( loadData ( getActivity ( ) , CATEGORY ) == "العاب" ) {
            makeSearch ( loadData ( getActivity ( ) , CATEGORY ) , etSearchFoods.getText ( ).toString ( ) , etSearchCost.getText ( ).toString ( ) );
        } else if ( loadData ( getActivity ( ) , CATEGORY ) == "ادوات مدرسية" ) {
            makeSearch ( loadData ( getActivity ( ) , CATEGORY ) , etSearchFoods.getText ( ).toString ( ) , etSearchCost.getText ( ).toString ( ) );
        }
    }

    public void makeSearch ( String category , String prouductname , String dolar ) {
        if ( ! category.isEmpty ( ) && prouductname.isEmpty ( ) && dolar.isEmpty ( ) ) {
            getAllProducts ( category );
        } else if ( ! category.isEmpty ( ) && ! prouductname.isEmpty ( ) && dolar.isEmpty ( ) ) {
            getAllProductsName ( category , prouductname );
        } else if ( ! category.isEmpty ( ) && ! prouductname.isEmpty ( ) && ! dolar.isEmpty ( ) ) {
            getAllProductsNameCost ( category , prouductname , dolar );
        } else if ( ! category.isEmpty ( ) && prouductname.isEmpty ( ) && ! dolar.isEmpty ( ) ) {
            getAllProductsCost ( category , dolar );
        }
    }

    @OnClick ( { R.id.search_foods , R.id.search_dolar } )
    public void onClick ( View view ) {
        switch ( view.getId ( ) ) {
            case R.id.search_foods:
                makeSearch ( loadData ( getActivity ( ) , CATEGORY ) , etSearchFoods.getText ( ).toString ( ) , etSearchCost.getText ( ).toString ( ) );
                break;
            case R.id.search_dolar:
                makeSearch ( loadData ( getActivity ( ) , CATEGORY ) , etSearchFoods.getText ( ).toString ( ) , etSearchCost.getText ( ).toString ( ) );
                break;
        }
    }

    public void getAllProductsCost ( String category , String cost ) {
        getApiClient ( ).getProductsAndCost ( category , cost ).enqueue ( new Callback < Product > ( ) {
            @Override
            public void onResponse ( Call < Product > call , Response < Product > response ) {
                try {
                    makeRecyclerView ( response.body ( ).getData ( ) );
                } catch ( Exception e ) {

                }

            }

            @Override
            public void onFailure ( Call < Product > call , Throwable t ) {
                try {

                }
                catch ( Exception e )
                {

                }
            }

        } );
    }


    public void getAllProductsName ( String category , String name ) {
        getApiClient ( ).getProductsAndName ( category , name ).enqueue ( new Callback < Product > ( ) {
            @Override
            public void onResponse ( Call < Product > call , Response < Product > response ) {
                try {
                    makeRecyclerView ( response.body ( ).getData ( ) );
                } catch ( Exception e ) {

                }

            }

            @Override
            public void onFailure ( Call < Product > call , Throwable t ) {
                try {

                }
                catch ( Exception e )
                {

                }
            }

        } );
    }

    public void getAllProductsNameCost ( String category , String cost , String name ) {
        getApiClient ( ).getProductsAndCostAndName ( category , cost , name ).enqueue ( new Callback < Product > ( ) {
            @Override
            public void onResponse ( Call < Product > call , Response < Product > response ) {
                try {
                    makeRecyclerView ( response.body ( ).getData ( ) );
                } catch ( Exception e ) {

                }

            }

            @Override
            public void onFailure ( Call < Product > call , Throwable t ) {
                try {

                }
                catch ( Exception e )
                {

                }
            }

        } );
    }


    public void getAllProducts ( String category ) {
        getApiClient ( ).getProducts ( category ).enqueue ( new Callback < Product > ( ) {
            @Override
            public void onResponse ( Call < Product > call , Response < Product > response ) {
                try {
                    makeRecyclerView ( response.body ( ).getData ( ) );
                } catch ( Exception e ) {

                }

            }

            @Override
            public void onFailure ( Call < Product > call , Throwable t ) {
                try {

                }
                catch ( Exception e )
                {

                }
            }

        } );
    }

    public void makeRecyclerView ( List < ProductData > products ) {
        recyclerFoods.setHasFixedSize ( true );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager ( getActivity ( ) );
        recyclerFoods.setLayoutManager ( layoutManager );
        productRecyclerItem = new ProductRecyclerItem ( products , getActivity ( ) );
        recyclerFoods.setAdapter ( productRecyclerItem );
    }


}
