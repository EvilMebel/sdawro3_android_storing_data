package com.sdacademy.zientara.rafal.awesomeapp.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.sdacademy.zientara.rafal.awesomeapp.R;
import com.sdacademy.zientara.rafal.awesomeapp.models.Product;
import com.sdacademy.zientara.rafal.awesomeapp.utils.DbHelper;

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
        int purchasedItems = getPurchasedItemsCount();
        int allItems = getAllItemsCount();
        showPurchasedInfo(purchasedItems, allItems);
        appendFruitsTotalCosts();
        appendTotalCosts();
    }

    private void appendFruitsTotalCosts() {
        double fruitsTotalCosts = getFruitsTotalCosts();
        outputText.append(String.format("\nFruits costs: %.2f PLN", fruitsTotalCosts));
    }

    private void appendTotalCosts() {
        double totalCosts = getTotalCosts();
        outputText.append(String.format("\nTotal costs: %.2f PLN", totalCosts));
    }

    private void showPurchasedInfo(int purchasedItems, int allItems) {
        String purchased = String.format("Progress %d/%d", purchasedItems, allItems);
        outputText.setText(purchased);
    }

    private double getFruitsTotalCosts() {
        ;gktrhihiurh
    }

    private double getTotalCosts() {
//        return DbHelper.getDoubleFromRawQuery("SELECT count(*) AS count from 'products'");
        return DbHelper.getDoubleFromRawQuery("SELECT sum(price * count) AS count from 'products'");
    }



    private int getPurchasedItemsCount() {
        return new Select().from(Product.class)
                .where("is_purchased != 0")
                .count();
    }

    private int getAllItemsCount() {
        return new Select().from(Product.class).count();
    }
}
