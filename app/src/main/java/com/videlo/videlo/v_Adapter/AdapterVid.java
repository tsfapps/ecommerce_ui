package com.videlo.videlo.v_Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.videlo.videlo.R;
import com.videlo.videlo.v_model.ApiModel;

import java.util.List;

public class AdapterVid extends RecyclerView.Adapter<AdapterVid.MyViewHolder> {


    private Context context;
    private List<ApiModel> apiModels;


    private OnItmClkListener mOnItmClkListener;
    private OnWishClkListener mOnWishClkListener;




    public AdapterVid(Context context, List<ApiModel> apiModels) {
        this.context = context;
        this.apiModels = apiModels;
    }


    public interface OnItmClkListener {
        void onItmClk(int position);
    }

    public void setOnItmClkListener(OnItmClkListener onItmClkListener) {
        mOnItmClkListener = onItmClkListener;
    }

    public interface OnWishClkListener {
        void onWishClk(int wish);
    }

    public void setOnWishClkListener(OnWishClkListener onWishClkListener) {
        mOnWishClkListener = onWishClkListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model, viewGroup, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        ApiModel apiModel = apiModels.get(i);
        myViewHolder.textView.setText(apiModel.getName());
        //  myViewHolder.textViewTwo.setText(apiModel.getPro());
        Glide.with(context).load(apiModel.getImage()).into(myViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return apiModels.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView, textViewTwo;
        private ImageView imageView, wishList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_one);
            textViewTwo = itemView.findViewById(R.id.tv_two);
            imageView = itemView.findViewById(R.id.img_one);



            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mOnItmClkListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mOnItmClkListener.onItmClk(position);
                        }
                    }
                }

            });





                   

        }
    }
}

