package com.newwavetech.leaverequestadmindemo1.activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.fragments.EmployeeListFragment;
import com.newwavetech.leaverequestadmindemo1.fragments.LeaveTypesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridItemEmployeeActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.toolbar_employee)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_griditem_employee);

        ButterKnife.bind(this);

        //SetToolBar
        toolbar.setTitle("Employee List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorGrey), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> finish());

        bottomNavigationView.setOnNavigationItemSelectedListener
                (item -> {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.menu_employee_list: {
                            selectedFragment = new EmployeeListFragment();
                            toolbar.setTitle("Employee List");
                            break;
                        }
                        case R.id.menu_leave_types: {
                            selectedFragment = new LeaveTypesFragment();
                            toolbar.setTitle("Leave Types");
                            break;
                        }
                    }
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();
                    return true;
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new EmployeeListFragment());
        transaction.commit();
    }

}
