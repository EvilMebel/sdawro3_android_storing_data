package com.sdacademy.zientara.rafal.awesomeapp;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.sdacademy.zientara.rafal.awesomeapp.models.Category;
import com.sdacademy.zientara.rafal.awesomeapp.models.Product;

/**
 * Created by Evil on 27.07.2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(createActiveAndroidConfiguration());
    }

    private Configuration createActiveAndroidConfiguration() {
        Configuration.Builder configuration = new Configuration.Builder(this);
        configuration.addModelClass(Category.class);
        configuration.addModelClass(Product.class);
        return configuration.create();
    }
}
