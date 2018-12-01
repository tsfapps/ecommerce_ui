package com.videlo.videlo.v_model;

public class User {

    private int ID;
    private String user_login;
    private String user_email;
    private String user_url;
    private String user_registered;

    public User(int ID, String user_login, String user_email, String user_url, String user_registered) {
        this.ID = ID;
        this.user_login = user_login;
        this.user_email = user_email;
        this.user_url = user_url;
        this.user_registered = user_registered;
    }

    public int getID() {
        return ID;
    }

    public String getUser_login() {
        return user_login;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_url() {
        return user_url;
    }

    public String getUser_registered() {
        return user_registered;
    }
}
