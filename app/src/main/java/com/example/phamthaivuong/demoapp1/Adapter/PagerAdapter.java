package com.example.phamthaivuong.demoapp1.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.phamthaivuong.demoapp1.Home.AllTab;
import com.example.phamthaivuong.demoapp1.Home.ScanTab;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                AllTab allTab = new AllTab();
                return allTab;
            case 1:
                ScanTab scanTab = new ScanTab();
                return scanTab;
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
