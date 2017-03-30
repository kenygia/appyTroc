package fr.lille.iut.appytroc;

import android.os.AsyncTask;
import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

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
    private  String basicAuth;

    static             String jsonString = new String();

    JSONObject jsonObject = new JSONObject();
    public static String codereponse;

    public AsyncTGet(User user) throws JSONException {

        jsonObject.put("name", user.getName());
        jsonObject.put("password", user.getPwd());
        basicAuth = "Basic " + Base64.encodeToString((""+user.getName()+":"+user.getPwd()+"").getBytes(), Base64.NO_WRAP);
    }

    public AsyncTGet(Offer offer) throws JSONException {

        jsonObject.put("id", offer.getId());
        jsonObject.put("id_user", offer.getId_user());
        jsonObject.put("titre", offer.getTitre());
        jsonObject.put("detail", offer.getDetail());
        jsonObject.put("active", offer.isActive());


    }

    @Override
    protected Void doInBackground(String... url) {



        try {
            URL lien = new URL(url[0]);

            HttpURLConnection httpURLConnection = (HttpURLConnection) lien.openConnection();

            httpURLConnection.setDoOutput(true);

            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setRequestProperty ("Authorization", basicAuth);

            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            httpURLConnection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(lien.openStream()));

            char[] buffer = new char[1024];


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

