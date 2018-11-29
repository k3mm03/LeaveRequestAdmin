package com.newwavetech.leaverequestadmindemo1.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> myFragmentList = new ArrayList<>();
    private final List<String> myFragmentTitleList = new ArrayList<>();

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return myFragmentList.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return myFragmentTitleList.get(position);
    }

    @Override
    public int getCount() {
        return myFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        myFragmentList.add(fragment);
        myFragmentTitleList.add(title);
    }
}
