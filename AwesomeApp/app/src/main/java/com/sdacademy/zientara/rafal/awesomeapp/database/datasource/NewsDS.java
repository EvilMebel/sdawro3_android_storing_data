package com.sdacademy.zientara.rafal.awesomeapp.database.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sdacademy.zientara.rafal.awesomeapp.database.AwesomeDBHelper;
import com.sdacademy.zientara.rafal.awesomeapp.database.AwesomeDBSchema;
import com.sdacademy.zientara.rafal.awesomeapp.entity.News;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class NewsDS {

    private static final String TAG = NewsDS.class.getSimpleName();

    public static List<News> getNews(AwesomeDBHelper openHelper) {

        List<News> newsList = new ArrayList<News>();

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);

        SQLiteDatabase database = openHelper.getReadableDatabase();
        String selection = String.format("%s < %s AND %s > %s", AwesomeDBSchema.News.Column.DateStart, reportDate,
                AwesomeDBSchema.News.Column.DateFinish, reportDate);
        Cursor cursor = database.query(AwesomeDBSchema.Tables.News, AwesomeDBSchema.News.Column.AllColumns,
                null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            newsList.add(cursorToNews(cursor));
            cursor.moveToNext();

        }
        cursor.close();

        long ct = System.currentTimeMillis();
        for (int i = newsList.size() - 1; i >= 0; i--) {
            News news = newsList.get(i);
            if (news.getDateStart().getTime() > ct || ct > news.getDateFinish().getTime())
                newsList.remove(i);
        }

        return newsList;
    }

    public static News getNewsById(int idNews, AwesomeDBHelper openHelper) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        String selection = String.format("%s = %s", AwesomeDBSchema.News.Column.Id, idNews);
        Cursor cursor = database.query(AwesomeDBSchema.Tables.News, AwesomeDBSchema.News.Column.AllColumns,
                selection, null, null, null, null);

        cursor.moveToFirst();

        News news = null;
        if (!cursor.isAfterLast())
            news = cursorToNews(cursor);
        cursor.close();

        return news;
    }

    public static void updateNews(News news, AwesomeDBHelper openHelper) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(AwesomeDBSchema.News.Column.Id, news.getId());
        contentValues.put(AwesomeDBSchema.News.Column.Name, news.getTitle());
        contentValues.put(AwesomeDBSchema.News.Column.Description, news.getDescription());
        contentValues.put(AwesomeDBSchema.News.Column.DateStart, news.getDateStart().getTime());
        contentValues.put(AwesomeDBSchema.News.Column.DateFinish, news.getDateFinish().getTime());
        contentValues.put(AwesomeDBSchema.News.Column.isAsset, news.isAsset());

        SQLiteDatabase db = openHelper.getWritableDatabase();

        if (db != null)
            db.updateWithOnConflict(AwesomeDBSchema.Tables.News, contentValues, String.format("%s = %s", AwesomeDBSchema.News.Column.Id, news.getId()), null, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public static void addOrUpdateNews(News news, AwesomeDBHelper openHelper) {
        News check = getNewsById(news.getId(), openHelper);

        if (check == null)
            insertNews(news, openHelper);
        else
            updateNews(news, openHelper);
    }

    public static void insertNews(News news, AwesomeDBHelper openHelper) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(AwesomeDBSchema.News.Column.Id, news.getId());
        contentValues.put(AwesomeDBSchema.News.Column.Name, news.getTitle());
        contentValues.put(AwesomeDBSchema.News.Column.Description, news.getDescription());
        contentValues.put(AwesomeDBSchema.News.Column.DateStart, news.getDateStart().getTime());
        contentValues.put(AwesomeDBSchema.News.Column.DateFinish, news.getDateFinish().getTime());

        SQLiteDatabase db = openHelper.getWritableDatabase();

        if (db != null)
            db.insertWithOnConflict(AwesomeDBSchema.Tables.News, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public static void removeAllNews(AwesomeDBHelper openHelper) {
        SQLiteDatabase db = openHelper.getReadableDatabase();

        db.delete(AwesomeDBSchema.Tables.News, null, null);
        db.close();
    }


    //CURSOR (ROW) TO OBJECT
    public static News cursorToNews(Cursor cursor) {
        News news = new News();

        news.setId(cursor.getInt(AwesomeDBSchema.News.ColumnID.Id));
        news.setTitle(cursor.getString(AwesomeDBSchema.News.ColumnID.Name));
        news.setDescription(cursor.getString(AwesomeDBSchema.News.ColumnID.Description));
        news.setDateStart(new Date(cursor.getLong(AwesomeDBSchema.News.ColumnID.DateStart)));
        news.setDateFinish(new Date(cursor.getLong(AwesomeDBSchema.News.ColumnID.DateFinish)));
        news.setIsAsset(cursor.getInt(AwesomeDBSchema.News.ColumnID.isAsset));

        return news;
    }
}

