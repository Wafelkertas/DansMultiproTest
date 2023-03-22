package com.shidqi.dansmultiprotest.repository;

import android.util.Log;

import com.shidqi.dansmultiprotest.CallbackWithData;
import com.shidqi.dansmultiprotest.model.ResponseItem;
import com.shidqi.dansmultiprotest.network.Api;
import com.shidqi.dansmultiprotest.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobsRepository {

    Api apiService = RetrofitClient.getInstance().getMyApi();

    public void getPosition(CallbackWithData<List<ResponseItem>>  callbackWithData) {
        Log.d("getPosition", "getPosition");
        Call<List<ResponseItem>> call = apiService.getJobs();
        call.enqueue(new Callback<List<ResponseItem>>() {
            @Override
            public void onResponse(Call<List<ResponseItem>> call, Response<List<ResponseItem>> response) {
                callbackWithData.execute(response.body());
            }

            @Override
            public void onFailure(Call<List<ResponseItem>> call, Throwable t) {
                callbackWithData.execute(new ArrayList<ResponseItem>());
            }


        });
    }
}
