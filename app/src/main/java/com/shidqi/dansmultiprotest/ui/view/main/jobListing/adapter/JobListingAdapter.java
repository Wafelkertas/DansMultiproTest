package com.shidqi.dansmultiprotest.ui.view.main.jobListing.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.shidqi.dansmultiprotest.data.model.ResponseItem;
import com.shidqi.dansmultiprotest.databinding.ItemJobListingBinding;
import com.shidqi.dansmultiprotest.util.CallbackWithData;

import java.util.ArrayList;
import java.util.List;

public class JobListingAdapter extends RecyclerView.Adapter<JobListingViewHolder> {
    List<ResponseItem> listData = new ArrayList<ResponseItem>();
    Context context;
    CallbackWithData<ResponseItem> callbackWithData;
    public JobListingAdapter(Context _context, CallbackWithData<ResponseItem> _callbackWithData){
        context = _context;
        callbackWithData = _callbackWithData;
    }

    @NonNull
    @Override
    public JobListingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemJobListingBinding itemJobListingBinding =  ItemJobListingBinding.inflate(LayoutInflater.from(context), parent,
                false);
        return new JobListingViewHolder(itemJobListingBinding, context);
    }

    @Override
    public void onBindViewHolder(@NonNull JobListingViewHolder holder, int position) {
        holder.bind(listData.get(position), callbackWithData);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setListData(List<ResponseItem> data){
        this.listData = data;
        notifyDataSetChanged();
    }
}
