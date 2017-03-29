package fr.lille.iut.appytroc;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tibo on 29/03/17.
 */

public class AsyncT extends AsyncTask<Void, Void, Void> {

    JSONObject jsonObject = new JSONObject();
    public static int codereponse;

    public AsyncT(User user) throws JSONException{

        jsonObject.put("login", user.getLogin());
        jsonObject.put("password", user.getPwd());
    }

    public AsyncT(Offer offer) throws JSONException {

        jsonObject.put("id", offer.getId());
        jsonObject.put("id_user", offer.getId_user());
        jsonObject.put("titre", offer.getTitre());
        jsonObject.put("detail", offer.getDetail());
        jsonObject.put("active", offer.isActive());

    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            URL url = new URL("http://172.19.162.94/v1/user");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setDoOutput(true);

            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            httpURLConnection.connect();



            
            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(jsonObject.toString());
            wr.flush();
            wr.close();


           codereponse = httpURLConnection.getResponseCode();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
