
package io.storage.bundle;

import io.storage.callback.WriteCallback;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/83:41 PM
 * desc   :
 * version: 1.0
 */

public final class WriteBundle extends BaseBundle {
    public String key;
    public boolean success;
    public Object data;
    public WriteCallback callback;

    public WriteBundle(String key, Object data, WriteCallback callback) {
        this.key = key;
        this.data = data;
        this.callback = callback;
    }
}