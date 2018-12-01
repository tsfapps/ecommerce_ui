package com.videlo.videlo.v_model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiModel {

    private String s;


    @SerializedName("id")
    private String name;


    @SerializedName("title")
    private String pro;


    @SerializedName("url")
    private String image;




    public String getName() {
        return name;
    }

    public String getImage() {

        return image;
    }

    public String getPro() {
        return pro;
    }



}
