package com.algamil.mywaystore.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.data.models.favourite.Favourite;
import com.algamil.mywaystore.data.models.favourite.FavouriteData;
import com.algamil.mywaystore.ui.activities.ProductActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static butterknife.ButterKnife.bind;
import static com.algamil.mywaystore.data.api.ApiClient.getApiClient;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_COST;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_DETAILS;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_IMAGE;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_NAME;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.saveData;

public class FavouriteAdapter extends RecyclerView.Adapter < FavouriteAdapter.BagsCustomRecyclerViewHolder > {

    Activity activity;
    List <FavouriteData> list;

    public static String OPR_FROM_FAVOURITE = "not added";
    public static final String OPR_FROM_FAVOURITE_KEY = "OPR_FROM_FAVOURITE_KEY";

    public FavouriteAdapter ( List < FavouriteData > list , Activity activity ) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BagsCustomRecyclerViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( parent.getContext ( ) ).inflate ( R.layout.bags_items , parent , false );
        bind ( this , view );
        return new BagsCustomRecyclerViewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull BagsCustomRecyclerViewHolder holder , int position ) {
        holder.tvCostBag.setText ( list.get ( position ).getCost ( ) );
        holder.tvNameBag.setText ( list.get ( position ).getProduct_name ( ) );
        Glide.with ( activity )
                .load ( list.get ( position ).getImage_url ( ) )
                .into ( holder.ivBag );

        holder.recyclerContainerBag.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                saveData ( activity , PRODUCT_NAME , list.get ( position ).getProduct_name ( ) );
                saveData ( activity , PRODUCT_DETAILS , list.get ( position ).getProduct_details ( ) );
                saveData ( activity , PRODUCT_IMAGE , list.get ( position ).getImage_url ( ) );
                saveData ( activity , PRODUCT_COST , list.get ( position ).getCost ( ) );
                saveData ( activity , OPR_FROM_FAVOURITE_KEY , OPR_FROM_FAVOURITE );
                Intent intent = new Intent ( holder.con , ProductActivity.class );
                activity.startActivity ( intent );
            }
        } );
        holder.fabDelete.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                makeDelete(list.get ( position ).getId ());
            }
        } );
    }

    public void makeDelete(String id)
    {

        getApiClient ().deletefromeFavourite ( id ).enqueue ( new Callback < Favourite > ( ) {
            @Override
            public void onResponse ( Call < Favourite > call , Response <Favourite> response ) {
                try {
                    Toast.makeText ( activity , "تم مسح الطلب" , Toast.LENGTH_SHORT ).show ( );
                }
                catch ( Exception e )
                {

                }

            }

            @Override
            public void onFailure ( Call < Favourite > call , Throwable t ) {
                try {

                }
                catch ( Exception e )
                {

                }
            }
        } );
    }

    @Override
    public int getItemCount ( ) {
        return list.size ( );
    }


    public class BagsCustomRecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView ( R.id.fab_delete )
        FloatingActionButton fabDelete;
        @BindView ( R.id.iv_bag )
        ImageView ivBag;
        @BindView ( R.id.tv_name_bag )
        TextView tvNameBag;
        @BindView ( R.id.tv_cost_bag )
        TextView tvCostBag;
        @BindView ( R.id.recycler_container_bag )
        RelativeLayout recyclerContainerBag;

        public Context con;

        public BagsCustomRecyclerViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            con = itemView.getContext ( );
            bind ( this , itemView );
        }

    }

}
