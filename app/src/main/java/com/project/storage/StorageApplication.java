package com.project.storage;

import android.app.Application;

import io.storage.Storage;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/84:09 PM
 * desc   :
 * version: 1.0
 */
public class StorageApplication extends Application {
    private static StorageApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static StorageApplication getInstance() {
        return application;
    }

    public void closeDatabase() {
        Storage.get(this).closeDatabase();
    }
}
