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
import com.newwavetech.leaverequestadmindemo1.pojo.LeaveTypes;
import com.newwavetech.leaverequestadmindemo1.viewmodels.LeaveTypesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaveTypesAdapter extends RecyclerView.Adapter<LeaveTypesAdapter.MyViewHolder> {

    private static List<LeaveTypes> leaveTypesList;
    private Dialog dialog;

    private LeaveTypesViewModel leaveTypesViewModel;
    private int id;

    public LeaveTypesAdapter(List<LeaveTypes> leaveTypesList) {
        this.leaveTypesList = leaveTypesList;
    }

    public void setData(List<LeaveTypes> list) {
        this.leaveTypesList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leavetypes_recyclerview_items, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.leave_name_tv.setText(leaveTypesList.get(i).getLeaveTypesName());
        myViewHolder.leave_description_tv.setText(leaveTypesList.get(i).getLeaveTypesDescription());

        myViewHolder.leavetype_edit_btn.setOnClickListener(v -> leaveTypeEditOnClick(myViewHolder, i));

        myViewHolder.leavetype_delete_btn.setOnClickListener(v -> leaveTypeDeleteOnClick(myViewHolder, i));
    }

    @Override
    public int getItemCount() {
        return leaveTypesList.size();
    }

    private void leaveTypeEditOnClick(LeaveTypesAdapter.MyViewHolder myViewHolder, int i) {

        dialog = new Dialog(myViewHolder.itemView.getContext());
        dialog.setContentView(R.layout.popup_layout);
        dialog.setCanceledOnTouchOutside(false);

        TextView titletv = dialog.findViewById(R.id.poppup_title);
        MaterialButton saveBtn = dialog.findViewById(R.id.popup_save_btn);
        MaterialButton cancelBtn = dialog.findViewById(R.id.popup_cancel_btn);
        EditText titleEd = dialog.findViewById(R.id.popup_title_ed);
        EditText descriptionEd = dialog.findViewById(R.id.popup_description_ed);

        titletv.setText("Edit Leavetype Details");

        //Display current item's data
        id = leaveTypesList.get(i).getLeaveTypesId();
        titleEd.setText(leaveTypesList.get(i).getLeaveTypesName());
        descriptionEd.setText(leaveTypesList.get(i).getLeaveTypesDescription());

        dialog.show();

        saveBtn.setOnClickListener(v1 -> {
            String editedTitle = String.valueOf(titleEd.getText());
            String editedDescription = String.valueOf(descriptionEd.getText());
            if (editedTitle.trim().equals("") || editedDescription.trim().equals("")) {
                Toast.makeText(myViewHolder.itemView.getContext(), "Text Fields should not be empty", Toast.LENGTH_SHORT).show();
            } else {
                leaveTypesViewModel.updateLeaveType(id, editedTitle, editedDescription);
                leaveTypesList.set(i, new LeaveTypes(editedTitle, editedDescription));
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        cancelBtn.setOnClickListener(v1 -> {
            dialog.cancel();
        });
    }

    private void leaveTypeDeleteOnClick(LeaveTypesAdapter.MyViewHolder myViewHolder, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(myViewHolder.itemView.getContext());

        builder.setMessage("Are you sure to delete?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (dialog, which) -> {
            leaveTypesViewModel.deleteLeaveType(leaveTypesList.get(i).getLeaveTypesId());
            leaveTypesList.remove(i);
            notifyDataSetChanged();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.leave_name_tv)
        TextView leave_name_tv;
        @BindView(R.id.leave_description_tv)
        TextView leave_description_tv;
        @BindView(R.id.leavetype_edit_btn)
        MaterialButton leavetype_edit_btn;
        @BindView(R.id.leavetype_delete_btn)
        MaterialButton leavetype_delete_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            leaveTypesViewModel = ViewModelProviders.of((FragmentActivity) itemView.getContext()).get(LeaveTypesViewModel.class);
        }
    }
}
