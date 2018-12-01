package com.videlo.videlo.v_model;

import android.widget.ImageView;

public class ModelHome {

    private String productname;
    private int productImage;


    public ModelHome(String productname, int productImage) {
        this.productname = productname;
        this.productImage = productImage;
    }

    public String getProductname() {
        return productname;
    }

    public int getProductImage() {
        return productImage;
    }


    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }
}
