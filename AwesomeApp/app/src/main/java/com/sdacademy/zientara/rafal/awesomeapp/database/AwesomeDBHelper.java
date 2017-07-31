package com.sdacademy.zientara.rafal.awesomeapp.database;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    }

    private boolean databaseFileExists() {

        File dbFile = new File(context.getDatabasePath(DB_NAME).toString());

        return dbFile.exists();
    }

    private String getSqlInitQuery() {
        try {
            AssetManager assets = context.getAssets();
            InputStream input = null;

            input = assets.open(DB_NAME);
            String outputPath = context.getDatabasePath(DB_NAME).toString();
            OutputStream output = new FileOutputStream(outputPath);

            StringBuffer fileContent = new StringBuffer("");

            byte[] buffer = new byte[1024];

            int n;
            while ((n = input.read(buffer)) != -1) {
                fileContent.append(new String(buffer, 0, n));
            }
            input.close();
            return fileContent.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
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
        db.execSQL("CREATE TABLE `news` (\n" +
                "\t`Id`\tINTEGER,\n" +
                "\t`Name`\tVARCHAR,\n" +
                "\t`Description`\tVARCHAR,\n" +
                "\t`DateStart`\tINTEGER DEFAULT (null),\n" +
                "\t`DateFinish`\tINTEGER DEFAULT (null),\n" +
                "\t`Path`\tVARCHAR,\n" +
                "\t`isAsset`\tINTEGER\n" +
                ");");
        //db.execSQL(getSqlInitQuery());
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
