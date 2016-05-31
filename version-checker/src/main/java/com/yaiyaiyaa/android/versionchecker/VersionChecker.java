package com.yaiyaiyaa.android.versionchecker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

/**
 * Created by mahendran on 31/5/16.
 */
public class VersionChecker {
    public static void check(Context context, int minSdkVersion, int minimumUserCount,
                             VersionCheckListener versionCheckListener) {
        try {
            initVersionCheckRequest(context, minSdkVersion, minimumUserCount, versionCheckListener);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            versionCheckListener.onResponse(false, null);
        }
    }

    public static void initVersionCheckRequest(Context context,
                                               int minSdkVersion,
                                               int minimumUserCount,
                                               final VersionCheckListener versionCheckListener)
            throws PackageManager.NameNotFoundException {
        LatestVersionDetails versionDetails = new LatestVersionDetails(
                context.getApplicationContext().getPackageName(),
                getApplicationName(context),
                getVersionInfo(context).versionName,
                getVersionInfo(context).versionCode,
                minSdkVersion
        );
        versionDetails.setMinUserCount(minimumUserCount);
        VersionCheckerRequest versionCheckerRequest = new VersionCheckerRequest(
                getApiUrl()+"access-token="+getApiKey(context),
                versionDetails.getAsParams(),
                new Response.Listener<LatestVersionDetails>() {
                    @Override
                    public void onResponse(LatestVersionDetails response) {
                        versionCheckListener.onResponse(response.isUpdateAvailable(), response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        versionCheckListener.onResponse(false, null);
                    }
                });
        Volley.newRequestQueue(context).add(versionCheckerRequest);
    }

    private static String getApiUrl() {
        return "http://api.yaiyaiyaa.com/v1/android-app/check?_format=json&";
    }

    private static PackageInfo getVersionInfo(Context context)
            throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
    }

    private static String getApplicationName(Context context) {
        int stringId = context.getApplicationInfo().labelRes;
        return context.getString(stringId);
    }

    private static String getApiKey(Context context)
            throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(
                context.getPackageName(), PackageManager.GET_META_DATA);
        Bundle bundle = applicationInfo.metaData;
        return bundle.getString("com.yaiyaiyaa.ApiKey");
    }
}
