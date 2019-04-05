package com.fetcher.views.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fetcher.R;
import com.fetcher.core.models.CompanyPhoto;
import com.fetcher.core.utils.RetrofitClientInstance;

import java.util.ArrayList;

public class CompanyPhotosAdapter extends RecyclerView.Adapter<CompanyPhotosAdapter.MyViewHolder> {

    private ArrayList<CompanyPhoto> companyPhotos;
    private Activity mActivity;

    public CompanyPhotosAdapter(ArrayList<CompanyPhoto> companyPhotos, Activity mActivity) {
        this.companyPhotos = companyPhotos;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.company_photo_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Glide.with(mActivity)
                .load(RetrofitClientInstance.BASE_URL + "fetcher/public/uploads/" + companyPhotos.get(i).getPhoto())
                .into(myViewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return companyPhotos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.imageView);
        }
    }
}
