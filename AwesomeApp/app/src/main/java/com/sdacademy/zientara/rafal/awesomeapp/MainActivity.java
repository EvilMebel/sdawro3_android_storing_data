package com.sdacademy.zientara.rafal.awesomeapp;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.sdacademy.zientara.rafal.awesomeapp.fragments.ExplorerFragment;
import com.sdacademy.zientara.rafal.awesomeapp.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        openExplorerFragment(Environment.getRootDirectory().getPath());
    }

    private void openExplorerFragment(String path) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mainActivity_fragmentContainer, ExplorerFragment.newInstance(path));
//        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            openSettingsFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSettingsFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mainActivity_fragmentContainer, new SettingsFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
