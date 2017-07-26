package com.sdacademy.zientara.rafal.awesomeapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sdacademy.zientara.rafal.awesomeapp.fragments.ExplorerFragment;
import com.sdacademy.zientara.rafal.awesomeapp.fragments.SettingsFragment;

import java.io.File;

public class MainActivity extends AppCompatActivity implements ExplorerFragment.ExploratorInteractionListener {

    private static final int EXTERNAL_STORAGE_REQUEST_CODE = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        openExplorerFragment(Environment.getRootDirectory().getPath(), false);
    }

    private void openExplorerFragment(String path, boolean canGoBack) {
        int enterAnim = android.R.anim.slide_in_left;
        int exitAnim = android.R.anim.slide_out_right;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(enterAnim, exitAnim, enterAnim, exitAnim);
        transaction.add(R.id.mainActivity_fragmentContainer, ExplorerFragment.newInstance(path));
        if (canGoBack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                openSettingsFragment();
                return true;

            case R.id.action_goto_external:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_REQUEST_CODE);
                return true;


            case R.id.action_goto_root:
                openExplorerFragment(Environment.getRootDirectory().getPath(), false);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case EXTERNAL_STORAGE_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    openExplorerFragment(Environment.getExternalStorageDirectory().getPath(), false);
                else
                    Toast.makeText(this, ":(", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void openSettingsFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mainActivity_fragmentContainer, new SettingsFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onPathClicked(String newFilePath) {
        openExplorerFragment(newFilePath, true);
    }
}
