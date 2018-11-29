package com.newwavetech.leaverequestadmindemo1.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.newwavetech.leaverequestadmindemo1.api.ApiService;
import com.newwavetech.leaverequestadmindemo1.api.RetrofitApi;
import com.newwavetech.leaverequestadmindemo1.pojo.RequestsList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RequestsViewModel extends AndroidViewModel {
    MutableLiveData<RequestsList> listMutableLiveData;
    MutableLiveData<RequestsList> acceptList;
    MutableLiveData<RequestsList> denyList;

    private Retrofit retrofit;
    private ApiService apiService;

    public RequestsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<RequestsList> getRequests(String status) {
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
            retrofit = RetrofitApi.getClient();
            apiService = retrofit.create(ApiService.class);

            apiService.getRequests("'" + status + "'")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(requestsList -> {
                        listMutableLiveData.setValue(requestsList);
                        Toast.makeText(getApplication(), requestsList.getRequestlist().size() + " Requests", Toast.LENGTH_SHORT).show();
                    }, throwable -> {
                        Toast.makeText(getApplication(), throwable.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                    });
        }
        return listMutableLiveData;
    }

    public LiveData<RequestsList> getAcceptRequests(String status) {
        if (acceptList == null) {
            acceptList = new MutableLiveData<>();
            retrofit = RetrofitApi.getClient();
            apiService = retrofit.create(ApiService.class);

            apiService.getRequests("'" + status + "'")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(requestsList -> {
                        acceptList.setValue(requestsList);
                        Toast.makeText(getApplication(), requestsList.getRequestlist().size() + " Requests", Toast.LENGTH_SHORT).show();
                    }, throwable -> {
                        Toast.makeText(getApplication(), throwable.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                    });
        }
        return acceptList;
    }

    public LiveData<RequestsList> getDenyRequests(String status) {
        if (denyList == null) {
            denyList = new MutableLiveData<>();
            retrofit = RetrofitApi.getClient();
            apiService = retrofit.create(ApiService.class);

            apiService.getRequests("'" + status + "'")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(requestsList -> {
                        denyList.setValue(requestsList);
                        Toast.makeText(getApplication(), requestsList.getRequestlist().size() + " Requests", Toast.LENGTH_SHORT).show();
                    }, throwable -> {
                        Toast.makeText(getApplication(), throwable.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                    });
        }
        return denyList;
    }

    public void updateRequest(int id, String determine, int helpingempid) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        apiService.updateRequest(id, determine, helpingempid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Toast.makeText(getApplication(), integer + " rows affected", Toast.LENGTH_SHORT).show(),
                        throwable -> {
                            Toast.makeText(getApplication(), throwable.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                        });
    }
}
