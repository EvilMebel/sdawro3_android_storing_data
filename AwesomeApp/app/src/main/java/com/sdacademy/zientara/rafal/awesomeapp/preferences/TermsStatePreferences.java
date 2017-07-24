package com.sdacademy.zientara.rafal.awesomeapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Evil on 24.07.2017.
 */

public class TermsStatePreferences {
    private static final String TERMS_ARE_ACCEPTED = "terms_are_accepted";

    public static void setTermsAccepted(Context context, boolean state) {
        PreferenceManager
                .getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(TERMS_ARE_ACCEPTED, state)
                .apply();
    }

    public static boolean areTermsAccepted(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(TERMS_ARE_ACCEPTED, false);
    }
}
