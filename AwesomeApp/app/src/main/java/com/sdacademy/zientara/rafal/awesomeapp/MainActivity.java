package com.sdacademy.zientara.rafal.awesomeapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mainActivity_buttonRed)
    public void clickRedButton() {
        setColor(redColor);
    }

    @OnClick(R.id.mainActivity_buttonBlue)
    public void clickBlueButton() {
        setColor(blueColor);
    }

    @OnClick(R.id.mainActivity_buttonGreen)
    public void clickGreenButton() {
        setColor(greenColor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //// TODO: load color
    }

    private void setColor(int color) {
        rootView.setBackgroundColor(color);
        //// TODO: store color here
    }

    @Override
    protected void onPause() {
        super.onPause();
        //// TODO: or store color here
    }
}
