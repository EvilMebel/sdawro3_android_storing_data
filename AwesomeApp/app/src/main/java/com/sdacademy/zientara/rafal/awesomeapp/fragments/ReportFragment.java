package com.sdacademy.zientara.rafal.awesomeapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.sdacademy.zientara.rafal.awesomeapp.R;
import com.sdacademy.zientara.rafal.awesomeapp.models.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Evil on 31.07.2017.
 */

public class ReportFragment extends Fragment {

    @BindView(R.id.raportFragment_outputText)
    TextView outputText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadReport();

    }

    private void loadReport() {
//        int purchasedItems = getPurchasedItemsCount();
        int allItems = getAllItemsCount();
    }

    private int getAllItemsCount() {
        return new Select().from(Product.class).count();
    }
}
