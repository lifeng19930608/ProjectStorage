
package io.storage.constant;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/83:41 PM
 * desc   :
 * version: 1.0
 */

public class Constant {
    public static final int DEFAULT_MEMORY_CACHE_SIZE = 128;

    public static final int MSG_MAIN_SAVE_CALLBACK = 1;
    public static final int MSG_MAIN_READ_CALLBACK = 2;
    public static final int MSG_MAIN_DELETE_CALLBACK = 3;
    public static final int MSG_MAIN_MASS_DELETE_CALLBACK = 4;
    public static final int MSG_MAIN_FIND_KEYS_BY_PREFIX_CALLBACK = 5;

    public static final int MSG_WORK_SAVE = -1;
    public static final int MSG_WORK_READ = -2;
    public static final int MSG_WORK_DELETE = -4;
    public static final int MSG_WORK_MASS_DELETE = -5;
    public static final int MSG_WORK_FIND_KEYS_BY_PREFIX = -6;

    public static final int MSG_SUBTYPE_NORMAL = 0;
    public static final int MSG_SUBTYPE_READ_ARRAY = 1;

    public static final String PANTHER_MODULE_NAME = "io.storage.StorageModule";

    public static final String SAVE_KEY_PREFIX = "write:";
    public static final String READ_KEY_PREFIX = "read:";
    public static final String DELETE_KEY_PREFIX = "delete:";
    public static final String MASS_DELETE_KEY_PREFIX = "mass_delete:";
    public static final String FIND_KEYS_BY_PREFIX_KEY_PREFIX = "find_keys_by_prefix:";
}