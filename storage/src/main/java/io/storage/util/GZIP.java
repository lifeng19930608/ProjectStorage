package io.storage.util;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * author : lifeng
 * e-mail : android_lifeng@sina.com
 * date   : 2019/3/83:41 PM
 * desc   :
 * version: 1.0
 */

public class GZIP {
    public static String compress(String data) {
        if (TextUtils.isEmpty(data)) {
            return data;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(data.getBytes());
            gzipOutputStream.close();
            String compressData = byteArrayOutputStream.toString("ISO-8859-1");
            byteArrayOutputStream.close();
            return compressData;
        } catch (Exception ignore) {
            return data;
        }
    }

    public static String decompress(String data) {
        if (TextUtils.isEmpty(data)) {
            return data;
        }
        try {
            String decompressedStr = "";
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data.getBytes("ISO-8859-1"));
            GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] b = new byte[256];
            int length = -1;
            while (-1 != (length = gzipInputStream.read(b))) {
                byteArrayOutputStream.write(b, 0, length);
            }
            decompressedStr = byteArrayOutputStream.toString("UTF-8");
            byteArrayOutputStream.close();
            byteArrayInputStream.close();
            gzipInputStream.close();
            return decompressedStr;
        } catch (Exception e) {
            return data;
        }
    }
}