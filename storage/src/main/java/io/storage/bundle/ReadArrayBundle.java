
package io.storage.bundle;

import io.storage.callback.ReadArrayCallback;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/83:41 PM
 * desc   :
 * version: 1.0
 */

public final class ReadArrayBundle<T> extends BaseBundle {
    public String key;
    public T[] data;
    public Class<T> dataClass;
    public ReadArrayCallback callback;

    public ReadArrayBundle(String key, Class<T> dataClass, ReadArrayCallback callback) {
        this.key = key;
        this.dataClass = dataClass;
        this.callback = callback;
    }
}