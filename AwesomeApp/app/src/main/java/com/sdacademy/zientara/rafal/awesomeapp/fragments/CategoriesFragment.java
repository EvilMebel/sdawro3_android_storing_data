package com.sdacademy.zientara.rafal.awesomeapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.sdacademy.zientara.rafal.awesomeapp.R;
import com.sdacademy.zientara.rafal.awesomeapp.models.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoriesFragment extends Fragment {
    private InteractionListener mListener;

    @BindView(R.id.categoriesFragment_categoryName)
    EditText categoryName;

    @BindView(R.id.categoriesFragment_addCategory)
    Button addCategory;

    @BindView(R.id.categoriesFragment_outputText)
    TextView outputText;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refreshOutput();
    }

    private void refreshOutput() {
        List<Category> categories = new Select().from(Category.class).execute();
        StringBuilder builder = new StringBuilder();
        for (Category category : categories) {
            builder.append(category.toString());
            builder.append("\n\n");
        }
        outputText.setText(builder.toString());
    }

    @OnClick(R.id.categoriesFragment_addCategory)
    public void clickSaveButton() {
        String newCatName = categoryName.getText().toString().trim();
        List<Category> models = new Select().from(Category.class)
                .where(Category.COLUMN_NAME + " like ?", newCatName)
                .execute();
        int count = models.size();
        if (count == 0) {
            Category category = new Category();
            category.setName(newCatName);
            category.save();
        } else {
            showToast("This category exists!");
        }

        refreshOutput();
    }

    private void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
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
    }

    public interface InteractionListener {
        void doNothing();
    }
}
