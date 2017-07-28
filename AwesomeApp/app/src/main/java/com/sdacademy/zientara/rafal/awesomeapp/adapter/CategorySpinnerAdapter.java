package com.sdacademy.zientara.rafal.awesomeapp.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.sdacademy.zientara.rafal.awesomeapp.models.Category;

import java.util.List;

/**
 * Created by Evil on 28.07.2017.
 */

public class CategorySpinnerAdapter implements SpinnerAdapter {
    private final LayoutInflater inflater;
    private Context applicationContext;
    private List<Category> categories;

    public CategorySpinnerAdapter(Context applicationContext, List<Category> categories) {
        this.applicationContext = applicationContext;
        this.categories = categories;
        inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        TextView text = (TextView) view.findViewById(android.R.id.text1);

        Category item = (Category) getItem(position);
        text.setText(item.getName());

        return view;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getDropDownView(position, convertView, parent);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return categories.isEmpty();
    }
}
