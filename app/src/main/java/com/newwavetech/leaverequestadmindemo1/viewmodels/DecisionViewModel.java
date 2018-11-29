package com.newwavetech.leaverequestadmindemo1.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.newwavetech.leaverequestadmindemo1.api.ApiService;
import com.newwavetech.leaverequestadmindemo1.api.RetrofitApi;
import com.newwavetech.leaverequestadmindemo1.pojo.DateUtils;
import com.newwavetech.leaverequestadmindemo1.pojo.DecisionList;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static com.newwavetech.leaverequestadmindemo1.pojo.DateUtils.getCurrentTimeString;

public class DecisionViewModel extends AndroidViewModel {
    MutableLiveData<DecisionList> listMutableLiveData;

    private Retrofit retrofit;
    private ApiService apiService;

    public DecisionViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<DecisionList> getDecisions() {
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
            retrofit = RetrofitApi.getClient();
            apiService = retrofit.create(ApiService.class);

            apiService.getDecision()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(decisionList -> {
                        listMutableLiveData.setValue(decisionList);
                    }, throwable -> {
                        Toast.makeText(getApplication(), throwable.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                    });
        }
        return listMutableLiveData;
    }

    public void insertDecision(int requestid, int employeeid, int adminid, int approve, int leavetypeid, String remark) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        apiService.insertDecision(requestid, employeeid, adminid, approve, DateUtils.getCurrentTimeString(), leavetypeid, remark, "", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    Toast.makeText(getApplication(), integer + " decision rows inserted", Toast.LENGTH_SHORT).show();
                },
                        throwable -> {
                    Toast.makeText(getApplication(), throwable.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                });
    }
}
