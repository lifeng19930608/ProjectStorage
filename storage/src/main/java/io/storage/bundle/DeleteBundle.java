
package io.storage.bundle;

import io.storage.callback.DeleteCallback;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/83:41 PM
 * desc   :
 * version: 1.0
 */

public final class DeleteBundle extends BaseBundle {
    public String key;
    public boolean success;
    public DeleteCallback callback;

    public DeleteBundle(String key, DeleteCallback callback) {
        this.key = key;
        this.callback = callback;
    }
}