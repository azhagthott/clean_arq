package com.appy.android.appycore.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static String getHash(String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] array = messageDigest.digest(text.getBytes());
            StringBuilder stringBuffer = new StringBuilder();
            for (byte b : array) {
                stringBuffer.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String md5(String text) {
        return Hash.getHash(text);
    }
}
