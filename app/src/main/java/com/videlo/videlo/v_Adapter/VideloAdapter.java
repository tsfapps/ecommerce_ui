package com.videlo.videlo.v_Adapter;

import android.content.Context;
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
import com.videlo.videlo.v_model.VideloModel;

import java.util.ArrayList;
import java.util.List;

public class VideloAdapter extends RecyclerView.Adapter<VideloAdapter.MyViewHolder> {

    private Context context;
    private List<VideloModel> videloModels;

    private OnItmCickListener tListener;

    public interface OnItmCickListener{
        void OnItmClk(int pos);
    }


    public void setOnItmClkListener(OnItmCickListener listener){
        tListener = listener;
    }


    public VideloAdapter(Context context, List<VideloModel> videloModels) {
        this.context = context;
        this.videloModels = videloModels;
    }

    @NonNull
    @Override
    public VideloAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model_main,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideloAdapter.MyViewHolder myViewHolder, int i) {

        VideloModel videloModel = videloModels.get(i);
        myViewHolder.textViewUrl.setText(videloModel.getUrl());
        myViewHolder.textViewTitle.setText(videloModel.getTitle());
        Glide.with(context).load(videloModel.getImage()).into(myViewHolder.imageViewVid);

    }

    @Override
    public int getItemCount() {
        return videloModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewVid;
        private TextView textViewTitle;
        private TextView textViewUrl;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewVid = itemView.findViewById(R.id.imageViewCategory);
            textViewTitle = itemView.findViewById(R.id.textViewCategory);
            textViewUrl = itemView.findViewById(R.id.tv_webViewUrl);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            tListener.OnItmClk(position);
                        }
                    }
                }
            });
        }
    }

    public void filterItem(List<VideloModel> newList){
        videloModels = new ArrayList<>();
        videloModels.addAll(newList);
        notifyDataSetChanged();
    }

}
