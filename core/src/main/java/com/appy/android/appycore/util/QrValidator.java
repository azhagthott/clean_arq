package com.appy.android.appycore.util;

import android.util.Log;

public class QrValidator {

    private static final String TAG = QrValidator.class.getCanonicalName();

    public static boolean validate(String key, String qrResult) {

        try {
            String str = qrResult.substring(qrResult.indexOf(";") + 1);
            String checksum = qrResult.substring(qrResult.indexOf("=") + 1, qrResult.indexOf(";"));
            String qrLower = str.toLowerCase().replaceAll("\\+", " ");
            String qrClean = qrLower.replaceAll("[^a-z0-9._;-]", "");
            String md5String = Hash.md5(key + qrClean);
            return checksum.equals(md5String.substring(0, 16));
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e);
            return false;
        }
    }
}
