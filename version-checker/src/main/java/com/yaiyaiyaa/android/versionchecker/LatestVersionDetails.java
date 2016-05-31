package com.yaiyaiyaa.android.versionchecker;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by mahendran on 31/5/16.
 */
public class LatestVersionDetails {
    @SerializedName("update_available")
    private boolean updateAvailable;

    @SerializedName("package_name")
    private String packageName;

    @SerializedName("application_name")
    private String applicationName;

    @SerializedName("version_name")
    private String latestVersionName;

    @SerializedName("version_code")
    private int latestVersionCode;

    @SerializedName("min_sdk")
    private int minSdk;

    @SerializedName("min_user_count")
    private int minUserCount;

    public LatestVersionDetails() {

    }

    public LatestVersionDetails(String packageName, String applicationName,
                                String latestVersionName, int latestVersionCode, int minSdk) {
        this.packageName = packageName;
        this.applicationName = applicationName;
        this.latestVersionCode = latestVersionCode;
        this.latestVersionName = latestVersionName;
        this.minSdk = minSdk;
    }

    public boolean isUpdateAvailable() {
        return updateAvailable;
    }

    public void setUpdateAvailable(boolean updateAvailable) {
        this.updateAvailable = updateAvailable;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getLatestVersionName() {
        return latestVersionName;
    }

    public void setLatestVersionName(String latestVersionName) {
        this.latestVersionName = latestVersionName;
    }

    public int getLatestVersionCode() {
        return latestVersionCode;
    }

    public void setLatestVersionCode(int latestVersionCode) {
        this.latestVersionCode = latestVersionCode;
    }

    public int getMinSdk() {
        return minSdk;
    }

    public void setMinSdk(int minSdk) {
        this.minSdk = minSdk;
    }

    public int getMinUserCount() {
        return minUserCount;
    }

    public void setMinUserCount(int minUserCount) {
        this.minUserCount = minUserCount;
    }

    public HashMap<String, String> getAsParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("package_name", packageName);
        params.put("application_name", applicationName);
        params.put("version_code", String.valueOf(latestVersionCode));
        params.put("version_name", latestVersionName);
        params.put("min_sdk", String.valueOf(minSdk));
        params.put("min_user_count", String.valueOf(minUserCount));
        return params;
    }
}
