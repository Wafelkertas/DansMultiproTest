package com.shidqi.dansmultiprotest.ui.view.main.jobListing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shidqi.dansmultiprotest.databinding.FragmentJobListBinding;
import com.shidqi.dansmultiprotest.ui.view.detail.DetailActivity;
import com.shidqi.dansmultiprotest.ui.view.main.jobListing.adapter.JobListingAdapter;
import com.shidqi.dansmultiprotest.ui.viewModel.MainViewModel;
import com.shidqi.dansmultiprotest.R;

public class JobListFragment extends Fragment {

    private FragmentJobListBinding binding;
    private MainViewModel mainViewModel;
    private JobListingAdapter jobListingAdapter;
    private Boolean isShowingFilter = false;
    private boolean loading = true;
    int pastVisibleItems, visibleItemCount, totalItemCount;
    private Handler debounceHandler = new Handler();
    private Runnable debounceRunnable;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentJobListBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mainViewModel.getData();
        bindListener();
        observeLiveData();
        setupAdapter();
    }

    private void observeLiveData() {
        mainViewModel.getJobsLiveData().observe(getViewLifecycleOwner(), data -> {

            if (jobListingAdapter != null) {

                jobListingAdapter.setListData(data);

            }
        });

        mainViewModel.getIsLoading().observe(getViewLifecycleOwner(), data -> {
            if (data) {
                binding.progressCircular.setVisibility(View.VISIBLE);
            } else {
                binding.progressCircular.setVisibility(View.INVISIBLE);
                if (jobListingAdapter.listData.isEmpty()) {
                    binding.tvEmptyState.setVisibility(View.VISIBLE);
                } else {
                    binding.tvEmptyState.setVisibility(View.GONE);

                }
            }
        });
    }

    private void setupAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        binding.rvSources.setLayoutManager(layoutManager);
        jobListingAdapter = new JobListingAdapter(getActivity(), data -> {
            Intent intent = new Intent(requireContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DATA, data.getId());
            startActivity(intent);
        });
        binding.rvSources.setAdapter(jobListingAdapter);
        binding.rvSources.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading && !mainViewModel.isError) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            // Do pagination.. i.e. fetch new data
                            mainViewModel.page += 1;
                            mainViewModel.getData();
                            loading = true;
                        }
                    }
                }
            }
        });

    }


    private void bindListener() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mainViewModel.searchQuery = s.toString();

                debounce();
            }
        });

        binding.btnApplyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.isFullTime = binding.switchType.isChecked();
                mainViewModel.location = String.valueOf(binding.etLocationFilter.getText());
                mainViewModel.page = 1;
                jobListingAdapter.clearData();
                mainViewModel.getData();
            }
        });
        binding.btnDropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowingFilter = !isShowingFilter;
                if (isShowingFilter) {
                    binding.layoutFilter.setVisibility(View.VISIBLE);
                    binding.btnDropdown.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up);

                } else {
                    binding.layoutFilter.setVisibility(View.GONE);
                    binding.btnDropdown.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down);
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void debounce() {
        debounceHandler.removeCallbacks(debounceRunnable);
        debounceRunnable = new Runnable() {
            @Override
            public void run() {
                jobListingAdapter.clearData();
                mainViewModel.page = 1;
                mainViewModel.getData();
            }
        };

        debounceHandler.postDelayed(debounceRunnable, 1500);
    }


}