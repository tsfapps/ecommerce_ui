package com.videlo.videlo;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static String BaseUrl = "http://appzack.com/";
    private static String BASE_URL = "http://appzack.com/";

    private static Retrofit retrofit;
    private static Retrofit retrofitSign;


    public static Retrofit getApiClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)

                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }





}
