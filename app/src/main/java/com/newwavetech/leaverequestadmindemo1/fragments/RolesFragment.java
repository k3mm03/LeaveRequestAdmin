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
import com.newwavetech.leaverequestadmindemo1.adapters.RoleAdapter;
import com.newwavetech.leaverequestadmindemo1.pojo.Roles;
import com.newwavetech.leaverequestadmindemo1.viewmodels.RolesViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RolesFragment extends Fragment {

    @BindView(R.id.role_recyclerview)
    RecyclerView recyclerView;

    RolesViewModel rolesViewModel;

    private List<Roles> rolesList;
    private Dialog dialog;

    public RolesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_roles, container, false);
        ButterKnife.bind(this, layout);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(layout.getContext()));

        final RoleAdapter adapter = new RoleAdapter(new ArrayList<Roles>());
        recyclerView.setAdapter(adapter);

        rolesViewModel = ViewModelProviders.of((FragmentActivity) layout.getContext()).get(RolesViewModel.class);
        rolesViewModel.getRoles().observe((FragmentActivity) layout.getContext(), rolesList -> adapter.setData(rolesList.getRolesList()));

        return layout;
    }

    @OnClick(R.id.role_add_fab)
    public void addOnClick() {

        dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.popup_layout);
        dialog.setCanceledOnTouchOutside(false);

        TextView titletv = dialog.findViewById(R.id.poppup_title);
        MaterialButton saveBtn = dialog.findViewById(R.id.popup_save_btn);
        MaterialButton cancelBtn = dialog.findViewById(R.id.popup_cancel_btn);
        EditText titleEd = dialog.findViewById(R.id.popup_title_ed);
        EditText descriptionEd = dialog.findViewById(R.id.popup_description_ed);

        titletv.setText("Add New Role Details");
        dialog.show();

        saveBtn.setOnClickListener(v1 -> {
            String editedTitle = String.valueOf(titleEd.getText());
            String editedDescription = String.valueOf(descriptionEd.getText());
            if (editedTitle.trim().equals("") || editedDescription.trim().equals("")) {
                Toast.makeText(this.getContext(), "Text Fileds should not be empty", Toast.LENGTH_SHORT).show();
            } else {
                rolesViewModel.insertRole(new Roles(editedTitle, editedDescription));
                dialog.dismiss();
            }
        });

        cancelBtn.setOnClickListener(v1 -> {
            dialog.cancel();
        });

    }
}
