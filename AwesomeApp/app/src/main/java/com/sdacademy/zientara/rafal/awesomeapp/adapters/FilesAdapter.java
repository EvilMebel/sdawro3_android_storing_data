package com.sdacademy.zientara.rafal.awesomeapp.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdacademy.zientara.rafal.awesomeapp.R;
import com.sdacademy.zientara.rafal.awesomeapp.viewHolders.FileViewHolder;
import com.sdacademy.zientara.rafal.fileexplorer.FileItem;

import java.util.List;

import butterknife.BindDrawable;

/**
 * Created by Evil on 23.07.2017.
 */

public class FilesAdapter extends RecyclerView.Adapter<FileViewHolder> {
    private final LayoutInflater inflater;
    List<FileItem> fileItems;

    @BindDrawable(R.drawable.ic_folder_open_black_24dp)
    Drawable folderIcon;

    @BindDrawable(R.drawable.ic_insert_drive_file_black_24dp)
    Drawable fileIcon;

    private OnItemClick onItemClick;

    public FilesAdapter(Context context, List<FileItem> fileItems) {
        this.fileItems = fileItems;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.file_item, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FileViewHolder holder, int position) {
        final FileItem fileItem = fileItems.get(position);
        holder.nameText.setText(fileItem.getName());
        holder.descriptionText.setText(fileItem.getDescription());
        holder.dateText.setText(fileItem.getDate());
        if (fileItem.isDirectory())
            holder.icon.setImageResource(R.drawable.ic_folder_open_black_24dp);
        else
            holder.icon.setImageResource(R.drawable.ic_insert_drive_file_black_24dp);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null)
                    onItemClick.onFileItemClicked(fileItem, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileItems.size();
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onFileItemClicked(FileItem fileItem, int position);
    }
}
