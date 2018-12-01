package com.videlo.videlo;


import com.videlo.videlo.v_model.VideloModel;
import com.videlo.videlo.v_model.ApiModel;
import com.videlo.videlo.v_model.LoginModel;
import com.videlo.videlo.v_model.SignupModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @POST("t_videlo/t_images.php")
    Call<List<ApiModel>> getLoc();

    @POST("t_videlo_url/t_agriculture.php")
    Call<List<VideloModel>> getAgriculture();


    @POST("t_videlo_url/t_appliances.php")
    Call<List<VideloModel>> getAppliances();


    @POST("t_videlo_url/t_automobies.php")
    Call<List<VideloModel>> getAutomobile();


    @POST("t_videlo_url/t_babies.php")
    Call<List<VideloModel>> getBabies();

    @POST("t_videlo_url/t_bags.php")
    Call<List<VideloModel>> getBag();


    @POST("t_videlo_url/t_books.php")
    Call<List<VideloModel>> getBooks();

    @POST("t_videlo_url/t_computers.php")
    Call<List<VideloModel>> getComputer();

    @POST("t_videlo_url/t_consumer.php")
    Call<List<VideloModel>> getConsumer();


    @POST("t_videlo_url/t_garden.php")
    Call<List<VideloModel>> getGarden();


    @POST("t_videlo_url/t_jewellery.php")
    Call<List<VideloModel>> getJewellery();

    @POST("t_videlo_url/t_machinery.php")
    Call<List<VideloModel>> getMachine();

    @POST("t_videlo_url/t_men.php")
    Call<List<VideloModel>> getMen();


    @POST("t_videlo_url/t_office.php")
    Call<List<VideloModel>> getOffice();


    @POST("t_videlo_url/t_packaging.php")
    Call<List<VideloModel>> getPackaging();


    @POST("t_videlo_url/t_personal.php")
    Call<List<VideloModel>> getPersonal();

    @POST("t_videlo_url/t_phone.php")
    Call<List<VideloModel>> getPhone();

    @POST("t_videlo_url/t_sports.php")
    Call<List<VideloModel>> getSports();


    @POST("t_videlo_url/t_watches.php")
    Call<List<VideloModel>> getWatche();


    @POST("t_videlo_url/t_women.php")
    Call<List<VideloModel>> getWomen();


    //signup
    @FormUrlEncoded
    @POST("t_videlo/t_signup.php")
    Call<SignupModel> signUp(@Field("user_email") String user_email,
                             @Field("user_password") String user_password,
                             @Field("user_login") String user_login
    );


    // item added to wishlist

    @FormUrlEncoded
    @POST("t_videlo/t_wishlist.php")
    Call<ApiModel> addToWishList(@Field("w_price") String cart_name,
                                 @Field("w_email") String cart_email
    );


    // Login
    @FormUrlEncoded
    @POST("t_videlo/t_login.php")
    Call<LoginModel> login(@Field("user_email") String user_email,
                           @Field("user_pass") String user_password);


}