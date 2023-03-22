package com.shidqi.dansmultiprotest.ui.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.shidqi.dansmultiprotest.databinding.FragmentJobListFragmentBinding;
import com.shidqi.dansmultiprotest.ui.viewModel.MainViewModel;
import com.shidqi.dansmultiprotest.R;

public class JobListFragment extends Fragment {

    private FragmentJobListFragmentBinding binding;
    private MainViewModel mainViewModel;
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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}