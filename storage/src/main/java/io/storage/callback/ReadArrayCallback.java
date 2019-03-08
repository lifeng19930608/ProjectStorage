
package io.storage.callback;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/83:41 PM
 * desc   :
 * version: 1.0
 */

public interface ReadArrayCallback<T> {
    void onResult(boolean success, T[] result);
}