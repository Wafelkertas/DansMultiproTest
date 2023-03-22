package com.shidqi.dansmultiprotest.ui.view.main.jobListing;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.shidqi.dansmultiprotest.data.model.ResponseItem;
import com.shidqi.dansmultiprotest.databinding.FragmentJobListFragmentBinding;
import com.shidqi.dansmultiprotest.ui.view.main.jobListing.adapter.JobListingAdapter;
import com.shidqi.dansmultiprotest.ui.viewModel.MainViewModel;
import com.shidqi.dansmultiprotest.R;

import java.util.List;

public class JobListFragment extends Fragment {

    private FragmentJobListFragmentBinding binding;
    private MainViewModel mainViewModel;
    private JobListingAdapter jobListingAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentJobListFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mainViewModel.getInitialData();
        bindClickListener();
        observeLiveData();
        setupAdapter();
    }

    private void observeLiveData(){
        mainViewModel.getJobPositionList().observe(getViewLifecycleOwner(), data -> {
        Log.d("observeData",data.toString());
            if(jobListingAdapter != null){
                jobListingAdapter.setListData(data);
            }
        });
    }

    private void setupAdapter(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        binding.rvSources.setLayoutManager(layoutManager);
        jobListingAdapter = new JobListingAdapter(getActivity(),data -> {
            Log.d("listOnClick", "listOnClick");
        } );
        binding.rvSources.setAdapter(jobListingAdapter);
    }



    private void bindClickListener(){
        binding.btnDropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("btnDropdown", "btnDropdown");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}