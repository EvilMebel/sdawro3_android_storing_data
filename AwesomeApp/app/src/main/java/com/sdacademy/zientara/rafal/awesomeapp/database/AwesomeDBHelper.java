package com.sdacademy.zientara.rafal.awesomeapp.database;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sdacademy.zientara.rafal.awesomeapp.database.datasource.NewsDS;
import com.sdacademy.zientara.rafal.awesomeapp.entity.News;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


public class AwesomeDBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "data.sqlite";
    private static AwesomeDBHelper sHelper;
    private Context context;

    public synchronized static AwesomeDBHelper getInstance(Context context) {
        if (sHelper == null)
            sHelper = new AwesomeDBHelper(context.getApplicationContext());
        return sHelper;
    }


    private AwesomeDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;

        try {
            createDataBase();
            Log.d("Success Create Database", "Success Create Database");

        } catch (IOException e) {
            Log.d("Error Create Database", e.getMessage());
        }

    }

    public void createDataBase() throws IOException {
        boolean dbExists = databaseFileExists();
        SQLiteDatabase dbRead = null;

        if (!dbExists) {
            String path = getReadableDatabase().getPath();
            dbRead = getReadableDatabase();
            dbRead.close();
            copyDatabase();
        }
    }

    private boolean databaseFileExists() {

        File dbFile = new File(context.getDatabasePath(DB_NAME).toString());

        return dbFile.exists();
    }

    private void copyDatabase() throws IOException {
        AssetManager assets = context.getAssets();
        InputStream input = assets.open(DB_NAME);
        String outputPath = context.getDatabasePath(DB_NAME).toString();
        OutputStream output = new FileOutputStream(outputPath);

        byte[] buffer = new byte[1024];
        int length;

        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }

        output.flush();
        output.close();
        input.close();
    }

    @Override
    public synchronized void close() {
        if (database != null) {
            database.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<News> getNews() {
        return NewsDS.getNews(this);
    }

    public News getNewsById(int newsId) {
        return NewsDS.getNewsById(newsId, this);
    }

    public void addOrUpdateNews(News news) {
        NewsDS.addOrUpdateNews(news, this);
    }

    public void insertNews(News news) {
        NewsDS.insertNews(news, this);
    }

    public void updateNews(News news) {
        NewsDS.updateNews(news, this);
    }

    public void removeNews() {
        NewsDS.removeAllNews(this);
    }
}
