package io.storage.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import io.storage.StorageConfiguration;
import io.storage.StorageModule;
import io.storage.constant.Constant;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/83:41 PM
 * desc   :
 * version: 1.0
 */

public class ConfigurationParser {
    private final Context context;

    public ConfigurationParser(Context context) {
        this.context = context;
    }

    public StorageConfiguration parse() {
        StorageConfiguration configuration = null;
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null && appInfo.metaData != null) {
                String className = appInfo.metaData.getString(Constant.PANTHER_MODULE_NAME, "");
                configuration = parseModule(className).applyConfiguration(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (configuration == null) {
            configuration = new StorageConfiguration.Builder(context).build();
        }
        return configuration;
    }

    private static StorageModule parseModule(String className) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to find StorageModule implementation", e);
        }
        Object module;
        try {
            module = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to instantiate StorageModule implementation for " + clazz, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to instantiate StorageModule implementation for " + clazz, e);
        }
        if (!(module instanceof StorageModule)) {
            throw new RuntimeException("Expected instanceof StorageModule, but found: " + module);
        }
        return (StorageModule) module;
    }
}