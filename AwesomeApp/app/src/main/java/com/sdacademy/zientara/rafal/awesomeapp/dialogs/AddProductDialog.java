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

import com.activeandroid.query.Select;
import com.sdacademy.zientara.rafal.awesomeapp.R;
import com.sdacademy.zientara.rafal.awesomeapp.adapter.CategorySpinnerAdapter;
import com.sdacademy.zientara.rafal.awesomeapp.models.Category;
import com.sdacademy.zientara.rafal.awesomeapp.models.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private CategorySpinnerAdapter spinnerAdapter;
    private OnProductAdded listener;

    public void setListener(OnProductAdded listener) {
        this.listener = listener;
    }

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
        spinnerAdapter = new CategorySpinnerAdapter(getActivity().getApplicationContext(), categories);
        categorySpinner.setAdapter(spinnerAdapter);
    }

    @OnClick(R.id.addProductDialog_addButton)
    public void addProductDialog() {
        long selectedItemId = categorySpinner.getSelectedItemId();
        Category category = categories.get((int) selectedItemId);

        Product product = new Product();
        product.setCategory(category);
        product.setName(getName());
        product.setCount(getCount());
        product.setPrice(getPrice());
        product.setIsPurchased(0);
        product.save();
        dismiss();
        listener.onProductAdded();
    }

    private Double getPrice() {
        return Double.parseDouble(price.getText().toString().trim());
    }

    private double getCount() {
        return Double.parseDouble(count.getText().toString().trim());
    }

    private String getName() {
        return nameText.getText().toString().trim();
    }

    public interface OnProductAdded {
        void onProductAdded();
    }
}
