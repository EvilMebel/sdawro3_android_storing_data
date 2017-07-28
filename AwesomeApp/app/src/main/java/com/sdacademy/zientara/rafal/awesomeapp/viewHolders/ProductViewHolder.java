package com.sdacademy.zientara.rafal.awesomeapp.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdacademy.zientara.rafal.awesomeapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Evil on 25.07.2017.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.productItem_details)
    public TextView details;

    @BindView(R.id.productItem_checkBox)
    public CheckBox checkBox;

    public ProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}