package com.sdacademy.zientara.rafal.awesomeapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdacademy.zientara.rafal.awesomeapp.R;
import com.sdacademy.zientara.rafal.awesomeapp.models.FileItem;
import com.sdacademy.zientara.rafal.awesomeapp.viewHolders.FileViewHolder;

import java.util.List;

/**
 * Created by Evil on 25.07.2017.
 */

public class FilesAdapter extends RecyclerView.Adapter<FileViewHolder> {
    private final LayoutInflater inflater;
    private List<FileItem> fileItems;
    private OnFileItemClicked onFileItemClicked;

    public FilesAdapter(Context context, List<FileItem> fileItems, OnFileItemClicked onFileItemClicked) {
        this.fileItems = fileItems;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.onFileItemClicked = onFileItemClicked;
    }

    @Override
    public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.file_item, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FileViewHolder holder, int position) {
        final FileItem fileItem = fileItems.get(position);
        holder.nameText.setText(fileItem.getName());
        if (fileItem.isDirectory())
            holder.icon.setImageResource(R.drawable.ic_folder);
        else
            holder.icon.setImageResource(R.drawable.ic_file);
        //// TODO: 25.07.2017 show file size
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFileItemClicked.onFileItemClicked(fileItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileItems.size();
    }

    public interface OnFileItemClicked {
        void onFileItemClicked(FileItem fileItem);
    }
}
