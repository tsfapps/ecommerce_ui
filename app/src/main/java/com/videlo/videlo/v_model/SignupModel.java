package com.videlo.videlo.v_model;

import com.google.gson.annotations.SerializedName;

public class SignupModel {

    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;


    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
