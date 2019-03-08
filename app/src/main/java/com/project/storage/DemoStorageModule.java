package com.project.storage;

import android.content.Context;
import android.os.Build;

import java.io.File;

import io.storage.StorageConfiguration;
import io.storage.StorageModule;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/84:14 PM
 * desc   :
 * version: 1.0
 */
public class DemoStorageModule implements StorageModule {

    @Override
    public StorageConfiguration applyConfiguration(Context context) {
        File databaseFolder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            databaseFolder = context.getExternalFilesDirs(null)[0];
        } else {
            databaseFolder = context.getExternalFilesDir(null);
        }
        if (databaseFolder != null && databaseFolder.exists()) {
            String path = databaseFolder.getAbsolutePath();
            if (!path.endsWith(File.separator)) {
                path = path + File.separator;
            }
            path = path + "database/";
            databaseFolder = new File(path);
        }
        return new StorageConfiguration.Builder(context)
                .databaseFolder(databaseFolder)
                .logEnabled(true)
                .databaseName("PantherDemo")
                .build();
    }
}
