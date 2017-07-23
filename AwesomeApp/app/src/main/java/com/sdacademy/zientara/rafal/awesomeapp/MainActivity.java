package com.sdacademy.zientara.rafal.awesomeapp;

import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.sdacademy.zientara.rafal.awesomeapp.fragments.DetailsFragment;
import com.sdacademy.zientara.rafal.awesomeapp.fragments.TitlesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TitlesFragment.OnTitlesFragmentInteractionListener, DetailsFragment.OnDetailsFragmentInteractionListener {

    @BindView(R.id.mainActivity_rootView)
    View rootView;

    @BindView(R.id.mainActivity_fragmentFirstContainer)
    ViewGroup fragmentFirstContainer;

    @BindView(R.id.mainActivity_fragmentSecondContainer)
    ViewGroup fragmentSecondContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addToFirstContainer(new TitlesFragment(), false);
        addToFirstContainer(new TitlesFragment(), true);
        addToSecondContainer(new DetailsFragment(), false);
        addToSecondContainer(new DetailsFragment(), true);
    }

    private void addToFirstContainer(Fragment newFragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.mainActivity_fragmentFirstContainer, newFragment);
        if (addToBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    private void addToSecondContainer(Fragment newFragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.mainActivity_fragmentSecondContainer, newFragment);
        if (addToBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //// TODO: interact!
    }
}
