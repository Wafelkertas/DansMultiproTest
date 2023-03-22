package com.shidqi.dansmultiprotest.network;

import com.shidqi.dansmultiprotest.model.Response;
import com.shidqi.dansmultiprotest.model.ResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://dev3.dansmultipro.co.id/api/recruitment/";

    @GET("positions.json?page=1")
    Call<List<ResponseItem>> getJobs();
}
