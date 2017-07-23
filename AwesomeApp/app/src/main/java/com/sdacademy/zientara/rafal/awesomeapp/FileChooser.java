package com.sdacademy.zientara.rafal.awesomeapp;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.ListView;

import com.sdacademy.zientara.rafal.fileexplorer.FileUtils;
import com.sdacademy.zientara.rafal.fileexplorer.Item;

public class FileChooser extends ListActivity {

    private File currentDir;
    private FileArrayAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        currentDir = new File(Environment.getRootDirectory().getPath());
        currentDir = new File(Environment.getExternalStorageDirectory().getPath());
//        currentDir = new File("/sdcard/");

        FileInputStream fileInputStream  = new FileInputStream(currentDir)
        fill(currentDir);
    }

    private void fill(File f) {
        List<Item> filesList = FileUtils.getFilesList(f);
        adapter = new FileArrayAdapter(FileChooser.this, R.layout.file_view, filesList);
        this.setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Item o = adapter.getItem(position);
        if (o.isDirectory()) {
            currentDir = new File(o.getPath());
            fill(currentDir);
        } else {
            onFileClick(o);
        }
    }

    private void onFileClick(Item o) {
        //Toast.makeText(this, "Folder Clicked: "+ currentDir, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("GetPath", currentDir.toString());
        intent.putExtra("GetFileName", o.getName());
        setResult(RESULT_OK, intent);
        finish();
    }
}
