package com.sdacademy.zientara.rafal.awesomeapp.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdacademy.zientara.rafal.awesomeapp.R;

import butterknife.ButterKnife;

/**
 * Created by Evil on 28.07.2017.
 */

public class AddProductDialog {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.skghjbretigbr, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
