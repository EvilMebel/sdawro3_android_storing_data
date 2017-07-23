package com.sdacademy.zientara.rafal.awesomeapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sdacademy.zientara.rafal.awesomeapp.adapters.FilesAdapter;
import com.sdacademy.zientara.rafal.fileexplorer.FileItem;
import com.sdacademy.zientara.rafal.fileexplorer.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FilesAdapter.OnItemClick {
    public static final int EXTERNAL_STORAGE_REQUEST_CODE = 1500;
    private File currentDir;
    private FilesAdapter adapter;

    @BindView(R.id.mainActivity_rootView)
    View rootView;

    @BindView(R.id.mainACtivity_pathText)
    TextView pathText;

    @BindView(R.id.mainActivity_recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        checkExternalStoragePermission();
        refreshList(new ArrayList<FileItem>());
        currentDir = new File(Environment.getExternalStorageDirectory().getPath());
//        currentDir = new File(Environment.getRootDirectory().getPath());
        showFiles(currentDir);
    }

    private void checkExternalStoragePermission() {
        //// TODO: 23.07.2017 ask for permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_REQUEST_CODE);
        /*if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

            }
        }*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_STORAGE_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showFiles(currentDir);
                } else
                    Toast.makeText(this, ":(", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void showFiles(File file) {
        pathText.setText(file.getPath());
        List<FileItem> filesList = FileUtils.getFilesList(file);
        refreshList(filesList);
    }

    private void refreshList(List<FileItem> filesList) {
        adapter = new FilesAdapter(this, filesList);
        adapter.setOnItemClick(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void onFileClick(FileItem fileItem) {
        Toast.makeText(this, "FileItem Clicked: " + currentDir, Toast.LENGTH_SHORT).show();
        //// TODO: 23.07.2017 open file!
    }

    private void showError(Exception e) {
        pathText.setText(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
    }

    @Override
    public void onFileItemClicked(FileItem fileItem, int position) {
        if (fileItem.isDirectory()) {
            currentDir = new File(fileItem.getPath());
            showFiles(currentDir);
        } else {
            onFileClick(fileItem);
        }
    }
}
