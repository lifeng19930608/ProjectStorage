
package io.storage.memorycache;

import android.text.TextUtils;

import java.util.Set;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/83:41 PM
 * desc   :
 * version: 1.0
 */

public class StorageMemoryCacheMap {
    private int maxSize;
    private static volatile LruCache<String, Object> cacheMap;

    public StorageMemoryCacheMap(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = maxSize;
        cacheMap = new LruCache<>(maxSize);
    }

    public synchronized void put(String key, Object value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        if (value == null) {
            cacheMap.remove(key);
            return;
        }
        cacheMap.put(key, value);
    }

    public synchronized void delete(String key) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        cacheMap.remove(key);
    }

    public synchronized Object get(String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        return cacheMap.get(key);
    }

    public synchronized void clear() {
        cacheMap.evictAll();
    }

    public synchronized int size() {
        return cacheMap.size();
    }

    public synchronized String[] keySet() {
        Set<String> keySet = cacheMap.keySet();
        return keySet.toArray(new String[keySet.size()]);
    }

    @Override
    public String toString() {
        return "=== Storage Memory Cache Map ===\nmax size: " + maxSize + "\n" + cacheMap.toString();
    }
}