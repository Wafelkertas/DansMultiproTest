package com.shidqi.dansmultiprotest.ui.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.shidqi.dansmultiprotest.data.model.ResponseItem;
import com.shidqi.dansmultiprotest.data.model.responseDetail.ResponseDetail;
import com.shidqi.dansmultiprotest.repository.JobsRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<ResponseItem>> jobPositionList = new MutableLiveData<List<ResponseItem>>();
    private MutableLiveData<ResponseDetail> jobResponseDetail = new MutableLiveData<ResponseDetail>();
    private final JobsRepository jobsRepository = new JobsRepository();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>(false);
    private MutableLiveData<Boolean> isLoadingDetail = new MutableLiveData<Boolean>(false);
    public Integer page = 1;
    public Boolean isError = false;
    public Boolean isErrorDetail = false;
    public String searchQuery = "";
    public String location = "";
    public Boolean isFullTime = true;
    public GoogleSignInAccount googleSignInAccount;
    public String jobId = "";

    public MutableLiveData<List<ResponseItem>> getJobsLiveData() {
        if (jobPositionList == null) {
            jobPositionList = new MutableLiveData<List<ResponseItem>>();
        }
        return jobPositionList;
    }

    public MutableLiveData<ResponseDetail> getJobDetailLiveData() {
        if (jobResponseDetail == null) {
            jobResponseDetail = new MutableLiveData<ResponseDetail>();
        }
        return jobResponseDetail;
    }

    public MutableLiveData<Boolean> getIsLoadingDetail() {
        if (isLoadingDetail == null) {
            isLoadingDetail = new MutableLiveData<Boolean>(false);
        }
        return isLoadingDetail;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        if (isLoading == null) {
            isLoading = new MutableLiveData(false);
        }
        return isLoading;
    }

    public void getData() {
        isLoading.setValue(true);
        jobsRepository.getJobs(page,searchQuery, location, isFullTime, data -> {

            if (data != null) {
                if (!data.isEmpty()) {
                    jobPositionList.setValue(data);
                }else{
                    isError = true;
                }
            }else{
                isError = true;
            }
            isLoading.setValue(false);
        });
    }

    public void getDetailJob(){
        isLoadingDetail.setValue(true);
        jobsRepository.getJobDetail(jobId, data -> {
            if (data != null) {
                jobResponseDetail.setValue(data);
            }else{
                isErrorDetail = true;
            }
            isLoadingDetail.setValue(false);
        });
    }


}
