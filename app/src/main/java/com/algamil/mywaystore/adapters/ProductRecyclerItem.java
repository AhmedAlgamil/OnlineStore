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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.algamil.mywaystore.R;
import com.algamil.mywaystore.data.models.product.ProductData;
import com.algamil.mywaystore.ui.activities.ProductActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;

import static butterknife.ButterKnife.bind;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_COST;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_DETAILS;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_IMAGE;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_NAME;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.PRODUCT_QUANTITY;
import static com.algamil.mywaystore.data.local.SharedPreferencesManger.saveData;

public class ProductRecyclerItem extends RecyclerView.Adapter < ProductRecyclerItem.CustomRecyclerViewHolder > {

    Activity activity;
    List < ProductData > list;

    public ProductRecyclerItem ( List < ProductData > list , Activity activity ) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CustomRecyclerViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from ( parent.getContext ( ) ).inflate ( R.layout.recycler_item_products , parent , false );
        bind ( this , view );
        return new CustomRecyclerViewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull CustomRecyclerViewHolder holder , int position ) {
        holder.tvCost.setText ( list.get ( position ).getCost ( ) );
        holder.tvName.setText ( list.get ( position ).getProduct_name ( ) );
        Glide.with ( activity )
                .load ( list.get ( position ).getImage_url ( ) )
                .into ( holder.ivProduct );
        holder.recyclerContainer.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                saveData ( activity , PRODUCT_NAME , list.get ( position ).getProduct_name ( ) );
                saveData ( activity , PRODUCT_DETAILS , list.get ( position ).getProduct_details ( ) );
                saveData ( activity , PRODUCT_IMAGE , list.get ( position ).getImage_url ( ) );
                saveData ( activity , PRODUCT_COST , list.get ( position ).getCost ( ) );
                saveData ( activity , PRODUCT_QUANTITY , "الكمية : " + list.get ( position ).getQuantity ( ) );
                Intent intent = new Intent ( holder.con , ProductActivity.class );
                activity.startActivity ( intent );
            }
        } );
    }

    @Override
    public int getItemCount ( ) {
        return list.size ( );
    }

    public class CustomRecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView ( R.id.iv_product )
        ImageView ivProduct;
        @BindView ( R.id.tv_name )
        TextView tvName;
        @BindView ( R.id.tv_cost )
        TextView tvCost;
        @BindView ( R.id.recycler_container )
        RelativeLayout recyclerContainer;

        public Context con;

        public CustomRecyclerViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            con = itemView.getContext ( );
            bind ( this , itemView );
        }

    }

}
