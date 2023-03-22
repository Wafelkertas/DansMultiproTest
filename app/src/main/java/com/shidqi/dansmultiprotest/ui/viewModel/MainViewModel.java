package com.shidqi.dansmultiprotest.ui.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shidqi.dansmultiprotest.util.CallbackWithData;
import com.shidqi.dansmultiprotest.data.model.ResponseItem;
import com.shidqi.dansmultiprotest.repository.JobsRepository;

import java.util.Arrays;
import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<ResponseItem>> jobPositionList = new MutableLiveData<List<ResponseItem>>();;
    private final JobsRepository jobsRepository = new JobsRepository();
    Integer page = 1;

    public MutableLiveData<List<ResponseItem>> getJobPositionList() {
        if (jobPositionList == null) {
            jobPositionList = new MutableLiveData<List<ResponseItem>>();
        }
        return jobPositionList;
    }

    public void getInitialData() {
        jobsRepository.getPosition(page,new CallbackWithData<List<ResponseItem>>() {
            @Override
            public void execute(List<ResponseItem> data) {
                Log.d("callbackReceived", "callbackReceived" + Arrays.toString(data.toArray()));
                jobPositionList.setValue(data);
            }
        });
    }


}
