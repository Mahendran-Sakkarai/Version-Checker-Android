package com.yaiyaiyaa.android.versionchecker;

/**
 * Created by mahendran on 31/5/16.
 */
public interface VersionCheckListener {
    void onResponse (boolean updateAvailable, LatestVersionDetails details);
}
