package com.appy.android.appycore.util;

import com.google.android.material.textfield.TextInputLayout;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class GeneralUtils {

    public static String MD5(String s) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(s.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void clearHint(TextInputLayout view, int s, CharSequence hint) {
        if (s >= 0) {
            view.setHint("");
        } else {
            view.setHint(hint);
        }
    }

    public static int calculateAge(int year, int month, int day) {
        int age;

        final Calendar calenderToday = Calendar.getInstance();
        int currentYear = calenderToday.get(Calendar.YEAR);
        int currentMonth = 1 + calenderToday.get(Calendar.MONTH);
        int todayDay = calenderToday.get(Calendar.DAY_OF_MONTH);

        age = currentYear - year;

        if (month > currentMonth) {
            --age;
        } else if (month == currentMonth) {
            if (day > todayDay) {
                --age;
            }
        }
        return age;
    }

    public static String SHA256(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(s.getBytes("UTF-8"));
        byte[] byteData = md.digest();
        String hash = "";
        for (int i = 0; i < byteData.length; i++) {
            hash += Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1);
        }
        return hash;
    }
}
