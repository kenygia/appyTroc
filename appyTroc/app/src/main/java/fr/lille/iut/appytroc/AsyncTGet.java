package fr.lille.iut.appytroc;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tibo on 30/03/17.
 */

public class AsyncTGet extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... url) {

        try {
            URL lien = new URL(url[0]);

            HttpURLConnection httpURLConnection = (HttpURLConnection) lien.openConnection();

            httpURLConnection.setDoOutput(true);

            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            httpURLConnection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(lien.openStream()));

            char[] buffer = new char[1024];

            String jsonString = new String();

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null ) {
                sb.append(line + "\n");
            }
            br.close();

            jsonString = sb.toString();

            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

