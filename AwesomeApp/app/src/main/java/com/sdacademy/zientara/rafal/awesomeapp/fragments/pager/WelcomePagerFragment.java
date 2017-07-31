package com.sdacademy.zientara.rafal.awesomeapp.fragments.pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdacademy.zientara.rafal.awesomeapp.R;
import com.sdacademy.zientara.rafal.awesomeapp.adapters.WelcomePagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Evil on 31.07.2017.
 */

public class WelcomePagerFragment extends Fragment {

    @BindView(R.id.welcomeFragment_viewPager)
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WelcomePagerAdapter pagerAdapter = new WelcomePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }
}
