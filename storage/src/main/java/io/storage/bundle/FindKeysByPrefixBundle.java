
package io.storage.bundle;

import io.storage.callback.FindKeysCallback;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/83:41 PM
 * desc   :
 * version: 1.0
 */

public class FindKeysByPrefixBundle extends BaseBundle {
    public String prefix;
    public String[] keys;
    public FindKeysCallback callback;

    public FindKeysByPrefixBundle(String prefix, FindKeysCallback callback) {
        this.prefix = prefix;
        this.callback = callback;
    }
}