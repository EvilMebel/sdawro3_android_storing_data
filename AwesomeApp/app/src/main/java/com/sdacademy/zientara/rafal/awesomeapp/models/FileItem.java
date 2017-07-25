package com.sdacademy.zientara.rafal.awesomeapp.models;

import java.io.File;

/**
 * Created by Evil on 25.07.2017.
 */

public class FileItem {
    String name;
    long lastModified;
    long fileSizeInBytes;
    boolean directory;
    String path;

    public FileItem(File currentFile) {
        name = currentFile.getName();
        lastModified = currentFile.lastModified();
        fileSizeInBytes = currentFile.length();
        directory = currentFile.isDirectory();
        path = currentFile.getPath();
    }

    public FileItem(String parentFilePath) {
        path = parentFilePath;
        directory = true;
        name = "..";
    }

    public String getName() {
        return name;
    }

    public long getLastModified() {
        return lastModified;
    }

    public long getFileSizeInBytes() {
        return fileSizeInBytes;
    }

    public boolean isDirectory() {
        return directory;
    }

    public String getPath() {
        return path;
    }
}
