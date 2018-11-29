package com.newwavetech.leaverequestadmindemo1.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.newwavetech.leaverequestadmindemo1.api.ApiService;
import com.newwavetech.leaverequestadmindemo1.api.RetrofitApi;
import com.newwavetech.leaverequestadmindemo1.pojo.Employees;
import com.newwavetech.leaverequestadmindemo1.pojo.Roles;
import com.newwavetech.leaverequestadmindemo1.pojo.RolesList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RolesViewModel extends AndroidViewModel {
    MutableLiveData<RolesList> listMutableLiveData;
    String roletitle;

    private Retrofit retrofit;
    private ApiService apiService;

    public RolesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<RolesList> getRoles() {
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
            retrofit = RetrofitApi.getClient();
            apiService = retrofit.create(ApiService.class);

            apiService.getRoles()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(roles -> {
                        listMutableLiveData.setValue(roles);
                    }, throwable -> {
                        Toast.makeText(getApplication(), "Something went wrong!!", Toast.LENGTH_SHORT);
                    });
        }
        return listMutableLiveData;
    }

    public Employees getRoleWithId(Employees employees) {

        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);
        apiService.getRoleWithId(employees.getRoleid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(roles -> {
                    try {
                        employees.setRoletitle(roles.getRolesList().get(0).getRoleTitle());
                    } catch (IndexOutOfBoundsException e) {
                        Toast.makeText(getApplication(), e.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                    }
                }, throwable -> {
                });

        return employees;
    }

    public void insertRole(Roles role) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to insert " + role.getRoleTitle(), Toast.LENGTH_SHORT).show();

        apiService.insertRole(role.getRoleTitle(), role.getRoleDescription(), "", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(roles -> {
                    Toast.makeText(getApplication(), "New Role is successfully inserted!!", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                });
    }

    public void updateRole(int id, String title, String description) {

        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to update Role " + id, Toast.LENGTH_SHORT).show();
        apiService.updateRole(id, title, description)
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

    public void deleteRole(int id) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to delete Role " + id, Toast.LENGTH_SHORT).show();
        apiService.deleteRole(id)
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
