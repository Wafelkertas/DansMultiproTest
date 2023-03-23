package com.shidqi.dansmultiprotest.ui.view.main.jobListing.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shidqi.dansmultiprotest.R;
import com.shidqi.dansmultiprotest.data.model.ResponseItem;
import com.shidqi.dansmultiprotest.databinding.ItemJobListingBinding;
import com.shidqi.dansmultiprotest.util.CallbackWithData;

public class JobListingViewHolder extends RecyclerView.ViewHolder {
    ItemJobListingBinding binding;
    Context context;

    public JobListingViewHolder(@NonNull ItemJobListingBinding newBinding, Context _context) {
        super(newBinding.getRoot());
        binding = newBinding;
        context = _context;
    }

    public void bind(ResponseItem data, CallbackWithData<ResponseItem> _callbackWithData) {
       if(data != null){
           if(data.getCompanyLogo() != null){
               Glide.with(binding.ivJobImage).load(data.getCompanyLogo()).error(R.drawable.placeholder).into(binding.ivJobImage);
           }else{
               Glide.with(binding.ivJobImage).load(R.drawable.placeholder).into(binding.ivJobImage);
           }
           if(data.getTitle() != null)this.binding.tvJobTitle.setText(data.getTitle());
           if(data.getCompany() != null)this.binding.tvJobGenre.setText(data.getCompany());
           if(data.getLocation() != null)this.binding.tvLocation.setText(data.getLocation());
           this.binding.getRoot().setOnClickListener(v -> _callbackWithData.execute(data));
           this.binding.btnGoDetail.setOnClickListener(v -> _callbackWithData.execute(data));
       }
    }
}
