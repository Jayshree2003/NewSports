package com.example.newsports.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsports.R;
import com.example.newsports.models.ViewAllModel;

import java.util.List;

public class ViewAllAadpter extends RecyclerView.Adapter<ViewAllAadpter.ViewHolder> {
    Context context;
    List<ViewAllModel> viewAllModelList;

    public ViewAllAadpter(Context context, List<ViewAllModel> viewAllModelList) {
        this.context = context;
        this.viewAllModelList = viewAllModelList;
    }

    @NonNull
    @Override
    public ViewAllAadpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_items,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAadpter.ViewHolder holder, int position) {
        if(context!=null){
            Glide.with(context).load(viewAllModelList.get(position).getImg_url()).into(holder.imageView);
            holder.name.setText(viewAllModelList.get(position).getName());
            holder.description.setText(viewAllModelList.get(position).getDescription());
            holder.rating.setText(viewAllModelList.get(position).getRating());
        }
    }

    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,description,rating;
        public ViewHolder(@NonNull View itemView) {


            super(itemView);
            imageView=itemView.findViewById(R.id.rec_img);
            name=itemView.findViewById(R.id.rec_name);
            description=itemView.findViewById(R.id.rec_des);
            rating=itemView.findViewById(R.id.rec_rating);
        }
    }
}
