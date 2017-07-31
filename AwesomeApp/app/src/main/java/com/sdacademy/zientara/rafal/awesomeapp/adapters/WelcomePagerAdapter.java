package com.sdacademy.zientara.rafal.awesomeapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sdacademy.zientara.rafal.awesomeapp.R;
import com.sdacademy.zientara.rafal.awesomeapp.fragments.pager.FirstPageFragment;
import com.sdacademy.zientara.rafal.awesomeapp.fragments.pager.SecondPageFragment;

import java.util.Locale;

import butterknife.OnClick;

public class WelcomePagerAdapter extends FragmentStatePagerAdapter {
    private static final String FIRST_TITLE = "First Title";
    private static final String SECOND_TITLE = "Second title";
    private static final int NUMBER_OF_PAGES = 2;

    public WelcomePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FirstPageFragment();
//                return new FirstPageFragment.newInstance(testPackDefinitionId);
            case 1:
                return new SecondPageFragment();
            default:
                throwException(position);
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return FIRST_TITLE;
            case 1:
                return SECOND_TITLE;
            default:
                throwException(position);
                return null;
        }
    }

    private void throwException(int position) {
        throw new IllegalArgumentException(String.format(Locale.getDefault(), "Position %d is not supported", position));
    }
}