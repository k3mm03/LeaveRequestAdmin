package com.newwavetech.leaverequestadmindemo1.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.newwavetech.leaverequestadmindemo1.api.ApiService;
import com.newwavetech.leaverequestadmindemo1.api.RetrofitApi;
import com.newwavetech.leaverequestadmindemo1.pojo.Departments;
import com.newwavetech.leaverequestadmindemo1.pojo.DepartmentsList;
import com.newwavetech.leaverequestadmindemo1.pojo.Employees;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class DepartmentsViewModel extends AndroidViewModel {
    MutableLiveData<DepartmentsList> listMutableLiveData;
    String departmenttitle;
    private Retrofit retrofit;
    private ApiService apiService;

    public DepartmentsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<DepartmentsList> getDepartments() {
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
            retrofit = RetrofitApi.getClient();
            apiService = retrofit.create(ApiService.class);

            apiService.getDepartments()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(departments -> {
                        listMutableLiveData.setValue(departments);
                    }, throwable -> {
                    });
        }
        return listMutableLiveData;
    }

    public Employees getDepartmentWithId(Employees employees) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);
        apiService.getDepartmentWithId(employees.getDepartmentid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(departmentsList -> {
                    try {
                        employees.setDepartmenttitle(departmentsList.getDepartmentsList().get(0).getDepartmentTitle());
                    } catch (IndexOutOfBoundsException e) {
                        Toast.makeText(getApplication(), e.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                    }
                }, throwable -> {
                });
        return employees;
    }

    public void insertDepartment(Departments departments) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to insert " + departments.getDepartmentTitle(), Toast.LENGTH_SHORT).show();

        apiService.insertDepartment(departments.getDepartmentTitle(), departments.getDepartmentDescription(), "", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(departments1 -> {
                    Toast.makeText(getApplication(), "New Department is successfully inserted!!", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                });
    }

    public void updateDepartment(int id, String title, String description) {

        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to update Department " + id, Toast.LENGTH_SHORT).show();
        apiService.updateDepartment(id, title, description)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(departments -> {
                    Toast.makeText(getApplication(), "Successfully updated!!", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                });
    }

    public void deleteDepartment(int id) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to delete Department " + id, Toast.LENGTH_SHORT).show();
        apiService.deleteDepartment(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Toast.makeText(getApplication(), "Successfully deleted!!", Toast.LENGTH_SHORT).show()
                        , throwable -> {
                        });
    }
}
