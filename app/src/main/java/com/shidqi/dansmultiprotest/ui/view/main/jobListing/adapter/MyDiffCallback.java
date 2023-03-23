package com.shidqi.dansmultiprotest.ui.view.main.jobListing.adapter;

import android.util.Log;

import androidx.recyclerview.widget.DiffUtil;

import com.shidqi.dansmultiprotest.data.model.ResponseItem;

import java.util.List;
import java.util.Objects;

public class MyDiffCallback extends DiffUtil.Callback {

    private List<ResponseItem> oldList;
    private List<ResponseItem> newList;

    public MyDiffCallback(List<ResponseItem> oldList, List<ResponseItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        ResponseItem oldItem = oldList.get(oldItemPosition);
        ResponseItem newItem = newList.get(newItemPosition);

        if(newItem != null && oldItem != null){
            return Objects.equals(oldItem.getId(), newItem.getId());
        }else {
            return true;
        }

    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ResponseItem oldItem = oldList.get(oldItemPosition);
        ResponseItem newItem = newList.get(newItemPosition);
        if(newItem != null&& oldItem != null){
            return oldItem.equals(newItem);
        }else {
            return true;
        }

    }
}
