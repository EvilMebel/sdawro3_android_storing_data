package com.sdacademy.zientara.rafal.awesomeapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.sdacademy.zientara.rafal.awesomeapp.R;
import com.sdacademy.zientara.rafal.awesomeapp.adapter.ProductsAdapter;
import com.sdacademy.zientara.rafal.awesomeapp.models.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsFragment extends Fragment {
    private InteractionListener mListener;

    @BindView(R.id.productsFragment_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.productsFragment_addButton)
    FloatingActionButton addButton;

    private List<Product> productsList;
    private ProductsAdapter productsAdapter;

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadProducts();
    }

    private void loadProducts() {
        productsList = new Select().from(Product.class).execute();
        productsAdapter = new ProductsAdapter(getActivity(), productsList);
        recyclerView.setAdapter(productsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionListener) {
            mListener = (InteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        productsAdapter = null;
        productsList = null;
    }

    public interface InteractionListener {
        void addedNewProduct();
    }
}
