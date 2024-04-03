package com.example.newsports.Adapter;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsports.PopularModel;
import com.example.newsports.R;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

   Context context;
   private List<PopularModel> popularModelList;

    public PopularAdapter(FragmentActivity activity, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }


    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {

        if(context!=null) {
            Glide.with(context).load(popularModelList.get(position).getImg_url()).into(holder.popImg);
            holder.name.setText(popularModelList.get(position).getName());
            holder.description.setText(popularModelList.get(position).getDescription());
            holder.rating.setText(popularModelList.get(position).getRating());
            holder.discount.setText(popularModelList.get(position).getDiscount());
            holder.name.setText(popularModelList.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView popImg;
        TextView name,description,rating,discount;

        public ViewHolder(View inflate) {
            super(inflate);


            popImg=inflate.findViewById(R.id.pop_Img);
            name=inflate.findViewById(R.id.pop_name);
            description=inflate.findViewById(R.id.pop_description);
            rating=inflate.findViewById(R.id.pop_rating);
            discount=inflate.findViewById(R.id.pop_discount);

        }
    }
}
