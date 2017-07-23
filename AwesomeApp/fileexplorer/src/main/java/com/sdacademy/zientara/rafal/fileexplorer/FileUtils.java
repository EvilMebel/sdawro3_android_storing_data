package com.sdacademy.zientara.rafal.fileexplorer;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Evil on 22.07.2017.
 */

public class FileUtils {

    public static String getExternalPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    public static List<Item> getFilesList(File f) {
        File[] dirs = f.listFiles();
        List<Item> dir = new ArrayList<Item>();
        List<Item> fls = new ArrayList<Item>();
        try {
            for (File currentFile : dirs) {
                Date lastModDate = new Date(currentFile.lastModified());
                DateFormat formater = DateFormat.getDateTimeInstance();
                String date_modify = formater.format(lastModDate);
                if (currentFile.isDirectory()) {
                    String num_item = getFilesCountDescription(currentFile);
                    dir.add(new Item(currentFile.getName(), num_item, date_modify, currentFile.getAbsolutePath(), true));
                } else {

                    fls.add(new Item(currentFile.getName(), currentFile.length() + " Byte", date_modify, currentFile.getAbsolutePath(), false));
                }
            }
        } catch (Exception e) {
            Log.e(FileUtils.class.getSimpleName(), "Error!: " + e.getMessage());
            e.printStackTrace();
        }
        Collections.sort(dir);
        Collections.sort(fls);
        dir.addAll(fls);
        if (!f.getName().equalsIgnoreCase("sdcard"))
            dir.add(0, new Item("..", "", "Parent Directory", f.getParent(), true));
        return dir;
    }

    private static String getFilesCountDescription(File currentFile) {
        int filesCount = getFilesCount(currentFile);
        String num_item = String.valueOf(filesCount);
        if (filesCount == 0)
            num_item = num_item + " item";
        else
            num_item = num_item + " items";
        return num_item;
    }

    private static int getFilesCount(File file) {
        File[] fbuf = file.listFiles();
        if (fbuf != null)
            return fbuf.length;
        return 0;
    }
}
