package com.newwavetech.leaverequestadmindemo1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.activities.GridItemDepartmentActivity;
import com.newwavetech.leaverequestadmindemo1.activities.GridItemEmployeeActivity;
import com.newwavetech.leaverequestadmindemo1.activities.GridItemHistoryActivity;
import com.newwavetech.leaverequestadmindemo1.adapters.GridViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaveManageFragment extends Fragment {

    String[] gridList = {"Employee", "Department", "History", "Import/Export"};

    int[] gridImageList = {
            R.drawable.ic_employee,
            R.drawable.ic_department,
            R.drawable.ic_history,
            R.drawable.ic_import_export
    };

    @BindView(R.id.admin_manage_gridview)
    GridView gridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_admin_management, container, false);

        ButterKnife.bind(this, layout);

        GridViewAdapter adapter = new GridViewAdapter(getContext(), gridList, gridImageList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> displaySelectedItem(position));

        return layout;
    }

    private void displaySelectedItem(int position) {
        switch (position) {
            case 0: {
                Intent intent = new Intent(getContext(), GridItemEmployeeActivity.class);
                startActivity(intent);
                break;
            }

            case 1: {
                Intent intent = new Intent(getContext(), GridItemDepartmentActivity.class);
                startActivity(intent);
                break;
            }
            case 2: {
                Intent intent = new Intent(getContext(), GridItemHistoryActivity.class);
                startActivity(intent);
                break;
            }
            case 3: {
                break;
            }
        }
    }
}
