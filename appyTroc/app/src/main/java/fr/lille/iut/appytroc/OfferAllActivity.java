package fr.lille.iut.appytroc;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class OfferAllActivity extends AppCompatActivity {

    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_all);
        mListView = (ListView) findViewById(R.id.mListView);
        List<Offer> offers = new ArrayList<>();

        offers.add(new Offer( 1,1,"The company","we sold it to and that way it comes under the monthly current budget and not the capital account"));
        offers.add(new Offer( 2,2,"A roman mum","I'm a kike, a yid, a heebie, a hook-nose, I'm kosher mum, I'm a Red Sea pedestrian, and proud of it!"));
        try {
            offers = generateOffers(getAllOffer());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdapterOffer adapter = new AdapterOffer(OfferAllActivity.this, offers);
        mListView.setAdapter(adapter);

    }

    private List<Offer> generateOffers(JSONObject jso) {
        List<Offer> list_offer = new ArrayList<>();
        try {
            JSONArray array = jso.getJSONArray("offer");

            for( int i = 0 ; i < array.length() ; i++ ) {
                JSONObject c = array.getJSONObject(i);
                int id = c.getInt("id");
                int id_user = c.getInt("id_user");
                String titre = c.getString("titre");
                String detail = c.getString("detail");
                boolean active = c.getBoolean("active");
                
                list_offer.add(new Offer(id,id_user,titre,detail));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list_offer;
    }

    private JSONObject getAllOffer() throws ExecutionException, InterruptedException, JSONException {




            OfferAsynct async = new OfferAsynct();
            async.setUrl("http://osmar.io:8080/v1/offer/all");

            async.setMethode("GET");
            async.execute();
            String reponse = async.get();
        JSONObject jso = new JSONObject(reponse);

        return jso;
    }

    private class OfferAsynct extends AsyncTask<Void, Void, String> {

        private String url = "http://";
        private String methode = "UNKNOW";
        private String baseAuthStr;
        private String encoded;
        private JSONObject jsonObject = new JSONObject();

        public String codereponse="";

        public String getUrl() {
            return url;
        }

        public String getMethode() {
            return methode;
        }

        public void setMethode(String methode) {
            this.methode = methode;
        }

        public void setUrl(String url) {
            this.url = url;
        }



        public OfferAsynct(User user) throws JSONException {

            jsonObject.put("name", user.getName());
            jsonObject.put("password", user.getPwd());
            baseAuthStr = user.getName() + ":" + user.getPwd();

            encoded = Base64.encodeToString(baseAuthStr.getBytes(), Base64.NO_WRAP);
        }

        public OfferAsynct() {
            baseAuthStr = "android:android";
            encoded = Base64.encodeToString(baseAuthStr.getBytes(), Base64.NO_WRAP);

        }

        @Override
        protected void onPostExecute(String result){

        }

        @Override
        protected String doInBackground(Void... params) {
            String result="";
            try {
                URL url = new URL(this.getUrl());

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setDoOutput(true);

                httpURLConnection.setRequestMethod(this.getMethode());

                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.addRequestProperty("Authorization", encoded);


                httpURLConnection.connect();


                InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result += line;

                }





            } catch (MalformedURLException e) {
                e.printStackTrace();
                result = "MalformedURLException";

            } catch (IOException e) {
                e.printStackTrace();
                result = "IOException";
            }
            Log.i("Res_result", result);

            return result;
        }


    }
}
