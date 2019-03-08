
package io.storage.bundle;

import io.storage.callback.MassDeleteCallback;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/83:41 PM
 * desc   :
 * version: 1.0
 */

public final class MassDeleteBundle extends BaseBundle{
    public String[] keys;
    public boolean success;
    public MassDeleteCallback callback;

    public MassDeleteBundle(String[] keys, MassDeleteCallback callback) {
        if (keys == null) {
            this.keys = new String[]{};
        } else {
            this.keys = keys;
        }
        this.callback = callback;
    }
}