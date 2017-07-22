package com.sdacademy.zientara.rafal.fileexplorer;

import android.content.ContextWrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Evil on 22.07.2017.
 */

public class FileWriter {
    private FileOutputStream fileOutputStream;
    String fileName;
    private File file;

    public FileWriter(String fileName, ContextWrapper contextWrapper) {
        this.fileName = fileName;
        prepareFile();

        try {
            fileOutputStream = contextWrapper.openFileOutput(fileName, MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void prepareFile() {
        file = new File(getPath());
        if(!file.exists())
            file.mkdirs();
    }

    private String getPath() {
        return FileUtils.getExternalPath() + fileName;
    }

    public void writeLine(String line) {

    }
}
