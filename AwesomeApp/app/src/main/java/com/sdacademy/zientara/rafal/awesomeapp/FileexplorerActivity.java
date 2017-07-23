package com.sdacademy.zientara.rafal.awesomeapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class FileexplorerActivity extends Activity {

    private static final int REQUEST_PATH = 1;

    String curFileName;

    EditText edittext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fileexplorer);
        edittext = (EditText) findViewById(R.id.editText);
    }

    public void getFile(View view) {
        Intent intent = new Intent(this, FileChooser.class);
        startActivityForResult(intent, REQUEST_PATH);
    }

    // Listen for results.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // See which child activity is calling us back.
        if (requestCode == REQUEST_PATH) {
            if (resultCode == RESULT_OK) {
                curFileName = data.getStringExtra("GetFileName");
                edittext.setText(curFileName);
            }
        }
    }
}
