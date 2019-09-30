package com.appy.android.appycore.util;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFileTask extends AsyncTask<String, Integer, Boolean> {

    private final static String TAG = DownloadFileTask.class.getCanonicalName();

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            int count;
            long total = 0;
            byte[] data = new byte[1024];

            URL url = new URL(strings[0]);
            String localStorage = strings[1];
            String documentName = strings[2];

            URLConnection connection = url.openConnection();
            connection.connect();

            int length = connection.getContentLength();

            File directory = new File(Environment.getExternalStorageDirectory() + File.separator + localStorage);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + localStorage + "/" + documentName);

            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress((int) ((total * 100) / length));
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e(TAG, "doInBackground: " + e.getMessage());
            return false;
        }
        return true;
    }
}
