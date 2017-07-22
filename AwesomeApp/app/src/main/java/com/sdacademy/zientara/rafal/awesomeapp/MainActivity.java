package com.sdacademy.zientara.rafal.awesomeapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String FILENAME = "first_file";

    @BindView(R.id.mainActivity_rootView)
    View rootView;

    @BindView(R.id.mainActivity_outputText)
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mainActivity_writeButton)
    public void writeData() {
        try {
            FileOutputStream  fileOutputStream = openFileOutput(FILENAME, MODE_PRIVATE);
            fileOutputStream.write("hello".getBytes());
            fileOutputStream.close();
            outputText.setText("Success!");
        } catch (FileNotFoundException e) {//openFileOutput
            e.printStackTrace();
            showError(e);
        } catch (IOException e) {//write
            e.printStackTrace();
            showError(e);
        }
    }

    @OnClick(R.id.mainActivity_readButton)
    public void readData() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            StringBuffer fileContent = new StringBuffer("");

            byte[] buffer = new byte[1024];

            int n;
            while ((n = fis.read(buffer)) != -1)
            {
                fileContent.append(new String(buffer, 0, n));
            }
            outputText.setText(fileContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            showError(e);
        } catch (IOException e) {
            e.printStackTrace();
            showError(e);
        }

    }

    private void showError(Exception e) {
        outputText.setText(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
    }

}
