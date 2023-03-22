package com.shidqi.dansmultiprotest.network;

import com.shidqi.dansmultiprotest.data.model.ResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "http://dev3.dansmultipro.co.id/api/recruitment/";

    @GET("positions.json")
    Call<List<ResponseItem>> getJobs(@Query("page") Integer page);
}
