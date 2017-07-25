package com.sdacademy.zientara.rafal.awesomeapp.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdacademy.zientara.rafal.awesomeapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Evil on 25.07.2017.
 */

public class FileViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fileItem_icon)
    public ImageView icon;

    @BindView(R.id.fileItem_nameText)
    public TextView nameText;

    @BindView(R.id.fileItem_fileSizeText)
    public TextView fileSizeText;

    public FileViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
