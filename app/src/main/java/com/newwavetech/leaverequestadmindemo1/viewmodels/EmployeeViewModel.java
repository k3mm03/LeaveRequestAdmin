package com.newwavetech.leaverequestadmindemo1.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.newwavetech.leaverequestadmindemo1.api.ApiService;
import com.newwavetech.leaverequestadmindemo1.api.RetrofitApi;
import com.newwavetech.leaverequestadmindemo1.pojo.EmployeeList;
import com.newwavetech.leaverequestadmindemo1.pojo.Employees;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class EmployeeViewModel extends AndroidViewModel {
    MutableLiveData<EmployeeList> listMutableLiveData;

    private Retrofit retrofit;
    private ApiService apiService;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<EmployeeList> getEmployees() {
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
            retrofit = RetrofitApi.getClient();
            apiService = retrofit.create(ApiService.class);

            apiService.getEmployees()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(employeeList -> {
                        listMutableLiveData.setValue(employeeList);
                    }, throwable -> {
                        Toast.makeText(getApplication(), throwable.getLocalizedMessage() + "", Toast.LENGTH_LONG).show();
                    });
        }

        return listMutableLiveData;
    }

    public void insertEmployee(Employees employees) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to insert " + employees.getName(), Toast.LENGTH_SHORT).show();

        apiService.insertEmployee(employees.getCode(), employees.getDepartmentid(), employees.getName(), employees.getPassword(), employees.getRemainingleave(), employees.getRoleid(), employees.getSalary(), employees.getStartworkingdate(), "", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employees1 -> {
                    Toast.makeText(getApplication(), "New Employee is successfully inserted!!", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Toast.makeText(getApplication(), throwable.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                });
    }

    public void updateEmployee(int id, String code, int departmentid, String name, String password, int remainingleave, int roleid, int salary, String startworkingdate) {

        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to update Employee " + name, Toast.LENGTH_SHORT).show();
        apiService.updateEmployee(id, code, departmentid, name, password, remainingleave, roleid, salary, startworkingdate, "", "")
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

    public void deleteEmployee(int id) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to delete Employee " + id, Toast.LENGTH_SHORT).show();
        apiService.deleteEmployee(id)
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

    public void deleteEmployeeWithDepartment(int id) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to delete Employees with DepartmentId " + id, Toast.LENGTH_SHORT).show();
        apiService.deleteEmployeeWithDepartment("departmentid=" + id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                            Toast.makeText(getApplication(), integer + " Employees deleted", Toast.LENGTH_SHORT).show();
                        }
                        , throwable -> {
                        });
    }

    public void deleteEmployeeWithRole(int id) {
        retrofit = RetrofitApi.getClient();
        apiService = retrofit.create(ApiService.class);

        Toast.makeText(getApplication(), "Trying to delete Employee " + id, Toast.LENGTH_SHORT).show();
        apiService.deleteEmployeeWithRole("roleid=" + id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Toast.makeText(getApplication(), integer + " Employees deleted", Toast.LENGTH_SHORT).show(),
                        throwable -> {
                        });
    }
}
