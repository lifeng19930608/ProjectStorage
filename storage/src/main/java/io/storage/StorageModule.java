

package io.storage;

import android.content.Context;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/83:41 PM
 * desc   :
 * version: 1.0
 */

public interface StorageModule {
    /**
     * Get a configuration for Storage init, do not be null
     *
     * @return configuration
     */
    StorageConfiguration applyConfiguration(Context context);
}