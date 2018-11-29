package com.newwavetech.leaverequestadmindemo1.fragments;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.newwavetech.leaverequestadmindemo1.adapters.DepartmentAdapter;
import com.newwavetech.leaverequestadmindemo1.pojo.Departments;
import com.newwavetech.leaverequestadmindemo1.viewmodels.DepartmentsViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DepartmentFragment extends Fragment {
    @BindView(R.id.department_recyclerview)
    RecyclerView recyclerView;

    DepartmentsViewModel departmentsViewModel;

    private List<Departments> departmentsList;
    private Dialog dialog;

    public DepartmentFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_department, container, false);
        ButterKnife.bind(this, layout);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(layout.getContext()));

        final DepartmentAdapter adapter = new DepartmentAdapter(new ArrayList<Departments>());
        recyclerView.setAdapter(adapter);

        departmentsViewModel = ViewModelProviders.of((FragmentActivity) layout.getContext()).get(DepartmentsViewModel.class);
        departmentsViewModel.getDepartments().observe((FragmentActivity) layout.getContext(), departments -> adapter.setData(departments.getDepartmentsList()));

        return layout;
    }

    @OnClick(R.id.department_add_fab)
    public void addOnClick() {
        dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.popup_layout);
        dialog.setCanceledOnTouchOutside(false);

        TextView titletv = dialog.findViewById(R.id.poppup_title);
        MaterialButton saveBtn = dialog.findViewById(R.id.popup_save_btn);
        MaterialButton cancelBtn = dialog.findViewById(R.id.popup_cancel_btn);
        EditText titleEd = dialog.findViewById(R.id.popup_title_ed);
        EditText descriptionEd = dialog.findViewById(R.id.popup_description_ed);

        titletv.setText("Add New Department Details");
        dialog.show();

        saveBtn.setOnClickListener(v1 -> {
            String editedTitle = String.valueOf(titleEd.getText());
            String editedDescription = String.valueOf(descriptionEd.getText());
            if (editedTitle.trim().equals("") || editedDescription.trim().equals("")) {
                Toast.makeText(this.getContext(), "Text Fileds should not be empty", Toast.LENGTH_SHORT).show();
            } else {
                departmentsViewModel.insertDepartment(new Departments(editedTitle, editedDescription));
                dialog.dismiss();
            }
        });

        cancelBtn.setOnClickListener(v1 -> {
            dialog.cancel();
        });
    }
}
