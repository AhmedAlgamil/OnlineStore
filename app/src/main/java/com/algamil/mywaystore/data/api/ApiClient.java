package com.algamil.mywaystore.data.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://mywaystore123.000webhostapp.com/";
    private static Retrofit rtf = null;

    public synchronized static ApiService getApiClient()
    {
        if(rtf == null)
        {
            rtf=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return rtf.create(ApiService.class);
    }


}
