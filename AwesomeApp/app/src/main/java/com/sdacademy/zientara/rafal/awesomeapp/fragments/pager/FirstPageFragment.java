package com.sdacademy.zientara.rafal.awesomeapp.fragments.pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdacademy.zientara.rafal.awesomeapp.R;

import butterknife.ButterKnife;

/**
 * Created by Evil on 31.07.2017.
 */

public class FirstPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_welcome, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
