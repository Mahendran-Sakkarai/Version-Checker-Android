package com.yaiyaiyaa.android.versionchecker;

import com.android.volley.NetworkResponse;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.GZIPInputStream;

/**
 * Created by mahendran on 31/5/16.
 */
public class Utils {
    public static Reader getJsonReader(NetworkResponse response) {
        Reader jsonReader = null;

        try {
            boolean isGZipped = isResponseGZipped(response);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(response.data);
            if (!isGZipped) {
                jsonReader = new InputStreamReader(inputStream);
            } else {
                jsonReader = new InputStreamReader(new GZIPInputStream(inputStream));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonReader;
    }

    public static Reader getJsonReader(byte[] response, boolean isGZipped) {
        Reader jsonReader = null;

        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(response);
            if (!isGZipped) {
                jsonReader = new InputStreamReader(inputStream);
            } else {
                jsonReader = new InputStreamReader(new GZIPInputStream(inputStream));
            }
        } catch (Exception e) {
        }
        return jsonReader;
    }

    public static boolean isResponseGZipped(NetworkResponse response) {
        boolean isGZipped = false;
        String contentResponse = response.headers.get("Content-Encoding");
        if (!isNullOrEmpty(contentResponse) && contentResponse.contains("gzip")) {
            isGZipped = true;
        }
        return isGZipped;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0 || str.equalsIgnoreCase("null");
    }
}
