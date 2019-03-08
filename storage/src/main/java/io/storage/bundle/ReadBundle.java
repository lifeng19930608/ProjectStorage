
package io.storage.bundle;

import io.storage.callback.ReadCallback;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/83:41 PM
 * desc   :
 * version: 1.0
 */

public final class ReadBundle<T> extends BaseBundle {
    public String key;
    public T data;
    public Class<T> dataClass;
    public ReadCallback callback;

    public ReadBundle(String key, Class<T> dataClass, ReadCallback callback) {
        this.key = key;
        this.dataClass = dataClass;
        this.callback = callback;
    }
}