package com.shidqi.dansmultiprotest.ui.view.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.bumptech.glide.Glide;
import com.shidqi.dansmultiprotest.R;
import com.shidqi.dansmultiprotest.databinding.ActivityDetailBinding;
import com.shidqi.dansmultiprotest.ui.view.main.MainActivity;
import com.shidqi.dansmultiprotest.ui.viewModel.MainViewModel;

public class DetailActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    public static final String EXTRA_DATA = "767";
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.jobId = getIntent().getStringExtra(EXTRA_DATA);
        mainViewModel.getDetailJob();
        bindLiveData();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void bindLiveData() {
        mainViewModel.getIsLoadingDetail().observe(this, loading -> {
            if (loading) {
                binding.progressCircular.setVisibility(View.VISIBLE);
            } else {
                binding.progressCircular.setVisibility(View.INVISIBLE);
            }

        });
        mainViewModel.getJobDetailLiveData().observe(this, data -> {
            if (data != null) {
                if (data.getCompanyLogo() != null) {
                    Glide.with(binding.ivJobImage).load(data.getCompanyLogo()).error(R.drawable.placeholder).into(binding.ivJobImage);
                } else {
                    Glide.with(binding.ivJobImage).load(R.drawable.placeholder).into(binding.ivJobImage);
                }
                binding.tvCompany.setText(data.getCompany());
                binding.tvFullTime.setText(data.getType());
                binding.tvLocation.setText(data.getLocation());
                binding.tvJobTitle.setText(data.getTitle());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.tvDescription.setText(Html.fromHtml(data.getDescription(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.tvDescription.setText(Html.fromHtml(data.getDescription()));
                }
            }
        });
    }
}