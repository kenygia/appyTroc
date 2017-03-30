package fr.lille.iut.appytroc;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tibo on 30/03/17.
 */

public class AsyncTGet extends AsyncTask<Void, Void, Void> {

    public static String codereponse;

    public AsyncTGet(User user) throws JSONException {



    }

    public AsyncTGet(Offer offer) throws JSONException {


    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            URL url = new URL("http://172.19.162.94:8080/v1/user");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setDoOutput(true);

            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            httpURLConnection.connect();

            BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));

            char[] buffer = new char[1024];

            String jsonString = new String();

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();

            jsonString = sb.toString();

            codereponse =""+ httpURLConnection.getResponseCode();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            codereponse= e.toString();

        } catch (IOException e) {
            e.printStackTrace();
            codereponse= e.toString();
        }
        return null;
    }
}

