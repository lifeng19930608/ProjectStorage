
package io.storage;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;

import io.storage.constant.Constant;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/83:41 PM
 * desc   :
 * version: 1.0
 */

public class StorageConfiguration {
    Context context;
    String databaseName;
    File databaseFolder;
    int memoryCacheSize;
    boolean logEnabled;

    private StorageConfiguration(Builder builder) {
        context = builder.context;
        databaseName = builder.databaseName;
        databaseFolder = builder.databaseFolder;
        memoryCacheSize = builder.memoryCacheSize;
        logEnabled = builder.logEnabled;

        // application context
        if (context == null) {
            throw new NullPointerException("Context can not be null!");
        }
        // default database name
        if (TextUtils.isEmpty(databaseName)) {
            databaseName = context.getPackageName();
        }
        // database folder
        boolean databaseFolderValid = false;
        if (databaseFolder != null) {
            boolean databaseFolderExist = false;
            try {
                databaseFolderExist = databaseFolder.exists();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!databaseFolderExist) {
                try {
                    databaseFolderValid = databaseFolder.mkdirs();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                databaseFolderValid = true;
            }
        }
        if (!databaseFolderValid) {
            databaseFolder = context.getFilesDir();
        }
        if (databaseFolder == null) {
            throw new NullPointerException("Database folder can not be null!");
        }
        // memory cache max size
        if (memoryCacheSize <= 0) {
            memoryCacheSize = Constant.DEFAULT_MEMORY_CACHE_SIZE;
        }
    }

    public static final class Builder {
        private Context context;
        private String databaseName;
        private File databaseFolder;
        private int memoryCacheSize;
        private boolean logEnabled;

        public Builder(Context context) {
            this.context = context.getApplicationContext();
        }

        /**
         * Database name
         *
         * @param val name
         * @return
         */
        public Builder databaseName(String val) {
            databaseName = val;
            return this;
        }

        /**
         * Database will in this folder
         *
         * @param val folder
         * @return
         */
        public Builder databaseFolder(File val) {
            databaseFolder = val;
            return this;
        }

        /**
         * Memory cache max size
         *
         * @param val size
         * @return
         */
        public Builder memoryCacheSize(int val) {
            memoryCacheSize = val;
            return this;
        }

        /**
         * Log
         *
         * @param val enabled
         * @return
         */
        public Builder logEnabled(boolean val) {
            logEnabled = val;
            return this;
        }

        public StorageConfiguration build() {
            return new StorageConfiguration(this);
        }
    }
}