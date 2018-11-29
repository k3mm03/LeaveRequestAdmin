package com.newwavetech.leaverequestadmindemo1.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.newwavetech.leaverequestadmindemo1.api.ApiService;
import com.newwavetech.leaverequestadmindemo1.api.RetrofitApi;
import com.newwavetech.leaverequestadmindemo1.pojo.LeaveTypes;
import com.newwavetech.leaverequestadmindemo1.pojo.LeaveTypesList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class LeaveTypesViewModel extends AndroidViewModel {
    MutableLiveData<LeaveTypesList> listMutableLiveData;

    private Retrofit retrofit;
    private ApiService apiService;

    public LeaveTypesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<LeaveTypesList> getLeaveTypes() {
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
            retrofit = RetrofitApi.getClient();
            apiService = retrofit.create(ApiService.class);

            apiService.getLeaveTypes()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(leaveTypesList -> {
                        listMutableLiveData.setValue(leaveTypesList);
                    }, throwable -> {
                        Toast.makeText(getApplication(), throwable.getLocalizedMessage() + "", Toast.LENGTH_LONG).show();
                    });
        }
        return listMutableLiveData;
    }

    public void insertLeaveType(LeaveTypes leaveType) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to insert " + leaveType.getLeaveTypesName(), Toast.LENGTH_SHORT).show();

        apiService.insertLeaveType(leaveType.getLeaveTypesName(), leaveType.getLeaveTypesDescription(), "", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(leaveTypes -> {
                    Toast.makeText(getApplication(), "New LeaveType is successfully inserted!!", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                });
    }

    public void updateLeaveType(int id, String title, String description) {

        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to update LeaveType " + id, Toast.LENGTH_SHORT).show();
        apiService.updateLeaveType(id, title, description)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Toast.makeText(getApplication(), "Successfully updated!!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplication(), e.getLocalizedMessage() + "", Toast.LENGTH_LONG).show();
                    }
                });

    }

    public void deleteLeaveType(int id) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to delete " + id, Toast.LENGTH_SHORT).show();
        apiService.deleteLeaveType(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Toast.makeText(getApplication(), "Successfully deleted!!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplication(), e.getLocalizedMessage() + "", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
