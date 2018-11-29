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
import com.newwavetech.leaverequestadmindemo1.pojo.Roles;
import com.newwavetech.leaverequestadmindemo1.viewmodels.EmployeeViewModel;
import com.newwavetech.leaverequestadmindemo1.viewmodels.RolesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.MyViewHolder> {
    private static List<Roles> roleList;

    private Dialog dialog;
    private RolesViewModel rolesViewModel;
    private EmployeeViewModel employeeViewModel;
    private int id;

    public RoleAdapter(List<Roles> roleList) {
        this.roleList = roleList;
        notifyDataSetChanged();
    }

    public void setData(List<Roles> list) {
        this.roleList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.role_recyclerview_items, viewGroup, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.role_name_tv.setText(roleList.get(i).getRoleTitle());
        myViewHolder.role_description_tv.setText(roleList.get(i).getRoleDescription());

        myViewHolder.role_edit_btn.setOnClickListener(v -> roleEditOnClick(myViewHolder, i));

        myViewHolder.role_delete_btn.setOnClickListener(v -> roleDeleteOnClick(myViewHolder, i));
    }

    @Override
    public int getItemCount() {
        return roleList.size();
    }

    private void roleEditOnClick(RoleAdapter.MyViewHolder myViewHolder, int i) {
        dialog = new Dialog(myViewHolder.itemView.getContext());
        dialog.setContentView(R.layout.popup_layout);
        dialog.setCanceledOnTouchOutside(false);

        TextView titletv = dialog.findViewById(R.id.poppup_title);
        MaterialButton saveBtn = dialog.findViewById(R.id.popup_save_btn);
        MaterialButton cancelBtn = dialog.findViewById(R.id.popup_cancel_btn);
        EditText titleEd = dialog.findViewById(R.id.popup_title_ed);
        EditText descriptionEd = dialog.findViewById(R.id.popup_description_ed);

        titletv.setText("Edit Role Details");

        //Display current item's data
        id = roleList.get(i).getRoleId();
        titleEd.setText(roleList.get(i).getRoleTitle());
        descriptionEd.setText(roleList.get(i).getRoleDescription());

        dialog.show();

        saveBtn.setOnClickListener(v1 -> {
            String editedTitle = String.valueOf(titleEd.getText());
            String editedDescription = String.valueOf(descriptionEd.getText());
            if (editedTitle.trim().equals("") || editedDescription.trim().equals("")) {
                Toast.makeText(myViewHolder.itemView.getContext(), "Text Fileds should not be empty", Toast.LENGTH_SHORT).show();
            } else {
                rolesViewModel.updateRole(id, editedTitle, editedDescription);
                roleList.set(i, new Roles(editedTitle, editedDescription));
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        cancelBtn.setOnClickListener(v1 -> {
            dialog.cancel();
        });
    }

    private void roleDeleteOnClick(RoleAdapter.MyViewHolder myViewHolder, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(myViewHolder.itemView.getContext());

        builder.setMessage("Are you sure to delete?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (dialog, which) -> {
            rolesViewModel.deleteRole(roleList.get(i).getRoleId());
            employeeViewModel.deleteEmployeeWithRole(roleList.get(i).getRoleId());
            roleList.remove(i);
            notifyDataSetChanged();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.role_name_tv)
        TextView role_name_tv;
        @BindView(R.id.role_description_tv)
        TextView role_description_tv;
        @BindView(R.id.role_edit_btn)
        MaterialButton role_edit_btn;
        @BindView(R.id.role_delete_btn)
        MaterialButton role_delete_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            rolesViewModel = ViewModelProviders.of((FragmentActivity) itemView.getContext()).get(RolesViewModel.class);
            employeeViewModel = ViewModelProviders.of((FragmentActivity) itemView.getContext()).get(EmployeeViewModel.class);
        }

    }
}
