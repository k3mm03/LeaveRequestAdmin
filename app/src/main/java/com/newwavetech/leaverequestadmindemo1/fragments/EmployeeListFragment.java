package com.newwavetech.leaverequestadmindemo1.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.activities.EmployeeDetailActivity;
import com.newwavetech.leaverequestadmindemo1.adapters.EmployeeAdapter;
import com.newwavetech.leaverequestadmindemo1.pojo.Employees;
import com.newwavetech.leaverequestadmindemo1.viewmodels.DepartmentsViewModel;
import com.newwavetech.leaverequestadmindemo1.viewmodels.EmployeeViewModel;
import com.newwavetech.leaverequestadmindemo1.viewmodels.RolesViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EmployeeListFragment extends Fragment {
    @BindView(R.id.employeelist_recyclerview)
    RecyclerView recyclerView;

    EmployeeViewModel employeeViewModel;
    DepartmentsViewModel departmentsViewModel;
    RolesViewModel rolesViewModel;
    private String departmentTitle;
    private String roleTitle;
    private Employees employee;

    public EmployeeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_employee_list, container, false);
        ButterKnife.bind(this, layout);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(layout.getContext()));

        final EmployeeAdapter adapter = new EmployeeAdapter(new ArrayList<Employees>());
        recyclerView.setAdapter(adapter);

        employeeViewModel = ViewModelProviders.of((FragmentActivity) layout.getContext()).get(EmployeeViewModel.class);
        departmentsViewModel = ViewModelProviders.of((FragmentActivity) layout.getContext()).get(DepartmentsViewModel.class);
        rolesViewModel = ViewModelProviders.of((FragmentActivity) layout.getContext()).get(RolesViewModel.class);
        employeeViewModel.getEmployees().observe((FragmentActivity) layout.getContext(), employeeList -> {
            adapter.setData(employeeList.getEmployeesList());
        });

        return layout;

    }

    @OnClick(R.id.employee_add_fab)
    public void addOnClick() {
        startActivity(new Intent(getContext(), EmployeeDetailActivity.class));
    }

}
