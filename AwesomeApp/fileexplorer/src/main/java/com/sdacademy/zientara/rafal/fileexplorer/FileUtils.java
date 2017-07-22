package com.sdacademy.zientara.rafal.fileexplorer;

import android.os.Environment;

import java.io.File;

/**
 * Created by Evil on 22.07.2017.
 */

public class FileUtils {

    public static String getExternalPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }
}
