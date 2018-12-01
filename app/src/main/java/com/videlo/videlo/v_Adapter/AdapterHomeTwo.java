package com.videlo.videlo.v_Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.videlo.videlo.R;
import com.videlo.videlo.v_model.ModelHomeTwo;

import java.util.List;
import java.util.zip.Inflater;

public class AdapterHomeTwo extends RecyclerView.Adapter<AdapterHomeTwo.MyViewHolder> {

    private List<ModelHomeTwo> modelHomeTwos;
    private Context context;
    private ProdictClick sprodictClick;

    public AdapterHomeTwo(List<ModelHomeTwo> modelHomeTwos, Context context) {
        this.modelHomeTwos = modelHomeTwos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.modeltwo, viewGroup, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        ModelHomeTwo modelHomeTwo = modelHomeTwos.get(i);
        myViewHolder.textView.setText(modelHomeTwo.getName());
        Glide.with(context).load(modelHomeTwo.getImage()).into(myViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return modelHomeTwos.size();
    }

    public interface ProdictClick {
        public void itemClick(int pos);
    }

    public void setOnClikListner(ProdictClick prodictClick) {

        this.sprodictClick = prodictClick;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_one);
            imageView = itemView.findViewById(R.id.img_one);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (sprodictClick != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            sprodictClick.itemClick(position);
                        }

                    }


                }


            });

        }
    }

}
