package com.algamil.mywaystore.data.api;

import com.algamil.mywaystore.data.models.bags.Bags;
import com.algamil.mywaystore.data.models.favourite.Favourite;
import com.algamil.mywaystore.data.models.login.Login;
import com.algamil.mywaystore.data.models.notification.Notification2;
import com.algamil.mywaystore.data.models.product.Product;
import com.algamil.mywaystore.data.models.sendEmail.SendingEmail;
import com.algamil.mywaystore.data.models.signup.SignUp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("customers/Login.php")
    @FormUrlEncoded
    Call< Login > login ( @Field ( "phone" ) String phone
            , @Field ( "password" ) String password );



    @POST("products/select.php")
    @FormUrlEncoded
    Call< Product > getProducts ( @Field ( "category" ) String category );


    @POST("products/selectcategoryname.php")
    @FormUrlEncoded
    Call< Product > getProductsAndName ( @Field ( "category" ) String category , @Field ( "product_name" ) String product_name );

    @POST("notifications/insert.php")
    @FormUrlEncoded
    Call< Notification2 > insertNotification (
            @Field ( "customer_name" ) String customer_name
            , @Field ( "customer_id" ) String customer_id
            ,@Field ( "customer_location" ) String customer_location
    );

    @POST("notifications/select.php")
    @FormUrlEncoded
    Call< Notification2 > getNotification(@Field ( "customer_id" ) String customer_id );

    @POST("products/selectcategoryAndCost.php")
    @FormUrlEncoded
    Call< Product > getProductsAndCost ( @Field ( "category" ) String category , @Field ( "cost" ) String cost );

    @POST("products/selectcostnamecategory.php")
    @FormUrlEncoded
    Call< Product > getProductsAndCostAndName ( @Field ( "category" ) String category , @Field ( "cost" ) String cost
            , @Field ( "product_name" ) String product_name);

    @POST("customers/update_password.php")
    @FormUrlEncoded
    Call< Login > changePassword (
            @Field ( "email" ) String email ,
            @Field ( "password" ) String  password
    );

    @POST("sendemail.php")
    @FormUrlEncoded
    Call< SendingEmail > sendEmail (
            @Field ( "to" ) String to ,
            @Field ( "code" ) String code
    );

    @POST("bags/allselect.php")
    @FormUrlEncoded
    Call< Bags > getMyBags ( @Field ( "customer_id" ) String customer_id );

    @POST("favourite/allselect.php")
    @FormUrlEncoded
    Call< Favourite > getMyFavouite ( @Field ( "customer_id" ) String customer_id );

    @POST("favourite/delete.php")
    @FormUrlEncoded
    Call<Favourite> deletefromeFavourite (@Field ( "id" ) String id );

    @POST("bags/delete.php")
    @FormUrlEncoded
    Call< Bags > delete ( @Field ( "id" ) String id );

    @POST("bags/insert.php")
    @FormUrlEncoded
    Call< Bags > addToBags (
            @Field ( "product_name" ) String product_name,
            @Field ( "cost" ) String cost,
            @Field ( "category" ) String category,
            @Field ( "product_details" ) String product_details,
            @Field ( "quantity_order" ) String quantity_order,
            @Field ( "customer_id" ) String customer_id,
            @Field ( "image_url" ) String image_url );

    @POST("favourite/insert.php")
    @FormUrlEncoded
    Call< Favourite > addToFavourite (
            @Field ( "product_name" ) String product_name,
            @Field ( "cost" ) String cost,
            @Field ( "category" ) String category,
            @Field ( "product_details" ) String product_details,
            @Field ( "quantity" ) String quantity,
            @Field ( "customer_id" ) String customer_id,
            @Field ( "image_url" ) String image_url );

    @POST("bags/update.php")
    @FormUrlEncoded
    Call< Bags > editOrder (
            @Field ( "product_name" ) String product_name,
            @Field ( "quantity_order" ) String quantity_order,
            @Field ( "customer_id" ) String customer_id);


    @FormUrlEncoded
    @POST("customers/insert.php")
    Call< SignUp > signUp ( @Field ( "name" ) String name
            , @Field ( "email" ) String email
            , @Field ( "phone" ) String phone
            , @Field ( "password" ) String password
            , @Field ( "location" ) String location
            , @Field ( "image_url" ) String image_url
    );

    @FormUrlEncoded
    @POST("customers/update.php")
    Call< SignUp > editProfile ( @Field ( "id" ) String id
            , @Field ( "name" ) String name
            , @Field ( "email" ) String email
            , @Field ( "phone" ) String phone
            , @Field ( "password" ) String password
            , @Field ( "location" ) String location
            , @Field ( "image_url" ) String image_url
    );

    @FormUrlEncoded
    @POST("upload.php")
    Call<String> Uploading (
            @Field ( "name" ) String name ,
            @Field ( "image" ) String image
    );

}
