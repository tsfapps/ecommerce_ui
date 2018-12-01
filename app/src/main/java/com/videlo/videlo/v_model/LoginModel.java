package com.videlo.videlo.v_model;

import com.google.gson.annotations.SerializedName;

public class LoginModel {


    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;


    @SerializedName("user")
    private User user;

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
