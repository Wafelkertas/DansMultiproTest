package com.shidqi.dansmultiprotest;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shidqi.dansmultiprotest.model.ResponseItem;
import com.shidqi.dansmultiprotest.repository.JobsRepository;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<ResponseItem>> jobPositionList;
    private final JobsRepository jobsRepository = new JobsRepository();

    public MutableLiveData<List<ResponseItem>> getJobPositionList() {
        if (jobPositionList == null) {
            jobPositionList = new MutableLiveData<List<ResponseItem>>();
        }
        return jobPositionList;
    }

    public void getInitialData(){
        jobsRepository.getPosition(new CallbackWithData<List<ResponseItem>>() {
            @Override
            public void execute(List<ResponseItem> data) {
                Log.d("callbackReceived", "callbackReceived" + Arrays.toString(data.toArray()));
                jobPositionList.setValue(data);
            }
        });
    }




}
