package com.newwavetech.leaverequestadmindemo1.activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.fragments.DepartmentFragment;
import com.newwavetech.leaverequestadmindemo1.fragments.RolesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridItemDepartmentActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_department)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_griditem_department);

        ButterKnife.bind(this);

        //SetToolBar
        toolbar.setTitle("Departments");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorGrey), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(v -> finish());

        bottomNavigationView.setOnNavigationItemSelectedListener
                (item -> {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.menu_department: {
                            selectedFragment = new DepartmentFragment();
                            toolbar.setTitle("Departments");
                            break;
                        }
                        case R.id.menu_roles: {
                            selectedFragment = new RolesFragment();
                            toolbar.setTitle("Roles");
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
        transaction.replace(R.id.frame_layout, new DepartmentFragment());
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
