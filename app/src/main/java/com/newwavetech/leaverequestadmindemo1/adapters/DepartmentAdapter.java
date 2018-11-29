package com.newwavetech.leaverequestadmindemo1.adapters;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.pojo.Departments;
import com.newwavetech.leaverequestadmindemo1.viewmodels.DepartmentsViewModel;
import com.newwavetech.leaverequestadmindemo1.viewmodels.EmployeeViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.MyViewHolder> {
    private static List<Departments> departmentList;

    private Dialog dialog;
    private DepartmentsViewModel departmentsViewModel;
    private EmployeeViewModel employeeViewModel;
    private int id;

    public DepartmentAdapter(List<Departments> departmentList) {
        this.departmentList = departmentList;
        notifyDataSetChanged();
    }

    public void setData(List<Departments> list) {
        this.departmentList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.department_recyclerview_items, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.department_name_tv.setText(departmentList.get(i).getDepartmentTitle());
        myViewHolder.department_description_tv.setText(departmentList.get(i).getDepartmentDescription());

        myViewHolder.department_edit_btn.setOnClickListener(v -> departmentEditOnClick(myViewHolder, i));

        myViewHolder.department_delete_btn.setOnClickListener(v -> departmentDeleteOnClick(myViewHolder, i));
    }

    @Override
    public int getItemCount() {
        return departmentList.size();
    }

    private void departmentDeleteOnClick(DepartmentAdapter.MyViewHolder myViewHolder, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(myViewHolder.itemView.getContext());

        builder.setMessage("Are you sure to delete?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (dialog, which) -> {
            Toast.makeText(myViewHolder.itemView.getContext(), departmentList.get(i).getDepartmentId() + "", Toast.LENGTH_SHORT).show();
            employeeViewModel.deleteEmployeeWithDepartment(departmentList.get(i).getDepartmentId());
            departmentsViewModel.deleteDepartment(departmentList.get(i).getDepartmentId());
            departmentList.remove(i);
            notifyDataSetChanged();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void departmentEditOnClick(DepartmentAdapter.MyViewHolder myViewHolder, int i) {
        dialog = new Dialog(myViewHolder.itemView.getContext());
        dialog.setContentView(R.layout.popup_layout);
        dialog.setCanceledOnTouchOutside(false);

        TextView titletv = dialog.findViewById(R.id.poppup_title);
        MaterialButton saveBtn = dialog.findViewById(R.id.popup_save_btn);
        MaterialButton cancelBtn = dialog.findViewById(R.id.popup_cancel_btn);
        EditText titleEd = dialog.findViewById(R.id.popup_title_ed);
        EditText descriptionEd = dialog.findViewById(R.id.popup_description_ed);

        titletv.setText("Edit Department Details");

        //Display current item's data
        id = departmentList.get(i).getDepartmentId();
        titleEd.setText(departmentList.get(i).getDepartmentTitle());
        descriptionEd.setText(departmentList.get(i).getDepartmentDescription());

        dialog.show();

        saveBtn.setOnClickListener(v1 -> {
            String editedTitle = String.valueOf(titleEd.getText());
            String editedDescription = String.valueOf(descriptionEd.getText());
            if (editedTitle.trim().equals("") || editedDescription.trim().equals("")) {
                Toast.makeText(myViewHolder.itemView.getContext(), "Text Fileds should not be empty", Toast.LENGTH_SHORT).show();
            } else {
                departmentsViewModel.updateDepartment(id, editedTitle, editedDescription);
                departmentList.set(i, new Departments(editedTitle, editedDescription));
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        cancelBtn.setOnClickListener(v1 -> {
            dialog.cancel();
        });

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.department_name_tv)
        TextView department_name_tv;
        @BindView(R.id.department_description_tv)
        TextView department_description_tv;
        @BindView(R.id.department_edit_btn)
        MaterialButton department_edit_btn;
        @BindView(R.id.department_delete_btn)
        MaterialButton department_delete_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            departmentsViewModel = ViewModelProviders.of((FragmentActivity) itemView.getContext()).get(DepartmentsViewModel.class);
            employeeViewModel = ViewModelProviders.of((FragmentActivity) itemView.getContext()).get(EmployeeViewModel.class);
        }

    }
}
