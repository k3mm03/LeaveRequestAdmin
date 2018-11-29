package com.newwavetech.leaverequestadmindemo1.activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.newwavetech.leaverequestadmindemo1.R;
import com.newwavetech.leaverequestadmindemo1.adapters.TabAdapter;
import com.newwavetech.leaverequestadmindemo1.fragments.LeaveManageFragment;
import com.newwavetech.leaverequestadmindemo1.fragments.RequestsListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminManagementActivity extends AppCompatActivity {

    static int adminid;
    int[] tabIcons = {
            R.drawable.ic_manage,
            R.drawable.ic_reminder
    };
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private TabAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_management);

        ButterKnife.bind(this);

        adminid = getIntent().getIntExtra("adminid", 0);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new LeaveManageFragment(), "Manage");
        adapter.addFragment(new RequestsListFragment(), "Requests");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setUpTabIcons();

        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.colorPurpleDark), PorterDuff.Mode.SRC_IN);

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener());
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.colorPurpleDark), PorterDuff.Mode.SRC_IN);

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setUpTabIcons() {

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }
}
