package com.shidqi.dansmultiprotest.ui.view.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shidqi.dansmultiprotest.R;
import com.shidqi.dansmultiprotest.databinding.ActivityMainBinding;
import com.shidqi.dansmultiprotest.ui.view.detail.DetailActivity;
import com.shidqi.dansmultiprotest.ui.view.login.LoginActivity;
import com.shidqi.dansmultiprotest.ui.view.main.jobListing.JobListFragment;
import com.shidqi.dansmultiprotest.ui.view.main.setting.SettingFragment;
import com.shidqi.dansmultiprotest.ui.viewModel.MainViewModel;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private GoogleSignInAccount account;
    private BottomNavigationView bottomNavigationView;
    private JobListFragment firstFragment = new JobListFragment();
    private SettingFragment secondFragment = new SettingFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        account = getIntent().getParcelableExtra(LoginActivity.EXTRA_PARCELABLE);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.googleSignInAccount = account;
        mainViewModel.getData();
        bottomNavigationView = binding.content.navigation;
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.action_search);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_navigation_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, firstFragment).commit();
                return true;

            case R.id.action_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, secondFragment).commit();
                return true;
        }
        return false;
    }
}