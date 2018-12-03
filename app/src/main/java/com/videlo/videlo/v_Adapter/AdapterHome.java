package com.videlo.videlo.v_Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.videlo.videlo.R;
import com.videlo.videlo.v_model.ModelHome;

import java.util.ArrayList;
import java.util.List;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.HomeViewHolder> {

    private Context context;
    private List<ModelHome> modelHomes;

    private OnHomeClkListener tOnHomeClkListener;


    public AdapterHome(Context context, List<ModelHome> modelHomes) {

        this.context = context;
        this.modelHomes = modelHomes;

    }


    public interface OnHomeClkListener {
        public void OnClkHome(int pos);
    }

    public void setOnHomeClkListener(OnHomeClkListener onHomeClkListener) {
        tOnHomeClkListener = onHomeClkListener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model, viewGroup, false);


        HomeViewHolder vh = new HomeViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder homeViewHolder, int i) {

        ModelHome modelHome = modelHomes.get(i);

        homeViewHolder.textView.setText(modelHome.getProductname());
        Glide.with(context).load(modelHome.getProductImage()).into(homeViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return modelHomes.size();
    }


    public class HomeViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_one);
            imageView = itemView.findViewById(R.id.img_one);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tOnHomeClkListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            tOnHomeClkListener.OnClkHome(position);
                        }

                    }
                }
            });

        }
    }
    public void filterItem(List<ModelHome> newList){
        modelHomes = new ArrayList<>();
        modelHomes.addAll(newList);
        notifyDataSetChanged();
    }

}
