package com.sdacademy.zientara.rafal.awesomeapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdacademy.zientara.rafal.awesomeapp.R;
import com.sdacademy.zientara.rafal.awesomeapp.database.AwesomeDBHelper;
import com.sdacademy.zientara.rafal.awesomeapp.entity.News;

import java.util.List;

import butterknife.ButterKnife;

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AwesomeDBHelper helper = AwesomeDBHelper.getInstance(getContext().getApplicationContext());
        List<News> news = helper.getNews();
        Log.d("TEST", "size of news " + news.size());
    }
}
