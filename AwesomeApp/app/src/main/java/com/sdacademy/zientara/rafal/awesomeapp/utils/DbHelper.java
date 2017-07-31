package com.sdacademy.zientara.rafal.awesomeapp.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Evil on 31.07.2017.
 */

public class DbHelper {

    public static double getCostsOfCategory(String categoryName) {
        return DbHelper.getDoubleFromRawQuery(
                "SELECT sum(price * count) AS count FROM 'products' AS 'p'" +
                        "JOIN 'categories' AS 'c' ON c.id == p.category " +
                        "WHERE c.cname LIKE \"" + categoryName + "\"");
    }

    public static double getDoubleFromRawQuery(String query) {
        SQLiteDatabase database = ActiveAndroid.getDatabase();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getDouble(0);
    }


}
