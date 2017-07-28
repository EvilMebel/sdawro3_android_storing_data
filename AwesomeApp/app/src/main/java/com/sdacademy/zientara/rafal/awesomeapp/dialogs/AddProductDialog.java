package com.sdacademy.zientara.rafal.awesomeapp.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.sdacademy.zientara.rafal.awesomeapp.R;
import com.sdacademy.zientara.rafal.awesomeapp.adapter.CategorySpinnerAdapter;
import com.sdacademy.zientara.rafal.awesomeapp.models.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Evil on 28.07.2017.
 */

public class AddProductDialog extends DialogFragment {

    @BindView(R.id.addProductDialog_nameText)
    EditText nameText;

    @BindView(R.id.addProductDialog_categorySpinner)
    Spinner categorySpinner;

    @BindView(R.id.addProductDialog_count)
    EditText count;

    @BindView(R.id.addProductDialog_price)
    EditText price;

    @BindView(R.id.addProductDialog_addButton)
    Button addButton;
    private List<Category> categories;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_product, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categories = new Select().from(Category.class).execute();
        categorySpinner.setAdapter(new CategorySpinnerAdapter(getActivity().getApplicationContext(), categories));
    }
}
