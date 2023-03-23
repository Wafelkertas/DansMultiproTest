package com.shidqi.dansmultiprotest.repository;

import android.util.Log;

import androidx.annotation.Nullable;

import com.shidqi.dansmultiprotest.data.model.responseDetail.ResponseDetail;
import com.shidqi.dansmultiprotest.util.CallbackWithData;
import com.shidqi.dansmultiprotest.data.model.ResponseItem;
import com.shidqi.dansmultiprotest.network.Api;
import com.shidqi.dansmultiprotest.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobsRepository {

    Api apiService = RetrofitClient.getInstance().getMyApi();

    public void getJobs(Integer page, String description, String location, Boolean isFullTime, CallbackWithData<List<ResponseItem>>  callbackWithData) {
        Log.d("getPosition", page.toString());
        Call<List<ResponseItem>> call = apiService.getJobs(page, description,location, isFullTime);
        call.enqueue(new Callback<List<ResponseItem>>() {
            @Override
            public void onResponse(Call<List<ResponseItem>> call, @Nullable Response<List<ResponseItem>> response) {
                if(response != null){
                    Log.d("onResponse1", response.toString());
                    if(response.code() == 200){
                        callbackWithData.execute(response.body());
                    }else{
                        callbackWithData.execute(new ArrayList<ResponseItem>());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ResponseItem>> call, Throwable t) {
                Log.d("onResponse2", t.toString());
            }


        });
    }

    public void getJobDetail(String jobId,CallbackWithData<ResponseDetail>  callbackWithData) {
        Call<ResponseDetail> call = apiService.getJobDetail(jobId);
        call.enqueue(new Callback<ResponseDetail>() {
            @Override
            public void onResponse(Call<ResponseDetail> call, @Nullable Response<ResponseDetail> response) {
                if(response != null){
                    Log.d("onResponse1", response.toString());
                    if(response.code() == 200){
                        callbackWithData.execute(response.body());
                    }else{
                        callbackWithData.execute(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDetail> call, Throwable t) {
                Log.d("onResponse2", t.toString());
            }


        });
    }
}
