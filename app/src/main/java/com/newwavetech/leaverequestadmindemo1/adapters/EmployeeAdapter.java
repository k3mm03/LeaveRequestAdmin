package com.newwavetech.leaverequestadmindemo1.adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.activities.EmployeeDetailActivity;
import com.newwavetech.leaverequestadmindemo1.api.ApiService;
import com.newwavetech.leaverequestadmindemo1.pojo.Employees;
import com.newwavetech.leaverequestadmindemo1.viewmodels.EmployeeViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder> {

    private static List<Employees> employeesList;

    private EmployeeViewModel employeeViewModel;
    private Retrofit retrofit;
    private ApiService apiService;
    private int id;

    public EmployeeAdapter(List<Employees> employeesList) {
        this.employeesList = employeesList;
        notifyDataSetChanged();
    }

    public void setData(List<Employees> list) {
        this.employeesList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employees_recyclerview_items, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Intent intent = new Intent(myViewHolder.itemView.getContext(), EmployeeDetailActivity.class);
        intent.putExtra("employee", employeesList.get(i));

        myViewHolder.employee_code_tv.setText(employeesList.get(i).getCode());
        myViewHolder.employee_name_tv.setText(employeesList.get(i).getName());
        myViewHolder.employee_role_tv.setText(employeesList.get(i).getRoletitle());
        myViewHolder.employee_department_tv.setText(employeesList.get(i).getDepartmenttitle());

        myViewHolder.employee_edit_btn.setOnClickListener(v -> {
            myViewHolder.itemView.getContext().startActivity(intent);
        });

        myViewHolder.employee_delete_btn.setOnClickListener(v -> employeeDeleteOnClick(myViewHolder, i));

        myViewHolder.itemView.setOnClickListener(v -> myViewHolder.itemView.getContext().startActivity(intent));
    }

    @Override
    public int getItemCount() {
        return employeesList.size();
    }

    private void employeeDeleteOnClick(EmployeeAdapter.MyViewHolder myViewHolder, int i) {

        AlertDialog.Builder builder = new AlertDialog.Builder(myViewHolder.itemView.getContext());

        builder.setMessage("Are you sure to delete?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (dialog, which) -> {
            employeeViewModel.deleteEmployee(employeesList.get(i).getId());
            employeesList.remove(i);
            notifyDataSetChanged();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.employee_code_tv)
        TextView employee_code_tv;
        @BindView(R.id.employee_name_tv)
        TextView employee_name_tv;
        @BindView(R.id.employee_role_tv)
        TextView employee_role_tv;
        @BindView(R.id.employee_department_tv)
        TextView employee_department_tv;
        @BindView(R.id.employee_edit_btn)
        MaterialButton employee_edit_btn;
        @BindView(R.id.employee_delete_btn)
        MaterialButton employee_delete_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            employeeViewModel = ViewModelProviders.of((FragmentActivity) itemView.getContext()).get(EmployeeViewModel.class);
        }
    }
}
