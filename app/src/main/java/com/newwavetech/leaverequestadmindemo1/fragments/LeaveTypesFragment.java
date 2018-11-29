package com.newwavetech.leaverequestadmindemo1.fragments;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.adapters.LeaveTypesAdapter;
import com.newwavetech.leaverequestadmindemo1.pojo.LeaveTypes;
import com.newwavetech.leaverequestadmindemo1.viewmodels.LeaveTypesViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeaveTypesFragment extends Fragment {
    @BindView(R.id.leavetypes_recyclerview)
    RecyclerView recyclerView;

    LeaveTypesViewModel leaveTypesViewModel;

    private Dialog dialog;

    public LeaveTypesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_leave_types, container, false);
        ButterKnife.bind(this, layout);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(layout.getContext()));

        final LeaveTypesAdapter adapter = new LeaveTypesAdapter(new ArrayList<LeaveTypes>());
        recyclerView.setAdapter(adapter);

        leaveTypesViewModel = ViewModelProviders.of((FragmentActivity) layout.getContext()).get(LeaveTypesViewModel.class);
        leaveTypesViewModel.getLeaveTypes().observe((FragmentActivity) layout.getContext(), leaveTypeList -> adapter.setData(leaveTypeList.getLeaveTypesList()));

        return layout;
    }

    @OnClick(R.id.leavetypes_add_fab)
    public void addOnClick() {
        dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.popup_layout);
        dialog.setCanceledOnTouchOutside(false);

        TextView titletv = dialog.findViewById(R.id.poppup_title);
        MaterialButton saveBtn = dialog.findViewById(R.id.popup_save_btn);
        MaterialButton cancelBtn = dialog.findViewById(R.id.popup_cancel_btn);
        EditText titleEd = dialog.findViewById(R.id.popup_title_ed);
        EditText descriptionEd = dialog.findViewById(R.id.popup_description_ed);

        titletv.setText("Add New LeaveType Details");
        dialog.show();

        saveBtn.setOnClickListener(v1 -> {
            String editedTitle = String.valueOf(titleEd.getText());
            String editedDescription = String.valueOf(descriptionEd.getText());
            if (editedTitle.trim().equals("") || editedDescription.trim().equals("")) {
                Toast.makeText(this.getContext(), "Text Fileds should not be empty", Toast.LENGTH_SHORT).show();
            } else {
                leaveTypesViewModel.insertLeaveType(new LeaveTypes(editedTitle, editedDescription));
                dialog.dismiss();
            }
        });

        cancelBtn.setOnClickListener(v1 -> {
            dialog.cancel();
        });

    }
}
