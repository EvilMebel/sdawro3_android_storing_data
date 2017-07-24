package com.sdacademy.zientara.rafal.awesomeapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sdacademy.zientara.rafal.awesomeapp.preferences.TermsStatePreferences;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mainActivity_rootView)
    View rootView;

    @BindColor(android.R.color.white)
    int whiteColor;

    @BindColor(R.color.redBackground)
    int redColor;

    @BindColor(R.color.blueBackground)
    int blueColor;

    @BindColor(R.color.greenBackground)
    int greenColor;

    @BindView(R.id.mainActivity_buttonRed)
    Button buttonRed;

    @BindView(R.id.mainActivity_buttonBlue)
    Button buttonBlue;

    @BindView(R.id.mainActivity_buttonGreen)
    Button buttonGreen;

    int currentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        checkTerms();
    }

    private void checkTerms() {
        if(!areTermsAccepted()) {
            showTermsDialog();
        }
    }

    private void showTermsDialog() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("Regulamin")
                .setMessage("Akceptuj albo wypad!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        storeTermsAccepted();
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("Nigdy!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "TO NIE! D:", Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .create()
                .show();
    }

    private void storeTermsAccepted() {
        TermsStatePreferences.setTermsAccepted(this, true);
    }

    private boolean areTermsAccepted() {
        return TermsStatePreferences.areTermsAccepted(this);
    }

    @OnClick(R.id.mainActivity_buttonRed)
    public void clickRedButton() {
        setAndStoreColor(redColor);
    }

    @OnClick(R.id.mainActivity_buttonBlue)
    public void clickBlueButton() {
        setAndStoreColor(blueColor);
    }

    @OnClick(R.id.mainActivity_buttonGreen)
    public void clickGreenButton() {
        setAndStoreColor(greenColor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        currentColor = sharedPreferences.getInt("background_color", whiteColor);
        setColor(currentColor);
    }

    private void setAndStoreColor(int color) {
        setColor(color);
        storeColor();
    }

    private void setColor(int color) {
        currentColor = color;
        rootView.setBackgroundColor(color);
    }

    private void storeColor() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("background_color", currentColor);
        editor.apply();
        //editor.commit();//albo to
    }
}
